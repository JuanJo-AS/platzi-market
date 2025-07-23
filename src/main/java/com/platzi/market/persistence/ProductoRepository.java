package com.platzi.market.persistence;

import java.util.List;
import java.util.Optional;

import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;

public class ProductoRepository {

    private ProductoCrudRepository productoCrud;

    public List<Producto> getAll() {
        return (List<Producto>) productoCrud.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrud.findByIdCategoria(idCategoria);
    }

    public List<Producto> getByCategoriaOrderByNameAsc(int idCategoria) {
        return productoCrud.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrud.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
}
