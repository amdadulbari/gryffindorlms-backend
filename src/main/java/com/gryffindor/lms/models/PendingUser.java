package com.gryffindor.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "pending_user")
public class PendingUser {
    @Id
    public String username;
    public int approvalCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(int approvalCode) {
        this.approvalCode = approvalCode;
    }
}
