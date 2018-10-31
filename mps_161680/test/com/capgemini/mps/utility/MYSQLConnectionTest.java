package com.capgemini.mps.utility;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.capgemini.mps.exception.MobilePurchaseException;

public class MYSQLConnectionTest {

    @Test
    public void testGetConnection() throws MobilePurchaseException,SQLException {

        try {
            assertNotNull(MYSQLConnection.getConnection());
        } catch (IOException e) {           
            e.printStackTrace();
        }
    }

}
