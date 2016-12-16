package com.example.samresendez.usceats;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
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

    String cafeName;
    menuAdapter adapter;
    ArrayList<ArrayList<String>> mArrayList;


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
        int a = now.get(Calendar.AM_PM);

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
        dateMap.put(0,"Jan");
        dateMap.put(1,"Feb");
        dateMap.put(2,"Mar");
        dateMap.put(3,"Apr");
        dateMap.put(4,"May");
        dateMap.put(5,"Jun");
        dateMap.put(6,"Jul");
        dateMap.put(7,"Aug");
        dateMap.put(8,"Sep");
        dateMap.put(9,"Oct");
        dateMap.put(10,"Nov");
        dateMap.put(11,"Dec");

        String urlBase = "https://uscdata.org/eats/v1/menus?where=%7B%22date%22%3A%20%22";
        urlBase = urlBase + weekMap.get(dayOfWeek);
        Log.e("This is the day",Integer.toString(dayOfWeek));
        urlBase = urlBase + "%2C%20";
        urlBase = urlBase + day;
        urlBase = urlBase  + "%20" + dateMap.get(month);
        Log.e("This is the month",Integer.toString(month));
        urlBase = urlBase + "%20" + year;
        urlBase = urlBase + "%2000%3A00%3A00%20GMT%22%7D";

        Log.e("Here is the URL:",urlBase);


        try {
            URL url = new URL(urlBase);
            //Get JSON object from URL

            InputStream in = url.openStream();
            String jsonString = IOUtils.toString(in,"UTF-8");

            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray arr = jsonResponse.getJSONArray("_items"); //Split up into different cafeterias (I think?)

            for(int i = 0; i < arr.length(); i++) {
                Log.e("Here's the raw array",arr.get(i).toString());
            }


            int mealNumber = 0;
            if(a == Calendar.AM) {
                //Sets the time if its in the morning
                if(hourOfDay > 11) {
                    mealNumber = 1;
                }
            }
            else if(hourOfDay > 11 && hourOfDay <= 16) {
                  mealNumber = 1;

            }
            else {
                mealNumber = 2;
            }
            Log.e("Size of arr: ",Integer.toString(arr.length()));
            for(int i = 0; i < arr.length(); i ++ ) {
                if(arr.getJSONObject(i).getJSONArray("meals").length() != 0) {
                    JSONArray mealObject = arr.getJSONObject(i).getJSONArray("meals").getJSONObject(mealNumber).getJSONArray("meal_sections");
                    Log.e("Testing for bugs", "Before or after?");
                    for (int k = 0; k < mealObject.length(); k++) {
                        JSONArray foodArray = mealObject.getJSONObject(k).getJSONArray("section_items");
                        for (int j = 0; j < foodArray.length(); j++) {

                            Log.e("Heres the item", foodArray.getJSONObject(j).toString());
                            Log.e("At this index", Integer.toString(i));
                            JSONObject obj = foodArray.getJSONObject(j);
                            if (i == 0) {
                                parksideList.add(obj.getString("food_name"));
                            } else if (i == 1) {
                                cafeList.add(obj.getString("food_name"));

                            } else if (i == 2) {
                                evkList.add(obj.getString("food_name"));
                            }
                        }
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
        mArrayList.add(evkList);
        mArrayList.add(parksideList);
        mArrayList.add(cafeList);
        if(cafeName == "EVK") {
            Log.e("Here we are","EVK is called");
            adapter.dataSet = evkList;
            adapter.notifyDataSetChanged();
        }
        else if(cafeName == "Cafe 84") {
            adapter.dataSet = cafeList;
            adapter.notifyDataSetChanged();
        }
        else if(cafeName == "Parkside") {
            adapter.dataSet = parksideList;
            adapter.notifyDataSetChanged();
        }
    }
}
