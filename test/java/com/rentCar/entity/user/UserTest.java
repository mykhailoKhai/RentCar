package com.rentCar.entity.user;

import com.rentCar.utill.MD5Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class UserTest {
    @Test
    public void compareUserTest(){
        User user1 = new User();
        user1.setUserId(8);
        user1.setLogin("Mikekhai");
        user1.setPassword(MD5Util.codingToMD5("Khajm"));
        user1.setLastName("Khai");
        user1.setFirstName("Mike");
        user1.setPhoneNum(380969696986L);
        user1.setEmail("khaj@gmail.com");
        user1.setRole(Role.CUSTOMER.toString());
        user1.setIsActive(true);
        user1.setAccount(123.45);
        user1.setDocumentSeries("KK");
        user1.setDocumentNum(1234567);
        user1.setDateIssue(new Date(2020, 5, 15));
        user1.setAuthority("Sambir GTFR");

        User user2 = new User();
        user2.setUserId(8);
        user2.setLogin("Mikekhai");
        user2.setPassword(MD5Util.codingToMD5("Khajm"));
        user2.setLastName("Khai");
        user2.setFirstName("Mike");
        user2.setPhoneNum(380969696986L);
        user2.setEmail("khaj@gmail.com");
        user2.setRole(Role.CUSTOMER.toString());
        user2.setIsActive(true);
        user2.setAccount(123.45);
        user2.setDocumentSeries("KK");
        user2.setDocumentNum(1234567);
        user2.setDateIssue(new Date(2020, 5, 15));
        user2.setAuthority("Sambir GTFR");

        Assert.assertEquals(user1, user2);

    }
}
