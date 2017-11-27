package utils;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Hytham on 2/15/2017.
 */

public class Prefs {
    SharedPreferences preferences;

    public Prefs (Activity activity){
        preferences = activity.getPreferences(Activity.MODE_PRIVATE);

    }
    public void setCity(String city){
        preferences.edit().putString("city" , city).commit();
    }
    //if user has not chosen a city, return default
    public String getCity(){
        return preferences.getString("city" , "Cairo");
    }
}
