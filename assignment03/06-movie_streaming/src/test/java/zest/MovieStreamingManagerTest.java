package zest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieStreamingManagerTest {

    @Mock
    private FileStreamService fileStreamService;
    @Mock
    private CacheService cacheService;

    private MovieStreamingManager manager;

    @BeforeEach
    public void setUp() {
        manager = new MovieStreamingManager(fileStreamService, cacheService);
    }

    @Test
    public void testStreamMovieCacheMiss() {
        String movieId = "movie123";
        MovieMetadata metadata = new MovieMetadata("Title", "Description");
        String token = "token123";

        when(cacheService.getDetails(movieId)).thenReturn(null);
        when(fileStreamService.retrieveMovie(movieId)).thenReturn(metadata);
        when(fileStreamService.generateToken(movieId)).thenReturn(token);

        StreamingDetails result = manager.streamMovie(movieId);

        assertNotNull(result);
        assertEquals(movieId, result.getMovieId());
        assertEquals(token, result.getStreamToken());
        assertEquals(metadata, result.getMetadata());

        verify(cacheService).getDetails(movieId);
        verify(fileStreamService).retrieveMovie(movieId);
        verify(fileStreamService).generateToken(movieId);
        verify(cacheService).cacheDetails(eq(movieId), any(StreamingDetails.class));
    }

    @Test
    public void testStreamMovieCacheHit() {
        String movieId = "movie123";
        StreamingDetails expectedDetails = new StreamingDetails(movieId, "token123", new MovieMetadata("Title", "Description"));

        when(cacheService.getDetails(movieId)).thenReturn(expectedDetails);

        StreamingDetails result = manager.streamMovie(movieId);

        assertEquals(expectedDetails, result);
        verify(cacheService).getDetails(movieId);
        verify(fileStreamService, never()).retrieveMovie(movieId);
        verify(fileStreamService, never()).generateToken(movieId);
    }

    @Test
    public void testValidateStreamingToken() {
        String token = "validToken123";

        when(fileStreamService.generateToken("movie123")).thenReturn(token);
        String retrievedToken = fileStreamService.generateToken("movie123");

        assertEquals(token, retrievedToken);
        verify(fileStreamService).generateToken("movie123");
    }

    @Test
    public void testFileSystemFailure() {
        String movieId = "movie123";
        when(cacheService.getDetails(movieId)).thenReturn(null);
        when(fileStreamService.retrieveMovie(movieId)).thenThrow(new RuntimeException("File system down"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            manager.streamMovie(movieId);
        });

        assertEquals("File system down", exception.getMessage());
        verify(cacheService).getDetails(movieId);
        verify(fileStreamService).retrieveMovie(movieId);
    }

}
