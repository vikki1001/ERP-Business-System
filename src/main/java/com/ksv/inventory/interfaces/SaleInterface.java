package com.ksv.inventory.interfaces;

import com.ksv.inventory.entity.Sale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface SaleInterface {

    public ObservableList<Sale> SALELIST = FXCollections.observableArrayList();
}
