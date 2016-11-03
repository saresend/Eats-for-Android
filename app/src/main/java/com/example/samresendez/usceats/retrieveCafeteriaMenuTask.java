package com.example.samresendez.usceats;

import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SamResendez on 11/2/16.
 */

public class retrieveCafeteriaMenuTask extends AsyncTask {


    @Override
    protected Object doInBackground(Object[] objects) {





        return null;
    }

    private URL getURL() {

        Calendar now = Calendar.getInstance();
        int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        int month = now.get(Calendar.MONTH);
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


        try {
            URL url = new URL("http://google.com");
        }
        catch(Exception e) {
            Log.e("Error:",e.getMessage());
        }

        return null;
    }
}
