package org.test.koltintest

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.test.asDate
import org.test.inflate
import org.test.koltintest.model.User

class UsersAdapter(private val selectionListener: (User) -> Unit) : RecyclerView.Adapter<UserViewHolder>() {

    private val users = arrayOf(
            User(1, "Guillermo Mazzola", "Software Developer", "19/12/1985".asDate()),
            User(2, "Pablo Araya", "Android Developer"),
            User(3, "Armando Picón", "Android Developer"))

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = parent
            .inflate(R.layout.fragment_users_item)
            .let(::UserViewHolder)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.bindUser(user)
        holder.itemView.setOnClickListener { selectionListener.invoke(user) }
    }

    override fun onViewRecycled(holder: UserViewHolder) {
        super.onViewRecycled(holder)

        holder.itemView.setOnClickListener(null)
    }

}
