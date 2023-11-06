package com.ksv.inventory.entity;

public class Item {

    private String itemName;
    private double unitPrice;
    private double quantity;
    private double total;
    private double due;

    public Item() {
    	
    }

    public Item(String itemName, double unitPrice, double quantity, double total,double due) {
    	this.itemName = itemName;
    	this.unitPrice = unitPrice;
    	this.quantity = quantity;
    	this.total = total;
    	this.due = due;
    	
    }
    
    public Item(String itemName, double unitPrice, double quantity, double total) {
    	this.itemName = itemName;
    	this.unitPrice = unitPrice;
    	this.quantity = quantity;
    	this.total = total;
    	
    }
    

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    public double getDue() {
		return due;
	}

	public void setDue(double due) {
		this.due = due;
	}

	@Override
    public String toString() {
        return "Item{" + "itemName=" + itemName + 
                ", unitPrice=" + unitPrice + 
                ", quantity=" + quantity + 
                ", dueAmount=" + due + 
                ", total=" + total + '}';
    }
}
