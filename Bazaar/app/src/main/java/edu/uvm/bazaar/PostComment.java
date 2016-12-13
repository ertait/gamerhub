package edu.uvm.bazaar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emma on 12/12/2016.
 */

public class PostComment extends StringRequest{
    private static final String COMMENT_REQUEST_URL = "http://www.uvm.edu/~ertait/postComment.php";
    private Map<String, String> params;
    public PostComment(String gameId, String username, String title, String text, Response.Listener<String> listener){
        super(RegisterRequest.Method.POST,COMMENT_REQUEST_URL,listener,null);//error listener is null
        params = new HashMap<>();
        params.put("gameId",gameId);
        params.put("userId",username);
        params.put("threadName",title);
        params.put("text",text);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

