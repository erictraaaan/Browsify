package tran.eric.browsify;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class ArtistSearchResults extends Activity implements LoaderManager.LoaderCallbacks<List<Artist>> {


    private String requestedArtist;

    private static final String LOG_TAG = ArtistSearchResults.class.getName();

    private static final int ARTIST_SEARCH_ID = 1;

    private ArtistAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private ProgressBar mProgressBar;

    public static final String LAST_FM_ARTIST_SEARCH =
            "http://ws.audioscrobbler.com/2.0/?method=artist.search&api_key=a569b770016d3dabb0ffe5e75131fa3f&format=json&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_search_results);

        Intent intent = getIntent();
        requestedArtist = intent.getStringExtra("artist");
        mEmptyStateTextView = (TextView) findViewById(R.id.no_results_found);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        getLoaderManager().initLoader(ARTIST_SEARCH_ID, null, this);

        ListView artistListView = (ListView) findViewById(R.id.list);
        artistListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new ArtistAdapter(this, new ArrayList<Artist>());
        artistListView.setAdapter(mAdapter);

        artistListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist currentArtist = mAdapter.getItem(position);

                Intent intent = new Intent(ArtistSearchResults.this, ArtistInfo.class);
                intent.putExtra("ChosenArtistOBJ", currentArtist);
                startActivity(intent);

            }
        });

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
        mProgressBar.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_results);  //This isn't working for some reason! TODO fix this
        if (data != null &&! data.isEmpty()){
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Artist>> loader) {
        mAdapter.clear();
    }


}
