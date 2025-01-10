package model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private static UserInfo instance;
    private String userId;
    private int age;
    private Gender gender;

    private UserInfo() {}

    public static UserInfo getInstance() {
        if (instance == null) {
            instance = new UserInfo();
        }
        return instance;
    }

}
