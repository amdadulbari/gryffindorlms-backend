package com.gryffindor.lms.services;

import com.gryffindor.lms.daos.UserDao;
import com.gryffindor.lms.models.User;
import com.gryffindor.lms.settings.StringEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

public class UserService {
    public boolean create(User user){
        UserDao userDao = new UserDao();
        user.setStatus(StringEnums.PENDING.getValue());
        userDao.save(user);
        return true;
    }
}
