package com.tasomaniac.openwith.settings;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tasomaniac.openwith.R;
import com.tasomaniac.openwith.data.Analytics;
import com.tasomaniac.openwith.data.Injector;
import com.tasomaniac.openwith.data.prefs.BooleanPreference;
import com.tasomaniac.openwith.data.prefs.TutorialShown;
import com.tasomaniac.openwith.intro.IntroActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;

    @Inject @TutorialShown BooleanPreference tutorialShown;
    @Inject Analytics analytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Injector.obtain(this).inject(this);
        if (!tutorialShown.get()) {
            startActivity(IntroActivity.newIntent(this, true));
            tutorialShown.set(true);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        analytics.sendScreenView("Settings");

        toolbar.setNavigationIcon(R.drawable.ic_action_done);
        toolbar.setNavigationContentDescription(R.string.done);
        setSupportActionBar(toolbar);

        collapsingToolbar.setTitle(getTitle());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, SettingsFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
