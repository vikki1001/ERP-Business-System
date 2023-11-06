package com.ksv.inventory.interfaces;

import com.ksv.inventory.entity.Supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface SupplierInterface {
    
    public ObservableList<Supplier> SUPPLIERLIST = FXCollections.observableArrayList();
}
