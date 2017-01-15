package tran.eric.browsify;

import java.util.List;

/**
 * Created by Eric on 1/11/17.
 * Implementation of the Aritst class
 */

public class Artist {
    private String name;
    private List<String> similarArtists;
    private String bio;
    private String artistImageURL;

    public Artist(String vName, String vArtistImageURL,String vBio){
        name = vName;
        artistImageURL = vArtistImageURL;
        bio = vBio;
    }

    public Artist (String vName){
        name = vName;
    }

    public String getName(){
        return name;
    }

    public List<String> getSimilarArtists(){
        return similarArtists;
    }

    public String getBio(){
        return bio;
    }

    public String getArtistImageURL(){
        return artistImageURL;
    }
}
