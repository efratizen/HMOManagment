package com.javatpoint.model;


import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class HmoMember {
    @Id
    @Max(value = 1000000000)
    @Min(value = 0)
    private Long id;
    @NotNull(message = "First name is a required field")
    @Size(min = 2, message = "Department Name must be at least 2 characters long")
    @NotEmpty(message = "Last name is a required field")
    private String firstName;
    @NotNull(message = "Last name is a required field")
    @NotEmpty(message = "Last name is a required field")
    @Size(min = 2, message = "Department Name must be at least 2 characters long")
    private String lastName;
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    private String city;
    private String street;
    private String buildingNumber;
    @Pattern(regexp = "^[0-9-]+$", message = "phone nust constain anly digits and '-' ")
    private String phone;
    @Pattern(regexp = "^[0-9-]+$", message = "phone nust constain anly digits and '-' ")
    private String mobilePhone;
    private String image;
    @OneToOne(mappedBy = "hmoMember")
    private CoronaDetails coronaDetails;

    public HmoMember(Long id, String firstName, String lastName, LocalDate dateOfBirth, String city, String street, String buildingNumber, String phone, String mobilePhone, String image,CoronaDetails coronaDetails) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
//        @Size(min=2, max=30)
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.image = image;
        this.coronaDetails=coronaDetails;
    }
    public HmoMember(){}

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CoronaDetails getCoronaDetails() {
        return coronaDetails;
    }

    public void setCoronaDetails(CoronaDetails coronaDetails) {
        this.coronaDetails = coronaDetails;
    }
}

