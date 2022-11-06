package com.example.module1.news

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsUIModel(
    val id: Int,
    val label: String,
    val img: String,
    val description: String,
    val time: Long,
    val organization: String,
    val address: String,
    val numberList: List<String>,
    val email: String,
    val imgOptionally: List<String>,
    val site: String,
    val categories: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>
    )

    companion object : Parceler<NewsUIModel> {

        override fun NewsUIModel.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(label)
            parcel.writeString(img)
            parcel.writeString(description)
            parcel.writeLong(time)
            parcel.writeString(organization)
            parcel.writeString(address)
            parcel.writeStringList(numberList)
            parcel.writeString(email)
            parcel.writeStringList(imgOptionally)
            parcel.writeString(site)
            parcel.writeStringList(categories)
        }

        override fun create(parcel: Parcel): NewsUIModel {
            return NewsUIModel(parcel)
        }
    }
}