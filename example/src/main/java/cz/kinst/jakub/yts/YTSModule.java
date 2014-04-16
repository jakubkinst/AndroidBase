package cz.kinst.jakub.yts;

import javax.inject.Singleton;

import cz.kinst.jakub.androidbase.AndroidModule;
import cz.kinst.jakub.yts.model.Torrent;
import dagger.Module;
import dagger.Provides;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Module(
        injects = {
                MainActivity.class,
                TorrentActivity.class,
                TorrentFragment.class,
                TorrentListFragment.class
        },
        includes = AndroidModule.class
)
public final class YTSModule {

    @Provides
    @Singleton
    YTSInterface provideYtsInterface() {
        return new RestAdapter.Builder()
                .setEndpoint("http://yts.re/api")
                .build().create(YTSInterface.class).getDetails("asfsdf", new Callback<Torrent>() {
                    @Override
                    public void success(Torrent torrent, Response response) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }
}
