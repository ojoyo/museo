package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.discovery.BluetoothDeviceEvent;
import com.kontakt.sdk.android.ble.discovery.EventType;
import com.kontakt.sdk.android.common.profile.RemoteBluetoothDevice;

import org.beaconmuseum.beaconmuseum.beacons.BeaconEventListener;
import org.beaconmuseum.beaconmuseum.beacons.BeaconEventProcessorInterface;
import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BeaconEventListenerTest {
    BeaconEventListener listener;

    @Before
    public void setUp() throws Exception {
        listener = new BeaconEventListener();
    }

    @Test
    public void testOnScanStart() throws Exception {
        listener.onScanStart();
    }

    @Test
    public void testOnScanStop() throws Exception {
        listener.onScanStop();
    }

    @Test
    public void testOnEvent() throws Exception {
        // Przygotowania
        BeaconEventProcessorInterface processor = mock(BeaconEventProcessorInterface.class);
        BluetoothDeviceEvent event = mock(BluetoothDeviceEvent.class);

        RemoteBluetoothDevice device = mock(RemoteBluetoothDevice.class);
        when(device.getUniqueId()).thenReturn("A");
        when(device.getName()).thenReturn("B");
        when(device.getDistance()).thenReturn(1.0);

        ArrayList<RemoteBluetoothDevice> list = new ArrayList<>();
        list.add(device);

        when(event.getEventType()).thenReturn(EventType.DEVICE_LOST);
        Mockito.<List<? extends RemoteBluetoothDevice>>when(event.getDeviceList()).thenReturn(list);

        // Wykonanie
        listener.registerProcessor(processor);
        listener.onEvent(event);

        // Weryfikacja
        verify(processor, only()).processBeaconEvent(eq(EventType.DEVICE_LOST), any(BeaconInfo.class));
    }
}