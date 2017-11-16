package com.bobbyzhang.bestblog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BobbyZhang on 2017/9/13.
 * 看我页面
 */

public class AboutmeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AboutmeFragment() {
    }

    public static AboutmeFragment newInstance(String param1, String param2) {
        AboutmeFragment fragment = new AboutmeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutme, container, false);
        //返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        ButterKnife.bind(this, view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @OnClick({R.id.bt_fa_toadd,R.id.tv_fa_version})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_fa_toadd:
                Intent intent01=new Intent(getActivity(),AddActivity.class);
                startActivity(intent01);
                break;
            case R.id.tv_fa_version:
                Intent intent02=new Intent(getActivity(),ColumnDetailsActivity.class);
                intent02.putExtra("remark","about BestBlog");
                intent02.putExtra("url","https://github.com/BobbyZhang93/BestBlog");
                startActivity(intent02);
                break;
        }
    }
}
