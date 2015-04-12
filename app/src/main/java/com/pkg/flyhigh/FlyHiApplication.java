package com.pkg.flyhigh;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseTwitterUtils;

/**
 * Created by Altair on 12-04-2015.
 */
public class FlyHiApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ozSFGg467aFtaFzm752FGeXQ1SaqjCozALAM5dix",
                "sOL6dgLtTPHCvzNObOKPpyAHttCGsIGDfUtCJHWi");

        ParseTwitterUtils.initialize("9ITsqv72M4JYWVH7T0q4O4aFO",
                "n3ofza8FpKONaaQxMycD8gkAM20vAcMczy6n2fyYlWNzv2ysmf");
    }
}
