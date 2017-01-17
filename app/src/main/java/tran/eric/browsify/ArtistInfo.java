package tran.eric.browsify;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class ArtistInfo extends Activity {

    private Artist chosenArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        Intent intent = getIntent();
        chosenArtist = (Artist) intent.getSerializableExtra("ChosenArtistOBJ");
        TextView tv = (TextView) findViewById(R.id.chosen_artist_name);
        tv.setText(chosenArtist.getName());

    }
}
