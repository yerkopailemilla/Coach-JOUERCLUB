package cl.jouer_club.coach_jouerclub.views.workshops;

import android.graphics.PorterDuff;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.utils.Tools;

public class CreateWorkshopActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private TextView lat_workshop, lng_workshop;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workshop);

        initToolbar();
        initMapFragment();
        initMapComponents();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar_create_workshop);
        toolbar.setTitle("Crear taller");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initMapComponents() {
        // get the bottom sheet view
        LinearLayout llBottomSheet = findViewById(R.id.bottom_sheet_add);

        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        // change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        lat_workshop = findViewById(R.id.lat_workshop);
        lng_workshop = findViewById(R.id.lng_workshop);

        // set callback for changes
        /*bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });*/

        (findViewById(R.id.fab_directions)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                currentLocation();
            }
        });

        Toast.makeText(getApplicationContext(), "Desliza hacia arriba para completar los campos.", Toast.LENGTH_LONG).show();

    }

    private void initMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMinZoomPreference(5);
                googleMap.setMaxZoomPreference(58);

                CameraPosition camera = new CameraPosition.Builder()
                        .target(new LatLng(-33.4057, -70.6822))
                        .zoom(15)
                        .bearing(0)
                        .tilt(30)
                        .build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

                final LatLng location = new LatLng(-33.4057, -70.6822);
                final Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(location)
                        .draggable(true));

                googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {
                        String lat = String.valueOf(marker.getPosition().latitude);
                        String lng = String.valueOf(marker.getPosition().longitude);
                        toolbar.setTitle(lat+" - "+lng);

                        lat_workshop.setText(lat);
                        lng_workshop.setText(lng);
                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        String lat = String.valueOf(marker.getPosition().latitude);
                        String lng = String.valueOf(marker.getPosition().longitude);
                        toolbar.setTitle(lat+" - "+lng);

                        lat_workshop.setText(lat);
                        lng_workshop.setText(lng);
                    }
                });

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
