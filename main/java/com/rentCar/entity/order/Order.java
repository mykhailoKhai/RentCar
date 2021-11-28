package com.rentCar.entity.order;

import com.rentCar.entity.car.Car;
import com.rentCar.entity.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private long orderId;
    private Date rentStart;
    private Date rentEnd;
    private boolean needDriver;
    private double totalCost;
    private String statusOrder;
    private String statusMessage;
    private double damagePaid;
    private boolean isPaidDamage;
    private String documentSeries;
    private long documentNum;
    private Date dateIssue;
    private String authority;
    private Car car;
    private User user;

    public Order() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getRentStart() {
        return rentStart;
    }

    public void setRentStart(Date rentStart) {
        this.rentStart = rentStart;
    }

    public Date getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(Date rentEnd) {
        this.rentEnd = rentEnd;
    }

    public boolean getNeedDriver() {
        return needDriver;
    }

    public void setNeedDriver(boolean needDrive) {
        this.needDriver = needDriver;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public double getDamagePaid() {
        return damagePaid;
    }

    public void setDamagePaid(double damagePaid) {
        this.damagePaid = damagePaid;
    }

    public boolean getIsPaidDamage() {
        return isPaidDamage;
    }

    public void setIsPaidDamage(boolean paidDamage) {
        isPaidDamage = paidDamage;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Order )) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                needDriver == order.needDriver &&
                Double.compare(order.totalCost, totalCost) == 0 &&
                Double.compare(order.damagePaid, damagePaid) == 0 &&
                isPaidDamage == order.isPaidDamage &&
                documentNum == order.documentNum &&
                Objects.equals(rentStart, order.rentStart) &&
                Objects.equals(rentEnd, order.rentEnd) &&
                Objects.equals(statusOrder, order.statusOrder) &&
                Objects.equals(statusMessage, order.statusMessage) &&
                Objects.equals(documentSeries, order.documentSeries) &&
                Objects.equals(dateIssue, order.dateIssue) &&
                Objects.equals(authority, order.authority) &&
                Objects.equals(car, order.car) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, rentStart, rentEnd, needDriver, totalCost, statusOrder, statusMessage, damagePaid, isPaidDamage, documentSeries, documentNum, dateIssue, authority, car, user);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", rentStart=" + rentStart +
                ", rentEnd=" + rentEnd +
                ", needDriver=" + needDriver +
                ", totalCost=" + totalCost +
                ", statusOrder='" + statusOrder + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", damagePaid=" + damagePaid +
                ", isPaidDamage=" + isPaidDamage +
                ", documentSeries='" + documentSeries + '\'' +
                ", documentNum=" + documentNum +
                ", dateIssue=" + dateIssue +
                ", authority='" + authority + '\'' +
                ", car=" + car +
                ", user=" + user +
                '}';
    }
}
