package vinod.vollyeg;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import org.xmlpull.v1.XmlSerializer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String tag_json_obj = "json_obj_req";

    String url = "https://api.androidhive.info/volley/person_object.json";
//    String url = "http://kwalitysfa.winitsoftware.com/services.asmx"; //https://stackoverflow.com/questions/43678487/post-xml-request-and-xml-response-volley-library
    String TAG = "JSON_OBJECT_REQUEST";
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");

        pDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                pDialog.hide();
            }
        })
        {
           /* *//*@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/xml");
                headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return headers;
            }*//*
            @Override
            public String getBodyContentType() {
                return "application/xml; charset=" +
                        getParamsEncoding();
            }

            @Override
            public byte[] getBody() {
                String postData ="<?xml version=\"1.0\" encoding=\"utf-8\"?> <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/"+""
                        +"envelope/\"> "+"  <soap:Body>    <CheckLogin xmlns=\"http://tempuri.org/\">  "+
                        "   <UserName>DXB1</UserName>       <Password>password</Password>       <GCMKey>0</GCMKey> "+
    "</CheckLogin>   </soap:Body> </soap:Envelope>";
                        // TODO get your final output
                try {
                    return postData == null ? null :
                            postData.getBytes(getParamsEncoding());
                } catch (UnsupportedEncodingException uee) {
                    // TODO consider if some other action should be taken
                    return null;
                }
            }*/

        };

        AppController.getInstance().addToRequest(jsonObjectRequest,tag_json_obj);

    }
}
