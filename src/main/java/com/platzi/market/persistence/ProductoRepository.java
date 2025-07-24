package com.platzi.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;

@Repository
public class ProductoRepository implements ProductRepository {

    private final ProductoCrudRepository productoCrud;
    private final ProductMapper mapper;

    public ProductoRepository(ProductoCrudRepository productCrud, ProductMapper mapper) {
        this.productoCrud = productCrud;
        this.mapper = mapper;
    }

    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrud.findAll();
        return mapper.toProducts(productos);
    }

    public List<Producto> getByCategoriaOrderByNameAsc(int idCategoria) {
        return productoCrud.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = (List<Producto>) productoCrud.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = (Optional<List<Producto>>) productoCrud
                .findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(p -> mapper.toProducts(p));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrud.findById(productId).map(p -> mapper.toProduct(p));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrud.save(producto));
    }

    public void delete(int idProducto) {
        productoCrud.deleteById(idProducto);
    }
}
