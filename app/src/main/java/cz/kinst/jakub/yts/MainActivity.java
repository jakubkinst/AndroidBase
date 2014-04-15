package cz.kinst.jakub.yts;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.kinst.jakub.androidbase.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.pager)
    ViewPager mPager;
    @InjectView(R.id.container)
    FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (mPager != null) { // PHONE Layout
            mPager.setPageMargin(100);
            mPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        } else { // TABLET Layout
            //TODO: tablet mode

        }
    }

    /*
    MENU
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
