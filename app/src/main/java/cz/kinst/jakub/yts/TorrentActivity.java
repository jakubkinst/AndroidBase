package cz.kinst.jakub.yts;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import cz.kinst.jakub.androidbase.ui.BaseActivity;
import cz.kinst.jakub.yts.model.Torrent;


public class TorrentActivity extends BaseActivity {

    public static final String EXTRA_TORRENT = "torrent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#90000000")));
        setContentView(R.layout.activity_torrent);

        Torrent torrent = ((Torrent) getIntent().getSerializableExtra(EXTRA_TORRENT));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, TorrentFragment.newInstance(torrent))
                    .commit();
        }
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setTitle(torrent.MovieTitleClean + " (" + torrent.MovieYear + ")");
    }


}
