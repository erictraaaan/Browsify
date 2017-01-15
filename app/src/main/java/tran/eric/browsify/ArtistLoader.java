package tran.eric.browsify;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by Eric on 1/11/17.
 * Loader
 */

public class ArtistLoader extends AsyncTaskLoader<List<Artist>> {

    private String string;

    private static final String LOG_TAG = ArtistLoader.class.getName();

    public ArtistLoader(Context context,String s ){
        super(context);
        string = s;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Artist> loadInBackground() {
        Log.d(LOG_TAG, "load in background method started");
        if (string == null){
            return null;
        }
        return BrowsifyQueryUtils.extractArtists(string);
    }
}
