package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.TaiKhoan;

public class TaiKhoanDao {
	private Connection con;
	public TaiKhoanDao() {
		con = DBConnection.getInstance().getCon();
	}
	public boolean themTaiKhoan(TaiKhoan tk) {
		String sql = "insert into taikhoan values(?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk.getUserName());
			stmt.setString(2, tk.getPassword());
			stmt.setString(3, tk.getMkCap2());
			int n = stmt.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<TaiKhoan> getDsTaiKhoan(){
		String sql = "select * from taikhoan";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			List<TaiKhoan> ds = new ArrayList<TaiKhoan>();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				tk.setUserName(rs.getString(1));
				tk.setPassword(rs.getString(2));
				tk.setMkCap2(rs.getString(3));
				ds.add(tk);
			}
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean doiMatKhau(String maTK, String mkMoi) {
		String sql = "update TaiKhoan set password = ? where username = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mkMoi);
			stmt.setString(2, maTK);
			int n = stmt.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
