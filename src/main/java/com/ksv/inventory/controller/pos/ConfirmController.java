package com.ksv.inventory.controller.pos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.ksv.inventory.entity.Item;
import com.ksv.inventory.pdf.PrintInvoice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class ConfirmController implements Initializable {

    @FXML
    private TextArea billingArea;
    @FXML
    private Label retailLabel;
    private double retail;
    private ObservableList<Item> items;
    private String barcode;
    private double due;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retailLabel.setText("Change: $" + retail);

        StringBuilder details = new StringBuilder("Item Name\t\t" + "Cost\t\t" + "Quantity\t\t"+ "Due Amount\t\t" + "Total\n");
 
        for (Item i : items) {
            details.append(i.getItemName())
                    .append("\t\t\t")
                    .append(i.getUnitPrice())
                    .append("\t\t\t")
                    .append(i.getQuantity())
                    .append("\t\t\t")
                    .append(due)
                    .append("\t\t\t")
                    .append(i.getTotal())
                    .append("\n");
        }

        billingArea.setText(details.toString());

    }
    
    
    public void setData(double retail, ObservableList<Item> items, String barcode,double due) {
        this.retail = retail;
        this.items = FXCollections.observableArrayList(items);
        this.barcode = barcode;
        this.due = due; 
    }
        
    @FXML
    public void doneAction(ActionEvent event) throws IOException {
        billingArea.setText("");
        PrintInvoice pi = new PrintInvoice(items, barcode,due);
        pi.generateReport();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
