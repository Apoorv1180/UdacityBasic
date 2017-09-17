package com.example.android.projectsixdemo.other;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.android.projectsixdemo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public String[] latPune, longtPune, latHotels, longtHotels, latRestaurants, longtRestaurants, latPlaces, longtPlaces, latNightlife, longtNightlife;
    MarkerOptions marker;
    LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Pune and move the camera
        LatLng stationaryPosition = new LatLng(18.5204, 73.8567);
        latPune = getResources().getStringArray(R.array.latitudes);
        longtPune = getResources().getStringArray(R.array.longitudes);
        latHotels = getResources().getStringArray(R.array.hotelLatitudes);
        longtHotels = getResources().getStringArray(R.array.hotelLongitudes);
        latRestaurants = getResources().getStringArray(R.array.restaurantLatitudes);
        longtRestaurants = getResources().getStringArray(R.array.restaurantLongitudes);
        latPlaces = getResources().getStringArray(R.array.placeNamesLatitudes);
        longtPlaces = getResources().getStringArray(R.array.placeNamesLongitudes);
        latNightlife = getResources().getStringArray(R.array.nightlifeNamesLatitudes);
        longtNightlife = getResources().getStringArray(R.array.nightlifeNamesLongitudes);

        final String locationTag = getIntent().getStringExtra("activity");

        if (locationTag.equals("MapsActivity")) {
            setLatLongPune();
            setLatLongHotelPune();
            setLatLongRestaurantPune();
            setLatLongPlacesPune();
            setLatLongNightlifePune();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(stationaryPosition));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        }
        if (locationTag.equals("HotelActivity")) {
            setHotelLocation();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        }
        if (locationTag.equals("RestaurantActivity")) {
            setRestaurantLocation();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        }
        if (locationTag.equals("PlacesToVisitActivity")) {
            setPlaceLocation();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        }
        if (locationTag.equals("NightLifeActivity")) {
            setNightlifeLocation();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        }
    }

    public void setHotelLocation() {
        int position = getIntent().getIntExtra("HotelPosition", 00);
        String hotelName = getIntent().getStringExtra("HotelName");
        location = new LatLng(Double.valueOf(latHotels[position]), Double.valueOf(longtHotels[position]));
        marker = new MarkerOptions().position(location).title(hotelName);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_hotel);
        marker.icon(icon);
        mMap.addMarker(marker);
    }

    public void setRestaurantLocation() {
        int position = getIntent().getIntExtra("RestaurantPosition", 00);
        String hotelName = getIntent().getStringExtra("RestaurantName");
        location = new LatLng(Double.valueOf(latRestaurants[position]), Double.valueOf(longtRestaurants[position]));
        marker = new MarkerOptions().position(location).title(hotelName);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_restaurant);
        marker.icon(icon);
        mMap.addMarker(marker);
    }

    public void setPlaceLocation() {
        int position = getIntent().getIntExtra("PlacePosition", 00);
        String hotelName = getIntent().getStringExtra("PlaceName");
        location = new LatLng(Double.valueOf(latPlaces[position]), Double.valueOf(longtPlaces[position]));
        marker = new MarkerOptions().position(location).title(hotelName);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_placestovisit);
        marker.icon(icon);
        mMap.addMarker(marker);
    }

    public void setNightlifeLocation() {
        int position = getIntent().getIntExtra("NightlifePosition", 00);
        String hotelName = getIntent().getStringExtra("NightlifeName");
        location = new LatLng(Double.valueOf(latNightlife[position]), Double.valueOf(longtNightlife[position]));
        marker = new MarkerOptions().position(location).title(hotelName);
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_nightlife);
        marker.icon(icon);
        mMap.addMarker(marker);
    }

    public void setLatLongPune() {
        for (int i = 0; i < latPune.length; i++) {
            location = new LatLng(Double.valueOf(latPune[i]), Double.valueOf(longtPune[i]));
            marker = new MarkerOptions().position(location).title("Pune");
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_how_to_reach);
            marker.icon(icon);
            mMap.addMarker(marker);
        }
    }

    public void setLatLongHotelPune() {
        for (int i = 0; i < latHotels.length; i++) {
            location = new LatLng(Double.valueOf(latHotels[i]), Double.valueOf(longtHotels[i]));
            marker = new MarkerOptions().position(location).title("Hotels");
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_hotel);
            marker.icon(icon);
            mMap.addMarker(marker);
        }
    }

    public void setLatLongNightlifePune() {
        for (int i = 0; i < latNightlife.length; i++) {
            location = new LatLng(Double.valueOf(latNightlife[i]), Double.valueOf(longtNightlife[i]));
            marker = new MarkerOptions().position(location).title("Nightlife");
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_nightlife);
            marker.icon(icon);
            mMap.addMarker(marker);
        }
    }

    public void setLatLongRestaurantPune() {
        for (int i = 0; i < latRestaurants.length; i++) {
            location = new LatLng(Double.valueOf(latRestaurants[i]), Double.valueOf(longtRestaurants[i]));
            marker = new MarkerOptions().position(location).title("Restaurant");
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_restaurant);
            marker.icon(icon);
            mMap.addMarker(marker);
        }
    }

    public void setLatLongPlacesPune() {
        for (int i = 0; i < latPlaces.length; i++) {
            location = new LatLng(Double.valueOf(latPlaces[i]), Double.valueOf(longtPlaces[i]));
            marker = new MarkerOptions().position(location).title("PlacesToVisit");
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_placestovisit);
            marker.icon(icon);
            mMap.addMarker(marker);
        }
    }
}

