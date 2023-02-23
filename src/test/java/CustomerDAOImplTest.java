import Emall.DAO.CustomerDAO;
import Emall.DAO.impl.CustomerDAOImpl;
import Emall.entity.Customer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerDAOImplTest {
    private static CustomerDAO customerDAO;

    @BeforeClass
    public static void setUp() {
        customerDAO = new CustomerDAOImpl();
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setName("John");
        customer.setEmail("john@example.com");
        customer.setPassword("password");
        customerDAO.createCustomer(customer);
        assertNotNull(customer.getId());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> precustomers = customerDAO.getAllCustomers();
        Customer customer1 = new Customer();
        customer1.setName("mike");
        customer1.setEmail("mike@example.com");
        customer1.setPassword("password");
        customerDAO.createCustomer(customer1);
        Customer customer2 = new Customer();
        customer2.setName("Jane");
        customer2.setEmail("jane@example.com");
        customer2.setPassword("password");
        customerDAO.createCustomer(customer2);
        List<Customer> customers = customerDAO.getAllCustomers();
        assertEquals(precustomers.size() + 2, customers.size());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setName("cindy");
        customer.setEmail("cindy@example.com");
        customer.setPassword("password");
        int customerId = customerDAO.createCustomer(customer);
        Customer retrievedCustomer = customerDAO.getCustomerById(customerId);
        assertNotNull(retrievedCustomer);
        assertEquals(customer.getName(), retrievedCustomer.getName());
        assertEquals(customer.getEmail(), retrievedCustomer.getEmail());
        assertEquals(customer.getPassword(), retrievedCustomer.getPassword());
    }


    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer();
        customer.setName("Smith");
        customer.setEmail("smith@example.com");
        customer.setPassword("password");
        int customerId = customerDAO.createCustomer(customer);
        customer.setId(customerId);
        customer.setName("John Smith");
        customer.setEmail("john.smith@example.com");
        customerDAO.updateCustomer(customer);
        Customer updatedCustomer = customerDAO.getCustomerById(customerId);
        assertEquals(customer.getName(), updatedCustomer.getName());
        assertEquals(customer.getEmail(), updatedCustomer.getEmail());
        assertEquals(customer.getPassword(), updatedCustomer.getPassword());
    }

    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer();
        customer.setName("White");
        customer.setEmail("white@example.com");
        customer.setPassword("password");
        int customerId = customerDAO.createCustomer(customer);
        customerDAO.deleteCustomer(customerId);
        Customer deletedCustomer = customerDAO.getCustomerById(customerId);
        assertNull(deletedCustomer);
    }
}
