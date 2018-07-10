import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
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

    public static void GetCoordinates(String strCriteria) {
        try {
            // use OKHttp client to create the connection and retrieve data
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+strCriteria+"&key=AIzaSyDBmXR_y1SPt2EYc7YNtQHdN4RwjYVtXX4")
                    .build();

            // get OKHTTP response
            Response response = client.newCall(request).execute();
            LogFile.write(Status.PASS, "The criteria of search is places on the API of Google.");

            // Convert response to a JSON
            JSONObject mainJsonObject = new JSONObject(response.body().string());
            JSONArray locArr = mainJsonObject.getJSONArray("results");
            // get Json object
            JSONObject resultsJson1 = locArr.getJSONObject(0);
            JSONObject resultsJson2 = resultsJson1.getJSONObject("geometry");
            JSONObject resultsJson3 = resultsJson2.getJSONObject("location");

            // Creating Gson instance
//            Gson gson = new Gson();
//
//            // Creating a CoordinatesResult object using Gson
//            CoordinatesResult coordinates = gson.fromJson(resultsJson3.toString(), CoordinatesResult.class);
            valLAT = resultsJson3.getDouble("lat");
            valLNG = resultsJson3.getDouble("lng");
            LogFile.write(Status.PASS, "The Coordinates are read from the JSON file: "+valLAT+", "+valLNG);
//            return coordinates;
        }
        catch (IOException ex) {
            LogFile.write(Status.FAIL,"FAIL in get coordinates: " + ex.getMessage());
        } catch (JSONException ex) {
            LogFile.write(Status.FAIL,"FAIL in get coordinates: " + ex.getMessage());
        }
//        return null;
    }

    public static double getValLAT(){ return valLAT; }
    public static double getValLNG() { return valLNG; }
}
