package org.test.koltintest

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_users_edit.*
import org.test.koltintest.model.User
import org.test.text
import org.test.validate

class UsersEditFragment : Fragment() {

    private val user by lazy { arguments?.getParcelable(ARG_USER) as User? }

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_users_edit, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            name.text = user?.name
            profession.text = user?.profession
            age.text = user?.age?.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.fragment_users_edit, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                if (validate()) {
                    // TODO perform save here

                    fragmentManager!!.popBackStack()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validate() = arrayOf(name, profession)
            .fold(true) { result, layout -> result and layout.validate(result) }

    companion object {
        private const val ARG_USER = "user"

        fun create(user: User? = null) = UsersEditFragment().apply {
            arguments = bundleOf(ARG_USER to user)
        }
    }

}
