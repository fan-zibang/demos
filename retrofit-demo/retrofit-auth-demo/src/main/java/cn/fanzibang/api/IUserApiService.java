package cn.fanzibang.api;

import cn.fanzibang.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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


}
