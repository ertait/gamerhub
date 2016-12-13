package edu.uvm.bazaar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emma on 12/13/2016.
 */

public class subscribeRequest extends StringRequest{
    private static final String THREAD_REQUEST_URL = "http://www.uvm.edu/~ertait/subscribethread.php";
    private Map<String, String> params;
    public subscribeRequest(String userId, String title, Response.Listener<String> listener){
        super(RegisterRequest.Method.POST,THREAD_REQUEST_URL,listener,null);//error listener is null
        params = new HashMap<>();
//        params.put("gameId",gameId);
        params.put("userId",userId);
        params.put("threadName",title);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
