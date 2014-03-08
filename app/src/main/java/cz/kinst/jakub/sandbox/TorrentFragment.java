package cz.kinst.jakub.sandbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.kinst.jakub.sandbox.base.BaseFragment;
import cz.kinst.jakub.sandbox.model.Torrent;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class TorrentFragment extends BaseFragment {
    private static final String ARG_TORRENT = "torrent";
    @InjectView(R.id.title)
    TextView mTitle;

    private Torrent mTorrent;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TorrentFragment newInstance(Torrent torrent) {
        TorrentFragment fragment = new TorrentFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TORRENT, torrent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_torrent, container, false);
        ButterKnife.inject(this, rootView);
        mTorrent = (Torrent) getArguments().getSerializable(ARG_TORRENT);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle.setText(mTorrent.MovieTitleClean);

    }

    @OnClick(R.id.magnet)
    void onMagnetClick(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mTorrent.TorrentMagnetUrl));
        startActivity(i);
    }

    @OnClick(R.id.imdb)
    void onImdbClick(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mTorrent.ImdbLink));
        startActivity(i);
    }


}
