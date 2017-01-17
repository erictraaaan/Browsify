package tran.eric.browsify;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ArtistSearchResults extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Artist>> {


    private String requestedArtist;

    private static final String LOG_TAG = ArtistSearchResults.class.getName();

    private static final int ARTIST_SEARCH_ID = 1;

    private ArtistAdapter mAdapter;

    //private ProgressBar mProgressBar;

    public static final String LAST_FM_ARTIST_SEARCH =
            "http://ws.audioscrobbler.com/2.0/?method=artist.search&api_key=a569b770016d3dabb0ffe5e75131fa3f&format=json&limit=5";
    //&artist=Snarky%20Puppy

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        requestedArtist = intent.getStringExtra("artist");

      //  mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_search_results);

        getLoaderManager().initLoader(ARTIST_SEARCH_ID, null, this);

        ListView artistListView = (ListView) findViewById(R.id.list);
        mAdapter = new ArtistAdapter(this, new ArrayList<Artist>());
        artistListView.setAdapter(mAdapter);
    }

    @Override
    public Loader<List<Artist>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(LAST_FM_ARTIST_SEARCH);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("artist", requestedArtist);

        return new ArtistLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Artist>> loader, List<Artist> data) {
        mAdapter.clear();
        if (data != null &&! data.isEmpty()){

            mAdapter.addAll(data);
        }



    }

    @Override
    public void onLoaderReset(Loader<List<Artist>> loader) {

        mAdapter.clear();
    }


}
