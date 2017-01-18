package tran.eric.browsify;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class ArtistInfo extends Activity implements LoaderManager.LoaderCallbacks<Artist> {

    private Artist chosenArtist;

    private String artistName;

    private TextView tv;

    private ImageView iv;

    private TextView TVartistName;

    private static final int ARTIST_SEARCH_ID = 1;

    public static final String LAST_FM_ARTIST_INFO =
            "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&api_key=a569b770016d3dabb0ffe5e75131fa3f&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        Intent intent = getIntent();




        //TODO change intent passing to only take in the string name, and not the entire object
        chosenArtist = (Artist) intent.getSerializableExtra("ChosenArtistOBJ");
        artistName = chosenArtist.getName();

        getLoaderManager().initLoader(ARTIST_SEARCH_ID, null, this);

        tv = (TextView) findViewById(R.id.chosen_artist_bio);
        iv = (ImageView) findViewById(R.id.chosen_artist_image);
        TVartistName = (TextView) findViewById(R.id.chosen_artist_name);

    }

    @Override
    public Loader<Artist> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(LAST_FM_ARTIST_INFO);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("artist", artistName);
        return new ChosenArtistLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<Artist> loader, Artist data) {
        chosenArtist = data;
        tv.setText(data.getBio());
        iv.setImageDrawable(data.getArtistImage());
        TVartistName.setText(data.getName());

    }

    @Override
    public void onLoaderReset(Loader<Artist> loader) {

    }
}
