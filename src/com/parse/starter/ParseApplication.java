package com.parse.starter;

import android.app.Application;

import android.os.StrictMode;
import android.util.Log;
import com.google.gson.Gson;
import com.parse.*;

import org.json.JSONObject;
import retrofit.RestAdapter;
import retrofit.client.*;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Body;

import java.util.ArrayList;
import java.util.List;

class BagOfPrimitives {
    private int value1 = 1;
    private String value2 = "abc";
    private int value3 = 3;
    BagOfPrimitives() {
        // no-args constructor
    }
}

interface ParseService {
    @Headers({
            "X-Parse-Application-Id: 5BkOOyiVimxDnJBvfLGXbyxwe1YnXhaqS6eVi4NS",
            "X-Parse-REST-API-Key: CUafShiNFs86vQjS0RE4ntedqH6tGZBGhzs1xKHJ",
            "Content-Type: application/json"
    })
    @POST("/classes/Gamore")
    String updateUser(@Body BagOfPrimitives obj);
}

public class ParseApplication extends Application {

  private String tag = "ParseApplication";

  @Override
  public void onCreate() {
    super.onCreate();

      try {
          if (android.os.Build.VERSION.SDK_INT > 9) {
              StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
              StrictMode.setThreadPolicy(policy);
          }

          Parse.initialize(this, "5BkOOyiVimxDnJBvfLGXbyxwe1YnXhaqS6eVi4NS", "iSveDzbZNyIOOmuh3iiXo0tr7Lqmf2xrcUktzvKA");

          ParseUser.enableAutomaticUser();
          ParseACL defaultACL = new ParseACL();

          // If you would like all objects to be private by default, remove this line.
          defaultACL.setPublicReadAccess(true);

          ParseACL.setDefaultACL(defaultACL, true);

          String url = "https://api.parse.com/1";

          RestAdapter restAdapter = new RestAdapter.Builder()
                  .setLogLevel(RestAdapter.LogLevel.FULL)
                  .setEndpoint(url)
                  .build();
          ParseService service = restAdapter.create(ParseService.class);
          service.updateUser(new BagOfPrimitives());

          ParseObject testObject = new ParseObject("TestObject");
          testObject.put("foo", "bar3");
          testObject.saveInBackground();

      } catch (Exception e) {

          e.printStackTrace();

      }


  }
}
