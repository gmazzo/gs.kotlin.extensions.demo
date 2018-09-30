package org.test.koltintest

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_users.*
import org.test.koltintest.model.User
import org.test.replaceWith

class UsersFragment : Fragment() {

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_users, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.adapter = UsersAdapter(this::onUserSelected)
    }

    private fun onUserSelected(user: User) {
        replaceWith(UsersEditFragment.create(user))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.fragment_users, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                replaceWith(UsersEditFragment.create())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun create() = UsersFragment()

    }

}
