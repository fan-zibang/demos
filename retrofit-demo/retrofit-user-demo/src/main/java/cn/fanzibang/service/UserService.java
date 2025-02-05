package cn.fanzibang.service;

import cn.fanzibang.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public User getUserById(String id) {
        if ("1".equals(id)) {
            return new User("1", "XiaoMing", "男", "12345678900");
        }
        return null;
    }

    public Boolean addUser(User user) {
        log.info("接收到参数：{}", user);
        if (Objects.isNull(user)) {
            return false;
        }
        return true;
    }

    public List<User> listUsers(Integer pageNo, Integer pageSize) {
        log.info("pageNo:{}, pageSize:{}", pageNo, pageSize);
        return Arrays.asList(
                new User("1", "XiaoMing", "男", "12345678900"),
                new User("2", "XiaoHong", "女", "12345678901")
        );
    }
}
