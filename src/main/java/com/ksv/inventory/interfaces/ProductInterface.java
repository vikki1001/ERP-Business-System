package com.ksv.inventory.interfaces;

import com.ksv.inventory.entity.Product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ProductInterface {
    
    public ObservableList<Product> PRODUCTLIST = FXCollections.observableArrayList();   
}
