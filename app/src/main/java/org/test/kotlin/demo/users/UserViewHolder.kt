package org.test.kotlin.demo.users

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_users_item.view.*
import org.test.kotlin.demo.model.User
import org.test.textOrHide

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    internal fun bindUser(user: User) {
        setText(itemView.name, itemView.nameLabel, user.name)
        setText(itemView.profession, itemView.professionLabel, user.profession)
        setText(itemView.age, itemView.ageLabel, user.age?.toString())
    }

    private fun setText(value: TextView, label: TextView, text: CharSequence?) {
        value.textOrHide = text
        label.visibility = value.visibility
    }

}
