package tran.eric.browsify;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by Eric on 1/11/17.
 */

public class ArtistAdapter extends ArrayAdapter<Artist> {

    private static final String LOG_TAG = ArtistAdapter.class.getName();

    public ArtistAdapter(Activity context, ArrayList<Artist>artistList){
        super(context,0,artistList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }
        Artist currentArtist = getItem(position);

        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.artist_name);
        artistNameTextView.setText(currentArtist.getName());

        return listItemView;
    }
}
