package com.tasomaniac.openwith;

import android.app.Application;
import android.content.pm.PackageManager;

import com.tasomaniac.openwith.data.Analytics;
import com.tasomaniac.openwith.intro.IntroActivity;
import com.tasomaniac.openwith.preferred.PreferredAppsActivity;
import com.tasomaniac.openwith.rx.SchedulingStrategy;
import com.tasomaniac.openwith.settings.SettingsActivity;
import com.tasomaniac.openwith.settings.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {
        AppModule.class,
        AnalyticsModule.class
}, dependencies = {
        Application.class
})
public interface AppComponent {

    Application app();

    PackageManager packageManager();

    SchedulingStrategy schedulingStrategy();

    Analytics analytics();

    IconLoader iconLoader();

    OkHttpClient okHttpClient();

    void inject(IntroActivity activity);

    void inject(PreferredAppsActivity activity);

    void inject(SettingsActivity activity);

    void inject(SettingsFragment fragment);

}
