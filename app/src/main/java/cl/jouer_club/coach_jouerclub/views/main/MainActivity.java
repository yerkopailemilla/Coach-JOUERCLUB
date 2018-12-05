package cl.jouer_club.coach_jouerclub.views.main;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.design.widget.BottomNavigationView;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.data.SessionsPreferences;
import cl.jouer_club.coach_jouerclub.utils.Tools;
import cl.jouer_club.coach_jouerclub.views.profile.ProfileFragment;
import cl.jouer_club.coach_jouerclub.views.workshops.WorkshopFragment;
import cl.jouer_club.coach_jouerclub.views.workshops.create_workshops.CreateWorkshopActivity;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_create_workshop;
    private BottomNavigationView navigation;

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
                    fab_create_workshop.setVisibility(View.VISIBLE);
                    getSupportActionBar().setTitle("Talleres");
                    Toast.makeText(MainActivity.this, "Talleres", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.navigation_info:
                    fragment = new ProfileFragment();
                    fab_create_workshop.setVisibility(View.GONE);
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

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fab_create_workshop = findViewById(R.id.fab_create_workshop);
        fab_create_workshop.setOnClickListener(goToCreateWorkshop);

        loadFragment(new WorkshopFragment());

        NestedScrollView nested_content = findViewById(R.id.nested_scroll_view);
        nested_content.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < oldScrollY) { // up
                    animateNavigation(false);
                }
                if (scrollY > oldScrollY) { // down
                    animateNavigation(true);
                }
            }
        });
    }

    View.OnClickListener goToCreateWorkshop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toCreateWorkshop = new Intent(MainActivity.this, CreateWorkshopActivity.class);
            startActivity(toCreateWorkshop);
        }
    };

    boolean isNavigationHide = false;

    private void animateNavigation(final boolean hide) {
        if (isNavigationHide && hide || !isNavigationHide && !hide) return;
        isNavigationHide = hide;
        int moveY = hide ? (2 * navigation.getHeight()) : 0;
        navigation.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }
}
