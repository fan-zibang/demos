package cn.fanzibang.entity;

public class User {

    private String userId;

    private String userName;

    private String sex;

    private String mobile;

    public User() {
    }

    public User(String userId, String userName, String sex, String mobile) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
