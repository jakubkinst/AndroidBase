package cz.kinst.jakub.sandbox;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cz.kinst.jakub.base.BaseActivity;

public class ListActivity extends BaseActivity {

    public static final String EXTRA_QUALITY = "quality";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        String quality = getIntent().getStringExtra(EXTRA_QUALITY);

        getActionBar().setTitle(R.string.listing_movies);
        getActionBar().setSubtitle(quality.equals("3d") ? quality.toUpperCase() : quality);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, TorrentListFragment.newInstance(quality)).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

}
