package org.beaconmuseum.beaconmuseum;

import com.google.inject.Inject;
//import org.junit.Test;
//import static org.junit.Assert.*;
import org.beaconmuseum.beaconmuseum.beacons.*;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class myComparator implements Comparator<BeaconInfo> {
    @Override
    public int compare(BeaconInfo b1, BeaconInfo b2) {
        if (b1.range < b2.range) return -1;
        if (b1.range > b2.range) return 1;
        return 0;
    }
}

public class AppManager {
    @Inject BeaconsInRangeList beaconsInRangeList;

    public BeaconInfo[] refreshGUI() {
        BeaconInfo bList[] =  BeaconsInRangeList.getInstance().getList();
        Arrays.sort(bList, new myComparator());
        return bList;
    }
}

//class MyComparatorTest {
//    @Test
//    public void testCompare() throws Exception {
//        BeaconInfo b1 = new BeaconInfo("b1", "beacon1", 10);
//        BeaconInfo b2 = new BeaconInfo("b2", "beacon2", 30);
//
//        myComparator comp = new myComparator();
//
//        assertEquals(comp.compare(b1,b2), -1);
//        assertEquals(comp.compare(b2,b1), 1);
//        assertEquals(comp.compare(b1,b1), 0);
//    }
//
//}
