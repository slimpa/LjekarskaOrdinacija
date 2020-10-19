package dto;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.PacijentDAO;

@ManagedBean(name = "pacijentDto")
@SessionScoped
public class PacijentDTO {

	private int idPacijent;
	private String ime;
	private String prezime;
	private Date datumRodjenja;
	private String adresa;
	private String telefon;
	
	public PacijentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PacijentDTO(String ime, String prezime, Date datumRodjenja, String adresa, String telefon) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
		this.telefon = telefon;
	}
	
	

	public PacijentDTO(int idPacijent, String ime, String prezime, Date datumRodjenja, String adresa, String telefon) {
		super();
		this.idPacijent = idPacijent;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
		this.telefon = telefon;
	}

	public int getIdPacijent() {
		return idPacijent;
	}

	public void setIdPacijent(int idPacijent) {
		this.idPacijent = idPacijent;
	}

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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPacijent;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PacijentDTO other = (PacijentDTO) obj;
		if (idPacijent != other.idPacijent)
			return false;
		return true;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public String insert() {
		
		System.out.println(this.ime + this.prezime+ this.adresa);
		PacijentDAO.insertPacijent(this);
		return "registarPacijenata.xhtml?faces-redirect-true";
	}
	
	public void delete() {
		System.out.println("obrisi");
	}
	
	public void modify() {
		System.out.println("modifikuj");
	}
}
