package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.CaLamViec;

public class CaLamViecDao {
	private Connection con;
	public CaLamViecDao() {
		con = DBConnection.getInstance().getCon();
	}
	public List<CaLamViec> getDsCaLamViec(){
		String sql = "select * from calamviec";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<CaLamViec> ds = new ArrayList<CaLamViec>();
			while(rs.next()) {
				CaLamViec ca = new CaLamViec();
				ca.setMaCa(rs.getString(1));
				ca.setLoaiCa(rs.getString(2));
				ca.setTimeStart(rs.getString(3));
				ca.setTimeEnd(rs.getString(4));
				ds.add(ca);
			}
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
