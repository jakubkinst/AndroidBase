package cz.kinst.jakub.yts;

import javax.inject.Singleton;

import cz.kinst.jakub.androidbase.AndroidModule;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

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
                .build().create(YTSInterface.class);
    }
}
