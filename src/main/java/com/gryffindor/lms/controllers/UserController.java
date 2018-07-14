package com.gryffindor.lms.controllers;

import com.eclipsesource.json.Json;
import com.gryffindor.lms.daos.UserDao;
import com.gryffindor.lms.mailer.SendMail;
import com.gryffindor.lms.models.PendingUser;
import com.gryffindor.lms.models.User;
import com.gryffindor.lms.settings.SettingsConstant;
import com.gryffindor.lms.settings.StringEnums;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

@RestController
@RequestMapping("api/user")
public class UserController {

    @PostMapping("create")
    public String createUser(@RequestBody User user) {
        System.out.println(user.toString());
        UserDao userDao = new UserDao();
        user.setStatus(StringEnums.PENDING.getValue());
        boolean saved = userDao.save(user);
        if (saved) {
            int code = new Random().nextInt(99999) + 1;
            SendMail sendMail = new SendMail();
            sendMail.send(user.getEmail(), code);
            PendingUser pendingUser = new PendingUser();
            pendingUser.setUsername(user.getUsername());
            pendingUser.setApprovalCode(code);
            userDao.savePendingUser(pendingUser);
            return SettingsConstant.getSuccessResponse();
        } else {
            return SettingsConstant.getFailedResponse();
        }
    }

    @PostMapping("login")
    public User login(@RequestBody String requestBody) {
        String username = com.eclipsesource.json.Json.parse(requestBody).asObject().getString("username", "");
        String password = com.eclipsesource.json.Json.parse(requestBody).asObject().getString("password", "");

        UserDao userDao = new UserDao();
        User user = userDao.login(username, password);

        if (user != null) {
            return user;
        } else {
            return user;
        }
    }

    @PostMapping("validate")
    public String validateUser(@RequestBody PendingUser pendingUser) {
        UserDao userDao = new UserDao();
        boolean validated = userDao.verifyUser(pendingUser);
        if (validated) {
            return SettingsConstant.getSuccessResponse();
        } else {
            return SettingsConstant.getFailedResponse();
        }
    }

    @PostMapping("classlist")
    public List getClassList(@RequestBody String requestBody) {
        String username = Json.parse(requestBody).asObject().getString("username", "");
        UserDao userDao = new UserDao();
        List classList = userDao.getJoinedClass(username);
        return classList;
    }

}
