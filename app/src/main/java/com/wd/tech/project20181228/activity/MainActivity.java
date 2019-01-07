package com.wd.tech.project20181228.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.apis.Apis;
import com.wd.tech.project20181228.bean.LoginBean;
import com.wd.tech.project20181228.presenter.PresenterImpl;
import com.wd.tech.project20181228.view.Iview;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Iview {

    @BindView(R.id.login_edit_phone)
    EditText login_edit_phone;
    @BindView(R.id.login_edit_password)
    EditText login_edit_password;
    @BindView(R.id.login_checkbox_remember)
    CheckBox login_checkbox_remember;
    @BindView(R.id.login_text_register)
    TextView login_text_register;
    @BindView(R.id.btn_login)
    Button btn_login;
    private PresenterImpl presenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new PresenterImpl(this);
        sharedPreferences = getSharedPreferences("swl",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        checkboxCheck();
    }
    //记住密码的判断
    private void checkboxCheck() {
        //取出记住密码的状态值进行判断
        boolean ischecked = sharedPreferences.getBoolean("ischecked", false);
        if (ischecked){
            //回显
            login_edit_phone.setText(sharedPreferences.getString("phone",null));
            login_edit_password.setText(sharedPreferences.getString("password",null));
            login_checkbox_remember.setChecked(true);
        }else {
            //清空
            editor.clear();
            editor.commit();
        }
    }

    @OnClick({R.id.login_checkbox_remember,R.id.login_text_register,R.id.btn_login})
    public void onClicked(View view){
        switch (view.getId()){
            //记住密码
            case R.id.login_checkbox_remember:
                break;
            //快速注册
            case R.id.login_text_register:
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
                break;
            //登录
            case R.id.btn_login:
                //判断记住密码框是否选中
                if (login_checkbox_remember.isChecked()){
                    //将输入框的值放入sharedprefences中
                    editor.putString("phone",login_edit_phone.getText().toString());
                    editor.putString("password",login_edit_password.getText().toString());
                    //存入一个勾选的状态值
                    editor.putBoolean("ischecked",true);
                    //提交
                    editor.commit();
                }else{
                    //清空
                    editor.clear();
                    editor.commit();
                }
                Map<String,String> map = new HashMap<>();
                map.put("phone",login_edit_phone.getText().toString());
                map.put("pwd",login_edit_password.getText().toString());
                presenter.startRequestPost(Apis.URL_LOGIN_POST,map,LoginBean.class);
                break;
        }
    }

    @Override
    public void showDataSuccess(Object data) {
        if (data instanceof LoginBean){
            LoginBean loginBean = (LoginBean) data;
            if (loginBean.getMessage().equals("登录成功")){
                String sessionId = loginBean.getResult().getSessionId();
                editor.putString("sessionId",sessionId);
                editor.commit();
                startActivity(new Intent(MainActivity.this,SuccessActivity.class));
                finish();
            }else{
                Toast.makeText(MainActivity.this,""+loginBean.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showDataFail(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
