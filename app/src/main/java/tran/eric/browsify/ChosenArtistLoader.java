package tran.eric.browsify;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class ChosenArtistLoader extends AsyncTaskLoader<Artist> {

    private String chosenArtistURL;

    private static final String LOG_TAG = ChosenArtistLoader.class.getName();


    public ChosenArtistLoader(Context context, String s) {
        super(context);
        chosenArtistURL = s;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Artist loadInBackground() {
        if (chosenArtistURL == null){
            return null;
        }
        return BrowsifyQueryUtils.extractSelectedArtist(chosenArtistURL);
    }
}
