package vinod.vollyeg;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Vinod.Kumar on 11-01-2018.
 */

public class AppController extends Application
{
    RequestQueue mRequestQueue;
    private static AppController mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    private RequestQueue getRequestQueue()
    {
        if(mRequestQueue==null)
        {
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return  mRequestQueue;
    }
    public void addToRequest(Request request,String tag)
    {
        getRequestQueue().add(request);
    }
    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
