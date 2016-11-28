package qust.gss.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javafx.beans.property.*;

public class ComboSales implements Serializable {
	private static final long serialVersionUID = 2648953344350218447L;

	private IntegerProperty csid;
	private StringProperty cid;
	private StringProperty uno;
	private IntegerProperty count;
	private DoubleProperty total;
	private ObjectProperty<LocalDateTime> saledate;
	
	public ComboSales(){}

	public ComboSales(String cid, Integer count) {
		this.cid = new SimpleStringProperty(cid);
		this.count = new SimpleIntegerProperty(count);
	}
	public ComboSales(String cid,String uno, Integer count,Double total) {
		this.cid = new SimpleStringProperty(cid);
		this.uno = new SimpleStringProperty(uno);
		this.count = new SimpleIntegerProperty(count);
		this.total = new SimpleDoubleProperty(total);
	}

	public final IntegerProperty csidProperty() {
		return this.csid;
	}
	

	public final int getCsid() {
		return this.csidProperty().get();
	}
	

	public final void setCsid(final int csid) {
		this.csidProperty().set(csid);
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
	

	public final IntegerProperty countProperty() {
		return this.count;
	}
	

	public final int getCount() {
		return this.countProperty().get();
	}
	

	public final void setCount(final int count) {
		this.countProperty().set(count);
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

	public final DoubleProperty totalProperty() {
		return this.total;
	}
	

	public final double getTotal() {
		return this.totalProperty().get();
	}
	

	public final void setTotal(final double total) {
		this.totalProperty().set(total);
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
	
	
	
	
	
	
}
