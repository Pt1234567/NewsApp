package com.example.news;
import com.example.news.model.newsApiResponse;
import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.news.model.newsApiResponse;
public class RequestManager {

    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNewsHeadlines(onFetchDataListener listener,String category,String query){
        CallNewsApi callNewsApi=retrofit.create(CallNewsApi.class);
        Call<newsApiResponse> call=callNewsApi.CallHeadlines("in",category,query,context.getString(R.string.api_key));
        
        try {
            call.enqueue(new Callback<newsApiResponse>() {
                @Override
                public void onResponse(Call<newsApiResponse> call, Response<newsApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }

                    listener.onFetchData(response.body().getArticles(),response.message());
                }

                @Override
                public void onFailure(Call<newsApiResponse> call, Throwable t) {
                listener.Error("Request Failed");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public RequestManager(Context context) {
        this.context = context;
    }


    public interface CallNewsApi{
        @GET("top-headlines")
        Call<newsApiResponse> CallHeadlines(
                @Query("country")  String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apikey") String api_key

        );
    }
}
