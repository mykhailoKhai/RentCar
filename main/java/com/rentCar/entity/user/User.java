package com.rentCar.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    private long userId;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private long phoneNum;
    private String email;
    private String role;
    private boolean isActive;
    private double account;

    private String documentSeries;
    private long documentNum;
    private Date dateIssue;
    private String authority;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public String getDocumentSeries() {
        return documentSeries;
    }

    public void setDocumentSeries(String documentSeries) {
        this.documentSeries = documentSeries;
    }

    public long getDocumentNum() {
        return documentNum;
    }

    public void setDocumentNum(long documentNum) {
        this.documentNum = documentNum;
    }

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof User )) return false;
        User user = (User) o;
        return userId == user.userId &&
                phoneNum == user.phoneNum &&
                role == user.role &&
                isActive == user.isActive &&
                Double.compare(user.account, account) == 0 &&
                documentNum == user.documentNum &&
                Objects.equals(login, user.login) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(documentSeries, user.documentSeries) &&
                Objects.equals(dateIssue, user.dateIssue) &&
                Objects.equals(authority, user.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, lastName, firstName, phoneNum, email, role, isActive, account, documentSeries, documentNum, dateIssue, authority);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNum=" + phoneNum +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", account=" + account +
                ", documentSeries='" + documentSeries + '\'' +
                ", documentNum=" + documentNum +
                ", dateIssue=" + dateIssue +
                ", authority='" + authority + '\'' +
                '}';
    }
}
