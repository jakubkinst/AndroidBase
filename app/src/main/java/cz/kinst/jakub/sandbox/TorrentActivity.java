package cz.kinst.jakub.sandbox;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cz.kinst.jakub.base.BaseActivity;
import cz.kinst.jakub.sandbox.model.Torrent;


public class TorrentActivity extends BaseActivity {

    public static final String EXTRA_TORRENT = "torrent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torrent);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, TorrentFragment.newInstance((Torrent) getIntent().getSerializableExtra(EXTRA_TORRENT)))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.torrent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
