package ru.RssReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class RssReaderActivity extends Activity
{
    private String rssFeed = "http://feeds.feedburner.com/PlanetAndroidCom?format=xml";

    /** Called when the activity is first created. */
    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.main);

        Loader loader = new Loader ();
        try {
            loader.load (rssFeed);
        } catch (IOException e) {
            e.printStackTrace ();

            Log.e ("activity", e.getMessage ());
            throw new RuntimeException (e.getMessage (), e);
        }
    }
}
