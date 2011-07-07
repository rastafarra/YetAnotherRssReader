package ru.RssReader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by IntelliJ IDEA.
 * User: Denis Smolyar aka Rastafarra
 * Date: 7/7/11
 * Time: 4:57 PM
 */

public class XmlHandler extends DefaultHandler {
    Boolean currentElement = false;
    String currentValue = null;

    private static Entry sitesList = null;



    public static Entry getSitesList() {
        return sitesList;
    }



    public static void setSitesList(Entry sitesList) {
        XmlHandler.sitesList = sitesList;
    }



    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        currentElement = true;

        if (localName.equals("maintag"))
        {
            /** Start */
            sitesList = new Entry ();
        } else if (localName.equals("website")) {
            /** Get attribute value */
            String attr = attributes.getValue("title");
            sitesList.setTitle (attr);
        }

    }



    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        currentElement = false;

        /** set value */
        if (localName.equalsIgnoreCase("title"))
            sitesList.setTitle (currentValue);
        else if (localName.equalsIgnoreCase("description"))
            sitesList.setDescription (currentValue);

    }



    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (currentElement) {
            currentValue = new String(ch, start, length);
            currentElement = false;
        }

    }
}
