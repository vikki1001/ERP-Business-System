package com.ksv.inventory.interfaces;

import com.ksv.inventory.entity.Invoice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ReportInterface {
 
    public ObservableList<Invoice> REPORTLIST = FXCollections.observableArrayList();
}
