package com.rentCar.util;

import com.rentCar.utill.MD5Util;
import org.junit.Assert;
import org.junit.Test;

public class MD5UtilTest {
    @Test
    public void codingToMD5(){
        String accept = MD5Util.codingToMD5("mike");
        String expected  = "18126e7bd3f84b3f3e4df094def5b7de";
        Assert.assertEquals(accept, expected);
    }
}
