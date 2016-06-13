package org.beaconmuseum.beaconmuseum;

import android.provider.Settings;
import android.util.Log;

import org.beaconmuseum.beaconmuseum.locator.Point;
import org.junit.Test;
import org.beaconmuseum.beaconmuseum.locator.LogicCalc;
import static org.junit.Assert.*;

/**
 * Created by skazy on 13.06.16.
 */
public class TriangleCreatorTest {
    @Test
    public void testMakeTriangle() throws Exception {
        double a_b = 6.3;
        double a_c = 8.99;
        double c_b = 7.38;
        double c_a = a_c;

        Point[] p = LogicCalc.makeTriangle(a_b, a_c, c_b, c_a);
        System.out.println("testMakeTriangle A = (" + p[0]._X + ", " + p[0]._Y + ")");
        System.out.println("testMakeTriangle B = (" + p[1]._X + ", " + p[1]._Y + ")");
        System.out.println("testMakeTriangle C = (" + p[2]._X + ", " + p[2]._Y + ")");

        assertEquals(p.length, 3);
    }
}
