package Emall.DAO;

import Emall.entity.Product;

import java.util.List;

public interface ProductDAO {
    public int createProduct(Product product);
    public Product getProductById(int productId);
    public List<Product> getAllProducts();
    public int updateProduct(Product product);
    public void deleteProduct(int productId);
}
