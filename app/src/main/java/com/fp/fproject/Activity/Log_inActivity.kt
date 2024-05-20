package com.fp.fproject.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.fp.fproject.R
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.facebook.AccessToken
import com.facebook.FacebookSdk
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider

class Log_inActivity : AppCompatActivity() {
    private lateinit var email_login: EditText
    private lateinit var pass_login: EditText
    private lateinit var btn_face: Button
    private lateinit var btn_gog: Button
    private lateinit var login: Button
    private lateinit var back_sp: ImageButton

    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_log_in)

        email_login = findViewById(R.id.email_login)
        pass_login = findViewById(R.id.pass_login)
        login = findViewById(R.id.login)
        btn_gog = findViewById(R.id.sinbtn_go)
        back_sp = findViewById(R.id.back_sp)



        auth = FirebaseAuth.getInstance()
        FacebookSdk.sdkInitialize(applicationContext)

        callbackManager = CallbackManager.Factory.create()
        val btn_face = findViewById<LoginButton>(R.id.lgbtn_face)
        btn_face.setReadPermissions("email", "public_profile")
        btn_face.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                handleFacebookAccessToken(result.accessToken)
            }

            override fun onCancel() {
                // Handle cancellation
            }

            override fun onError(error: FacebookException) {
                // Handle error
            }

        })

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_gog.setOnClickListener {
            gog_signIn()
        }

        login.setOnClickListener {
            loginUser()
        }
        back_sp.setOnClickListener{
            startActivity(Intent(this@Log_inActivity,Sin_upActivity::class.java))
        }


//        sp = getSharedPreferences("Data", MODE_PRIVATE)
//        editor = sp.edit()
//        val logged = sp.getBoolean("ISLOGGEDIN", false)
//        if (logged == true) {
//            startActivity(Intent(this@Log_inActivity, HomeScreenActivity::class.java))
//            finish()
//        }
//        login!!.setOnClickListener {
//            val email = e_mail!!.text.toString()
//            val password = pass!!.text.toString()
//            if (email == "" || password == "") {
//                Toast.makeText(
//                    this@Log_inActivity,
//                    "Please enter all the fields",
//                    Toast.LENGTH_SHORT
//                ).show()
//            } else {
//                val checkemailpassword = DB!!.checkemailpassword(email, password)
//                if (checkemailpassword == true) {
//                    editor?.putString("email", email)
//                    editor?.putString("password", password)
//                    editor?.putBoolean("ISLOGGEDIN", true)
//                    editor?.apply()
//                    Toast.makeText(this@Log_inActivity, "Login Successfully", Toast.LENGTH_SHORT)
//                        .show()
//                    startActivity(Intent(this@Log_inActivity, HomeScreenActivity::class.java))
//                    finish()
//                } else {
//                    Toast.makeText(this@Log_inActivity, "Login Successfully", Toast.LENGTH_SHORT)
//                        .show()
//                    startActivity(Intent(this@Log_inActivity, HomeScreenActivity::class.java))
//                    finish()
//                }
//            }
//        }
//        val back = findViewById<ImageButton>(R.id.back_sp)
//        back.setOnClickListener { onBackPressed() }
//        val lg_btn = findViewById<Button>(R.id.lgbtn_face)
//        lg_btn.setOnClickListener {
//            startActivity(
//                Intent(
//                    this@Log_inActivity,
//                    HomeScreenActivity::class.java
//                )
//            )
//        }
//        sin_up?.setOnClickListener(View.OnClickListener {
//            startActivity(
//                Intent(
//                    this@Log_inActivity,
//                    Sin_upActivity::class.java
//                )
//            )
//        })

    }

    private fun gog_signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun loginUser() {
        val email = email_login.text.toString().trim()
        val password = pass_login.text.toString().trim()

        if (email.isEmpty()) {
            email_login.error = "Email is required"
            email_login.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_login.error = "Enter a valid email address"
            email_login.requestFocus()
            return
        }

        if (password.isEmpty() || password.length < 6) {
            pass_login.error = "Password must be at least 6 characters long"
            pass_login.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login success
                    Toast.makeText(
                        baseContext, "Login successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, NextActivity::class.java)
                    startActivity(intent)
                    // Implement what you want to do after successful login,
                    // such as navigating to another activity or updating UI.

                } else {
                    // If login fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Login failed. ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
    @Deprecated("Deprecated in Java")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


            callbackManager.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    val user = FirebaseAuth.getInstance().currentUser
                    // Continue with your app logic
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    val user = auth.currentUser
                    // You can navigate to another activity or perform any other action here
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun SignUp(view: View) {
        val intent = Intent(this, Sin_upActivity::class.java)
        startActivity(intent)
    }

}