package model;

import model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedAPI {
    //https://www.youtube.com/watch?v=ZoFRW2luur8
    String BASE_URL = "https://www.reddit.com/r/";
    //aqui va el nombre del subreddit
    @GET("earthporn.rss")
    //llamada a la clase que obtiene el RSS feed de reddit
    Call<Feed> getFeed();
}
