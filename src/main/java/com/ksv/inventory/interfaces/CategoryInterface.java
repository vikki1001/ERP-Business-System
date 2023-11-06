package com.ksv.inventory.interfaces;

import com.ksv.inventory.entity.Category;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface CategoryInterface {
    
    public ObservableList<Category> CATEGORYLIST = FXCollections.observableArrayList();   
}
