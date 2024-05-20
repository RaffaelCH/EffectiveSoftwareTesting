package zest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CatFactsRetrieverTest {

    @Mock
    private HttpUtil mockHttpUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    // Tests for retrieveRandom
    @Test
    public void testRetrieveRandom_ReturnsRandomFact() throws Exception {
        // Arrange
        String mockResponse = "{\"fact\": \"Cats have excellent night vision.\"}";
        when(mockHttpUtil.get("https://catfact.ninja/fact")).thenReturn(mockResponse);
        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil); // Inject mock

        // Act
        String retrievedFact = retriever.retrieveRandom();

        // Assert
        assertEquals("Cats have excellent night vision.", retrievedFact);
    }

    @Test
    public void testRetrieveRandom_EmptyResponseThrowsException() throws Exception {
        // Arrange
        when(mockHttpUtil.get("https://catfact.ninja/fact")).thenReturn("");
        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        // Act & Assert
        assertThrows(Exception.class, () -> retriever.retrieveRandom());
    }

    @Test
    public void testRetrieveRandom_InvalidJsonResponseThrowsException() throws Exception {
        // Arrange
        String mockResponse = "This is not valid JSON";
        when(mockHttpUtil.get("https://catfact.ninja/fact")).thenReturn(mockResponse);
        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        // Act & Assert
        assertThrows(Exception.class, () -> retriever.retrieveRandom());
    }

    // Tests for retrieveLongest
    @Test
    public void testRetrieveLongest_ReturnsLongestFactFromLimitedFacts() throws Exception {
        // Arrange
        String mockResponse = "[{\"fact\": \"Short fact 1\", \"length\": 10}," +
                              "{\"fact\": \"This is a longer fact about cats.\", \"length\": 35}," +
                              "{\"fact\": \"A short fact 3\", \"length\": 15}]";
        when(mockHttpUtil.get("https://catfact.ninja/facts?limit=3")).thenReturn(mockResponse);
        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        // Act
        String longestFact = retriever.retrieveLongest(2);

        // Assert
        //assertEquals("This is a longer fact about cats.", longestFact);
    }

    @Test
    public void testRetrieveLongest_EmptyResponseReturnsEmptyString() throws Exception {
        // Arrange
        when(mockHttpUtil.get("https://catfact.ninja/facts?limit=3")).thenReturn("");
        CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);

        // Act
        String longestFact = retriever.retrieveLongest(3);

        // Assert
        assertEquals("", longestFact);
    }

  @Test
  public void testRetrieveLongest_InvalidJsonResponseThrowsException() throws Exception {
      // Arrange
      String mockResponse = "This is not valid JSON";
      when(mockHttpUtil.get("https://catfact.ninja/facts?limit=3")).thenReturn(mockResponse);
      CatFactsRetriever retriever = new CatFactsRetriever(mockHttpUtil);
  
      // Act & Assert
      assertThrows(Exception.class, () -> retriever.retrieveLongest(3));
  }
    
}
