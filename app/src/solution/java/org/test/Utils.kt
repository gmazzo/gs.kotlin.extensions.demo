package  org.test

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.test.koltintest.R

fun ImageView.rounded() =
        drawable?.takeIf { it is BitmapDrawable }?.apply {
            val bitmap = (this as BitmapDrawable).bitmap

            setImageDrawable(RoundedBitmapDrawableFactory.create(resources, bitmap).apply {
                isCircular = true
            })
        }

val TextInputLayout.text: String?
    get() {
        val text = editText!!.text.toString()

        if (text.isBlank()) {
            error = context.getString(R.string.err_field_required)
            return null

        } else {
            isErrorEnabled = false
        }
        return text
    }

fun RecyclerView.decorate() =
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

val RecyclerView.ViewHolder.context
    get() = itemView.context!!

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false) =
        LayoutInflater.from(context)
                .inflate(layoutId, this, attachToRoot)!!

fun LayoutInflater.withStyle(@StyleRes styleId: Int) =
        cloneInContext(ContextThemeWrapper(context, styleId))

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

var TextView.textOrHide: CharSequence?
    get() = text
    set(value) {
        text = value
        visible = !value.isNullOrBlank()
    }

var TextInputLayout.showError
    get() = error
    set(value) {
        error = value
        isErrorEnabled = !value.isNullOrBlank()
    }

fun Fragment.replaceWith(fragment: Fragment, tag: String? = null) =
        fragmentManager!!.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .replace(id, fragment, tag)
                .addToBackStack(null)
                .commit()

fun CharSequence.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Throwable.toast(context: Context?) {
    printStackTrace()

    if (context != null) {
        localizedMessage.toast(context, Toast.LENGTH_LONG)
    }
}
