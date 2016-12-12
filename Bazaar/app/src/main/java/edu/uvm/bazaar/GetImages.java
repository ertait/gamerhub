package edu.uvm.bazaar;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by Emma on 12/6/2016.
 */

public class GetImages extends JsonArrayRequest {
    private static final String GAME_URL = "http://www.uvm.edu/~ertait/gameList.php";

    public GetImages(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    //public String GAME_URL = "http://www.uvm.edu/~ertait/gameList.php";
//    public GetImages(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
//        super(Method.GET, GAME_URL, listener, null);
//    }
}
