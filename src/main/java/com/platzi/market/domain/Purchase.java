package com.platzi.market.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Purchase {

    private Integer purchaseId;

    private String clientId;

    private LocalDateTime date;

    private String paymentMethod;

    private String comment;

    private String state;

    private List<PurchaseItem> items;

}
