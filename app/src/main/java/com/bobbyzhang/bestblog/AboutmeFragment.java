package com.bobbyzhang.bestblog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.sns.SNS;
import com.avos.sns.SNSBase;
import com.avos.sns.SNSCallback;
import com.avos.sns.SNSException;
import com.avos.sns.SNSType;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

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

    // 1、定义一个 ThirdPartyType 变量
    private SNSType ThirdPartyType;
    // 2、定义一个 callback，用来接收授权后的数据
    final SNSCallback myCallback = new SNSCallback() {
        @Override
        public void done(SNSBase object, SNSException e) {
            if (e == null) {
                SNS.loginWithAuthData(object.userInfo(), new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        // 5、关联成功，已在 _User 表新增一条用户数据
                        Log.e("@xun",avUser.getUsername()+"--"+avUser.getUuid());
                    }
                });
            } else {
                e.printStackTrace();
                Log.e("@xun",e.getMessage());
            }
        }
    };


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

    @OnClick({R.id.bt_fa_toadd,R.id.tv_fa_version,R.id.bt_QQLogin})
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
            case R.id.bt_QQLogin:
                try {
                    ThirdPartyType = SNSType.AVOSCloudSNSQQ;
                    SNS.setupPlatform(getActivity(), SNSType.AVOSCloudSNSQQ, "1106425655", "t851Dudi2VeVtnE8", "http://blog.itbobby.top/");
                    SNS.loginWithCallback(getActivity(), SNSType.AVOSCloudSNSQQ, myCallback);
                    Log.e("@xun","bt_QQLogin");
                } catch (AVException e) {
                    e.printStackTrace();
                    Log.e("@xun",e.getMessage());
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 4、在页面 activity 回调里填写 ThirdPartyType
        if (resultCode == RESULT_OK) {
            SNS.onActivityResult(requestCode, resultCode, data, ThirdPartyType);

        }
    }

}
