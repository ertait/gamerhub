package edu.uvm.bazaar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.sql.Array;

import edu.uvm.loginregister.R;

public class ImageAdapter extends BaseAdapter{
//    private Context context;
//
//    public ImageAdapter(Context context) {
//        super();
//        this.context = context;
//
//        GridViewConfig.addImageUrls();
//    }
//
//
//    public int getCount() {
//        return GridViewConfig.getResim_list().size();
//
//    }
//
//    public Object getItem(int position) {
//
//        return GridViewConfig.getResim_list().get(position);
//    }
//
//
//    public long getItemId(int position) {
//
//
//
//        return position;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//        if(convertView==null)
//        {
//            imageView=new ImageView(context);
//            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(5,5,5,5);
//        }else{
//            imageView=(ImageView)convertView;
//
//        }
//        imageView.setImageDrawable(LoadImageFromURL(GridViewConfig.
//                getResim_list().get(position)));
//        return imageView;
//    }
//
//    private Drawable LoadImageFromURL(String url)
//
//    {
//        try
//        {
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "src");
//            return d;
//        }catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
//    }
//}

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
        //RequestQueue rqueue = Volley.newRequestQueue(ImageAdapter.this);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
//    RequestQueue lqueue = Volley.newRequestQueue(this);
//    String url = "http://www.uvm.edu/~ertait/gameList.php";
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
//        final int[] mThumbIds = new int[]{};
//        RequestQueue imageQueue = Volley.newRequestQueue(mContext);
//        JsonArrayRequest imageRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            JSONObject jsonResponse = null;
//                            if (response.length() > 0) {
//                                for (int i; i<response.length(); i++){
//                                    mThumbIds[i] = response.getJSONObject(0);
//                                }
//                                jsonResponse = response.getJSONObject(0);
//                                AlertDialog.Builder rspns = new AlertDialog.Builder(mContext);
//                                rspns.setMessage(jsonResponse.toString()).setNegativeButton("Retry", null).create().show();
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

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
//    private Array getImages(){
//        RequestQueue imageRequest = Volley.newRequestQueue(mContext);
//
//        return null;
//    }
//     references to our images
    private Integer[] mThumbIds = {//new GetImages(url listener);
//            {
//
            R.drawable.league, R.drawable.wow,
            R.drawable.civ, R.drawable.nomansky,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7*/
   };
}


