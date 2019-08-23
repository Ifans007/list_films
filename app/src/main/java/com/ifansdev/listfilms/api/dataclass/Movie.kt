package com.ifansdev.listfilms.api.dataclass

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("title") val title: String,
        @SerializedName("poster_path") val poster: String,
        @SerializedName("overview") val overview: String
        ) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(title)
                parcel.writeString(poster)
                parcel.writeString(overview)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Movie> {
                override fun createFromParcel(parcel: Parcel): Movie {
                        return Movie(parcel)
                }

                override fun newArray(size: Int): Array<Movie?> {
                        return arrayOfNulls(size)
                }
        }
}