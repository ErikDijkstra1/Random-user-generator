package com.example.randomusergenerator;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PersonManager {
    private static Context appContext;
    private static ArrayList<Person> personList = new ArrayList<>();
    private static RequestQueue queue;

    private PersonManager() {
    }

    public static void setAppContext(Context context) {
        appContext = context;
    }

    private static void createPeople() {    //TODO api create people
        for (int i = 0; i < 10; i++) {
//            personList.add(new Person(R.drawable.erikpic, "Erik", i + "", "Dutch", "Dutch", "04-10-2005", "0123456789", "Parc Tichelt 9", "sigma male", "erik_dijkstra@ziggo.nl"));
        }
    }

    private static void createPeople(int size) {
        queue =Volley.newRequestQueue(appContext);
        final String url = "https://randomuser.me/api/?results=500";

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            JSONArray array = jsonObject.getJSONArray("results");

                            for (int i = 0; i < array.length(); i++) {
                                Person person = new Person();

                                JSONObject result = (JSONObject) array.get(i);

                                JSONObject name = result.getJSONObject("name");
                                person.setFirstName(name.getString("first"));
                                person.setLastName(name.getString("last"));

                                JSONObject location = result.getJSONObject("location");
                                person.setCountry(location.getString("country"));

                                person.setNationality(result.getString("nat"));

                                JSONObject picture = result.getJSONObject("picture");
                                person.setPicture(picture.getString("medium"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println(volleyError.getLocalizedMessage());
                    }
                }
        );
        queue.add(request);
    }

    public static ArrayList<Person> getPeople() {
        if (personList.isEmpty()) {
            createPeople(5);
        }
        return personList;
    }

    public static Person getPerson(int id) {
        if (personList.isEmpty()) {
            createPeople(5);
        }
        return personList.get(id);
    }

}
