package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.LoaiSP;

public class LoaiSPDao {
	private static Connection con;
	
	public LoaiSPDao() {
		this.con = DBConnection.getInstance().getCon();
	}
	public void themLoaiSP(LoaiSP loai) throws SQLException {
		String insert = "insert into LoaiSP values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(insert);
		stmt.setString(1, loai.getMaLoai());
		stmt.setString(2, loai.getTenLoai());
		stmt.setString(3, loai.getMoTa());
		stmt.executeUpdate();
	}
	public List<LoaiSP> getDSLoaiSP() throws SQLException{
		List<LoaiSP> ds = new ArrayList<LoaiSP>();
		String sql = "select * from LoaiSP";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs= stmt.executeQuery();
		while(rs.next()) {
			LoaiSP sp = new LoaiSP(
					rs.getString("maLoai"),
					rs.getString("tenLoai"),
					rs.getString("moTa")
					);
			ds.add(sp);
		}
		return ds;
	}
}
