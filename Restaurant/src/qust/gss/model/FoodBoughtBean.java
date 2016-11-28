package qust.gss.model;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FoodBoughtBean {
	private StringProperty fid;
	private StringProperty name;
	private StringProperty category;
	private IntegerProperty amount;
	private DoubleProperty price;
	@SuppressWarnings("unused")
	private DoubleProperty total;
	private NumberBinding totalBind;

	public FoodBoughtBean() {
	}

	public FoodBoughtBean(String fid,String name, String category, Integer amount, Double price) {
		this.fid = new SimpleStringProperty(fid);
		this.name = new SimpleStringProperty(name);
		this.category = new SimpleStringProperty(category);
		this.amount = new SimpleIntegerProperty(amount);
		this.price = new SimpleDoubleProperty(price);
		this.totalBind = this.amount.multiply(this.price);
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final IntegerProperty amountProperty() {
		return this.amount;
	}

	public final int getAmount() {
		return this.amountProperty().get();
	}

	public final void setAmount(final int amount) {
		this.amountProperty().set(amount);
	}

	public final DoubleProperty priceProperty() {
		return this.price;
	}

	public final double getPrice() {
		return this.priceProperty().get();
	}

	public final void setPrice(final double price) {
		this.priceProperty().set(price);
	}

	public final DoubleProperty totalProperty() {
		return new SimpleDoubleProperty(totalBind.doubleValue());
	}

	public final double getTotal() {
		return this.totalBind.doubleValue();
	}

	public final void setTotal(final double total) {
		this.totalProperty().set(totalBind.doubleValue());
	}

	public final StringProperty categoryProperty() {
		return this.category;
	}

	public final String getCategory() {
		return this.categoryProperty().get();
	}

	public final void setCategory(final String category) {
		this.categoryProperty().set(category);
	}

	public final StringProperty fidProperty() {
		return this.fid;
	}
	

	public final String getFid() {
		return this.fidProperty().get();
	}
	

	public final void setFid(final String fid) {
		this.fidProperty().set(fid);
	}
	
}
