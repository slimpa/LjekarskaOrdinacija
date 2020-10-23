package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DoktorDTO;

public class DoktorDAO {
	
	private static String SQL_SELECT_ALL = "SELECT * FROM pacijent.doktor;";
	
	public static List<DoktorDTO> selectAll(){
		List<DoktorDTO> doktori = new ArrayList<DoktorDTO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			ps = conn.prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				doktori.add(new DoktorDTO(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return doktori;
	}

}
