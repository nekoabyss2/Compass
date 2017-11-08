package com.egci428.compass

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0) // set lattitude and logitude for the marker
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Bangkok")) //set position and title for marker
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

        var currentLatLng : LatLng = LatLng(39.913, 116.407)

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(39.913, 116.407),3f))
        mMap.addMarker(MarkerOptions().position(LatLng(39.913, 116.407)).title(" Beijing"))

        mMap.setOnMapClickListener {
            LatLng -> mMap.animateCamera(CameraUpdateFactory.newLatLng(LatLng))
        }

        mMap.setOnMapLongClickListener {
            LatLng -> mMap.addMarker(MarkerOptions().position(LatLng).title(LatLng.toString()))

            val line = mMap.addPolyline(PolylineOptions()
                    .add(currentLatLng, LatLng)
                    .width(5f)
                    .color(Color.RED))
            currentLatLng = LatLng

        }

        mMap.addMarker(MarkerOptions().position(LatLng(15.55234, 100.0))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).title("orange marker"))

        mMap.addMarker(MarkerOptions().position(LatLng(15.55234, 100.6))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).title("custom marker"))

        /*val line = mMap.addPolyline(PolylineOptions()
                .add(LatLng(13.7934, 100.322), LatLng(15.55234, 100.0)
                ,LatLng(15.55234, 100.6))
                .width(9f)
                .color(Color.RED))*/

        val polygon = mMap.addPolygon(PolygonOptions()
                .add(LatLng(13.7934, 100.3225), LatLng(13.7643, 99.5),
                        LatLng(13.3, 99.5))
                .strokeColor(Color.DKGRAY)
                .fillColor(Color.YELLOW))
    }
}
