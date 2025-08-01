package com.platzi.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.persistence.crud.CompraCrudRepository;
import com.platzi.market.persistence.entity.Compra;
import com.platzi.market.persistence.mapper.PurchaseMapper;

@Repository
public class CompraRepository implements PurchaseRepository {

    private CompraCrudRepository compraCrudRepository;
    private PurchaseMapper purchaseMapper;

    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper purchaseMapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.purchaseMapper = purchaseMapper;
    }

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    // Si no hay nada dentro de Optional, la función map no se ejecuta
    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }

}
