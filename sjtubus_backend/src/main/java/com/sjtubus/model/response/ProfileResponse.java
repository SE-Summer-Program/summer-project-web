package com.sjtubus.model.response;

import com.sjtubus.entity.User;

/**
 * @author Allen
 * @date 2018/7/13 15:53
 */
public class ProfileResponse extends HttpResponse {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
