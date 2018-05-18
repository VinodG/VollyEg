package vinod.vollyeg;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public   interface Api
{
    public String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<Hero>> getHeros();
    @GET("marvel")
    Call<Object> getHerosObject();

}
