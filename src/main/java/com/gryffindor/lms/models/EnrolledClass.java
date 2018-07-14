package com.gryffindor.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="enrolled")
public class EnrolledClass {
    @Id
    @GeneratedValue
    private int id;
    private String classUserName;
    private String studentUserName;
    private String invitationCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public String getClassUserName() {

        return classUserName;
    }

    @JsonProperty
    public void setClassUserName(String classUserName) {
        this.classUserName = classUserName;
    }

    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(String studentUserName) {
        this.studentUserName = studentUserName;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
