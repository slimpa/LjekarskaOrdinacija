package dto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.DoktorDAO;
import dao.PacijentDAO;


@ManagedBean(name = "doktorDto")
@SessionScoped
public class DoktorDTO {

	private int idDoktora;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdDoktora() {
		return idDoktora;
	}
	public void setIdDoktora(int idDoktora) {
		this.idDoktora = idDoktora;
	}
	
	public DoktorDTO(String ime, String prezime, String username, String password) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
	}

	public DoktorDTO(int idDoktora, String ime, String prezime, String username, String password) {
		super();
		this.idDoktora = idDoktora;
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
	}
	public DoktorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String login() {
		System.out.println(this.username + this.password);
		if(!this.username.equals("") && !this.password.equals("")) {
			System.out.println("nisu null");
			List<DoktorDTO> doktori = DoktorDAO.selectAll();
			for(DoktorDTO doktor : doktori) {
				System.out.println(doktor.ime+ doktor.prezime+ doktor.getUsername() + doktor.getPassword());
				if(doktor.getUsername().equals(username) && doktor.getPassword().equals(password) ) {
					return "registarDoktora.xhtml?faces-redirect=true";
				}
			}
		}
		return "login.xhtml?faces-redirect=true";
	}
	
	public String loginAsPatient() {
		return "pacijentHome.xhtml?faces-redirect=true";
	}
	
	public List<DoktorDTO> getAll(){
		return DoktorDAO.selectAll();
	}
	
	public List<String> getAllString(){
		List<String> retVal = new ArrayList<String>();
		List<DoktorDTO> temp = DoktorDAO.selectAll();
		for(int i = 0; i < temp.size(); i++) {
			retVal.add(new String(temp.get(i).getIme() + " " + temp.get(i).getPrezime()));
		}
		return retVal;
	}
}
