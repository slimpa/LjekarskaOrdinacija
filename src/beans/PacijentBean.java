package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.PacijentDAO;
import dto.PacijentDTO;

@ManagedBean(name = "pacijentBean")
@SessionScoped
public class PacijentBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<PacijentDTO> getAll(){
		return PacijentDAO.getAll();
	}

	public String add() {
		return "registarPacijenata.xhtml?faces-redirect=true";
	}
	
	public List<String> getAllString(){
		List<String> retVal = new ArrayList<String>();
		List<PacijentDTO> temp = PacijentDAO.getAll();
		for(int i = 0; i < temp.size(); i++) {
			retVal.add(new String(temp.get(i).getIme() + " " + temp.get(i).getPrezime()));
		}
		return retVal;
	}
}
