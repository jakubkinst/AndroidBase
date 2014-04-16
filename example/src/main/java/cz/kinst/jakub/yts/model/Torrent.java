package cz.kinst.jakub.yts.model;

import java.io.Serializable;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class Torrent implements Serializable {

    public String MovieID;
    public String MovieTitleClean;
    public String MovieYear;
    public String MovieRating;
    public String Genre;
    public String Quality;
    public String CoverImage;
    public String TorrentMagnetUrl;
    public String Size;
    public String ImdbLink;
    public String MediumScreenshot1;
    public String MediumScreenshot2;
    public String MediumScreenshot3;


    public String LargeScreenshot1;
    public String LargeScreenshot2;
    public String LargeScreenshot3;
}