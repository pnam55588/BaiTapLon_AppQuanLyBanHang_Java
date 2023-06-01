package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class CTHoaDonDao {
	private Connection con;
	public CTHoaDonDao() {
		con = DBConnection.getInstance().getCon();
	}
	
	public boolean themCTHoaDon(String maHD, String maSP, int soLuong){
		String sql = "insert into cthoadon values(?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			stmt.setInt(3, soLuong);
			int n = stmt.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<CTHoaDon> getDsCtHoaDon() throws SQLException{
		String sql = "select * from cthoadon";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<CTHoaDon> ds = new ArrayList<CTHoaDon>();
		while(rs.next()) {
			CTHoaDon cthd = new CTHoaDon();
			cthd.setHoaDon(new HoaDon(rs.getString(1)));
			cthd.setSanPham(new SanPham(rs.getString(2)));
			cthd.setSoLuong(rs.getInt(3));
			ds.add(cthd);
		}
		return ds;
	}
}
