package org.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import org.test.koltintest.BuildConfig
import org.test.koltintest.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)

fun Date.toDateString() = dateFormat.format(this)

fun CharSequence.asDate(): Date {
    try {
        return dateFormat.parse(toString())

    } catch (e: ParseException) {
        throw IllegalArgumentException(e)
    }
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int) =
        LayoutInflater.from(context)
                .inflate(layoutId, this, false)!!

var TextView.textOrHide: CharSequence?
    get() = text
    set(value) {
        text = value
        isVisible = !value.isNullOrBlank()
    }

fun TextInputLayout.validate(setFocus: Boolean = false,
                             validation: TextInputLayout.() -> Boolean = { isValid }): Boolean {
    if (!validation.invoke(this)) {
        error = resources.getText(R.string.err_field_required)

        if (setFocus) {
            requestFocus()
        }
        return false

    } else {
        isErrorEnabled = false
    }
    return true
}

var TextInputLayout.text: CharSequence?
    get() = editText?.text
    set(value) {
        editText?.setText(value)
    }

val TextInputLayout.isValid: Boolean get() = !text.isNullOrBlank()

fun Fragment.replaceWith(fragment: Fragment, tag: String? = null) =
        fragmentManager!!.beginTransaction()
                .replace(id, fragment)
                .addToBackStack(tag)
                .commit()

fun CharSequence.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun CharSequence.snackbar(view: View, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, this, duration).show()
}

fun Throwable.report(context: Context) {
    printStackTrace()

    if (BuildConfig.DEBUG) {
        localizedMessage.toast(context)
    }
}
