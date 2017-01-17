package tran.eric.browsify;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Eric on 1/11/17.
 * Implementation of the Aritst class
 */

@SuppressWarnings("serial")
public class Artist implements Serializable{
    private String name;
    private List<String> similarArtists;
    private String bio;
    private String artistImageURL;
    private transient Drawable artistImage;

    public Artist(String vName, String vArtistImageURL,String vBio){
        name = vName;
        artistImageURL = vArtistImageURL;
        bio = vBio;
    }

    public Artist (String vName, Drawable vArtistImage, String vURL){
        name = vName;
        artistImageURL = vURL;
        artistImage = vArtistImage;
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

    public Drawable getArtistImage(){ return artistImage;}
}