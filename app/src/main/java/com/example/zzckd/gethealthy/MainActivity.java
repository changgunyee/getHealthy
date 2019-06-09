package com.example.zzckd.gethealthy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.zzckd.gethealthy.dialog.CustomDIalog;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private changeImageRunnableClass changeImageRunnable;
    public ImageView characterImageView;
    private Handler handler;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private ToggleButton exerciseToggleButton;
    private long mShakeTime;
    private static final int SHAKE_SKIP_TIME=500;
    private static final float SHAKE_THRESHOLD_GRAVITY=2.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler=new Handler();
        changeImageRunnable=new changeImageRunnableClass();

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
//                toggleExercise(getCurrentFocus());
                exerciseToggleButton=findViewById(R.id.toggleButton);
                exerciseToggleButton.performClick();
            }
        });
        mContext = getApplicationContext();
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("꾸미기")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("홈")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("운동량")));
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        mTabLayout.getTabAt(1).select();
        Toast.makeText(getApplicationContext(),"캐릭터는 당신을 따라 자동으로 운동을 시작합니다\n한번 흔들어 보세요!!!",Toast.LENGTH_LONG).show();
    }
    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        return tabView;
    }


    public void toggleExercise(View v){
        characterImageView= findViewById(R.id.characterImage);
        if(changeImageRunnable.stop){
            changeImageRunnable.stop=false;
            changeImageRunnable.run();
        }else{
            characterImageView.setImageDrawable(getDrawable(R.drawable.character_default));
            changeImageRunnable.stop();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
    class changeImageRunnableClass implements Runnable{
        int updateInterval = 1000; //=one second
        boolean is_before=true;
        public boolean stop = true;

        public void stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            if(!stop) {
                characterImageView = findViewById(R.id.characterImage);
                if(is_before){
                    characterImageView.setImageDrawable(getDrawable(R.drawable.character_after));
                }else{
                    characterImageView.setImageDrawable(getDrawable(R.drawable.character_before));
                }
                is_before=!is_before;
                handler.postDelayed(this, 1000);
            }
        }
    }
    public void shareWith(View v){
        Toast.makeText(getApplicationContext(),"현재 모습을 캡쳐해서 공유합니다!!!!!",Toast.LENGTH_LONG).show();
    }
    public void showDialog(View v){
        CustomDIalog dialog=new CustomDIalog(this);
        dialog.show();
    }
    public void showDecorateBar(View v){
        TableLayout tableLayout=findViewById(R.id.tableLayout);
        if(tableLayout.getVisibility()==View.VISIBLE) {
            tableLayout.setVisibility(View.INVISIBLE);
        }
        else{
            tableLayout.setVisibility(View.VISIBLE);
        }
    }
}