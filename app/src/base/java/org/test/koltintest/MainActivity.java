package org.test.koltintest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        if (savedInstanceState == null) {
            onNavigationItemSelected(navigation.getMenu().findItem(R.id.users));
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show(); // TODO código duplicado

        Fragment newFragment = null;
        switch (item.getItemId()) {
            case R.id.users:
                newFragment = UsersFragment.create();
                break;

            case R.id.shapes:
                newFragment = ShapeFragment.create();
                break;
        }

        if (newFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContent, newFragment)
                    .commit();
        }
        return true;
    }

}
