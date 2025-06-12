package com.fanzibang.retrofit.auth.api;

import com.fanzibang.retrofit.auth.entity.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface IUserApiService {

    /**
     * 查询用户列表
     * @return
     */
    @GET("/users")
    Call<List<User>> listUsers(@Query("pageNo") Integer pageNo, @Query("pageSize") Integer pageSize);

    @GET("/users")
    Call<List<User>> listUsersByQueryMap(@QueryMap Map<String, String> page);

    /**
     * 根据用户id查询用户详情
     */
    @GET("/user/{userId}")
    Call<User> getUserById(@Path("userId") String userId);

    @POST("/user")
    Call<Boolean> addUser(@Body User user);

    @FormUrlEncoded
    @PUT("user/{userId}")
    Call<Void> updateUser(@Path("userId") String userId, @Field("sex") String sex, @Field("mobile") String mobile);

}
