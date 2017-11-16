package com.bobbyzhang.bestblog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bobbyzhang.bestblog.utils.NetUtil;
import com.bobbyzhang.bestblog.utils.SnackbarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BobbyZhang on 2017/9/13.
 * 添加专栏页面
 */
public class AddActivity extends AppCompatActivity {

    private View ll_au;
    private NetUtil mNetUtil;

    @BindView(R.id.et_au_remark)
    EditText et_au_remark;
    @BindView(R.id.atv_au_url)
    AutoCompleteTextView atv_au_url;
    @BindView(R.id.bt_au_submit)
    Button bt_au_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        mNetUtil =new NetUtil();
        ll_au = findViewById(R.id.ll_au);
    }


    @OnClick({R.id.bt_au_testurl,R.id.bt_au_submit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_au_testurl:
                if (!mNetUtil.checkNetWorkStatus(getApplicationContext())){
                    SnackbarUtil.ShortSnackbar(ll_au,"网络错误",SnackbarUtil.Info).show();
                }else {
                    final boolean[] isNetok = new boolean[1];
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                             isNetok[0] = mNetUtil.checkURL(atv_au_url.getText().toString());
                            if (isNetok[0]){
                                SnackbarUtil.ShortSnackbar(ll_au,"链接正确",SnackbarUtil.Info).show();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        bt_au_submit.setVisibility(View.VISIBLE);
                                    }
                                });
                            }else {
                                Log.e("@con",et_au_remark.getText().toString());
                                SnackbarUtil.ShortSnackbar(ll_au,"链接有误请重新输入",SnackbarUtil.Info).show();
                            }
                        }
                    }).start();
                }
                break;
            case R.id.bt_au_submit://存在潜在问题
                if(et_au_remark.getText().toString()==""||et_au_remark.getText().toString()==null){
                    SnackbarUtil.ShortSnackbar(ll_au,"请完善表单后提交",SnackbarUtil.Info).show();
                }else{
                    Toast.makeText(getApplicationContext(),"添加功能内测中稍后见",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}

