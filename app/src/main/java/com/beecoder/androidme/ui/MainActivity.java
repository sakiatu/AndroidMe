package com.beecoder.androidme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.beecoder.androidme.R;
import com.beecoder.androidme.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private int headIndex;
    private int bodyIndex;
    private int legsIndex;

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        twoPane = findViewById(R.id.android_me_linear_layout) != null;
        Button nextButton = findViewById(R.id.next_button);

        if (twoPane) {
            nextButton.setVisibility(View.GONE);

            GridView gridView = findViewById(R.id.master_list_grid_view);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {
                BodyPartFragment headFragment = new BodyPartFragment(AndroidImageAssets.getHeads(), 0);
                BodyPartFragment bodyFragment = new BodyPartFragment(AndroidImageAssets.getBodies(), 0);
                BodyPartFragment legsFragment = new BodyPartFragment(AndroidImageAssets.getLegs(), 0);

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
        } else {
            nextButton.setOnClickListener(v -> openAndroidMeActivity());
        }


    }

    private void openAndroidMeActivity() {
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legsIndex", legsIndex);

        Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onImageSelected(int position) {
        int bodyPartNumber = position / 12;

        int listIndex = position - bodyPartNumber * 12;

        if (twoPane) {
            BodyPartFragment fragment;
            switch (bodyPartNumber) {
                case 0:
                    fragment = new BodyPartFragment(AndroidImageAssets.getHeads(), listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, fragment).commit();
                    break;
                case 1:
                    fragment = new BodyPartFragment(AndroidImageAssets.getBodies(), listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, fragment).commit();
                    break;
                case 2:
                    fragment = new BodyPartFragment(AndroidImageAssets.getLegs(), listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legs_container, fragment).commit();
                    break;
                default:
                    break;
            }
        } else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legsIndex = listIndex;
                    break;
                default:
                    break;
            }
        }
    }
}