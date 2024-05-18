package zest;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatFactsRetrieverTest {
    private final CatFactsRetriever catFactsRetriever = new CatFactsRetriever();

    @Mock
    private HttpUtil httpUtil;

    @Test
    public void normalInput(){
        String response = "{fact: aaa}";
        try {
            when(httpUtil.get("https://catfact.ninja/fact")).thenReturn(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            assertEquals("aaa", catFactsRetriever.retrieveRandom());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
