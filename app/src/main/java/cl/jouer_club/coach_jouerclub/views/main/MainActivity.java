package cl.jouer_club.coach_jouerclub.views.main;

import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.design.widget.BottomNavigationView;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.data.SessionsPreferences;
import cl.jouer_club.coach_jouerclub.utils.Tools;
import cl.jouer_club.coach_jouerclub.views.profile.ProfileFragment;
import cl.jouer_club.coach_jouerclub.views.workshops.WorkshopFragment;

public class MainActivity extends AppCompatActivity {

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.logo);
        String nick = new SessionsPreferences(getApplicationContext()).getSessionNickname();
        toolbar.setTitle("Bienvenido, " + nick);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_workshop:
                    fragment = new WorkshopFragment();
                    getSupportActionBar().setTitle("Talleres");
                    Toast.makeText(MainActivity.this, "Talleres", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.navigation_info:
                    fragment = new ProfileFragment();
                    getSupportActionBar().setTitle("Perfil");
                    Toast.makeText(MainActivity.this, "Perfil", Toast.LENGTH_SHORT).show();
                    break;
            }

            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new WorkshopFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
