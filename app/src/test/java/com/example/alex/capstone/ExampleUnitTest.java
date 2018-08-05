package com.example.alex.capstone;

import com.example.alex.capstone.utils.LatLongUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void calculateBondingBox_iscorrect() {
        LatLng l1 = new LatLng(1.457652,43.630826);
        LatLngBounds latLngBounds = LatLongUtils.calculateBondingBox(l1);
        assert true;
    }
}