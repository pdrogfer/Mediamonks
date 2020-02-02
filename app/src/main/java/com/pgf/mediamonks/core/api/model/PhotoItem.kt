package com.pgf.mediamonks.core.api.model

import android.os.Parcel
import android.os.Parcelable

data class PhotoItem(
	val albumId: Int? = null,
	val id: Int? = null,
	val title: String? = null,
	val url: String? = null,
	val thumbnailUrl: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString())

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(albumId)
		parcel.writeValue(id)
		parcel.writeString(title)
		parcel.writeString(url)
		parcel.writeString(thumbnailUrl)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<PhotoItem> {
		override fun createFromParcel(parcel: Parcel): PhotoItem {
			return PhotoItem(parcel)
		}

		override fun newArray(size: Int): Array<PhotoItem?> {
			return arrayOfNulls(size)
		}
	}
}
