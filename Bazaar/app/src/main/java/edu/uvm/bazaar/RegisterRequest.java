package edu.uvm.bazaar;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emma on 11/23/2016.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://www.uvm.edu/~ertait/Register.php";
    private Map<String, String> params;
    public RegisterRequest(String name, String username, String password, Response.Listener<String> listener){
        super(RegisterRequest.Method.POST,REGISTER_REQUEST_URL,listener,null);//error listener is null
        params = new HashMap<>();
        params.put("name",name);
        params.put("username",username);
        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
