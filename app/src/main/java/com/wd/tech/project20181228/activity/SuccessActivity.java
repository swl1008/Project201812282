package com.wd.tech.project20181228.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wd.tech.project20181228.R;
import com.wd.tech.project20181228.fragment.CarFragment;
import com.wd.tech.project20181228.fragment.CircleFragment;
import com.wd.tech.project20181228.fragment.HomeFragment;
import com.wd.tech.project20181228.fragment.MineFragment;
import com.wd.tech.project20181228.fragment.OrderFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessActivity extends BaseActivity {
    @BindView(R.id.success_viewpager)
    ViewPager success_viewPager;
    @BindView(R.id.success_group)
    RadioGroup success_group;
    @BindView(R.id.tab_home_bottom_shouye)
    RadioButton tabhomebottomshouye;
    @BindView(R.id.tab_home_bottom_quanzi)
    RadioButton tabhomebottomquanzi;
    @BindView(R.id.tab_home_bottom_che)
    RadioButton tabhomebottomche;
    @BindView(R.id.tab_home_bottom_dingdan)
    RadioButton tabhomebottomdingdan;
    @BindView(R.id.tab_home_bottom_wode)
    RadioButton tabhomebottomwod;

    @Override
    public void initData() {
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        success_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Fragment fragment = null;
                switch (i){
                    case 0:
                        fragment = new HomeFragment();
                        break;
                    case 1:
                        fragment = new CircleFragment();
                        break;
                    case 2:
                        fragment = new CarFragment();
                        break;
                    case 3:
                        fragment = new OrderFragment();
                        break;
                    case 4:
                        fragment = new MineFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        success_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        tabhomebottomshouye.setChecked(true);
                        tabhomebottomquanzi.setChecked(false);
                        tabhomebottomche.setChecked(false);
                        tabhomebottomdingdan.setChecked(false);
                        tabhomebottomwod.setChecked(false);
                        break;
                    case 1 :
                        tabhomebottomshouye.setChecked(false);
                        tabhomebottomquanzi.setChecked(true);
                        tabhomebottomche.setChecked(false);
                        tabhomebottomdingdan.setChecked(false);
                        tabhomebottomwod.setChecked(false);
                        break;
                    case 2:
                        tabhomebottomshouye.setChecked(false);
                        tabhomebottomquanzi.setChecked(false);
                        tabhomebottomche.setChecked(true);
                        tabhomebottomdingdan.setChecked(false);
                        tabhomebottomwod.setChecked(false);
                        break;
                    case 3:
                        tabhomebottomshouye.setChecked(false);
                        tabhomebottomquanzi.setChecked(false);
                        tabhomebottomche.setChecked(false);
                        tabhomebottomdingdan.setChecked(true);
                        tabhomebottomwod.setChecked(false);
                        break;
                    case 4:
                        tabhomebottomshouye.setChecked(false);
                        tabhomebottomquanzi.setChecked(false);
                        tabhomebottomche.setChecked(false);
                        tabhomebottomdingdan.setChecked(false);
                        tabhomebottomwod.setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        success_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tab_home_bottom_shouye:
                        success_viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_home_bottom_quanzi:
                        success_viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_home_bottom_che:
                        success_viewPager.setCurrentItem(2);
                        break;
                    case R.id.tab_home_bottom_dingdan:
                        success_viewPager.setCurrentItem(3);
                        break;
                    case R.id.tab_home_bottom_wode:
                        success_viewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    @Override
    public int getContent() {
        return R.layout.activity_success;
    }
}
