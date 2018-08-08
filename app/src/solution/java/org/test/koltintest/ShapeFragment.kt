package org.test.koltintest

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ShapeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(LAYOUTS[arguments!!.getInt(ARG_INDEX)], container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            val nextIndex = (arguments!!.getInt(ARG_INDEX) + 1) % LAYOUTS.size

            fragmentManager!!.beginTransaction()
                    .replace(id, ShapeFragment.create(nextIndex))
                    .addToBackStack(null)
                    .commit()
        }
    }

    companion object {

        private val LAYOUTS = arrayOf(
                R.layout.fragment_first,
                R.layout.fragment_second,
                R.layout.fragment_third)

        private const val ARG_INDEX = "index"

        fun create(index: Int = 0) = ShapeFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_INDEX, index)
            }
        }

    }

}
