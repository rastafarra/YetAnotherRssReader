package ru.RssReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Denis Smolyar aka Rastafarra
 * Date: 7/7/11
 * Time: 4:03 PM
 */

public class Loader {
    public ArrayList <Entry> load (String rssFeed) throws IOException {
        HttpClient client = new DefaultHttpClient ();
        HttpGet request = new HttpGet (rssFeed);

        HttpResponse response = client.execute (request);
        InputStream stream = response.getEntity ().getContent ();

        return parse (stream);
    }



    private ArrayList <Entry> parse (InputStream stream) {
        return null;
    }
}
