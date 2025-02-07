package cn.fanzibang;

import cn.fanzibang.api.IUserApiService;
import cn.fanzibang.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Resource
    private IUserApiService userApiService;

    @Test
    public void test_url_manipulation_path_ano() throws IOException {
        // 省略校验...
        // 从 token 取出用户id，根据用户id查询用户详情
        Call<User> userCall = userApiService.getUserById("1");
        User user = userCall.execute().body();
        assert user != null;
    }

    @Test
    public void test_url_manipulation_query_ano() throws IOException {
        // 省略校验...
        // 从 token 取出用户id，根据用户id查询用户详情
        Call<List<User>> userCall = userApiService.listUsers(2, 10);
        List<User> userList = userCall.execute().body();
        assert userList != null;
        log.info("userList size:{}", userList.size());
    }

    @Test
    public void test_url_manipulation_query_map_ano() throws IOException {
        // 省略校验...
        // 从 token 取出用户id，根据用户id查询用户详情
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", "3");
        map.put("pageSize", "10");
        Call<List<User>> userCall = userApiService.listUsersByQueryMap(map);
        List<User> userList = userCall.execute().body();
        assert userList != null;
        log.info("userList size:{}", userList.size());
    }

    @Test
    public void test_body_ano() throws IOException {
        User user = new User("1", "XiaoMing", "男", "12345678900");
        Call<Boolean> success = userApiService.addUser(user);
        log.info("success: {}", success.execute().body());
    }

    @Test
    public void test_field_ano() throws IOException {
        Call<Void> call = userApiService.updateUser("1","女", "12345678912");
        int code = call.execute().code();
        log.info("http code: {}", code);
    }

}
