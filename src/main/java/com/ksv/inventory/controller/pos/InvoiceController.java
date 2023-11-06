package com.ksv.inventory.controller.pos;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import com.ksv.inventory.entity.Employee;
import com.ksv.inventory.entity.Invoice;
import com.ksv.inventory.entity.Item;
import com.ksv.inventory.entity.Payment;
import com.ksv.inventory.entity.Product;
import com.ksv.inventory.entity.Sale;
import com.ksv.inventory.model.EmployeeModel;
import com.ksv.inventory.model.InvoiceModel;
import com.ksv.inventory.model.ProductModel;
import com.ksv.inventory.model.SalesModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InvoiceController implements Initializable {

    @FXML
    private TextField totalAmountField, paidAmountField;
    private double netPrice;
    private ObservableList<Item> items;
    private EmployeeModel employeeModel;
    private ProductModel productModel;
    private SalesModel salesModel;
    private InvoiceModel invoiceModel;
    private Payment payment;
    private double amount;
    
//    private Employee employee;
    
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productModel = new ProductModel();
        employeeModel = new EmployeeModel();
        salesModel = new SalesModel();
        invoiceModel = new InvoiceModel();
        totalAmountField.setText(String.valueOf(netPrice));
        
    }

    public void setData(double netPrice, ObservableList<Item> items, Payment payment) {

        this.netPrice = netPrice;
        this.items = FXCollections.observableArrayList(items);
        this.payment = payment;
    }
 
    
    
    /**/
    @FXML
    public void confirmAction(ActionEvent event) throws Exception {

        if (validateInput()) {
            double paid = Double.parseDouble(paidAmountField.getText().trim());
            double retail = 0.0;
            double due;
            if (paid> netPrice) {
            	 retail = retail +  Math.abs(paid - netPrice);
            	 due = 0.0;
            }
            else {
            	due = Math.abs(netPrice - paid);
            }
            /* define int */
            int num = 2;
            String invoiceId = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
            		Invoice invoice = new Invoice(
                    invoiceId,
                    employeeModel.getEmployee(num),
                    /*name, mobile, email,due*/
                    employeeModel.getEmployeeName(num),
                    employeeModel.getEmployeeEmail(num),
                    employeeModel.getEmployeePhone(num),
                    payment.getSubTotal(),
                    payment.getVat(),
                    payment.getDiscount(),
                    payment.getPayable(),
                    paid,
                    retail,
                    due
            
            );

            		invoiceModel.saveInvoice(invoice);
                     
            for (Item i : items) {

                Product p = productModel.getProductByName(i.getItemName());
                double quantity = p.getQuantity() - i.getQuantity();
                p.setQuantity(quantity);
                productModel.decreaseProduct(p);

                Sale sale = new Sale(
                        invoiceModel.getInvoice(invoiceId),
                        productModel.getProductByName(i.getItemName()),
                        i.getQuantity(),
                        i.getUnitPrice(),
                        i.getTotal()
                );

                salesModel.saveSale(sale);
            }
            
            FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/Confirm.fxml")));
            ConfirmController controller = new ConfirmController();
            controller.setData(retail, items ,invoiceId,due);
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.setOnMousePressed((MouseEvent e) -> {
                xOffset = e.getSceneX();
                yOffset = e.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent e) -> {
                stage.setX(e.getScreenX() - xOffset);
                stage.setY(e.getScreenY() - yOffset);
            });
            stage.setTitle("Confirm");
            stage.getIcons().add(new Image("/images/logo.png"));
            stage.setScene(scene);
            stage.show();
        }

    }
    
    
    
    private boolean validateInput() {

        String errorMessage = "";

        if (paidAmountField.getText() == null || paidAmountField.getText().length() == 0) {
            errorMessage += "Invalid Input!\n";
        } /*else if (Double.parseDouble(paidAmountField.getText()) < netPrice) {
            errorMessage += "Insufficient Input!\n";
        }*/
        else if(Double.parseDouble(paidAmountField.getText()) > 0 && Double.parseDouble(paidAmountField.getText()) < netPrice) {
        	System.out.println("greater than zero but insufficent");
        	amount = netPrice -  Double.parseDouble(paidAmountField.getText());
        	System.out.println("calling for invoice controller : "+amount);
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please input the valid amount");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            paidAmountField.setText("");

            return false;
        }
    }
    
    
    @FXML
    public void closeAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
