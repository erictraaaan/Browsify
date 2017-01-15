package tran.eric.browsify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    public static final String LAST_FM_ARTIST_SEARCH =
            "http://ws.audioscrobbler.com/2.0/?method=artist.search&api_key=a569b770016d3dabb0ffe5e75131fa3f&format=json&limit=5";
    public static final String LAST_FM_ARTIST_INFO =
            "http://earthquake.usgs.gov/fdsnws/event/1/query";
    public static final String LAST_FM_ARTIST_ALBUMS =
            "http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&api_key=a569b770016d3dabb0ffe5e75131fa3f&format=json&limit=10";
    public static final String LAST_FM_ALBUM_INFO =
            "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=a569b770016d3dabb0ffe5e75131fa3f&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /* Temporary way to get to the artist search page */
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getRequestedArtist(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ArtistSearchResults.class);
                intent.putExtra("artist", getRequestedArtist());
                startActivity(intent);
            }
        });

    }

    private String getRequestedArtist(){
        EditText searchArtist = (EditText) findViewById(R.id.requested_artist);
        String requestedArtist = searchArtist.getText().toString();
        return requestedArtist;
    }
}