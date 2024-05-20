package com.fp.fproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fp.fproject.R
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import com.fp.fproject.databinding.ActivitySinUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import android.util.Patterns
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider

class Sin_upActivity : AppCompatActivity() {
    private lateinit var fullname_sin: EditText
    private lateinit var email_sin: EditText
    private lateinit var pass_sin: EditText
    private lateinit var btn_face: Button
    private lateinit var btn_gog: Button
    private lateinit var sin_up: Button
    private lateinit var login: Button

    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123
    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_sin_up)

        fullname_sin = findViewById(R.id.fullname_sin)
        email_sin = findViewById(R.id.email_sin)
        pass_sin = findViewById(R.id.pass_sin)
        sin_up = findViewById(R.id.sin_up)
        btn_face = findViewById(R.id.lgbtn_face)
        btn_gog = findViewById(R.id.sinbtn_go)
        login = findViewById(R.id.login)


        auth = Firebase.auth

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

        login.setOnClickListener{
            val intent = Intent(this, Log_inActivity::class.java)
            startActivity(intent)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        sin_up.setOnClickListener {

                signUpUser()
            }
//            auth.createUserWithEmailAndPassword(sEmail, sPass)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("TAG", "createUserWithEmail:success")
//                        val user = auth.currentUser
//                        updateUI(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            baseContext,
//                            "Authentication failed.",
//                            Toast.LENGTH_SHORT,
//                        ).show()
//                        updateUI(null)
//                    }
//                }



//        sin_up?.setOnClickListener(View.OnClickListener {
//            val fullname = fullname_sin?.getText().toString()
//            val e_mail = email_sin?.getText().toString()
//            val password = pass_sin?.getText().toString()
//            if (fullname == "" || e_mail == "" || password == "") {
//                Toast.makeText(
//                    this@Sin_upActivity,
//                    "Please enter all the fields",
//                    Toast.LENGTH_SHORT
//                ).show()
//            } else {
//                mAuth?.createUserWithEmailAndPassword(e_mail, password)
//                    ?.addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            // User creation successful
//                            val authResult = task.result // Get the AuthResult if needed
//                            // Your logic for successful user creation
//                            Toast.makeText(this@Sin_upActivity,"Sign up Successfully"+ mAuth?.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()
//
//
//                        } else {
//                            // User creation failed
//                            val exception = task.exception // Get the exception if needed
//                            // Your logic for handling failed user creation
//                            Toast.makeText(this@Sin_upActivity,"Sign up Failed"+ mAuth?.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                val checkfullname = DB!!.checkfullname(fullname)
//                if (checkfullname == false) {
//                    val insert = DB!!.insertData(fullname, e_mail, password)
//                    if (insert == true) {
//                        Toast.makeText(
//                            this@Sin_upActivity,
//                            "Registered Successfully",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        startActivity(Intent(this@Sin_upActivity, HomeScreenActivity::class.java))
//                        finish()
//                    } else {
//                        Toast.makeText(this@Sin_upActivity, "Registered Failed", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                } else {
//                    Toast.makeText(
//                        this@Sin_upActivity,
//                        "User already exist! please login",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        })


    }
    private fun gog_signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signUpUser() {
        val email = email_sin.text.toString().trim()
        val password = pass_sin.text.toString().trim()

        if (email.isEmpty()) {
            email_sin.error = "Email is required"
            email_sin.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_sin.error = "Enter a valid email address"
            email_sin.requestFocus()
            return
        }

        if (password.isEmpty() || password.length < 6) {
            pass_sin.error = "Password must be at least 6 characters long"
            pass_sin.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign up success
                    Toast.makeText(
                        baseContext, "Sign up successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, NextActivity::class.java)
                    startActivity(intent)
                    // You can navigate to another activity or perform any other action here
                } else {
                    // If sign up fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Sign up failed. ${task.exception?.message}",
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


//    private fun updateUI(user: FirebaseUser?) {
//
//        val intent = Intent(this,Log_inActivity::class.java)
//        startActivity(intent)
//    }


}