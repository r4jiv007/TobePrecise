package com.example.rajiv.precise.activities.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by rajiv on 15/2/16.
 */
public class HandleXml {
    private String Title = "Title";
    private String ImageUrl = "ImageUrl";
    private String ProductUrl = "ProductUrl";
    private String xmlString = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    public HandleXml(String xml) {
        this.xmlString = xml;
    }

    public String getTitle() {
        return Title;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getProductUrl() {
        return ProductUrl;
    }


    public ArrayList<Product> getProductList() throws Exception{
        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
        XmlPullParser myParser = xmlFactoryObject.newPullParser();

        int event;
        String text = null;
        InputStream stream = new ByteArrayInputStream( xmlString.getBytes() );
        myParser.setInput(stream, null);
        ArrayList<Product> mProductList = new ArrayList<>();

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();

                switch (event) {
                    case XmlPullParser.START_TAG:

                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("Title")) {
                            Title = text;
                        } else if (name.equals("ProductURL")) {
                            ProductUrl = text;
                        } else if (name.equals("ImageURL")) {
                            ImageUrl = text;
                        } else if (name.equals("JewelleryItem")) {
                            Product mProduct = new Product(Title, ImageUrl, ProductUrl);
                            mProductList.add(mProduct);
                        }

                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProductList;
    }
}