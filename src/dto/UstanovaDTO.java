package dto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.UstanovaDAO;

@ManagedBean(name = "ustanovaDto")
@SessionScoped
public class UstanovaDTO {

	private int idUstanove;
	private String radnoVrijeme;
	public UstanovaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UstanovaDTO(int idUstanove, String radnoVrijeme) {
		super();
		this.idUstanove = idUstanove;
		this.radnoVrijeme = radnoVrijeme;
	}
	public int getIdUstanove() {
		return idUstanove;
	}
	public void setIdUstanove(int idUstanove) {
		this.idUstanove = idUstanove;
	}
	public String getRadnoVrijeme() {
		return radnoVrijeme;
	}
	public void setRadnoVrijeme(String radnoVrijeme) {
		this.radnoVrijeme = radnoVrijeme;
	}
	
	public String vrijeme() {
		UstanovaDTO u = UstanovaDAO.getAll();
		return u.getRadnoVrijeme();
	}
	
	public String promjeni() {
		UstanovaDAO.update(this);
		return "registarDoktora.xhtml?faces-redirect=true";
	}
	
}
