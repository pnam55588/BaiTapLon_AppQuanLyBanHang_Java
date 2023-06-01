package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.CaLamViec;
import entity.LoaiNV;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienDao {
	private Connection con;
	public NhanVienDao() {
		this.con = DBConnection.getInstance().getCon();
	}
	
	public boolean themNhanVien(NhanVien nv, String maLoai,String maCa) throws Exception{
		String sql = "insert  into taikhoan values (?,?,?); \n"
				+ "insert into nhanvien values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getMaNV()); // username la maNV
			stmt.setString(2, "1");
			stmt.setString(3, nv.getSdt()); // mkCap2 la sdt
			
			stmt.setString(4, nv.getMaNV());
			stmt.setString(5, nv.getTenNV());
			stmt.setString(6, nv.getSoID());
			stmt.setString(7, nv.getSdt());
			stmt.setString(8, nv.getGioiTinh());
			stmt.setDate(9, Date.valueOf(nv.getNgaySinh()));
			stmt.setDouble(10, nv.getLuong());
			stmt.setDate(11, Date.valueOf(nv.getNgayVaoLam()));
			stmt.setString(12, maCa);
			stmt.setString(13, maLoai);
			stmt.setString(14, nv.getMaNV()); //username la maNV
			
			int n = stmt.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public List<NhanVien> getDsNhanVien() throws SQLException{
		String sql = "select * from Nhanvien";
		PreparedStatement stmt = con.prepareStatement(sql);
		List<NhanVien> ds = new ArrayList<NhanVien>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			NhanVien nv = new NhanVien();
			nv.setMaNV(rs.getString(1));
			nv.setTenNV(rs.getString(2));
			nv.setSoID(rs.getString(3));
			nv.setSdt(rs.getString(4));
			nv.setGioiTinh(rs.getString(5));
			nv.setNgaySinh(rs.getDate(6).toLocalDate());
			nv.setLuong(rs.getDouble(7));
			nv.setNgayVaoLam(rs.getDate(8).toLocalDate());
			nv.setCa(new CaLamViec(rs.getString(9)));
			nv.setLoai(new LoaiNV(rs.getString(10)));
			nv.setTaiKhoan(new TaiKhoan(rs.getString(11)));
			ds.add(nv);
		}
		return ds;
	}
	public boolean xoaNhanVien(String ma) {
		
		String sql1 = "update hoadon set manv = null where manv= '"+ma+"'";
		String sql2 = "delete from nhanvien where manv = '"+ma+"'";
		try {
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			
			stmt1.execute();
			int n = stmt2.executeUpdate();
			return n>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void suaNhanVien(String ma, String ten,String sdt, double luong, String maCa, String maLoai) throws SQLException {
		String sql = "update nhanvien set "
				+ "tennv = ?,"
				+ "sdt = ?,"
				+ "luong = ?,"
				+ "maca = ?,"
				+ "maloai = ?\n"
				+ "where manv = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ten);
		stmt.setString(2, sdt);
		stmt.setDouble(3, luong);
		stmt.setString(4, maCa);
		stmt.setString(5, maLoai);
		stmt.setString(6, ma);
		stmt.execute();
	}
}
