package me.tremor.Airglow_user.service;

import me.tremor.Airglow_user.models.Login;
import me.tremor.Airglow_user.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
/**
 * Interface representing the HTTP requests
 */
public interface UserClient {
    @POST("login")
    Call<User> login(@Body Login login);
    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization")String authToken);
}
