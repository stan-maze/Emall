package service;

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
import java.util.List;

import static org.junit.Assert.*;

public class CartServiceTest {

    private CartService cartService;
    private ProductService productService;
    private UserService userService;
    private OrderService orderService;
    private User testUser;
    private MyOrder testOrder;
    private Product testProduct;
    private CartItem testCartItem;

    @Before
    public void setUp() throws SQLException {
        cartService = new CartService();
        productService = new ProductService();
        userService = new UserService();
        orderService = new OrderService();

        testUser = new User();
        testUser.setId(userService.getNewUserId());
        testUser.setName("Mike Smith");
        testUser.setEmail("mikesmith@example.com");
        testUser.setPassword("password");
        userService.insertUser(testUser);

        testOrder = new MyOrder();
        testOrder.setId(orderService.getnewOrderId());
        testOrder.setUser(testUser);
        orderService.insertOrder(testOrder);

        testProduct = new Product();
        testProduct.setId(productService.getnewProductId());
        testProduct.setName("Product" + testProduct.getId());
        testProduct.setDescription("Test Product Description");
        testProduct.setPrice(9.99);
        testProduct.setImageUrl("https://example.com/product.jpg");
        productService.insertProduct(testProduct);

        testCartItem = new CartItem();
        testCartItem.setId(cartService.getnewCartItemId());
        testCartItem.setProduct(testProduct);
        testCartItem.setOrderId(testOrder.getId());
        testCartItem.setQuantity(2);
    }

    @After
    public void tearDown() {
        cartService.deleteCartItem(testCartItem.getId());
        orderService.deleteOrder(testOrder.getId());
        userService.deleteUser(testUser.getId());
        productService.deleteProduct(testProduct.getId());
    }

    @Test
    public void testInsertCartItem() {
        cartService.insertCartItem(testCartItem);
        List<CartItem> cartItems = cartService.getCartItemsByOrderId(testOrder.getId());
        assertTrue(cartItems.size() > 0);
        CartItem actualCartItem = cartService.getCartItemById(testCartItem.getId());
        assertNotNull(actualCartItem);
        assertEquals(testCartItem.getProduct().getName(), actualCartItem.getProduct().getName());
        assertEquals(testCartItem.getOrderId(), actualCartItem.getOrderId());
        assertEquals(testCartItem.getQuantity(), actualCartItem.getQuantity());
        assertEquals(testCartItem.getTotal(), actualCartItem.getTotal(), 0.001);
    }

    @Test
    public void testUpdateCartItem() {
        cartService.insertCartItem(testCartItem);
        testCartItem.setQuantity(1);
        cartService.updateCartItem(testCartItem);
        CartItem actualCartItem = cartService.getCartItemById(testCartItem.getId());
        assertEquals(testCartItem.getQuantity(), actualCartItem.getQuantity());
        assertEquals(testCartItem.getTotal(), actualCartItem.getTotal(), 0.001);
    }

    @Test
    public void testGetCartItemsByOrderId() {
        cartService.insertCartItem(testCartItem);
        List<CartItem> cartItems = cartService.getCartItemsByOrderId(testOrder.getId());
        assertTrue(cartItems.size() > 0);
    }

    @Test
    public void testDeleteCartItem() {
        cartService.insertCartItem(testCartItem);
        cartService.deleteCartItem(testCartItem.getId());
        CartItem actualCartItem = cartService.getCartItemById(testCartItem.getId());
        assertNull(actualCartItem);
    }

    @Test
    public void testDeleteCartItemsByOrderId() {
        cartService.insertCartItem(testCartItem);
        cartService.deleteCartItemsByOrderId(testOrder.getId());
        List<CartItem> cartItems = cartService.getCartItemsByOrderId(testOrder.getId());
        assertTrue(cartItems.isEmpty());
    }

}
