package net.sparkeek.instatest.rest;

import net.sparkeek.instatest.models.recent.InstaUserRecent;
import net.sparkeek.instatest.models.self.InstaUserSelf;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by adrienrx on 30/09/2016.
 */

public interface InstaSelfApiEndpointInterface {
    @GET("users/self/")
    Observable<InstaUserSelf> getUser(@Query("access_token") String token);

    @GET("users/self/media/recent/")
    Observable<InstaUserRecent> getRecentPost(@Query("access_token") String token);
}
