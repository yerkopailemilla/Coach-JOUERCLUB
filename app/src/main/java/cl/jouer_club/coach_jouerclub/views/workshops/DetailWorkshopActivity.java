package cl.jouer_club.coach_jouerclub.views.workshops;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.api.workshops.getOne.GetOneWorkshop;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import cl.jouer_club.coach_jouerclub.utils.ExpandableList;
import cl.jouer_club.coach_jouerclub.views.WorkshopParticipantsFragment;

public class DetailWorkshopActivity extends AppCompatActivity {

    public static final String WORKSHOP = "cl.jouer_club.coach_jouerclub.views.workshops.KEY.WORKSHOP";
    private TextView detailWorkshop_name_tv;
    private ImageButton bt_toggle_passenger;
    private View lyt_expand_passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_workshop);

        WorkshopModel workshopModel = (WorkshopModel) getIntent().getSerializableExtra(WORKSHOP);
        int workshopId = workshopModel.getIdentificador();
        detailWorkshop_name_tv = findViewById(R.id.detailWorkshop_name_tv);

        bt_toggle_passenger = findViewById(R.id.bt_toggle_passenger);
        lyt_expand_passenger = findViewById(R.id.lyt_expand_passenger);

        bt_toggle_passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ExpandableList(bt_toggle_passenger, lyt_expand_passenger).toggleSectionPassenger();
            }
        });

        new DisplayOneWorkshop(workshopId).execute();
        initMapFragment(workshopModel.getLatitud(), workshopModel.getLongitud());
        WorkshopParticipantsFragment.newInstance(workshopId);

    }

    private void initMapFragment(String latitude, String longitude) {
        final double lat = Double.parseDouble(latitude);
        final double lng = Double.parseDouble(longitude);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.workshop_map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMinZoomPreference(5);
                googleMap.setMaxZoomPreference(58);

                CameraPosition camera = new CameraPosition.Builder()
                        .target(new LatLng(lat, lng))
                        .zoom(13)
                        .bearing(0)
                        .tilt(30)
                        .build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

                final LatLng location = new LatLng(lat, lng);
                googleMap.addMarker(new MarkerOptions()
                        .position(location)
                        .draggable(true));
            }
        });
    }

    private class DisplayOneWorkshop extends GetOneWorkshop{

        private ProgressDialog dialog;

        public DisplayOneWorkshop(int workshopId) {
            super(workshopId);
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(DetailWorkshopActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.setProgress(values[1]);
        }

        @Override
        protected void onPostExecute(WorkshopModel workshopModel) {
            if (workshopModel != null){
                detailWorkshop_name_tv.setText(workshopModel.getNombre());
                dialog.dismiss();
//                Log.d("WORKSHOPS", workshopModel.getDescripcion());
            }
        }
    }
}
