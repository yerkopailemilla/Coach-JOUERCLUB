package cl.jouer_club.coach_jouerclub.views.workshops;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.adapters.WorkshopParticipantsAdapter;
import cl.jouer_club.coach_jouerclub.api.users.GetUsersTask;
import cl.jouer_club.coach_jouerclub.api.workshops.deleteWorkshop.DeleteWorkshopTask;
import cl.jouer_club.coach_jouerclub.api.workshops.getOne.GetOneWorkshop;
import cl.jouer_club.coach_jouerclub.models.user.UserModel;
import cl.jouer_club.coach_jouerclub.models.workshop.WorkshopModel;
import cl.jouer_club.coach_jouerclub.utils.ExpandableList;
import cl.jouer_club.coach_jouerclub.views.main.MainActivity;

public class DetailWorkshopActivity extends AppCompatActivity {

    public static final String WORKSHOP = "cl.jouer_club.coach_jouerclub.views.workshops.KEY.WORKSHOP";
    private TextView detailWorkshop_name_tv, detailWorkshop_date_tv, detailWorkshop_times_tv,
            detailWorkshop_participants_tv, detailWorkshop_description_tv;
    private ImageButton bt_toggle_passenger;
    private View lyt_expand_passenger;
    private int workshopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_workshop);

        WorkshopModel workshopModel = (WorkshopModel) getIntent().getSerializableExtra(WORKSHOP);
        workshopId = workshopModel.getIdentificador();
        detailWorkshop_name_tv = findViewById(R.id.detailWorkshop_name_tv);
        detailWorkshop_date_tv = findViewById(R.id.detailWorkshop_date_tv);
        detailWorkshop_times_tv = findViewById(R.id.detailWorkshop_times_tv);
        detailWorkshop_participants_tv = findViewById(R.id.detailWorkshop_participants_tv);
        detailWorkshop_description_tv = findViewById(R.id.detailWorkshop_description_tv);

        bt_toggle_passenger = findViewById(R.id.bt_toggle_passenger);
        lyt_expand_passenger = findViewById(R.id.lyt_expand_passenger);

        bt_toggle_passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ExpandableList(bt_toggle_passenger, lyt_expand_passenger).toggleSectionPassenger();
            }
        });

        new DisplayOneWorkshop(workshopId).execute();
        new ShowUsers(workshopId).execute();
        initMapFragment(workshopModel.getLatitud(), workshopModel.getLongitud());

        Button deleteWorkshop = findViewById(R.id.btnDeleteWorkshop);
        deleteWorkshop.setOnClickListener(removeWorkshoo);

    }

    View.OnClickListener removeWorkshoo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new RemoveWorkshop(workshopId).execute();
        }
    };

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
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.coach_marker))
                        .draggable(true));
            }
        });
    }

    public class ShowUsers extends GetUsersTask {

        public ShowUsers(int workshopId) {
            super(workshopId);
        }

        @Override
        protected void onPostExecute(List<UserModel> userModels) {
            RecyclerView recyclerWorkshopParticipants = findViewById(R.id.recylerVWorkshopParticipants);
            recyclerWorkshopParticipants.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailWorkshopActivity.this);
            recyclerWorkshopParticipants.setLayoutManager(linearLayoutManager);
            if (userModels != null && userModels.size() > 0){
                WorkshopParticipantsAdapter workshopParticipantsAdapter = new WorkshopParticipantsAdapter(userModels);
                recyclerWorkshopParticipants.setAdapter(workshopParticipantsAdapter);
                workshopParticipantsAdapter.notifyDataSetChanged();
            }
        }
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
                String begin = workshopModel.getInicio();
                String end = workshopModel.getTermino();
                String[] beginSplit = begin.split(" ");
                String[] endSplit = end.split(" ");
                String times = beginSplit[1] +" - "+endSplit[1];

                detailWorkshop_name_tv.setText(workshopModel.getNombre());
                detailWorkshop_date_tv.setText(beginSplit[0]);
                detailWorkshop_times_tv.setText(times);
                detailWorkshop_participants_tv.setText(String.valueOf(workshopModel.getJugadores()));
                detailWorkshop_description_tv.setText(workshopModel.getDescripcion());

                dialog.dismiss();
            }
        }
    }

    private class RemoveWorkshop extends DeleteWorkshopTask {

        public RemoveWorkshop(int workshopId) {
            super(workshopId);
        }

        @Override
        protected void onPostExecute(WorkshopModel workshopModel) {
            startActivity(new Intent(DetailWorkshopActivity.this, MainActivity.class));
        }
    }
}
