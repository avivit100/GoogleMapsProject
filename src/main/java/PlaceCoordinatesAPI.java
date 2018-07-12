import com.aventstack.extentreports.Status;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class PlaceCoordinatesAPI {
    private static  double valLAT;
    private static double valLNG;

    /* GetCoordinates - This method GET from google API, the coordinates, by giving the search criteria */
    public static void GetCoordinates(String strCriteria) {
        try {
            // use OKHttp client to create the connection and retrieve data
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Constants.API_GOOGLE + strCriteria + Constants.API_KEY)
                    .build();

            // get OKHTTP response
            Response response = client.newCall(request).execute();
            LogFile.write(Status.PASS, "The criteria of search is places on the API of Google.");

            // Convert response to a JSON
            JSONObject mainJsonObject = new JSONObject(response.body().string());
            JSONArray locArr = mainJsonObject.getJSONArray(Constants.API_RESULTS);
            // get Json object
            JSONObject resultsJson1 = locArr.getJSONObject(0);
            JSONObject resultsJson2 = resultsJson1.getJSONObject(Constants.API_GEOMETRY);
            JSONObject resultsJson3 = resultsJson2.getJSONObject(Constants.API_LOCATION);

            valLAT = resultsJson3.getDouble(Constants.API_LAT);
            valLNG = resultsJson3.getDouble(Constants.API_LNG);
            LogFile.write(Status.PASS, "The Coordinates are read from the JSON file: "+valLAT+", "+valLNG);
        }
        catch (IOException ex) {
            LogFile.write(Status.FAIL,"FAIL in get coordinates: " + ex.getMessage());
        } catch (JSONException ex) {
            LogFile.write(Status.FAIL,"FAIL in get coordinates: " + ex.getMessage());
        }
    }

    /* getValLAT - This method returns the LAT value of the location */
    public static double getValLAT(){ return valLAT; }

    /* getValLAT - This method returns the LNG value of the location */
    public static double getValLNG() { return valLNG; }
}
