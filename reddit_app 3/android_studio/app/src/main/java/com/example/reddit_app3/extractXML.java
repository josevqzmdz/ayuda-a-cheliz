package com.example.reddit_app3;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class extractXML {

    private static final String TAG = "ExtractXML";

    private String tag;
    private String xml;

    public extractXML(String xml, String tag){
        this.tag = tag;
        this.xml = xml;
    }

    public List<String> start(){
        List<String> result = new ArrayList<>();

        // toma el tag de html y toma el string literal de dicho tag
        String[] splitXML = xml.split(tag + "\"");
        // cuenta el numero de tags que hav
        int count = splitXML.length;

        for (int i=1; i < count; i++){
            String temp = splitXML[i];
            int index = temp.indexOf("\"");
            //para debbugear
            Log.d(TAG, "start: index: "+ index);
            Log.d(TAG, "start: extracted: "+ temp);

            temp = temp.substring(0, index);
            Log.d(TAG, "start: snipped: " +temp);
            result.add(temp);
        }

        return result;
    }
}
