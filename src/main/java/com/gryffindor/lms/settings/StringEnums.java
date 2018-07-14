package com.gryffindor.lms.settings;

import java.io.Serializable;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

public enum StringEnums
{
    ACTIVE("active"),
    PENDING("pending"),
    BANNED("banned"),
    TEACHER("teacher"),
    STUDENT("student");

    private String status;

    StringEnums(String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}