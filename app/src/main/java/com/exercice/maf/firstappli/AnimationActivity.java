package com.exercice.maf.firstappli;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

public class AnimationActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream stream = null;
        try {
            stream = getAssets().open("piggy.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GifWebView view = new GifWebView(this, "helloAndroid45.gif");

        setContentView(view);
    }
}
//TODO trouver une methose pour afficher les GIFs
//https://oyant.com/android/ajout-dune-image-dans-le-menu-contextuel-de-lexemple.html