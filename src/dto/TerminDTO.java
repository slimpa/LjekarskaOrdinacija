package dto;

import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DoktorDAO;
import dao.PacijentDAO;
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
	
	private String doktorSelected;
	private String pacijentSelected;
	private Date datumSelected;
	private List<TerminDTO> terminiPoDatumu = new ArrayList<TerminDTO>();
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
	public String getDoktorSelected() {
		return doktorSelected;
	}
	public void setDoktorSelected(String doktorSelected) {
		this.doktorSelected = doktorSelected;
	}
	public String getPacijentSelected() {
		return pacijentSelected;
	}
	public void setPacijentSelected(String pacijentSelected) {
		this.pacijentSelected = pacijentSelected;
	}
	public Date getDatumSelected() {
		return datumSelected;
	}
	public void setDatumSelected(Date datumSelected) {
		this.datumSelected = datumSelected;
	}
	public List<TerminDTO> getAll(){
		return TerminDAO.selectAll();
	}
	
	public List<TerminDTO> getByDate(){
		return TerminDAO.selectByDate();
	}
	public List<TerminDTO> getTerminiPoDatumu() {
		return terminiPoDatumu;
	}
	public void setTerminiPoDatumu(List<TerminDTO> terminiPoDatumu) {
		this.terminiPoDatumu = terminiPoDatumu;
	}
	
	public String createTermin() {
		
		
		TerminDTO t = new TerminDTO();
		
		String[] pacijent = pacijentSelected.split(" ");
		List<PacijentDTO> pacijenti = PacijentDAO.getAll();
		int idPacijenta = 0;
		for(PacijentDTO p : pacijenti) {
			if(p.getIme().equals(pacijent[0]) && p.getPrezime().equals(pacijent[1])) {
				idPacijenta = p.getIdPacijent();
			}
		}
		
		String[] doktor = doktorSelected.split(" ");
		List<DoktorDTO> doktori = DoktorDAO.selectAll();
		int idDoktora = 0;
		for(DoktorDTO d : doktori) {
			if(d.getIme().equals(doktor[0]) && d.getPrezime().equals(doktor[1])) {
				idDoktora = d.getIdDoktora();
			}
		}
		t.setDatumPregleda(datumSelected);
		t.setIdPacijenta(idPacijenta);
		t.setIdDoktora(idDoktora);
		
		TerminDAO.insert(t);
		
		return "radSaTerminima.xhtml?faces-redirect=true";
	}
	
public String createTerminPacijent() {
		
		
		TerminDTO t = new TerminDTO();
		
		String[] pacijent = pacijentSelected.split(" ");
		List<PacijentDTO> pacijenti = PacijentDAO.getAll();
		int idPacijenta = 0;
		for(PacijentDTO p : pacijenti) {
			if(p.getIme().equals(pacijent[0]) && p.getPrezime().equals(pacijent[1])) {
				idPacijenta = p.getIdPacijent();
			}
		}
		
		String[] doktor = doktorSelected.split(" ");
		List<DoktorDTO> doktori = DoktorDAO.selectAll();
		int idDoktora = 0;
		for(DoktorDTO d : doktori) {
			if(d.getIme().equals(doktor[0]) && d.getPrezime().equals(doktor[1])) {
				idDoktora = d.getIdDoktora();
			}
		}
		t.setDatumPregleda(datumSelected);
		t.setIdPacijenta(idPacijenta);
		t.setIdDoktora(idDoktora);
		
		TerminDAO.insert(t);
		
		return "pacijentHome.xhtml?faces-redirect=true";
	}
	
	public void poDatumu(){
		TerminDTO t = new TerminDTO();
		t.setDatumPregleda(datumSelected);
		this.setTerminiPoDatumu(TerminDAO.poDatumu(t));
	}
}
