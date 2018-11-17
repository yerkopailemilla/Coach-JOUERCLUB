package cl.jouer_club.coach_jouerclub.views.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.jouer_club.coach_jouerclub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}

//public class MainActivity extends AbstractMapActivity implements OnNavigationListener, OnInfoWindowClickListener, OnMarkerDragListener {
//    private static final String STATE_NAV="nav";
//    private static final int[] MAP_TYPE_NAMES= { R.string.normal, R.string.hybrid, R.string.satellite, R.string.terrain };
//    private static final int[] MAP_TYPES= { GoogleMap.MAP_TYPE_NORMAL, GoogleMap.MAP_TYPE_HYBRID, GoogleMap.MAP_TYPE_SATELLITE, GoogleMap.MAP_TYPE_TERRAIN };
//    private GoogleMap map=null;
//    String filterAddress = "";
//    Marker marker;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (readyToGo()) {
//            setContentView(R.layout.activity_main);
//        }
//        SupportMapFragment mapFrag= (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFrag.setRetainInstance(true);
//        initListNav();
//        map=mapFrag.getMap();
//        if (savedInstanceState == null) {
//            CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng( 41.003739, 28.999572));
//            CameraUpdate zoom=CameraUpdateFactory.zoomTo(10);
//            map.moveCamera(center);
//            map.animateCamera(zoom);
//            addMarker(map, 41.003739, 28.999572, R.string.un, R.string.united_nations);
//            map.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
//            map.setOnInfoWindowClickListener(this);
//            map.setOnMarkerDragListener(this);
//        }
//    }
//
//    @Override public boolean onNavigationItemSelected(int itemPosition, long itemId) {
//        map.setMapType(MAP_TYPES[itemPosition]);
//        return(true);
//    }
//
//    @Override public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putInt(STATE_NAV, getSupportActionBar().getSelectedNavigationIndex());
//    }
//
//    @Override public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        getSupportActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_NAV));
//    }
//
//    @Override public void onInfoWindowClick(Marker marker) {
//        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
//    }
//
//    @Override public void onMarkerDragStart(Marker marker) {
//        LatLng position=marker.getPosition();
//        Log.d(getClass().getSimpleName(), String.format("Drag from %f:%f", position.latitude, position.longitude));
//    }
//
//    @Override public void onMarkerDrag(Marker marker) {
//        LatLng position=marker.getPosition();
//        Log.d(getClass().getSimpleName(), String.format("Dragging to %f:%f", position.latitude, position.longitude));
//    }
//
//    @Override public void onMarkerDragEnd(Marker marker) {
//        LatLng position=marker.getPosition();
//        String filterAddress = "";
//        Geocoder geoCoder = new Geocoder( getBaseContext(), Locale.getDefault());
//        try {
//            List<Address> addresses = geoCoder.getFromLocation( position.latitude, position.longitude, 1);
//            if (addresses.size() > 0) {
//                for (int index = 0; index < addresses.get(0).getMaxAddressLineIndex(); index++)
//                    filterAddress += addresses.get(0).getAddressLine(index) + " ";
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    } catch (Exception e2) {
//        // TODO: handle exception
//        e2.printStackTrace();
//    }
//    Log.d(getClass().getSimpleName(), String.format("Dragging to %f:%f", position.latitude, position.longitude));
//    TextView myTextView = (TextView) findViewById(R.id.test); myTextView.setText("Address is: " + filterAddress);
//}
//
//private void initListNav() {
//    ArrayList<String> items=new ArrayList<String>();
//    ArrayAdapter<String> nav=null;
//    ActionBar bar=getSupportActionBar();
//    for (int type : MAP_TYPE_NAMES) {
//        items.add(getString(type));
//    }
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//        nav= new ArrayAdapter<String>( bar.getThemedContext(), android.R.layout.simple_spinner_item, items);
//    } else {
//        nav= new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, items);
//    }
//    nav.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//    bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//    bar.setListNavigationCallbacks(nav, this);
//}
//
//private void addMarker(GoogleMap map, double lat, double lon, int title, int snippet) {
//    map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
//            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
//            .draggable(true));
//}
//}

