package cz.kinst.jakub.sandbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import cz.kinst.jakub.base.Logger;
import cz.kinst.jakub.lib.DefaultAdapter;
import cz.kinst.jakub.lib.ViewProvider;
import cz.kinst.jakub.sandbox.base.BaseFragment;
import cz.kinst.jakub.sandbox.model.Torrent;
import cz.kinst.jakub.sandbox.model.TorrentResponse;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class TorrentListFragment extends BaseFragment {
    private static final String ARG_QUALITY = "quality";
    private static final String ARG_LIMIT = "limit";
    private static final String ARG_ORDER = "order";
    private static final String ARG_GENRE = "genre";
    private static final String ARG_KEYWORDS = "keywords";
    private static final String ARG_TITLE = "title";

    @InjectView(R.id.list)
    ListView mList;
    @InjectView(R.id.loading)
    ProgressBar mLoading;
    @InjectView(R.id.title)
    TextView mTitleTextView;
    private String mQuality;


    YTSInterface mYts = new RestAdapter.Builder()
            .setEndpoint("http://yts.re/api")
            .build().create(YTSInterface.class);
    private DefaultAdapter<Torrent> mAdapter;
    private int mLimit;
    private String mOrder;
    private String mGenre;
    private String mKeywords;
    private String mTitle;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TorrentListFragment newInstance(String title, String quality, String order, String keywords, String genre, int limit) {
        TorrentListFragment fragment = new TorrentListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TITLE, title);
        args.putSerializable(ARG_QUALITY, quality);
        args.putSerializable(ARG_LIMIT, limit);
        args.putSerializable(ARG_ORDER, order);
        args.putSerializable(ARG_GENRE, genre);
        args.putSerializable(ARG_KEYWORDS, keywords);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_torrent_list, container, false);
        ButterKnife.inject(this, rootView);
        mQuality = getArguments().getString(ARG_QUALITY);
        mOrder = getArguments().getString(ARG_ORDER);
        mGenre = getArguments().getString(ARG_GENRE);
        mLimit = getArguments().getInt(ARG_LIMIT);
        mKeywords = getArguments().getString(ARG_KEYWORDS);
        mTitle = getArguments().getString(ARG_TITLE);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitleTextView.setText(mTitle);
        mAdapter = new DefaultAdapter<Torrent>(new ArrayList<Torrent>(), new ViewProvider<Torrent>() {
            @Override
            public View getView(Torrent item, View view, ViewGroup parent) {
                TorrentViewHolder holder;
                if (view != null) {
                    holder = (TorrentViewHolder) view.getTag();
                } else {
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.item_torrent, parent, false);
                    ButterKnife.inject(view);
                    holder = new TorrentViewHolder(view);
                    view.setTag(holder);
                }

                holder.mTitle.setText(item.MovieTitleClean + " (" + item.MovieYear + ")");
                holder.mRating.setText(item.MovieRating);
                holder.mRating.setAlpha(Float.parseFloat(item.MovieRating) / 10f);
                holder.mGenre.setText(item.Genre);
                holder.mSize.setText(item.Size);
                holder.mQuality.setText(item.Quality);
                Picasso.with(getActivity()).load(item.CoverImage).centerCrop().resize(200, 200).into(holder.mImageView);

                return view;
            }
        });

        mList.setAdapter(mAdapter);

        mList.setVisibility(View.INVISIBLE);

        loadData();
    }

    @OnItemClick(R.id.list)
    void onItemClick(int pos) {
        Torrent t = mAdapter.getItem(pos);
        Intent i = new Intent(getActivity(),TorrentActivity.class);
        i.putExtra(TorrentActivity.EXTRA_TORRENT, t);
        startActivity(i);
    }

    private void loadData() {
        Logger.logD("Loading data...");
        mYts.listTorrents(mOrder, mLimit, mQuality, mKeywords, mGenre, new Callback<TorrentResponse>() {
            @Override
            public void success(TorrentResponse torrentResponse, Response response) {
                Logger.logD("Loaded data.");
                mList.setVisibility(View.VISIBLE);
                mLoading.setVisibility(View.INVISIBLE);
                mAdapter.addAll(torrentResponse.MovieList);
            }

            @Override
            public void failure(RetrofitError error) {
                Logger.logE("Failed to load Torrents: " + error.getMessage());
                mList.setVisibility(View.VISIBLE);
                mLoading.setVisibility(View.INVISIBLE);
                Crouton.makeText(getActivity(), getString(R.string.network_error), Style.ALERT).show();
            }
        });
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'null'
     * for easy to all layout elements.
     *
     * @author Android Butter Zelezny, plugin for IntelliJ IDEA/Android Studio by Inmite (www.inmite.eu)
     */
    static class TorrentViewHolder {
        @InjectView(R.id.imageView)
        ImageView mImageView;
        @InjectView(R.id.title)
        TextView mTitle;
        @InjectView(R.id.genre)
        TextView mGenre;
        @InjectView(R.id.rating)
        TextView mRating;
        @InjectView(R.id.size)
        TextView mSize;
        @InjectView(R.id.quality)
        TextView mQuality;

        TorrentViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }



}
