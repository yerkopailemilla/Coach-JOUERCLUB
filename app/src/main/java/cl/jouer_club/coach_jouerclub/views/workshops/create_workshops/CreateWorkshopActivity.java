package cl.jouer_club.coach_jouerclub.views.workshops.create_workshops;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import cl.jouer_club.coach_jouerclub.R;
import cl.jouer_club.coach_jouerclub.data.SessionsPreferences;
import cl.jouer_club.coach_jouerclub.utils.Tools;
import cl.jouer_club.coach_jouerclub.views.main.MainActivity;

public class CreateWorkshopActivity extends AppCompatActivity implements CreateWorkshopContract.View {

    private BottomSheetBehavior bottomSheetBehavior;
    private Toolbar toolbar;
    private TextView textView_participants;
    private SeekBar seekBar_participants;
    private EditText editText_description, editText_name;
    private Button btnTimeBegin, btnTimeEnd, btnDateWorkshop;
    private CreateWorkshopContract.Presenter createWorkshopPresenter;

    private int participants;
    private String lat, lng, date, time_begin, time_end;

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar_create_workshop);
        toolbar.setTitle("Crear taller");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workshop);
        createWorkshopPresenter = new CreateWorkshopPresenter(this);

        initToolbar();
        initMapFragment();
        initMapComponents();

        Button btnCreateWorkshop = findViewById(R.id.btnCreateWorkshop);
        btnCreateWorkshop.setOnClickListener(createWorkshop);

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
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.coach_marker))
                        .draggable(true));

                googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        lat = String.valueOf(marker.getPosition().latitude);
                        lng = String.valueOf(marker.getPosition().longitude);
                    }
                });

            }
        });
    }

    private void initMapComponents() {
        // get the bottom sheet view
        LinearLayout llBottomSheet = findViewById(R.id.bottom_sheet_add);

        // init the bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        // change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        (findViewById(R.id.fab_directions)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                currentLocation();
            }
        });

        textView_participants = findViewById(R.id.textView_participants);
        seekBar_participants = findViewById(R.id.seekBar_participants);
        editText_description = findViewById(R.id.editText_description);
        editText_name = findViewById(R.id.editText_name);

        seekBar_participants.getProgressDrawable().setColorFilter(getResources().getColor(R.color.amber_400), PorterDuff.Mode.SRC_ATOP);
        seekBar_participants.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView_participants.setText(String.valueOf(progress));
                participants = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnTimeBegin = findViewById(R.id.btnTimeBegin);
        btnTimeBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBegin((Button) v);
            }
        });

        btnTimeEnd = findViewById(R.id.btnTimeEnd);
        btnTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeEnd((Button) v);
            }
        });

        btnDateWorkshop = findViewById(R.id.btnDateWorkshop);
        btnDateWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDatePickerLight((Button) v);
            }
        });

        Toast.makeText(CreateWorkshopActivity.this, "Desliza hacia arriba para completar los campos.", Toast.LENGTH_LONG).show();

    }

    private void timeBegin(final Button bt) {
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                ((Button) findViewById(R.id.btnTimeBegin)).setText(hourOfDay + ":" + 00 + ":" + 00);
            }
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        datePicker.enableMinutes(false);
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getFragmentManager(), "Timepickerdialog");
    }

    private void timeEnd(final Button bt) {
        Calendar cur_calender = Calendar.getInstance();
        TimePickerDialog datePicker = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                ((Button) findViewById(R.id.btnTimeEnd)).setText(hourOfDay + ":" + 00 + ":" + 00);
            }
        }, cur_calender.get(Calendar.HOUR_OF_DAY), cur_calender.get(Calendar.MINUTE), true);
        datePicker.enableMinutes(false);

        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.show(getFragmentManager(), "Timepickerdialog");
    }

    private void dialogDatePickerLight(final Button bt) {
        Calendar cur_calender = Calendar.getInstance();
        DatePickerDialog datePicker = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        ((Button) findViewById(R.id.btnDateWorkshop)).setText(Tools.getFormattedDateSimple(date_ship_millis));
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(getResources().getColor(R.color.colorPrimary));
        datePicker.setMinDate(cur_calender);
        datePicker.show(getFragmentManager(), "Datepickerdialog");
    }

    private View.OnClickListener createWorkshop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            date = btnDateWorkshop.getText().toString();
            time_begin = btnTimeBegin.getText().toString();
            time_end = btnTimeEnd.getText().toString();

            String name = editText_name.getText().toString();
            String description = editText_description.getText().toString();
            int players = participants;
            String latitude = lat;
            String longitude = lng;
            String begin = date + " " + time_begin;
            String end = date + " " + time_end;
            String status = "pending";
            int coach = new SessionsPreferences(getApplicationContext()).getSessionId();
            createWorkshopPresenter.createWorkshop(name, description, players, latitude, longitude, begin, end, status, coach);
        }
    };

    @Override
    public void createSuccess() {
        Toast.makeText(CreateWorkshopActivity.this, "Taller deportivo creado exitosamente.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CreateWorkshopActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void createFailed(String error) {
        Toast.makeText(CreateWorkshopActivity.this, error, Toast.LENGTH_LONG).show();
    }
}
