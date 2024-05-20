import android.os.Parcel
import android.os.Parcelable

data class FoodItem(
    val name: String?,
    val rating: String?,
    val reviews: String?,
    val deliveryInfo: String?,
    val deliveryTime: String?,
    val category1: String?,
    val category2: String?,
    val category3: String?,
    val imageResource: Int?,
    val favoriteIcon: Int?,
    val verificationIcon: Int?,
    val price: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as Int?,
        parcel.readValue(Int::class.java.classLoader) as Int?,
        parcel.readValue(Int::class.java.classLoader) as Int?,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(rating)
        parcel.writeString(reviews)
        parcel.writeString(deliveryInfo)
        parcel.writeString(deliveryTime)
        parcel.writeString(category1)
        parcel.writeString(category2)
        parcel.writeString(category3)
        parcel.writeValue(imageResource)
        parcel.writeValue(favoriteIcon)
        parcel.writeValue(verificationIcon)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<typ> {
        override fun createFromParcel(parcel: Parcel): typ {
            return typ(parcel)
        }

        override fun newArray(size: Int): Array<typ?> {
            return arrayOfNulls(size)
        }
    }
}
