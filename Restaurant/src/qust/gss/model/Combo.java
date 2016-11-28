package qust.gss.model;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Combo implements Serializable {
	private static final long serialVersionUID = -2717766872791417700L;

	private StringProperty cid;
	private StringProperty name;
	private IntegerProperty staple;
	private IntegerProperty vegetables;
	private IntegerProperty meat;
	private IntegerProperty gruel;
	private IntegerProperty amount;
	private DoubleProperty price;

	public Combo(String cid,String name,Integer amount) {
		this.cid = new SimpleStringProperty(cid);
		this.name = new SimpleStringProperty(name);
		this.amount = new SimpleIntegerProperty(amount);
	}
	
	public Combo(String cid,String name,Double price) {
		this.cid = new SimpleStringProperty(cid);
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(price);
	}
	public Combo(String cid, Integer staple, Integer vegetables, Integer meat, Integer gruel,double price) {
		this.cid = new SimpleStringProperty(cid);
		this.staple = new SimpleIntegerProperty(staple);
		this.vegetables = new SimpleIntegerProperty(vegetables);
		this.meat = new SimpleIntegerProperty(meat);
		this.gruel = new SimpleIntegerProperty(gruel);
		this.price = new SimpleDoubleProperty(price);
	}
	public Combo(String cid,String name, Integer staple, Integer vegetables, Integer meat, Integer gruel,double price) {
		this.cid = new SimpleStringProperty(cid);
		this.name = new SimpleStringProperty(name);
		this.staple = new SimpleIntegerProperty(staple);
		this.vegetables = new SimpleIntegerProperty(vegetables);
		this.meat = new SimpleIntegerProperty(meat);
		this.gruel = new SimpleIntegerProperty(gruel);
		this.price = new SimpleDoubleProperty(price);
	}

	public final StringProperty cidProperty() {
		return this.cid;
	}

	public final String getCid() {
		return this.cidProperty().get();
	}

	public final void setCid(final String cid) {
		this.cidProperty().set(cid);
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

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final IntegerProperty stapleProperty() {
		return this.staple;
	}
	

	public final int getStaple() {
		return this.stapleProperty().get();
	}
	

	public final void setStaple(final int staple) {
		this.stapleProperty().set(staple);
	}
	

	public final IntegerProperty vegetablesProperty() {
		return this.vegetables;
	}
	

	public final int getVegetables() {
		return this.vegetablesProperty().get();
	}
	

	public final void setVegetables(final int vegetables) {
		this.vegetablesProperty().set(vegetables);
	}
	

	public final IntegerProperty meatProperty() {
		return this.meat;
	}
	

	public final int getMeat() {
		return this.meatProperty().get();
	}
	

	public final void setMeat(final int meat) {
		this.meatProperty().set(meat);
	}
	

	public final IntegerProperty gruelProperty() {
		return this.gruel;
	}
	

	public final int getGruel() {
		return this.gruelProperty().get();
	}
	

	public final void setGruel(final int gruel) {
		this.gruelProperty().set(gruel);
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
	
	

}
