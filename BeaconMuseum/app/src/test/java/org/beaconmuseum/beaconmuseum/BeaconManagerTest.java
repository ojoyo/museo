package org.beaconmuseum.beaconmuseum;

import com.google.inject.AbstractModule;

import org.beaconmuseum.beaconmuseum.beacons.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.robolectric.*;
import org.robolectric.annotation.Config;
import roboguice.RoboGuice;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class BeaconManagerTest {
    private BeaconScanContext scanContextMock = mock(BeaconScanContext.class);
    private ProximityManagerWrapper proximityManagerMock = mock(ProximityManagerWrapper.class);
    private BeaconEventListener eventListenerMock = mock(BeaconEventListener.class);
    private KontaktSDKWrapper kontaktSDKMock = mock(KontaktSDKWrapper.class);
    private BeaconManager manager;

    @Before
    public void setUp() throws Exception {
        RoboGuice.overrideApplicationInjector(RuntimeEnvironment.application, new MyTestModule());
        manager = RoboGuice.getInjector(RuntimeEnvironment.application).getInstance(BeaconManager.class);
    }

    @After
    public void tearDown() {
        RoboGuice.Util.reset();
    }

    @Test
    public void testInitialize() throws Exception {
        manager.initialize(null);

        verify(kontaktSDKMock).initialize(null);
    }

    @Test
    public void testDestroy() throws Exception {
        manager.destroy();

        verify(kontaktSDKMock, Mockito.never()).reset();
    }

    @Test
    public void testDestroyInitialized() throws Exception {
        when(proximityManagerMock.isCreated()).thenReturn(true);

        manager.initialize(null);
        manager.destroy();

        verify(proximityManagerMock).disconnect();
        verify(kontaktSDKMock).reset();
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(BeaconScanContext.class).toInstance(scanContextMock);
            bind(ProximityManagerWrapper.class).toInstance(proximityManagerMock);
            bind(BeaconEventListener.class).toInstance(eventListenerMock);
            bind(KontaktSDKWrapper.class).toInstance(kontaktSDKMock);
        }
    }
}