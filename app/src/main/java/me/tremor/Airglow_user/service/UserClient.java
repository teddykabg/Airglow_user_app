package me.tremor.Airglow_user.service;

import me.tremor.Airglow_user.models.Event;
import me.tremor.Airglow_user.models.Events_id;
import me.tremor.Airglow_user.models.Login;
import me.tremor.Airglow_user.models.Registration_email;
import me.tremor.Airglow_user.models.Registration_phone;
import me.tremor.Airglow_user.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface representing the HTTP requests
 */
public interface UserClient {
    @POST("login")
    Call<User> login(@Body Login login);
    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization")String authToken);
    @POST("users/")//registrationEmail
    Call<User> registration(@Body Registration_email registrationEmail);
    @POST("users/")//registrationEmail
    Call<User> registration(@Body Registration_phone registrationPhone);
    //To query Short events info
    @GET("events/{id}/")
    Call<Event>getEventInfo(@Header("x-access-token")String token, @Path("id")String id);//ottengo le info per popolare
    //un evento
    @GET("events/near/46/11/1")//Todo: changhe<User> with <Event>
    Call<Events_id>fetchIds(@Header("x-access-token")String token);//ottengo un array di id e altre info.


}
