package com.pil.activemq;

import java.io.Serializable;

public class Order implements Serializable {
	
	private String id;
	private String item;
	private String quantity; 
	private String price;
	private String remarks;
	
	public Order(String id, String item, String quantity, String price, String remarks) {
		super();
		this.id = id;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
		this.remarks = remarks;
	}
	
	public Order() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String toString() {
		String ord = "{"
				+ "\"id\":\"" + id + "\","
				+ "\"item\":\"" + item + "\","
				+ "\"quantity\":\"" + quantity + "\","
				+ "\"price\":\"" + price + "\","
				+ "\"remarks\":\"" + remarks + "\""
				+"}";
		return ord;
	}
	
	
}
