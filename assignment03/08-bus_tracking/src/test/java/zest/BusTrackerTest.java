package zest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class BusTrackerTest {

    private GPSDeviceService gpsService;
    private MapService mapService;
    private NotificationService notificationService;
    private BusTracker busTracker;

    private Location location = new Location(0, 0, false, "");
    private Location keyLocation1 = new Location(1, 1, true, "KeyLocation1");
    private Location keyLocation2 = new Location(2, 2, true, "KeyLocation2");

    private String busId1 = "bus1";
    private String busId2 = "bus2";

    @BeforeEach
    public void SetupMocks() {
        gpsService = mock(GPSDeviceService.class);
        mapService = mock(MapService.class);
        notificationService = mock(NotificationService.class);
        busTracker = new BusTracker(gpsService, mapService, notificationService);
    }

    @Test
    public void Test_InitialDataReceived_MapLocationUpdated() {
        when(gpsService.getCurrentLocation(busId1)).thenReturn(location);

        busTracker.updateBusLocation(busId1);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        String mapServiceBusId = busIdCaptor.getValue();
        Location lastLocation = locationCaptor.getValue();
        assertEquals(mapServiceBusId, busId1);
        assertEquals(lastLocation, location);
    }

    @Test
    public void Test_MultipleDataReceived_MapLocationUpdated() {
        when(gpsService.getCurrentLocation(busId1)).thenReturn(keyLocation1, keyLocation2);

        var busTracker = new BusTracker(gpsService, mapService, notificationService);
        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId1);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService, atLeastOnce()).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        String mapServiceBusId = busIdCaptor.getValue();
        Location lastLocation = locationCaptor.getValue();
        assertEquals(mapServiceBusId, busId1);
        assertEquals(lastLocation, keyLocation2);
    }

    @Test
    public void Test_NoDataReceived_MapLocationNotUpdated() {
        when(gpsService.getCurrentLocation(busId1)).thenReturn(location, null);

        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId1);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService, atLeastOnce()).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        String mapServiceBusId = busIdCaptor.getValue();
        Location lastLocation = locationCaptor.getValue();
        assertEquals(mapServiceBusId, busId1);
        assertEquals(lastLocation, location);
    }

    @Test
    public void Test_MultipleBusesDataReceived_MapLocationUpdated() {
        when(gpsService.getCurrentLocation(busId1)).thenReturn(keyLocation1);
        when(gpsService.getCurrentLocation(busId2)).thenReturn(keyLocation2);

        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId2);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService, times(2)).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        var mapServiceBusIds = busIdCaptor.getAllValues();
        var locations = locationCaptor.getAllValues();
        assertEquals(mapServiceBusIds.get(0), busId1);
        assertEquals(mapServiceBusIds.get(1), busId2);
        assertEquals(locations.get(0), keyLocation1);
        assertEquals(locations.get(1), keyLocation2);
    }

    @Test
    public void Test_KeyLocationReached_NotificationSent() {
        when(gpsService.getCurrentLocation(busId1)).thenReturn(location, keyLocation1);

        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId1);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(notificationService, times(1)).notifyPassengers(busIdCaptor.capture(), messageCaptor.capture());
        var notificationBusId = busIdCaptor.getValue();
        var notificationMessage = messageCaptor.getValue();
        assertEquals(notificationBusId, busId1);
        VerifyNotification(notificationMessage, keyLocation1.getWaypointName());
    }

    @Test
    public void Test_NoKeyLocationReached_NoNotificationSent() {
        when(gpsService.getCurrentLocation(busId1)).thenReturn(location, null);

        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId1);

        verify(notificationService, never());
    }

    @Test
    public void Test_MultipleKeyLocationsReached_NotificationsSent() {
        when(gpsService.getCurrentLocation(busId1)).thenReturn(location, keyLocation1, keyLocation2);
        when(gpsService.getCurrentLocation(busId2)).thenReturn(location, location, keyLocation2);

        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId2);
        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId2);
        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId2);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(notificationService, times(3)).notifyPassengers(busIdCaptor.capture(), messageCaptor.capture());
        var notificationBusIds = busIdCaptor.getAllValues();
        var notificationMessages = messageCaptor.getAllValues();
        assertEquals(notificationBusIds.get(0), busId1);
        assertEquals(notificationBusIds.get(1), busId1);
        assertEquals(notificationBusIds.get(2), busId2);
        VerifyNotification(notificationMessages.get(0), keyLocation1.getWaypointName());
        VerifyNotification(notificationMessages.get(1), keyLocation2.getWaypointName());
        VerifyNotification(notificationMessages.get(2), keyLocation2.getWaypointName());
    }

    private void VerifyNotification(String notificationMessage, String waypointName) {
        assertEquals(notificationMessage, "The bus has arrived at " + waypointName);
    }
}