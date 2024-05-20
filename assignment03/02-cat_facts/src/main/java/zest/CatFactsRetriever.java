package zest;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CatFactsRetriever {

    private final HttpUtil httpUtil;

    public CatFactsRetriever(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    /**
     * Returns a String containing a random fact about cats
     * as retrieved from the catfact.ninja API.
     *
     * @return a random fact about cats
     */
    public String retrieveRandom() throws IOException {
        String response = httpUtil.get("https://catfact.ninja/fact");
        JSONObject jo = new JSONObject(response);
        return jo.getString("fact");
    }

    /**
     * Returns a String containing the longest fact about cats
     * as retrieved by querying a list of limit facts from the
     * catfact.ninja API.
     *
     * @param limit the maximum number of facts to retrieve from
     *              the API
     * @return the longest fact from the list
     */
    public String retrieveLongest(int limit) throws IOException {
        String response = httpUtil.get("https://catfact.ninja/facts?limit=" + limit);
    
        // Check if the response is empty
        if (response == null || response.isEmpty()) {
            return ""; // Return an empty string
        }
    
        // Check if the response is in the expected JSON format
        if (!response.startsWith("{")) {
            throw new JSONException("Invalid JSON response"); // Throw JSONException for invalid JSON response
        }
    
        JSONArray ja = new JSONObject(response).getJSONArray("data");
    
        int length = 0;
        String longestFact = "";
        for (Object e : ja) {
            if (e instanceof JSONObject) {
                JSONObject jo = (JSONObject) e;
                if (jo.getInt("length") > length) {
                    longestFact = jo.getString("fact");
                    length = jo.getInt("length");
                }
            }
        }
    
        return longestFact;
    }
    

}
