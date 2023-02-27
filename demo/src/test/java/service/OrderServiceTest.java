package service;

import com.eshop.model.DAO.impl.OrderDAOImpl;
import com.eshop.model.entity.CartItem;
import com.eshop.model.entity.MyOrder;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.User;
import com.eshop.service.CartService;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import com.eshop.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    private OrderService orderService;
    private UserService userService;
    private User testUser;
    private MyOrder testOrder;
    private Product testProduct1;
    private Product testProduct2;
    private CartItem testCartItem1;
    private CartItem testCartItem2;

    @Before
    public void setup() throws SQLException {
        testOrder = new MyOrder();
        orderService = new OrderService();
        userService = new UserService();
        testUser = new User();
        testUser.setId(userService.getNewUserId());
        testUser.setName("John Doe");
        testUser.setEmail("johndoe@example.com" + new Date());
        testUser.setPassword("password");
        userService.insertUser(testUser);
        testProduct1 = new Product();
        testProduct1.setName("Test Product 1");
        testProduct1.setDescription("Test Product 1 Description");
        testProduct1.setPrice(9.99);
        testProduct1.setImageUrl("https://example.com/product1.jpg");
        new ProductService().insertProduct(testProduct1);
        testProduct2 = new Product();
        testProduct2.setName("Test Product 2");
        testProduct2.setDescription("Test Product 2 Description");
        testProduct2.setPrice(19.99);
        testProduct2.setImageUrl("https://example.com/product2.jpg");
        new ProductService().insertProduct(testProduct2);
        testCartItem1 = new CartItem();
        testCartItem1.setProduct(testProduct1);
        testCartItem1.setOrderId(orderService.getnewOrderId());
        testCartItem1.setQuantity(2);
        testCartItem2 = new CartItem();
        testCartItem2.setProduct(testProduct2);
        testCartItem2.setOrderId(orderService.getnewOrderId());
        testCartItem2.setQuantity(1);
        testOrder.setUser(testUser);
        testCartItem1.setUser(testUser);
        testCartItem2.setUser(testUser);
        CartItem[] items = {testCartItem1, testCartItem2};
        testOrder.setItems(Arrays.asList(items));
//        order 和 cart是互相映射, 要相互绑定好最后再插入, 先插入product, 再插入order, 最后插入cartitem, 因为外键约束
        System.out.println(orderService.insertOrder(testOrder));
        new CartService().insertCartItem(testCartItem1);
        new CartService().insertCartItem(testCartItem2);
    }

    @After
    public void tearDown() {
        new CartService().deleteCartItem(testCartItem1.getId());
        new CartService().deleteCartItem(testCartItem2.getId());
        new OrderDAOImpl().deleteOrder(testOrder.getId());
        userService.deleteUser(testUser.getId());
        new ProductService().deleteProduct(testProduct1.getId());
        new ProductService().deleteProduct(testProduct2.getId());
    }

    @Test
    public void testGetOrderById() {
        MyOrder actualOrder = orderService.getOrderById(testOrder.getId());
        assertEquals(testOrder.getTotal(), actualOrder.getTotal(), 0.001);
        assertEquals(testOrder.getUser().getName(), actualOrder.getUser().getName());
        assertEquals(testOrder.getUser().getEmail(), actualOrder.getUser().getEmail());
        assertEquals(testOrder.getUser().getPassword(), actualOrder.getUser().getPassword());
        List<CartItem> actualCartItems = actualOrder.getItems();
        assertEquals(2, actualCartItems.size());
        CartItem actualCartItem1 = actualCartItems.get(0);
        CartItem actualCartItem2 = actualCartItems.get(1);
        assertEquals(testCartItem1.getProduct().getName(), actualCartItem1.getProduct().getName());
        assertEquals(testCartItem1.getQuantity(), actualCartItem1.getQuantity());
        assertEquals(testCartItem1.getTotal(), actualCartItem1.getTotal(), 0.001);
        assertEquals(testCartItem2.getProduct().getName(), actualCartItem2.getProduct().getName());
        assertEquals(testCartItem2.getQuantity(), actualCartItem2.getQuantity());
        assertEquals(testCartItem2.getTotal(), actualCartItem2.getTotal(), 0.001);
    }

    @Test
    public void testUpdateOrder() {
        testOrder.setTotal(49.97);
        orderService.updateOrder(testOrder);
        MyOrder actualOrder = orderService.getOrderById(testOrder.getId());
        assertEquals(testOrder.getTotal(), actualOrder.getTotal(), 0.001);
    }

    @Test
    public void testGetOrdersByUserId() {
        List<MyOrder> actualOrders = orderService.getOrdersByUserId(testUser.getId());
        assertEquals(1, actualOrders.size());
        MyOrder actualOrder = actualOrders.get(0);
        assertEquals(testOrder.getTotal(), actualOrder.getTotal(), 0.001);
        assertEquals(testOrder.getUser().getName(), actualOrder.getUser().getName());
        assertEquals(testOrder.getUser().getEmail(), actualOrder.getUser().getEmail());
        assertEquals(testOrder.getUser().getPassword(), actualOrder.getUser().getPassword());
        List<CartItem> actualCartItems = actualOrder.getItems();
        assertEquals(2, actualCartItems.size());
        CartItem actualCartItem1 = actualCartItems.get(0);
        CartItem actualCartItem2 = actualCartItems.get(1);
        assertEquals(testCartItem1.getProduct().getName(), actualCartItem1.getProduct().getName());
        assertEquals(testCartItem1.getQuantity(), actualCartItem1.getQuantity());
        assertEquals(testCartItem1.getTotal(), actualCartItem1.getTotal(), 0.001);
        assertEquals(testCartItem2.getProduct().getName(), actualCartItem2.getProduct().getName());
        assertEquals(testCartItem2.getQuantity(), actualCartItem2.getQuantity());
        assertEquals(testCartItem2.getTotal(), actualCartItem2.getTotal(), 0.001);
    }

    @Test
    public void testInsertOrderWithCartItems() {
        MyOrder newOrder = new MyOrder();
        int id =orderService.getnewOrderId();
        newOrder.setUser(testUser);
        List<CartItem> cartItems = new ArrayList<>();
        CartItem newCartItem1 = new CartItem();
        newCartItem1.setUser(testUser);
        newCartItem1.setOrderId(id);
        newCartItem1.setProduct(testProduct1);
        newCartItem1.setQuantity(1);
        cartItems.add(newCartItem1);
        CartItem newCartItem2 = new CartItem();
        newCartItem2.setUser(testUser);
        newCartItem2.setOrderId(id);
        newCartItem2.setProduct(testProduct2);
        newCartItem2.setQuantity(2);
        cartItems.add(newCartItem2);
        newOrder.setItems(cartItems);
        orderService.insertOrder(newOrder);
        new CartService().insertCartItem(newCartItem1);
        new CartService().insertCartItem(newCartItem2);
        MyOrder actualOrder = orderService.getOrderById(id);
        System.out.println(actualOrder);
        assertNotNull(actualOrder);
        assertEquals(testUser.getName(), actualOrder.getUser().getName());
        assertEquals(testUser.getEmail(), actualOrder.getUser().getEmail());
        assertEquals(testUser.getPassword(), actualOrder.getUser().getPassword());
        List<CartItem> actualCartItems = actualOrder.getItems();

        assertEquals(2, actualCartItems.size());
        CartItem actualCartItem1 = actualCartItems.get(0);
        CartItem actualCartItem2 = actualCartItems.get(1);
        assertEquals(newCartItem1.getProduct().getName(), actualCartItem1.getProduct().getName());
        assertEquals(newCartItem1.getQuantity(), actualCartItem1.getQuantity());
        assertEquals(newCartItem1.getTotal(), actualCartItem1.getTotal(), 0.001);
        assertEquals(newCartItem2.getProduct().getName(), actualCartItem2.getProduct().getName());
        assertEquals(newCartItem2.getQuantity(), actualCartItem2.getQuantity());
        assertEquals(newCartItem2.getTotal(), actualCartItem2.getTotal(), 0.001);
    }
}

