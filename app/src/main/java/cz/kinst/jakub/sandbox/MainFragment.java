package cz.kinst.jakub.sandbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.kinst.jakub.sandbox.base.BaseFragment;

/**
 * Created by jakubkinst on 07/03/14.
 */
public class MainFragment extends BaseFragment {

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.button_1080)
    void onButton1080Click(){
        openList("1080p");
    }
    @OnClick(R.id.button_720)
    void onButton720Click(){
        openList("720p");
    }
    @OnClick(R.id.button_3d)
    void onButton3dClick(){
        openList("3d");
    }

    private void openList(String quality) {
        Intent i = new Intent(getActivity(),ListActivity.class);
        i.putExtra(ListActivity.EXTRA_QUALITY,quality);
        startActivity(i);
    }

}
