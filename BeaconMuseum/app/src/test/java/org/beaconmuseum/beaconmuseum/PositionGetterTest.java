package org.beaconmuseum.beaconmuseum;

import android.provider.Settings;
import android.util.Log;

import org.beaconmuseum.beaconmuseum.locator.Point;
import org.junit.Test;
import org.beaconmuseum.beaconmuseum.locator.LogicCalc;
import static org.junit.Assert.*;

public class PositionGetterTest {
    @Test
    public void testGettingPos() throws Exception {
        Point triangle[] = new Point[3];
        triangle[0] = new Point(2.86, 5.16);
        triangle[1] = new Point(1.36, 1.54);
        triangle[2] = new Point(6.67, 1.16);

        Double dist[] = new Double[3];

        dist[0] = new Double(5.78);
        dist[1] = new Double(8.2);
        dist[2] = new Double(4.61);

        Point res = LogicCalc.getPosition(triangle, dist);

        System.out.println("testGettingPos: POZYCJA (" + res._X + ", " + res._Y + ")");
    }
}
