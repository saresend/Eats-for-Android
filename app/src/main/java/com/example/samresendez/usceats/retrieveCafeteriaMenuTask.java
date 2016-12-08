package com.example.samresendez.usceats;

import android.os.AsyncTask;
import android.util.Log;
import android.util.StringBuilderPrinter;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SamResendez on 11/2/16.
 */

public class retrieveCafeteriaMenuTask extends AsyncTask {

    menuAdapter adapter;

    ArrayList<String> evk;
    ArrayList<String> parkside;
    ArrayList<String> cafe;

    ArrayList<String> evkList = new ArrayList<>();
    ArrayList<String> parksideList = new ArrayList<>();
    ArrayList<String> cafeList = new ArrayList<>();

    @Override
    protected Object doInBackground(Object[] objects) {

        getURL();



        return null;
    }

    private URL getURL() {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DATE);

        int hourOfDay = now.get(Calendar.HOUR);

        Log.e("Hour, Weekday, month:",Integer.toString(hourOfDay)+Integer.toString(dayOfWeek)+Integer.toString(month));


        Map<Integer,String> weekMap = new HashMap<>();
        weekMap.put(1,"Sun");
        weekMap.put(2,"Mon");
        weekMap.put(3,"Tue");
        weekMap.put(4,"Wed");
        weekMap.put(5,"Thu");
        weekMap.put(6,"Fri");
        weekMap.put(7,"Sat");

        Map<Integer,String> dateMap = new HashMap<>();
        dateMap.put(1,"Jan");
        dateMap.put(2,"Feb");
        dateMap.put(3,"Mar");
        dateMap.put(4,"Apr");
        dateMap.put(5,"May");
        dateMap.put(6,"Jun");
        dateMap.put(7,"Jul");
        dateMap.put(8,"Aug");
        dateMap.put(9,"Sep");
        dateMap.put(10,"Oct");
        dateMap.put(11,"Nov");
        dateMap.put(12,"Dec");

        String urlBase = "https://uscdata.org/eats/v1/menus?where=%7B%22date%22%3A%20%22";
        urlBase = urlBase + weekMap.get(dayOfWeek);
        urlBase = urlBase + "%2C%20";
        urlBase = urlBase + day;
        urlBase = urlBase  + "%20" + dateMap.get(month);
        urlBase = urlBase + "%20" + year;
        urlBase = urlBase + "%2000%3A00%3A00%20GMT%22%7D";

        //Just here for testing, pls remove for actual functionality
        urlBase = "https://uscdata.org/eats/v1/menus?where=%7B%22date%22%3A%20%22Mon%2C%2025%20Apr%202016%2000%3A00%3A00%20GMT%22%7D";

        Log.e("Here is the URL:",urlBase);


        try {
            URL url = new URL(urlBase);
            //Get JSON object from URL

            InputStream in = url.openStream();
            String jsonString = IOUtils.toString(in,"UTF-8");

            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray arr = jsonResponse.getJSONArray("_items"); //Split up into different cafeterias (I think?)

            int mealNumber = 0;

            if(hourOfDay > 10 && hourOfDay < 16) {
                mealNumber = 1;
            }
            else{
                mealNumber = 2;
            }

            Log.e("Size of arr: ",Integer.toString(arr.length()));
            for(int i = 0; i < arr.length(); i ++ ) {
                Log.e("Inside Loop","We are looping. Lol");

                JSONArray mealObject = arr.getJSONObject(i).getJSONArray("meals").getJSONObject(mealNumber).getJSONArray("meal_sections");
                JSONArray foodArray = mealObject.getJSONObject(0).getJSONArray("section_items");
                JSONObject object = foodArray.getJSONObject(0);

                for(int j =0; j < foodArray.length(); j++) {

                    Log.e("Heres the item",foodArray.getJSONObject(j).toString());
                    JSONObject obj = foodArray.getJSONObject(j);
                    if(i==0) {
                        evkList.add(obj.getString("food_name"));
                    }
                    else if(i==1) {
                        parksideList.add(obj.getString("food_name"));

                    }
                    else if(j==2) {
                        cafeList.add(obj.getString("food_name"));
                    }
                }
            }
            Log.e("We're here","Printing out lists");
            Log.e("EVK: ", evkList.toString());
            Log.e("Parkside: ", parksideList.toString());
            Log.e("cafe 84: ",cafeList.toString());

        }
        catch(Exception e) {
            Log.e("Error:",e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        //super.onPostExecute(o);
        evk = evkList;
        cafe = cafeList;
        parkside = parksideList;
        adapter.dataSet = parkside;


    }
}
