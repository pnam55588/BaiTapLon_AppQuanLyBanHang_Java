package dao;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.LoaiKH;

public class LoaiKHDao {
	private Connection con;
	public LoaiKHDao() {
		con = DBConnection.getInstance().getCon();
	}
	
	public void themLoaiKH(LoaiKH loai) throws SQLException {
		String sql = "insert into loaikh values("
				+ "'"+loai.getMaLoai()+"',"
				+ "'"+ loai.getTenLoai() + "',"
				+ "'"+ loai.getMoTa() + "'"
				+ ")";
		Statement stmt = con.createStatement();
		stmt.execute(sql);
	}
	public List<LoaiKH> getDsLoaiKH() throws SQLException{
		String sql = "select * from loaikh";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<LoaiKH> ds = new ArrayList<LoaiKH>();
		while(rs.next()) {
			LoaiKH loai = new LoaiKH();
			loai.setMaLoai(rs.getString(1));
			loai.setTenLoai(rs.getString(2));
			loai.setMoTa(rs.getString(3));
			ds.add(loai);
		}
		return ds;
	}
}
