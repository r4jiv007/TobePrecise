package com.example.rajiv.precise.activities.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONObject;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajiv on 15/2/16.
 */
public class Network {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {

                Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                int exitValue = ipProcess.waitFor();
                return (exitValue == 0);

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }

        }

    }


    public static String fetchProduct() throws IOException {


//        String temp = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
//                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
//                + "<soap:Body>"
//                + "<FeaturedProducts xmlns=\"http://tempuri.org/\">"
//                + "<ProductCount>%s</ProductCount>" + "</FeaturedProducts>" + "</soap:Body>"
//                + "</soap:Envelope>";
        String NAMESPACE = "http://tempuri.org/";
        String SOAPAction = "http://tempuri.org/FeaturedProducts";
        String addPropertyString = "ProductCount";
        String usecaseString = "FeaturedProducts";
        String URL = "http://www.joyalukkas.com/gift-card/gift-card-ws.asmx?op=FeaturedProducts";
        String result = null;
        final SoapObject request = new SoapObject(NAMESPACE,
                usecaseString);
        PropertyInfo productCount = new PropertyInfo();
        productCount.setName("ProductCount");
        productCount.setValue(100);
        productCount.setType(int.class);
        request.addProperty(productCount);
        final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        final HttpTransportSE androidHttpTransport = new HttpTransportSE(
                URL);
        androidHttpTransport.debug = true;

        List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();

        headerList.add(new HeaderProperty("Content-Type", "text/xml; charset=utf-8"));

        try {
            androidHttpTransport.call(SOAPAction, envelope,headerList);
            SoapObject resultSoapPrimitive;
            resultSoapPrimitive = (SoapObject) envelope.getResponse();
            if (resultSoapPrimitive != null) {
                result =androidHttpTransport.responseDump;
            } else {
                Log.d("response", "result json is NULL!!! ");

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("static", "Exception in making call to server");
        }
        return result;
//        String result = null;
//        InputStream resStream=null;
//        try {
//            URL oURL = new URL("http://www.joyalukkas.com/gift-card/gift-card-ws.asmx?op=FeaturedProducts");
//            HttpURLConnection con = (HttpURLConnection) oURL.openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-type", "text/xml; charset=utf-8");
//            con.setRequestProperty("SOAPAction",
//                    "http://tempuri.org/FeaturedProducts");
//            con.setReadTimeout(10000 /* milliseconds */);
//            con.setConnectTimeout(15000 /* milliseconds */);
//
//
//            String reqXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
//                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
//                    "<soap:Body>" +
//                    "<FeaturedProducts xmlns=\"http://tempuri.org/\">" +
//                    "<ProductCount>int</ProductCount>" +
//                    "</FeaturedProducts>" +
//                    " </soap:Body>" +
//                    "</soap:Envelope>";
//
//            OutputStream reqStream = con.getOutputStream();
//            reqStream.write(reqXml.getBytes());
//            resStream = con.getInputStream();
//            String contentAsString = readIt(resStream, 20480);
//            return contentAsString;
//        }finally {
//            if(resStream!=null){
//                resStream.close();
//            }
//        }

    }

    public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
