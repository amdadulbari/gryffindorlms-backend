package com.gryffindor.lms.controllers;

import com.gryffindor.lms.daos.ClassroomDao;
import com.gryffindor.lms.daos.UserDao;
import com.gryffindor.lms.models.Classroom;
import com.gryffindor.lms.models.EnrolledClass;
import com.gryffindor.lms.settings.SettingsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

@RestController
@RequestMapping("api/classroom")
public class ClassroomController {
    @Autowired
    UserDao userDao;

    @PostMapping("create")
    public String create(@RequestBody Classroom classroom) {
        ClassroomDao classroomDao = new ClassroomDao();
        System.out.println(classroom.getCreatorName());
        classroomDao.save(classroom);
        return "Yes";
    }

    @PostMapping("join")
    public String joinClass(@RequestBody EnrolledClass enrolledClass) {
        try {
            ClassroomDao classroomDao = new ClassroomDao();
            classroomDao.joinClass(enrolledClass);
            return SettingsConstant.getSuccessResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return SettingsConstant.getFailedResponse();
        }
    }
}
