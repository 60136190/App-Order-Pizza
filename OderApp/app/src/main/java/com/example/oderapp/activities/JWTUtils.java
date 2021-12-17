package com.example.oderapp.activities;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class JWTUtils {
    public static void decodeJWT(String EncodeString ) throws Exception
    {
        String[] splitstr = EncodeString.split("\\.");
        Log.d("","Payload: "+getJson(splitstr[1]));
//        String[] b = new String[] {getJson(splitstr[1])};
//        Log.d("Hay dsj djs dsja akdso",b[1]);

        String c = getJson(splitstr[1]);
        String[] words = c.split(",");//tach chuoi dua tren khoang trang
        //su dung vong lap foreach de in cac element cua mang chuoi thu duoc
            System.out.println(words[1]);


    }
    public static String getJson(String EncodeString) throws UnsupportedEncodingException
    {
        byte[] decodebyte = Base64.decode(EncodeString,Base64.URL_SAFE);
        return new String(decodebyte,"UTF-8");
    }

}
