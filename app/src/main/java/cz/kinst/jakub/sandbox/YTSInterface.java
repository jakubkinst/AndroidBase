package cz.kinst.jakub.sandbox;

import cz.kinst.jakub.sandbox.model.TorrentResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jakubkinst on 07/03/14.
 */
public interface YTSInterface {

    @GET("/list.json")
    void listTorrents(@Query("sort") String sort, @Query("limit") int limit, @Query("quality") String quality, @Query("keywords") String keywords, @Query("genre") String genre, Callback<TorrentResponse> callback);
}
