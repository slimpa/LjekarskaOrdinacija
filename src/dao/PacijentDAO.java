package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.PacijentDTO;

public class PacijentDAO {
	
	private static String SQL_SELECT_ALL = "select * from pacijent.pacijent;";
	private static String SQL_INSERT = "INSERT INTO `pacijent`.`pacijent` "
			+ "(`ime`, `prezime`, `datumRodjenja`, `adresa`, `telefon`) "
			+ "VALUES (?,?,?,?,?);";
	
	public static List<PacijentDTO> getAll(){
		
		List<PacijentDTO> pacijenti = new ArrayList<PacijentDTO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				pacijenti.add(new PacijentDTO(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getDate(4),rs.getString(5),rs.getString(6)));
			}
			
			ps.close();
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return pacijenti;
	}

public static boolean insertPacijent(PacijentDTO p) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			
			ps.setString(1, p.getIme());
			ps.setString(2, p.getPrezime());
			ps.setDate(3, new java.sql.Date(p.getDatumRodjenja().getTime()));
			ps.setString(4, p.getAdresa());
			ps.setString(5, p.getTelefon());
			
			ps.executeUpdate();
			ps.close();
			
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return false;
	}
}
