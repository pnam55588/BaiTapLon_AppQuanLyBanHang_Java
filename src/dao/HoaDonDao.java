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
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDonDao {
	Connection con;
	public HoaDonDao() {
		con = DBConnection.getInstance().getCon();
	}
	
	public void themHoaDon(HoaDon hd,String maNV,String maKH) throws SQLException {
		String sql = "insert into hoadon values( ?,?,?,?,?,? )";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, hd.getMaHD());
		stmt.setDate(2, Date.valueOf(hd.getNgayLapHD()));
		stmt.setDouble(3, hd.getTienNhan());
		stmt.setDouble(4, hd.getTienThua());
		stmt.setString(5, maNV);
		stmt.setString(6, maKH);
		stmt.executeUpdate();
	}
	public List<HoaDon> getDsHoaDon() throws SQLException {
		String sql = "select * from hoadon";
		PreparedStatement stmt = con.prepareStatement(sql);
		List<HoaDon> ds = new ArrayList<HoaDon>();
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HoaDon hd = new HoaDon();
			hd.setMaHD(rs.getString(1));
			hd.setNgayLapHD(rs.getDate(2).toLocalDate().plusDays(2));
			hd.setTienNhan(rs.getDouble(3));
			hd.setTienThua(rs.getDouble(4));
			hd.setNhanVien(new NhanVien(rs.getString(5)));
			hd.setKhachHang(new KhachHang(rs.getString(6)));
			ds.add(hd);
		}
		return ds;
	}
	
}
