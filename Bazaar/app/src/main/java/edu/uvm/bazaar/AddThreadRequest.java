package edu.uvm.bazaar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emma on 12/12/2016.
 */

public class AddThreadRequest extends StringRequest{
    private static final String THREAD_REQUEST_URL = "http://www.uvm.edu/~ertait/createGameThreadTable.php";
    private Map<String, String> params;
    public AddThreadRequest(String gameId, String username, String title, Response.Listener<String> listener){
        super(RegisterRequest.Method.POST,THREAD_REQUEST_URL,listener,null);//error listener is null
        params = new HashMap<>();
        params.put("gameId",gameId);
        params.put("userId",username);
        params.put("title",title);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

