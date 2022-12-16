package app.b4a.gtp3speaker;

import android.app.Application;

import com.parse.Parse;

public class ParseInit extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("3QPRJa55ZM2D050qwIZ04acjLvizrVIVuduo1V52")
                // if defined
                .clientKey("7eZ98HADQEgFAuHPP16J0Vo956ZScaJS7sKNVrhg")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
