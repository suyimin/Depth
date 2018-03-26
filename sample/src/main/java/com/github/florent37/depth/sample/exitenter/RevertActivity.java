package com.github.florent37.depth.sample.exitenter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.florent37.depth.Depth;
import com.github.florent37.depth.DepthProvider;
import com.github.florent37.depth.sample.R;

public class RevertActivity extends AppCompatActivity implements FragmentCallback {

    boolean opened = false;
    private Depth depth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        depth = DepthProvider.getDepth(this);

        setContentView(R.layout.depth_activity_revert);
        //makeAppFullscreen();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, Fragment1.newInstance(false)).commit();
        }

        depth.setFragmentContainer(R.id.fragment_container);
    }

    private void makeAppFullscreen() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void openResetFragment(final Fragment fragment) {
        if(opened) {
            depth.animate()
                    .revert(fragment)
                    .start();
        } else {
            depth.animate()
                    .reduce(fragment)
                    .start();
        }

        opened = !opened;
    }

}
