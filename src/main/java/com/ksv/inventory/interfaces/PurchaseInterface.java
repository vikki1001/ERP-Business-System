package com.ksv.inventory.interfaces;

import com.ksv.inventory.entity.Purchase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface PurchaseInterface {
    
    public ObservableList<Purchase> PURCHASELIST = FXCollections.observableArrayList();
}
