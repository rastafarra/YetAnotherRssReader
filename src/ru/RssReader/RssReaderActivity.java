package ru.RssReader;

import android.app.Activity;
import android.os.Bundle;

public class RssReaderActivity extends Activity
{
    private String rssFeed = "http://feeds.feedburner.com/PlanetAndroidCom?format=xml";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
