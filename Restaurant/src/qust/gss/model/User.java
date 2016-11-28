package qust.gss.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import qust.gss.util.DateUtil;

public class User implements Serializable {
	private static final long serialVersionUID = -5428920895912340872L;

	private StringProperty uno;
	private StringProperty name;
	private StringProperty password;
	private ObjectProperty<LocalDateTime> regDate;
	private DoubleProperty price;

	public User() {
	}
	public User(String uno,String name,Double price){
		this.uno = new SimpleStringProperty(uno);
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(price);
	}

	public User(String uno, String name, String password) {
		this.uno = new SimpleStringProperty(uno);
		this.name = new SimpleStringProperty(name);
		this.password = new SimpleStringProperty(password);
	}
	public User(String uno, String name, String password, String regDate) {
		this.uno = new SimpleStringProperty(uno);
		this.name = new SimpleStringProperty(name);
		this.password = new SimpleStringProperty(password);
		this.regDate = new SimpleObjectProperty<LocalDateTime>(DateUtil.parse(regDate));
	}

	public final StringProperty unoProperty() {
		return this.uno;
	}

	public final String getUno() {
		return this.unoProperty().get();
	}

	public final void setUno(final String uno) {
		this.unoProperty().set(uno);
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

	public final StringProperty passwordProperty() {
		return this.password;
	}

	public final String getPassword() {
		return this.passwordProperty().get();
	}

	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}

	public final ObjectProperty<LocalDateTime> regDateProperty() {
		return this.regDate;
	}

	public final String getRegDate() {
		return DateUtil.format(this.regDateProperty().get());
	}

	public final void setRegDate(final String regDate) {
		this.regDateProperty().set(DateUtil.parse(regDate));
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
	

}
