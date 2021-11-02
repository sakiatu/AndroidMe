package com.beecoder.androidme.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.beecoder.androidme.R;
import com.beecoder.androidme.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        if (savedInstanceState == null) {
            int headIndex = getIntent().getIntExtra("headIndex", 0);
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            int legsIndex = getIntent().getIntExtra("legsIndex", 0);
            BodyPartFragment headFragment = new BodyPartFragment(AndroidImageAssets.getHeads(), headIndex);
            BodyPartFragment bodyFragment = new BodyPartFragment(AndroidImageAssets.getBodies(), bodyIndex);
            BodyPartFragment legsFragment = new BodyPartFragment(AndroidImageAssets.getLegs(), legsIndex);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();
            fragmentManager.beginTransaction()
                    .add(R.id.legs_container, legsFragment)
                    .commit();
        }
    }
}