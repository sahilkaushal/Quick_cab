package com.example.touchstone.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.touchstone.demo.fragment.Bottom_sheet;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Map.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Map#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Map extends Fragment {
    MapView mapView;
    GoogleMap map;
    private GPSTracke gps;
    double latitude, longitude;
    ProgressBar progressBar;
    TextView textView;
    Marker marker;
    SeekBar seekBar;
    TextView textView1;
    TextView textView2;
    protected BottomSheetLayout bottomSheetLayout;
    ImageView driver, cancel;
    public int progres ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        bottomSheetLayout = (BottomSheetLayout) v.findViewById(R.id.bottomsheet);
        bottomSheetLayout.setPeekOnDismiss(true);
        driver = (ImageView) v.findViewById(R.id.driver_pic);
        cancel = (ImageView) v.findViewById(R.id.cancelcab);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        textView = (TextView) v.findViewById(R.id.Sending_request);
        seekBar = (SeekBar) v.findViewById(R.id.seekBar); //get the location
        getlocation();



        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);

        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);

        map.setMyLocationEnabled(true);
        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {

            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16);

        map.animateCamera(cameraUpdate);
        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));

        textView1 = (TextView) v.findViewById(R.id.XL);
        textView2 = (TextView) v.findViewById(R.id.XXL);


        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {

                progressBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        progressBar.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Request Accepted", Toast.LENGTH_SHORT).show();

                        map.clear();
                        textView1.setVisibility(View.GONE);
                        textView2.setVisibility(View.GONE);
                        seekBar.setVisibility(View.GONE);

                        if(progres<=50){

                            //Booked cab
                            map.addMarker(new MarkerOptions().position(new LatLng(30.578102, 76.836847))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
                        }else {

                            //Booked cab
                            map.addMarker(new MarkerOptions().position(new LatLng(30.578930, 76.837396))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_xxl_cab)));
                        }


                        //Current user Location
                        map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
                        //cancel the cab

                        cancel.setVisibility(View.VISIBLE);
                        cancel.findViewById(R.id.cancelcab).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "Request Cancel", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Navigation.class);
                                startActivity(intent);

                            }
                        });


//driver fragement
                        driver.setVisibility(View.VISIBLE);
                        driver.findViewById(R.id.driver_pic).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                new Bottom_sheet().show(getActivity().getSupportFragmentManager(), R.id.bottomsheet);

                            }
                        });
                    }
                }, 5000);


                return true;


            }
        });


//Xl cab's

        //HUT
        map.addMarker(new MarkerOptions().position(new LatLng(30.578102, 76.836847))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
        //sukhmani Technical collage
        map.addMarker(new MarkerOptions().position(new LatLng(30.578800, 76.837927))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
        //Nursing collage
        map.addMarker(new MarkerOptions().position(new LatLng(30.577481, 76.838409))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
        //Nursing collage
        map.addMarker(new MarkerOptions().position(new LatLng(30.577503, 76.838364))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
        //Nursing collage
        map.addMarker(new MarkerOptions().position(new LatLng(30.577800, 76.838587))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));


//seekbar working
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                Toast.makeText(getActivity(), "CAB Changed", Toast.LENGTH_SHORT).show();

                progres = progress ;

                if (progress > 0 && progress <= 50) {
                    //seekbar XL cab's and marker
                    map.clear();
                    //current location
                    map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));




                    //HUT
                    map.addMarker(new MarkerOptions().position(new LatLng(30.578102, 76.836847))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
                    //sukhmani Technical collage
                    map.addMarker(new MarkerOptions().position(new LatLng(30.578800, 76.837927))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
                    //Nursing collage
                    map.addMarker(new MarkerOptions().position(new LatLng(30.577481, 76.838409))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
                    //Nursing collage
                    map.addMarker(new MarkerOptions().position(new LatLng(30.577503, 76.838364))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));
                    //Nursing collage
                    map.addMarker(new MarkerOptions().position(new LatLng(30.577800, 76.838587))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_menu_marker_car)));


//seekbar XXL cab's and marker
                } else if (progress > 50 && progress <= 100) {
                    map.clear();
                    //current location
                    map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));

 //XXl cab's

                    map.addMarker(new MarkerOptions().position(new LatLng(30.578930, 76.837396))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_xxl_cab)));
                    //XXl cab's
                    map.addMarker(new MarkerOptions().position(new LatLng(30.578160, 76.836532))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_xxl_cab)));
                    //XXl cab's
                    map.addMarker(new MarkerOptions().position(new LatLng(30.579094, 76.836896))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_xxl_cab)));
                    //XXl cab's
                    map.addMarker(new MarkerOptions().position(new LatLng(30.578636, 76.838534))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_xxl_cab)));


                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });

        return v;

    }




    private void getlocation() {
        gps = new GPSTracke(getActivity());
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            // \n is for new line
            Toast.makeText(getActivity(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}