package com.samuelvialle.recyclerrssreader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PullParseXML {
    private int READ_TIME_OUT = 12000;
    private int CONNECT_TIME_OUT = 15000;
    private String feedUrl = null;
    private XmlPullParserFactory xmlFactoryObject;
    public volatile boolean parsingComplete = true;

    //TODO çektiğimiz haberlere ait verileri nesneler halinde tutacağımız listemiz
    private ArrayList<NewsModel> postList = new ArrayList<>();

    public PullParseXML(String url) {
        this.feedUrl = url;
    }

    public ArrayList<NewsModel> getPostList() {
        return postList;
    }

    //TODO XML'i ayrıştırıp gelen veriyi kaydeden metodumuz
    public void parseXML(XmlPullParser xmlPullParser) {
        int event;
        String text = null;
        try {

            NewsModel newsItem = new NewsModel();
            event = xmlPullParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name = xmlPullParser.getName();

                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (name.equalsIgnoreCase("item")) {
                            newsItem = new NewsModel();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = xmlPullParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "item":
                                postList.add(newsItem);
                                break;
                            case "title":
                                newsItem.setTitle(text.replace("&#39;", "'"));
                                break;
                            case "link":
                                newsItem.setLink(text);
                                break;
                            case "image":
                                newsItem.setImageUrl(text);
                                break;

                            default:
                                break;
                        }
                        break;
                }
                event = xmlPullParser.next();
            }

            parsingComplete = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO XML'i indiren metodumuz
    public void downloadXML() {
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(feedUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(READ_TIME_OUT);
                httpURLConnection.setConnectTimeout(CONNECT_TIME_OUT);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();

                xmlFactoryObject = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlFactoryObject.newPullParser();

                xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                xmlPullParser.setInput(inputStream, null);

                parseXML(xmlPullParser);
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}