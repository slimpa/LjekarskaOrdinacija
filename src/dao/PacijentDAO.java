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
	private static String SQL_DELETE_PREGLED = 
			"DELETE FROM `pacijent`.`pregled` WHERE (`idPacijenta` = ?);";
	private static String SQL_DELETE_PACIJENT = 
			"DELETE FROM `pacijent`.`pacijent` WHERE (`idPacijent` = ?);";
	private static String SQL_UPDATE = "UPDATE `pacijent`.`pacijent` "
			+ "SET `idPacijent` = ?,"
			+ "`ime` = ?, "
			+ "`prezime` = ?, "
			+ "`datumRodjenja` = ?,"
			+ " `adresa` = ?,"
			+ " `telefon` = ?"
			+ " WHERE (`idPacijent` = ?);\r\n";
	
	
	public static boolean update(PacijentDTO p) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_UPDATE);
			ps.setInt(1, p.getIdPacijent());
			ps.setString(2, p.getIme());
			ps.setString(3, p.getPrezime());
			ps.setDate(4, new java.sql.Date(p.getDatumRodjenja().getTime()));
			ps.setString(5, p.getAdresa());
			ps.setString(6, p.getTelefon());
			ps.setInt(7, p.getIdPacijent());
			
			System.out.println(ps.toString());
			
			ps.executeUpdate();
			
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return false;
	}
	
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
	
	public static boolean deletePacijent(PacijentDTO p) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		Connection conn2 = null;
		PreparedStatement ps2 = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_DELETE_PREGLED);
			
			ps.setInt(1, p.getIdPacijent());
			
			
			System.out.println(ps.toString());
			
			ps.executeUpdate();
			
			
			ps.close();
			
			conn2 = ConnectionPool.getConnectionPool().checkOut();
			ps2 = conn.prepareStatement(SQL_DELETE_PACIJENT);
			
			ps2.setInt(1, p.getIdPacijent());
			
			
			System.out.println(ps2.toString());
			
			ps2.executeUpdate();
			
			
			ps2.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
			ConnectionPool.getConnectionPool().checkIn(conn2);
		}
		return false;
	}
}
