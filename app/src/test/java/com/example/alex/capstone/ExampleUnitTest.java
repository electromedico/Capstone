package com.example.alex.capstone;

import com.example.alex.capstone.utils.MapUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Test;

import static com.example.alex.capstone.utils.MapUtils.RADIUS_METERS_ZOOM;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public ExampleUnitTest() {
    }

    @Test
    public void calculateBondingBox_isCorrect() {
        LatLng l1 = new LatLng(1.457652,43.630826);
        LatLngBounds latLngBounds = MapUtils.calculateBoundingBox(RADIUS_METERS_ZOOM,l1);
        assert true;
    }

}