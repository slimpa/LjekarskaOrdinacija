package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UstanovaDTO;

public class UstanovaDAO {

	
	private static String SQL_SELECT_ALL = "SELECT * FROM pacijent.ustanova;";
	private static String SQL_UPDATE = "UPDATE `pacijent`.`ustanova`"
			+ " SET `radnoVrijeme` = ? WHERE (`idUstanove` = '1');\r\n";
			
	
	public static UstanovaDTO getAll(){
		UstanovaDTO radnoVrijeme = new UstanovaDTO();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				radnoVrijeme = new UstanovaDTO(rs.getInt(1), rs.getString(2));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return radnoVrijeme;
	}
	
	public static boolean update(UstanovaDTO u) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_UPDATE);
			ps.setString(1, u.getRadnoVrijeme());
			
			System.out.println(ps.toString());
			
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
