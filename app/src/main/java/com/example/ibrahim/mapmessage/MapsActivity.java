package com.example.ibrahim.mapmessage;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.ibrahim.mapmessage.network.pojo.EntryItem;
import com.example.ibrahim.mapmessage.util.BitmapHelper;
import com.example.ibrahim.mapmessage.util.SplitStringHelper;
import com.example.ibrahim.mapmessage.viewModel.MapActivityViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback {

    @BindView(R.id.parent_progress_layout)
    ConstraintLayout parentProgressLayout;
    private GoogleMap mMap;
    private MapActivityViewModel mViewModel;
    private List<Marker> markers = new ArrayList<>();
    final static String POSITIVE = "Positive";
    final static String NEUTRAL = "Neutral";
    final static String NEGATIVE = "Negative";
    final static String ALL = "All";
    private String currentEmotion = ALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initViewModel();

    }


    private void initViewModel() {

        parentProgressLayout.setVisibility(View.VISIBLE);
        mViewModel = ViewModelProviders.of(this).get(MapActivityViewModel.class);
        mViewModel.getResponse().observe(this, response -> {

            for (EntryItem entry : response.getFeed().getEntry()) {

                String emotion = SplitStringHelper.getEmotion(entry.getContent().getMessage());
                String cityName = SplitStringHelper.getCityName(entry.getContent().getMessage());
                String mMessage = SplitStringHelper.getMessage(entry.getContent().getMessage());
                geocode(cityName, mMessage, emotion.trim());
            }

        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    private void geocode(String cityName, String fullMessage, String emotion) {
        Geocoder gcd = new Geocoder(this, Locale.ENGLISH);
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocationName(cityName, 1);
            drawMarker(new LatLng(addresses.get(0).getLatitude(),
                            addresses.get(0).getLongitude()),
                    fullMessage,
                    emotion);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void drawMarker(LatLng latLng, String title, String emotion) {
        Drawable circleDrawable = null;
        if (emotion.equals(NEUTRAL)) {
            circleDrawable = getResources().getDrawable(R.drawable.ic_normal_24dp);

        } else if (emotion.equals(POSITIVE)) {
            circleDrawable = getResources().getDrawable(R.drawable.ic_positive_24dp);

        } else if (emotion.equals(NEGATIVE)) {
            circleDrawable = getResources().getDrawable(R.drawable.ic_negative_24dp);
        }
        BitmapDescriptor markerIcon = BitmapHelper.getMarkerIconFromDrawable(circleDrawable);

        markers.add(mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(emotion)
                .icon(markerIcon)));
        parentProgressLayout.setVisibility(View.GONE);

    }

    @OnClick(R.id.iv_positive)
    public void onIvPositiveClicked() {
        getEmotions(POSITIVE);
    }

    @OnClick(R.id.iv_normal)
    public void onIvNormalClicked() {
        getEmotions(NEUTRAL);


    }

    @OnClick(R.id.iv_negative)
    public void onIvNegativeClicked() {
        getEmotions(NEGATIVE);
    }

    @OnClick(R.id.allBtn)
    public void onAllBtnClicked() {
        getEmotions(ALL);
    }

    private void filterMarkers(String emotion) {

        if (emotion.equals(ALL)) {
            for (Marker marker : markers) {
                marker.setVisible(true);
            }
            return;
        }

        for (Marker marker : markers) {
            if (marker.getSnippet().equals(emotion)) {
                marker.setVisible(true);
            } else {
                marker.setVisible(false);

            }
        }
    }

    private void getEmotions(String emotion) {
        if (currentEmotion.equals(emotion)) {
            return;
        }
        filterMarkers(emotion);
        currentEmotion = emotion;
    }

}
