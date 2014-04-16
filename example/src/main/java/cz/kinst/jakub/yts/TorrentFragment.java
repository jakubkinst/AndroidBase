package cz.kinst.jakub.yts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cz.kinst.jakub.androidbase.ui.BaseFragment;
import cz.kinst.jakub.yts.model.Torrent;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class TorrentFragment extends BaseFragment {
    private static final String ARG_TORRENT = "torrent";

    @Inject
    YTSInterface mYts;

    @InjectView(R.id.showcase)
    ImageView mShowcase;
    @InjectView(R.id.genre)
    TextView mGenre;
    @InjectView(R.id.quality)
    TextView mQuality;
    @InjectView(R.id.size)
    TextView mSize;
    @InjectView(R.id.rating)
    TextView mRating;
    @InjectView(R.id.imdb)
    Button mImdb;
    @InjectView(R.id.magnet)
    Button mMagnet;


    private Torrent mTorrent;
    @Inject
    Handler mHandler;
    private Runnable mShowcaseDriver;
    private int mShowcaseCurrentPosition = 0;

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

        mYts.getDetails(mTorrent.MovieID, new Callback<Torrent>() {
            @Override
            public void success(Torrent torrent, Response response) {
                mTorrent = torrent;
                onDetailsLoaded();
            }

            @Override
            public void failure(RetrofitError error) {
                croutonError(R.string.network_error);
            }
        });
        mRating.setText(mTorrent.MovieRating);
        mGenre.setText(mTorrent.Genre);
        mSize.setText(mTorrent.Size);
        mQuality.setText(mTorrent.Quality);

    }

    private void onDetailsLoaded() {
        Picasso.with(getActivity()).load(mTorrent.LargeScreenshot1).fetch();
        Picasso.with(getActivity()).load(mTorrent.LargeScreenshot2).fetch();
        Picasso.with(getActivity()).load(mTorrent.LargeScreenshot3).fetch();

        // Start showcasing screenshots
        mShowcaseDriver = new Runnable() {
            @Override
            public void run() {
                String url = null;
                if (mShowcaseCurrentPosition == 0) {
                    url = mTorrent.LargeScreenshot1;
                    mShowcaseCurrentPosition = 1;
                } else if (mShowcaseCurrentPosition == 1) {
                    url = mTorrent.LargeScreenshot2;
                    mShowcaseCurrentPosition = 2;
                } else {
                    url = mTorrent.LargeScreenshot3;
                    mShowcaseCurrentPosition = 0;
                }
                Picasso.with(getActivity()).load(url).into(mShowcase);
                mHandler.postDelayed(mShowcaseDriver, 5000);
            }
        };
        mShowcaseDriver.run();

    }

    @OnClick(R.id.magnet)
    void onMagnetClick() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mTorrent.TorrentMagnetUrl));
        startActivity(i);
    }

    @OnClick(R.id.imdb)
    void onImdbClick() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(mTorrent.ImdbLink));
        startActivity(i);
    }


}
