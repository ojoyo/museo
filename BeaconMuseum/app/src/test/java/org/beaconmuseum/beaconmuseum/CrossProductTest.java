package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.map.CrossProduct;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CrossProductTest {
    @Test
    public void testProduct() throws Exception {
        CrossProduct[] list = new CrossProduct[2];
        list[0] = new CrossProduct();
        list[1] = new CrossProduct(0.0, 0.0);

        for(CrossProduct p : list) {
            assertEquals(-1, p.product(0, 1, 1, 0), 0.1);
            assertEquals(0, p.product(0, 1, 0, -1), 0.1);
            assertEquals(0, p.product(0, 1, 0, 2), 0.1);
            assertEquals(1, p.product(1, 0, 0, 1), 0.1);
        }
    }

    @Test
    public void testProduct1() throws Exception {
        CrossProduct p = new CrossProduct(1.0, 1.0);

        assertEquals(-1, p.product(1, 2, 2, 1), 0.1);
        assertEquals(0, p.product(2, 1, 0, 1), 0.1);
        assertEquals(0, p.product(2, 1, 100, 1), 0.1);
        assertEquals(1, p.product(2, 1, 1, 2), 0.1);
    }
}