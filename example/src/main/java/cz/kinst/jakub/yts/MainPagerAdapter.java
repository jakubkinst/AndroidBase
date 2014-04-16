package cz.kinst.jakub.yts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by jakubkinst on 08/03/14.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TorrentListFragment.newInstance("Most popular", "", "seeds", "", "", 200);
            case 1:
                return TorrentListFragment.newInstance("Newest", "", "date", "", "", 200);
            case 2:
                return TorrentListFragment.newInstance("Best rating", "", "rating", "", "", 200);
            case 3:
                return TorrentListFragment.newInstance("Best 3D", "3d", "rating", "", "", 200);

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
