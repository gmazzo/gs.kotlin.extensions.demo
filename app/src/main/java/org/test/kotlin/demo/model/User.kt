package org.test.kotlin.demo.model

import android.os.Parcelable
import android.text.format.DateUtils
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(
        val id: Long,
        val name: String,
        val profession: String,
        val birthDate: Date? = null) : Parcelable {

    @IgnoredOnParcel
    val age = birthDate?.let { Math.toIntExact((System.currentTimeMillis() - it.time) / DateUtils.YEAR_IN_MILLIS) }

}
