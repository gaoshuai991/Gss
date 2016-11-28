package qust.gss.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesRecord implements Serializable {
	private static final long serialVersionUID = -1141924811331086153L;

	private IntegerProperty srid;
	private StringProperty uno;
	private StringProperty fid;
	private IntegerProperty amount;
	private DoubleProperty total;
	private ObjectProperty<LocalDateTime> saledate;

	public SalesRecord() {
	}

	public SalesRecord(String uno, String fid, Integer count, Double total) {
		this.uno = new SimpleStringProperty(uno);
		this.fid = new SimpleStringProperty(fid);
		this.amount = new SimpleIntegerProperty(count);
		this.total = new SimpleDoubleProperty(total);
	}

	public SalesRecord(String uno, String fid, Integer count, Double total, LocalDateTime saledate) {
		this.uno = new SimpleStringProperty(uno);
		this.fid = new SimpleStringProperty(fid);
		this.amount = new SimpleIntegerProperty(count);
		this.total = new SimpleDoubleProperty(total);
		this.saledate = new SimpleObjectProperty<LocalDateTime>(saledate);
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

	public final StringProperty fidProperty() {
		return this.fid;
	}

	public final String getFid() {
		return this.fidProperty().get();
	}

	public final void setFid(final String fid) {
		this.fidProperty().set(fid);
	}

	public final IntegerProperty amountProperty() {
		return this.amount;
	}

	public final int getAmount() {
		return this.amountProperty().get();
	}

	public final void setAmount(final int count) {
		this.amountProperty().set(count);
	}

	public final DoubleProperty totalProperty() {
		return this.total;
	}

	public final double getTotal() {
		return this.totalProperty().get();
	}

	public final void setTotal(final double total) {
		this.totalProperty().set(total);
	}

	public final ObjectProperty<LocalDateTime> saledateProperty() {
		return this.saledate;
	}

	public final LocalDateTime getSaledate() {
		return this.saledateProperty().get();
	}

	public final void setSaledate(final LocalDateTime saledate) {
		this.saledateProperty().set(saledate);
	}

	public final IntegerProperty sridProperty() {
		return this.srid;
	}

	public final int getSrid() {
		return this.sridProperty().get();
	}

	public final void setSrid(final int srid) {
		this.sridProperty().set(srid);
	}

}
