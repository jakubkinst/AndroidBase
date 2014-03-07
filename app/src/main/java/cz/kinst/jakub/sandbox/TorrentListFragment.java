package cz.kinst.jakub.sandbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class TorrentListFragment extends BaseFragment {
    private static final String ARG_QUALITY = "type";
    @InjectView(R.id.list)
    ListView mList;
    private String mQuality;


    YTSInterface mYts = new RestAdapter.Builder()
            .setEndpoint("http://yts.re/api")
            .build().create(YTSInterface.class);
    private DefaultAdapter<Torrent> mAdapter;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TorrentListFragment newInstance(String type) {
        TorrentListFragment fragment = new TorrentListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUALITY, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_torrent_list, container, false);
        ButterKnife.inject(this, rootView);
        mQuality = getArguments().getString(ARG_QUALITY);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                holder.mRating.setAlpha(Float.parseFloat(item.MovieRating)/10f);
                holder.mGenre.setText(item.Genre);
                Picasso.with(getActivity()).load(item.CoverImage).centerCrop().resize(200, 200).into(holder.mImageView);

                return view;
            }
        });

        mList.setAdapter(mAdapter);

        loadData();
    }

    @OnItemClick(R.id.list)
    void onItemClick(int pos) {
        Torrent t = mAdapter.getItem(pos);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(t.TorrentMagnetUrl));
        startActivity(i);
    }

    private void loadData() {
        Logger.logD("Loading data...");
        mYts.listTorrents("seeds", 100, mQuality, "", "", new Callback<TorrentResponse>() {
            @Override
            public void success(TorrentResponse torrentResponse, Response response) {
                mAdapter.addAll(torrentResponse.MovieList);
                Logger.logD("Loaded data.");
            }

            @Override
            public void failure(RetrofitError error) {
                Logger.logE("Failed to load Torrents: " + error.getMessage());
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

        TorrentViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }



}
