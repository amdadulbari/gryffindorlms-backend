package com.gryffindor.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="classroom")
public class Classroom {

    @Id
    private String classUsername;
    @Field(termVector = TermVector.YES)
    public String invitation_code;
    private String name;
    private String details;


    private String creatorName;

    public String getClassUsername() {

        return classUsername;
    }

    public void setClassUsername(String username) {
        this.classUsername = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classUsername='" + classUsername + '\'' +
                ", invitation_code='" + invitation_code + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", creatorName='" + creatorName + '\'' +
                '}';
    }
}
