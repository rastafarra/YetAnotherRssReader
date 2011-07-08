package ru.RssReader;

/**
 * Created by IntelliJ IDEA.
 * User: Denis Smolyar aka Rastafarra
 * Date: 7/7/11
 * Time: 4:03 PM
 */


import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

public class Loader {
    public ArrayList <Entry> load (String rssFeed)
            throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        HttpClient client = new DefaultHttpClient ();
        HttpGet request = new HttpGet (rssFeed);

        HttpResponse response = client.execute (request);
        InputStream stream = response.getEntity ().getContent ();

        return parse (stream);
    }



    private ArrayList <Entry> parse (InputStream stream)
            throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance ().newDocumentBuilder ();
        Document doc = docBuilder.parse (stream);

        XPath xpath = XPathFactory.newInstance ().newXPath ();
        XPathExpression expr = xpath.compile ("/rss/channel/item");

        Object obj = expr.evaluate (doc, XPathConstants.NODESET);

        NodeList nList = (NodeList) obj;

        ArrayList <Entry> entryList = new ArrayList <Entry> ();

        for (int i = 0; i < nList.getLength (); i++) {
            String title = (String) xpath.evaluate ("title", nList.item (i), XPathConstants.STRING);
            String link = (String) xpath.evaluate ("link", nList.item (i), XPathConstants.STRING);
            String description = (String) xpath.evaluate ("description", nList.item (i), XPathConstants.STRING);
            String pubDate = (String) xpath.evaluate ("pubDate", nList.item (i), XPathConstants.STRING);

            Entry entry = new Entry ();
            entry.setTitle (title);
            entry.setDescription (description);
            entry.setLink (link);
            entry.setPubDate (pubDate);

            entryList.add (entry);
        }

        return entryList;
    }
}
