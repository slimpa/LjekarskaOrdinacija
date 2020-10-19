package dto;

import java.sql.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.TerminDAO;

@ManagedBean(name="terminDto")
@SessionScoped
public class TerminDTO {

	private int id;
	private Date datumPregleda;
	private int idPacijenta;
	private int idDoktora;
	private String imePacijenta;
	private String prezimePacijenta;
	private String imeDoktora;
	private String prezimeDoktora;
	public TerminDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TerminDTO(int id, Date datumPregleda, int idPacijenta, int idDoktora) {
		super();
		this.id = id;
		this.datumPregleda = datumPregleda;
		this.idPacijenta = idPacijenta;
		this.idDoktora = idDoktora;
	}
	public TerminDTO(int id, Date datumPregleda, int idPacijenta, int idDoktora, String imePacijenta,
			String prezimePacijenta, String imeDoktora, String prezimeDoktora) {
		super();
		this.id = id;
		this.datumPregleda = datumPregleda;
		this.idPacijenta = idPacijenta;
		this.idDoktora = idDoktora;
		this.imePacijenta = imePacijenta;
		this.prezimePacijenta = prezimePacijenta;
		this.imeDoktora = imeDoktora;
		this.prezimeDoktora = prezimeDoktora;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDatumPregleda() {
		return datumPregleda;
	}
	public void setDatumPregleda(Date datumPregleda) {
		this.datumPregleda = datumPregleda;
	}
	public int getIdPacijenta() {
		return idPacijenta;
	}
	public void setIdPacijenta(int idPacijenta) {
		this.idPacijenta = idPacijenta;
	}
	public int getIdDoktora() {
		return idDoktora;
	}
	public void setIdDoktora(int idDoktora) {
		this.idDoktora = idDoktora;
	}
	public String getImePacijenta() {
		return imePacijenta;
	}
	public void setImePacijenta(String imePacijenta) {
		this.imePacijenta = imePacijenta;
	}
	public String getPrezimePacijenta() {
		return prezimePacijenta;
	}
	public void setPrezimePacijenta(String prezimePacijenta) {
		this.prezimePacijenta = prezimePacijenta;
	}
	public String getImeDoktora() {
		return imeDoktora;
	}
	public void setImeDoktora(String imeDoktora) {
		this.imeDoktora = imeDoktora;
	}
	public String getPrezimeDoktora() {
		return prezimeDoktora;
	}
	public void setPrezimeDoktora(String prezimeDoktora) {
		this.prezimeDoktora = prezimeDoktora;
	}
	
	public List<TerminDTO> getAll(){
		return TerminDAO.selectAll();
	}
	public List<TerminDTO> getByDate(){
		return TerminDAO.selectByDate();
	}
}
