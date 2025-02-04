package cn.fanzibang.api;

import cn.fanzibang.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface IUserApiService {

    /**
     * 查询店铺列表
     * @return
     */
    @GET("shops/list")
    Call<List<User>> listShops();

    /**
     * 根据店铺id查询店铺详情
     */
    @GET("shop/{shopId}")
    Call<User> getShopById(@Path("shopId") String shopId);

}
