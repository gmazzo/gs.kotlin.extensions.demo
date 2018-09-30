package org.test.koltintest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.test.replaceWith

class ShapeFragment : Fragment() {

    private val layouts = intArrayOf(
            R.layout.fragment_first,
            R.layout.fragment_second,
            R.layout.fragment_third)

    private val index by lazy { arguments!!.getInt(ARG_INDEX) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(layouts[index], container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            val nextIndex = (index + 1) % layouts.size
            val tag = "shape$index"

            fragmentManager!!.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            replaceWith(ShapeFragment.create(nextIndex), tag)
        }
    }

    companion object {
        private const val ARG_INDEX = "index"

        fun create(index: Int = 0) = ShapeFragment().apply {
            arguments = bundleOf(ARG_INDEX to index)
        }
    }

}
