package me.tremor.Airglow_user;

import me.tremor.Airglow_user.models.IdEvent;
import me.tremor.Airglow_user.models.Login;
import me.tremor.Airglow_user.models.Registration_email;
import me.tremor.Airglow_user.models.Registration_phone;
import me.tremor.Airglow_user.models.ShortEvent;
import me.tremor.Airglow_user.models.User;
import me.tremor.Airglow_user.StackApiResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface representing the HTTP requests
 */
public interface UserClient {
    @POST("login")
    Call<User> login(@Body Login login);
    @POST("login")
    Call<StackApiResponse> login2(@Body Login login);
    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization")String authToken);
    @POST("users/")//registrationEmail
    Call<User> registration(@Body Registration_email registrationEmail);
    @POST("users/")//registrationEmail
    Call<User> registration(@Body Registration_phone registrationPhone);
    //To query Short events info
   /* @GET("events/{id}/")
    Call<StackApiResponse>getEventInfo(@Header("x-access-token")String token, @Path("id")String id);//short infos*/
    @GET("events/{id}/")
    Call<Item>getEventInfos(@Header("x-access-token")String token, @Path("id")String id);
    @GET("events/{id}/")
    Call<ShortEvent>getEventInfoss(@Header("x-access-token")String token, @Path("id")String id);
    @GET("events/near/46/11/1")//Todo: changhe<User> with <Event>
    Call<IdEvent>fetchIds3(@Header("x-access-token")String token);//Ids
    @GET("teddy/46/11/1")//Todo: changhe<User> with <Event>
    Call<StackApiResponse>fetchIds(/*@Header("x-access-token")String token,@Path("id")String id*/);//short infos;//Ids

    @GET("answers")
    Call<StackApiResponse> getAnswers(
            @Query("page") int page,
            @Query("pagesize") int size,
            @Query("site") String site
    );


}
