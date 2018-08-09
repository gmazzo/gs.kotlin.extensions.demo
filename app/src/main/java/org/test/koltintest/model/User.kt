package org.test.koltintest.model

import android.os.Parcelable
import android.text.format.DateUtils
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(
        val id: Long,
        val name: String,
        val profession: String,
        val birthDate: Date? = null) : Parcelable {

    val age: Int? get() = birthDate?.run { Math.toIntExact((System.currentTimeMillis() - time) / DateUtils.YEAR_IN_MILLIS) }

}
