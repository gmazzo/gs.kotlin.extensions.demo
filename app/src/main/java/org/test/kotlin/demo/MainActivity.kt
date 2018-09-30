package org.test.kotlin.demo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.test.kotlin.demo.shapes.ShapeFragment
import org.test.kotlin.demo.users.UsersFragment
import org.test.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected)

        if (savedInstanceState == null) {
            onNavigationItemSelected(navigation.menu.findItem(R.id.users))
        }
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.title.toast(this)

        when (item.itemId) {
            R.id.users -> UsersFragment.create()
            R.id.shapes -> ShapeFragment.create()
            else -> null
        }?.let {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContent, it)
                    .commit()
        }
        return true
    }

}
