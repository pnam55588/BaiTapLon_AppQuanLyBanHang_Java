package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DBConnection;
import entity.LoaiSP;
import entity.NhaCungCap;
import entity.SanPham;

public class SanPhamDao {
	private Connection con;
	public SanPhamDao() {
		this.con = DBConnection.getInstance().getCon();
	}
	
	public void themSanPham(SanPham sp,String maLoai,String maNCC) throws SQLException {
		Date date = Date.from(sp.getNgaySanXuat().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String sql = "insert into SanPham values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, sp.getMaSP());
		stmt.setString(2, sp.getTenSP());
		stmt.setInt(3, sp.getSoLuong());
		stmt.setDouble(4, sp.getGiaNhapKho());
		stmt.setDouble(5, sp.getDonGia());
		stmt.setDate(6, sqlDate);
		stmt.setString(7, sp.getMau());
		stmt.setString(8, sp.getSize());
		stmt.setString(9, sp.getAnh());
		stmt.setString(10, maLoai);
		stmt.setString(11, maNCC);
		stmt.setString(12, sp.getChatLieu());
		stmt.execute();
	}
	public List<SanPham> getDsSanPham() throws SQLException{
		List<SanPham> ds = new ArrayList<SanPham>();
		String sql = "select * from SanPham";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			SanPham sp = new SanPham();
			sp.setMaSP(rs.getString("maSP"));
			sp.setTenSP(rs.getString("tenSp"));
			sp.setSoLuong(rs.getInt("soLuong"));
			sp.setGiaNhapKho(rs.getDouble("giaNhapKho"));
			sp.setDonGia(rs.getDouble("donGia"));
			sp.setMaSP(rs.getString("maSP"));
			sp.setNgaySanXuat(rs.getDate("ngaySanXuat").toLocalDate());
			sp.setMau(rs.getString("mau"));
			sp.setSize(rs.getString("size"));
			sp.setAnh(rs.getString("anh"));
			sp.setLoai(new LoaiSP(rs.getString("maLoai")));
			sp.setNcc(new NhaCungCap(rs.getString("maNCC")));
			sp.setChatLieu(rs.getString("chatLieu"));
			ds.add(sp);
		}
		return ds;
	}
	public boolean xoaSanPham(String ma){
		try {
			String sql = "delete from sanpham where masp = ?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			int n = stmt.executeUpdate();
			return n >0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void suaSanPham(String ma, String ten, double giaNhapKho, int soLuong, 
							String mau, String size, String anh, String maNCC,String chatLieu) throws SQLException {
		String sql = "update sanpham set "
				+ "tensp = ?,"
				+ "gianhapkho = ?,"
				+ "soluong = ?,"
				+ "mau = ?,"
				+ "size = ?,"
				+ "anh = ?,"
				+ "mancc = ?,"
				+ "chatlieu = ?,"
				+ "dongia = ?\n"
				+ "where masp = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ten);
		stmt.setDouble(2, giaNhapKho);
		stmt.setInt(3, soLuong);
		stmt.setString(4, mau);
		stmt.setString(5, size);
		stmt.setString(6, anh);
		stmt.setString(7, maNCC);
		stmt.setString(8, chatLieu);
		stmt.setDouble(9, giaNhapKho*(1+10/100));
		stmt.setString(10, ma);
		
		stmt.executeUpdate();
	}
	
}
