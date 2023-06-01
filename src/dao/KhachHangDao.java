package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.KhachHang;
import entity.LoaiKH;

public class KhachHangDao {
	Connection con;
	public KhachHangDao() {
		con = DBConnection.getInstance().getCon();
	}
	public void themKhachHang(KhachHang kh, String maLoai) throws SQLException {
		String sql = "insert into khachhang values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, kh.getMaKH());
		stmt.setString(2, kh.getTenKH());
		stmt.setString(3, kh.getSoID());
		stmt.setString(4, kh.getSdt() );
		stmt.setDouble(5, kh.getSoTienMuaHang());
		stmt.setString(6, kh.getGioiTinh()  );
		stmt.setString(7, kh.getDiaChi()  );
		stmt.setString(8, kh.getEmail() );
		stmt.setString(9, maLoai);
		
		stmt.executeUpdate();
	}
	public List<KhachHang> getDsKhachHang() throws SQLException{
		String sql = "select * from khachhang";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<KhachHang> ds = new ArrayList<KhachHang>();
		while(rs.next()) {
			KhachHang kh= new KhachHang();
			kh.setMaKH(rs.getString(1));
			kh.setTenKH(rs.getString(2));
			kh.setSoID(rs.getString(3));
			kh.setSdt(rs.getString(4));
			kh.setSoTienMuaHang(rs.getDouble(5));
			kh.setGioiTinh(rs.getString(6));
			kh.setDiaChi(rs.getString(7));
			kh.setEmail(rs.getString(8));
			kh.setLoai(new LoaiKH(rs.getString(9)));
			ds.add(kh);
		}
		return ds;
	}
	public boolean xoaKhachHang(String ma){
		try {
			String sql1 = "update hoadon set makh = null where makh = ? "; 
			String sql2 = "delete from khachhang where makh = ?";
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			
			stmt1.setString(1, ma);
			stmt2.setString(1, ma);
			stmt1.execute();
			int n = stmt2.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void suaKhachHang(String ma, String ten, String sdt, String email,String maloai, String diachi) throws SQLException {
		String sql ="update khachhang set "
				+ "tenkh = ?,"
				+ "sdt = ?, "
				+ "email = ?, "
				+ "maLoai = ?,"
				+ "diachi = ? \n"
				+ "where makh = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ten);
		stmt.setString(2, sdt);
		stmt.setString(3, email);
		stmt.setString(4, maloai);
		stmt.setString(5, diachi);
		stmt.setString(6, ma);
		stmt.execute();
	}
}
