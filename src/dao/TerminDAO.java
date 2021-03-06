package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import dto.TerminDTO;

public class TerminDAO {

	private static String SQL_SELECT_ALL = "SELECT * FROM "
			+ "pacijent.pregled inner join pacijent.doktor inner join pacijent.pacijent where "
			+ "pregled.idPacijenta = pacijent.idPacijent and pregled.idDoktora = doktor.id;";
	
	private static String SQL_SELECT_BY_DATE = "SELECT * FROM pacijent.pregled "
			+ "inner join pacijent.doktor "
			+ "inner join pacijent.pacijent"
			+ " where pregled.idPacijenta = pacijent.idPacijent "
			+ "and pregled.idDoktora = doktor.id and pregled.datumPregleda = ?;";
	
	private static String SQL_INSERT = "INSERT INTO `pacijent`.`pregled` "
			+ "(`datumPregleda`, `idPacijenta`, `idDoktora`) "
			+ "VALUES (?,?,?);\r\n" ;
	public static List<TerminDTO> selectAll(){
		List<TerminDTO> termini = new ArrayList<TerminDTO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				termini.add(new TerminDTO(rs.getInt(1), rs.getDate(2), rs.getInt(3),rs.getInt(4),
						rs.getString(11), rs.getString(12), rs.getString(6), rs.getString(7)));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return termini;
	}
	
	public static List<TerminDTO> selectByDate(){
		List<TerminDTO> termini = new ArrayList<TerminDTO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println(java.sql.Date.valueOf(java.time.LocalDate.now()));
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_SELECT_BY_DATE);
			ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(1)));
			System.out.println(ps);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				termini.add(new TerminDTO(rs.getInt(1), rs.getDate(2), rs.getInt(3),rs.getInt(4),
						rs.getString(11), rs.getString(12), rs.getString(6), rs.getString(7)));
			}
			for(TerminDTO termin: termini) {
				System.out.println(termin.getImePacijenta());
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return termini;
	}
	
	public static List<TerminDTO> poDatumu(TerminDTO terminArg){
		List<TerminDTO> termini = new ArrayList<TerminDTO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		System.out.println(terminArg.getDatumPregleda());
		ZoneId  zid = ZoneId.of("Europe/Belgrade");
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_SELECT_BY_DATE);
			ps.setDate(1, new java.sql.Date(terminArg.getDatumPregleda().getTime()));
//			System.out.println(ps);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				termini.add(new TerminDTO(rs.getInt(1), rs.getDate(2), rs.getInt(3),rs.getInt(4),
						rs.getString(11), rs.getString(12), rs.getString(6), rs.getString(7)));
			}
			for(TerminDTO termin: termini) {
				System.out.println(termin.getImePacijenta());
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return termini;
	}
	
	public static boolean insert(TerminDTO t) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			
			ps.setDate(1, new java.sql.Date(t.getDatumPregleda().getTime()));
			ps.setInt(2, t.getIdPacijenta());
			ps.setInt(3, t.getIdDoktora());
			
			ps.execute();
			
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
