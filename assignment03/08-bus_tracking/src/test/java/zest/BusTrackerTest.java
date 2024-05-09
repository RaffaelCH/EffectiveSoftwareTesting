package zest;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BusTrackerTest {

    @Test
    public void Test_initialDataReceived_MapLocationUpdated() {
        var gpsService = mock(GPSDeviceService.class);
        var mapService = mock(MapService.class);
        var notificationService = mock(NotificationService.class); // dummy

        var location = new Location(1, 1, false, "");

        var busId = "bus1";
        when(gpsService.getCurrentLocation(busId)).thenReturn(location);

        var busTracker = new BusTracker(gpsService, mapService, notificationService);
        busTracker.updateBusLocation(busId);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        String mapServiceBusId = busIdCaptor.getValue();
        Location lastLocation = locationCaptor.getValue();
        assertEquals(mapServiceBusId, busId);
        assertEquals(lastLocation, location);
    }

    @Test
    public void Test_multipleDataReceived_MapLocationUpdated() {
        var gpsService = mock(GPSDeviceService.class);
        var mapService = mock(MapService.class);
        var notificationService = mock(NotificationService.class); // dummy

        var location1 = new Location(1, 1, false, "");
        var location2 = new Location(1, 2, false, "");

        var busId = "bus1";
        when(gpsService.getCurrentLocation(busId)).thenReturn(location1, location2);

        var busTracker = new BusTracker(gpsService, mapService, notificationService);
        busTracker.updateBusLocation(busId);
        busTracker.updateBusLocation(busId);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService, atLeastOnce()).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        String mapServiceBusId = busIdCaptor.getValue();
        Location lastLocation = locationCaptor.getValue();
        assertEquals(mapServiceBusId, busId);
        assertEquals(lastLocation, location2);
    }

    @Test
    public void Test_noDataReceived_MapLocationNotUpdated() {
        var gpsService = mock(GPSDeviceService.class);
        var mapService = mock(MapService.class);
        var notificationService = mock(NotificationService.class); // dummy

        var location = new Location(1, 1, false, "");

        var busId = "bus1";
        when(gpsService.getCurrentLocation(busId)).thenReturn(location, null);

        var busTracker = new BusTracker(gpsService, mapService, notificationService);
        busTracker.updateBusLocation(busId);
        busTracker.updateBusLocation(busId);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService, atLeastOnce()).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        String mapServiceBusId = busIdCaptor.getValue();
        Location lastLocation = locationCaptor.getValue();
        assertEquals(mapServiceBusId, busId);
        assertEquals(lastLocation, location);
    }

    @Test
    public void Test_multipleBusesDataReceived_MapLocationUpdated() {
        var gpsService = mock(GPSDeviceService.class);
        var mapService = mock(MapService.class);
        var notificationService = mock(NotificationService.class); // dummy

        var location1 = new Location(1, 1, false, "");
        var location2 = new Location(1, 2, false, "");

        var busId1 = "bus1";
        var busId2 = "bus2";
        when(gpsService.getCurrentLocation(busId1)).thenReturn(location1);
        when(gpsService.getCurrentLocation(busId2)).thenReturn(location2);

        var busTracker = new BusTracker(gpsService, mapService, notificationService);
        busTracker.updateBusLocation(busId1);
        busTracker.updateBusLocation(busId2);

        ArgumentCaptor<String> busIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Location> locationCaptor = ArgumentCaptor.forClass(Location.class);
        verify(mapService, times(2)).updateMap(busIdCaptor.capture(), locationCaptor.capture());
        var mapServiceBusIds = busIdCaptor.getAllValues();
        var locations = locationCaptor.getAllValues();
        assertEquals(mapServiceBusIds.get(0), busId1);
        assertEquals(mapServiceBusIds.get(1), busId2);
        assertEquals(locations.get(0), location1);
        assertEquals(locations.get(1), location2);
    }
}