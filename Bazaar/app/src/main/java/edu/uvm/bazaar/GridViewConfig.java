package edu.uvm.bazaar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Emma on 11/27/2016.
 */

public class GridViewConfig {
        private static ArrayList<String> resim_list=new ArrayList<String>();

        public static ArrayList<String> getResim_list() {
            return resim_list;

        }

        public static void setResim_list(ArrayList<String> resim_list) {
            GridViewConfig.resim_list = resim_list;
        }
       // RequestQueue iQueue = Volley.newRequestQueue(GridViewConfig.this);
//        public static void addImageUrls(){
//
//           getURLs();
//
//        }
//    RequestQueue iQueue = Volley.newRequestQueue(GridViewConfig.this);
////    public static void getURLs(){
//        String url = "http://www.uvm.edu/~ertait/gameList.php";
//
//        JsonArrayRequest imageRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            JSONObject jsonResponse = null;
//                            if (response.length() > 0) {
//
//                                for (int i = 0; i<response.length(); i++){
//                                    jsonResponse = response.getJSONObject(i);
//                                    resim_list.add(jsonResponse.getString("fldLogo"));
//                                }
////                                jsonResponse = response.getJSONObject(0);
////                                AlertDialog.Builder rspns = new AlertDialog.Builder(mContext);
////                                rspns.setMessage(jsonResponse.toString()).setNegativeButton("Retry", null).create().show();
//
//                            }
//                        }catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//
////            //  Here you have to specify your image url path
////            resim_list.add("http://www......../image1.png");
////
////            resim_list.add("http://www......../image2.png");
////
////            resim_list.add("http://www......../image3.png");
////
////            resim_list.add("http://www......../image4.png");
////
////            resim_list.add("http://www......../image5.png");
////            resim_list.add("http://www......../image6.png");
//        iQueue.add(imageRequest);
    };



//}
