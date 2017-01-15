package tran.eric.browsify;

import java.util.List;

/**
 * Created by Eric on 1/11/17.
 * implementation of the album class
 */

public class Album {
    private String albumName;
    private String albumArtist;
    private String albumImageURL;
    private List<String> albumTracks;

    public void Album (String vAlbumName, String vAlbumArtist, String vAlbumImageURL, List<String> vAlbumTracks){
        albumName = vAlbumName;
        albumArtist = vAlbumArtist;
        albumImageURL = vAlbumImageURL;
        albumTracks = vAlbumTracks;
    }

    public String getAlbumName(){
        return albumName;
    }

    public String getAlbumArtist(){
        return albumArtist;
    }

    public String getAlbumImageURL(){
        return albumImageURL;
    }

    public List<String> getAlbumTracks(){
        return albumTracks;
    }
}
