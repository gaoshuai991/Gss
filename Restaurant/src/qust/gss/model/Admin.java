package qust.gss.model;

import java.io.Serializable;

import javafx.beans.property.StringProperty;

public class Admin implements Serializable {
	private static final long serialVersionUID = -6389701176541456183L;
	
	private StringProperty ano;
	private StringProperty name;
	private StringProperty password;
	
	public final StringProperty anoProperty() {
		return this.ano;
	}
	
	public final String getAno() {
		return this.anoProperty().get();
	}
	
	public final void setAno(final String ano) {
		this.anoProperty().set(ano);
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
	
	

}
