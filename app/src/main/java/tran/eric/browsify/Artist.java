package tran.eric.browsify;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Eric on 1/11/17.
 * Implementation of the Artist class
 */

@SuppressWarnings("serial")
public class Artist implements Serializable{
    private String name;
    private List<Artist> similarArtists;
    private String bio;
    private String artistImageURL;
    private transient Drawable artistImage;

    public Artist(String vName, String vArtistImageURL, Drawable vArtistImage, String vBio, List<Artist> vSimilarArtists){
        name = vName;
        artistImageURL = vArtistImageURL;
        artistImage = vArtistImage;
        bio = vBio;
        similarArtists = vSimilarArtists;
    }

    public Artist (String vName, Drawable vArtistImage, String vURL){
        name = vName;
        artistImageURL = vURL;
        artistImage = vArtistImage;
    }

    public Artist (String vName, String vURL){
        name = vName;
        artistImageURL = vURL;
    }

    public Artist(){
    }

    public void setArtistDetails (String vName, String vArtistImageURL,
                                  Drawable vArtistImage, String vBio, List<Artist> vSimilarArtists){
        name = vName;
        artistImageURL = vArtistImageURL;
        artistImage = vArtistImage;
        bio = vBio;
        similarArtists = vSimilarArtists;
    }

    public String getName(){
        return name;
    }

    public List<Artist> getSimilarArtists(){
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