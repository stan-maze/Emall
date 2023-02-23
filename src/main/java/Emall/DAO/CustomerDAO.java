package Emall.DAO;

import Emall.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public int createCustomer(Customer customer);
    public Customer getCustomerById(int customerId);
    public List<Customer> getAllCustomers();
    public int updateCustomer(Customer customer);
    public void deleteCustomer(int customerId);
}
