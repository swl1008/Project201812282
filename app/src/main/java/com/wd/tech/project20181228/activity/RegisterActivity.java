package com.wd.tech.project20181228.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.apis.Apis;
import com.wd.tech.project20181228.bean.RegisterBean;
import com.wd.tech.project20181228.presenter.PresenterImpl;
import com.wd.tech.project20181228.view.Iview;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class RegisterActivity extends AppCompatActivity implements Iview {

    @BindView(R.id.register_edit_phone)
    EditText register_edit_phone;
    @BindView(R.id.register_edit_password)
    EditText register_edit_password;
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.register_icon_eye)
    ImageView register_icon_eye;
    private PresenterImpl presenter;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter = new PresenterImpl(this);


        register_icon_eye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    register_edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    register_edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                return false;
            }
        });

    }



    @OnClick({R.id.btn_register,R.id.register_had})
    public void initClick(View view){
        switch (view.getId()){
            case R.id.btn_register:
                if (register_edit_phone.getText().toString().equals("") || register_edit_password.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(!(register_edit_phone.getText().toString().length() == 11)){
                    Toast.makeText(RegisterActivity.this,"手机号为11位",Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,String> map = new HashMap<>();
                    map.put("phone",register_edit_phone.getText().toString());
                    map.put("pwd",register_edit_password.getText().toString());
                    presenter.startRequestPost(Apis.URL_REGISTER_POST,map,RegisterBean.class);
                }
                break;
            case R.id.register_had:
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                finish();
                break;

        }
    }
    @Override
    public void showDataSuccess(Object data) {
        if (data instanceof RegisterBean){
            RegisterBean registerBean = (RegisterBean) data;
            if (registerBean.getMessage().equals("注册成功")){
                Toast.makeText(RegisterActivity.this,""+registerBean.getMessage(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                finish();
            }else{
                Toast.makeText(RegisterActivity.this,""+registerBean.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showDataFail(String error) {
        Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
