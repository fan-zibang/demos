package cn.fanzibang.service;

import cn.fanzibang.entity.User;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    public User getUserById(String id) {
        if ("1".equals(id)) {
            return new User("1", "XiaoMing", "男");
        }
        return null;
    }

    public Boolean addUser(User user) {
        System.out.println("接收到参数：" + user);
        if (Objects.isNull(user)) {
            return false;
        }
        return true;
    }




}
