package tran.eric.browsify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static tran.eric.browsify.MainActivity.LOG_TAG;

/**
 * Created by Eric on 1/11/17.
 */

public class BrowsifyQueryUtils {

    public static final String LOG_TAG = BrowsifyQueryUtils.class.getName();

    public static List<Artist> extractArtists(String requestUrl){
        Log.d(LOG_TAG, "extract artists called");
        ArrayList<Artist> artistArrayList = new ArrayList<>();

        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);

            JSONArray artistArray = baseJsonResponse.getJSONObject("results").getJSONObject("artistmatches").getJSONArray("artist");

            for (int i = 0 ; i < artistArray.length() ; i ++){
                JSONObject o = artistArray.getJSONObject(i);
                JSONObject largeImage = o.getJSONArray("image").getJSONObject(3);

                Drawable d = drawableFromUrl(largeImage.getString("#text"));

                artistArrayList.add(new Artist(o.getString("name"), d , largeImage.getString("#text")));
            }

        }   catch (IOException e){
            Log.e (LOG_TAG, "Error closing input stream");
        }   catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return artistArrayList;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
}
