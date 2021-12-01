package com.rentCar.util;

import com.rentCar.utill.DBManager;
import org.junit.Assert;
import org.junit.Test;

public class DBManagerTest {

    @Test
    public void shouldReturnDBManager() {
        DBManager dbManager = DBManager.getInstance();
        Assert.assertNotNull(dbManager);
    }

}
