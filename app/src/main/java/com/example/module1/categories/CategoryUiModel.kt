package com.example.module1.categories

import android.os.Parcel
import android.os.Parcelable

data class CategoryUiModel(val img: String, val text: String, val value: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(img)
        parcel.writeString(text)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryUiModel> {
        override fun createFromParcel(parcel: Parcel): CategoryUiModel {
            return CategoryUiModel(parcel)
        }

        override fun newArray(size: Int): Array<CategoryUiModel?> {
            return arrayOfNulls(size)
        }
    }
}
