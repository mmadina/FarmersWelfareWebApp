package org.company.ucm.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private Boolean adminUser;
    private Date registeredDate;
}
