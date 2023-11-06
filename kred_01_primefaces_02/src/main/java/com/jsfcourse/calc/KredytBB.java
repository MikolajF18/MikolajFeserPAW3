package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String x="5000";
	private String y="2";
	private String z="10";
	private Double result;
	
	

	public String getX() {
		return x;
	}



	public void setX(String x) {
		this.x = x;
	}



	public String getY() {
		return y;
	}



	public void setY(String y) {
		this.y = y;
	}



	public String getZ() {
		return z;
	}



	public void setZ(String z) {
		this.z = z;
	}



	public Double getResult() {
		return result;
	}



	public void setResult(Double result) {
		this.result = result;
	}

	public void onInputChanged(ValueChangeEvent event) {
       
    }

	@Inject
	FacesContext ctx;

	public boolean obliczRate() {
		try {
			double x = Double.parseDouble(this.x);
			double y = Double.parseDouble(this.y);
			double z = Double.parseDouble(this.z);

			result = (x+((z/100)*x))/(y*12);
			result*=100;
			result=(double)Math.round(result);
			result/=100;
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String kredyt() {
		if (obliczRate()) {
			return "showresult";
		}
		return null;
	}
	
	public String kredyt_AJAX() {
		if (obliczRate()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

}
