package com.platzi.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.platzi.market.domain.PurchaseItem;
import com.platzi.market.persistence.entity.ComprasProducto;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mapping(source = "id.idProducto", target = "productId")
    @Mapping(source = "cantidad", target = "quantity")
    @Mapping(source = "estado", target = "active")
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mapping(target = "compra", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "id.idCompra", ignore = true)
    ComprasProducto toComprasProducto(PurchaseItem item);
}
