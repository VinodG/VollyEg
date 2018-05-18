package vinod.vollyeg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrifitActivity extends AppCompatActivity implements Callback<Object> {

    ListView lv ;
    ArrayAdapter<String > adapter = null;
    ArrayList<String> al  =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrifit);
        lv= (ListView)findViewById(R.id.lv);
        //creating retrofit object
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        //Creating the api interface
        Api api= retrofit.create(Api.class);

        Call<List<Hero>> call =api.getHeros();
        Call<Object> ca= api.getHerosObject();
        ca.enqueue(this);
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList =response.body();
                Log.d("Thread Name : " , Thread.currentThread().getId()+"");
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(RetrifitActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });




    }



    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
         if(response !=null)
         {
             if(response.body() instanceof List)
             {
                 Object   object= response.body();
                 List<Object> list = (List<Object>) object;
                 if( list!=null  &&  list.size() >0 && (list.get(0) instanceof LinkedTreeMap))
                 {
                     Log.d("Thread Name : " , Thread.currentThread().getId()+"");
                     for(int i=0;i< list.size();i++)
                     {
                         al.add(list.get(i).toString());
                     }
                     adapter = new ArrayAdapter<String>(RetrifitActivity.this,android.R.layout.simple_list_item_1,al);
                     lv.setAdapter(adapter);
                 }
             }
             else
             {

             }

         }


    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        Toast.makeText(RetrifitActivity.this,t.getMessage().toString(),Toast.LENGTH_LONG).show();
        Log.d("onFailure ","onFailure");

    }
}
