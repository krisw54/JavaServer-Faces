/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import managers.*;
import dbWrappers.*;
import dto.*;
import gateway.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author w028006g
 */
public class TestDatabaseFunctions {

    @Test
    public void DatabaseConnction() {
        try {
            Connection conn;
            conn = DatabaseManager.getConnection();
            Assert.assertNotNull(conn);
            Assert.assertTrue(conn.isValid(0));
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Test
    public void viewItemDetailsFromDatabase() {
        ViewItemDetails d = new ViewItemDetails();
        assertEquals("Apple", d.getItemDetails(1, 1).getItemName());
    }

    @Test
    public void addUserToDatabase() {
        UserGateway u = new UserGateway();
        boolean ok = u.insert(new UserDTO("TestUserFirstName", "TestUserLastName", "email@email.com", "testUserName", "testUserPassword"));
        assertTrue(ok);
    }

    @Test
    public void listStores() {
        StoreGateway gs = new StoreGateway();
        ArrayList<StoreDTO> dbStores = gs.find();
        ArrayList<StoreDTO> expected = new ArrayList();
        expected.add(new StoreDTO(1, "Stoke", "1212 Stoke Street, Stoke-On-Trent", "012782 5654656", 1));
        expected.add(new StoreDTO(2, "Stafford", "44 Staffs Street, Stoke-On-Trent", "012782 588888", 1));
        expected.add(new StoreDTO(3, "Stone", "44 database Street, Stone", "01256 5554466", 1));
        assertArrayEquals(dbStores.toArray(), expected.toArray());
    }

    @Test
    public void loadProducts() {
        ItemsGateway i = new ItemsGateway();
        ArrayList<ItemDTO> items;
        ArrayList<ItemDTO> expected = new ArrayList();
        items = i.find(1);

        expected.add(new ItemDTO(1, "Apple", "A crisp red apple", "0123232", "resources/images/apple.jpg", 0.50, 10));
        expected.add(new ItemDTO(2, "Pear", "A lovely green pear", "121232", "resources/images/pear.jpg", 0.99, 0));
        expected.add(new ItemDTO(3, "Clock", "Clock with batteries", "12332", "resources/images/clock.jpg", 10.99, 100));
        expected.add(new ItemDTO(4, "iPhone", "iPhone 6s, brand new bargin price", "2345332", "resources/images/iphone.jpg", 300.29, 1));
        expected.add(new ItemDTO(5, "Banana", "Lovely bunch of bananas", "2333232", "resources/images/ban.jpg", 0.67, 1));
        assertArrayEquals(expected.toArray(), items.toArray());
    }

    @Test
    public void findUserProfile() {
        UserGateway u = new UserGateway();
        UserDTO user = u.find(new UserDTO("TestUserFirstName", "TestUserLastName", "email@email.com", "testUserName", "testUserPassword"));
        UserDTO expected = new UserDTO("TestUserFirstName", "TestUserLastName", "email@email.com", "testUserName", "testUserPassword");
        assertEquals(expected.getUsename(), user.getUsename());
        assertEquals(expected.getPassword(), user.getPassword());
    }

    @Test
    public void findPreviousOrders() {
        OrderGateway o = new OrderGateway();
        ArrayList<OrderDTO> orders;
        ArrayList<OrderDTO> expected = new ArrayList();
        orders = o.find(3);

        expected.add(new OrderDTO(2, "2017-12-05", "11:34:33", 1, "Apple", 0.50, 2, 1.0));
        expected.add(new OrderDTO(2, "2017-12-05", "11:34:33", 3, "Clock", 10.99, 10, 100.99));
        assertArrayEquals(expected.toArray(), orders.toArray());
    }

    @Test
    public void editUserDetails() {
        UserGateway u = new UserGateway();
        boolean ok = u.update(new UserDTO(4, "TestUserFirstName1", "TestUserLastName1", "email@email.com1", "testUserName", "testUserPassword1"));
        assertTrue(ok);
    }

    @Test
    public void deleteItemFromDatabase() {
//        ItemsGateway i = new ItemsGateway();
//        boolean ok = i.delete(1, 1);
//
//        assertTrue(ok);
//
//        ArrayList<ItemDTO> items;
//        ArrayList<ItemDTO> expected = new ArrayList();
//        items = i.find(1);
//
//        expected.add(new ItemDTO(2, "Pear", "A lovely green pear", "121232", "resources/images/pear.jpg", 0.99, 0));
//        expected.add(new ItemDTO(3, "Clock", "Clock with batteries", "12332", "resources/images/clock.jpg", 10.99, 100));
//        expected.add(new ItemDTO(4, "iPhone", "iPhone 6s, brand new bargin price", "2345332", "resources/images/iphone.jpg", 300.29, 1));
//        expected.add(new ItemDTO(5, "Banana", "Lovely bunch of bananas", "2333232", "resources/images/ban.jpg", 0.67, 1));
//        
//        assertArrayEquals(expected.toArray(), items.toArray());
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
