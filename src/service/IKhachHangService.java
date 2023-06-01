package service;

import java.sql.SQLException;
import java.util.List;

import dao.KhachHangDao;
import entity.KhachHang;

public interface IKhachHangService {
	public boolean themKhachHang(KhachHang kh, String maLoai) throws SQLException;
	public List<KhachHang> getDsKhachHang() throws SQLException;
	public boolean xoaKhachHang(String ma) throws SQLException;
	public KhachHang timMa(String ma) throws SQLException;
	public List<KhachHang> timKiem(String ma, String ten, String sdt) throws SQLException;
	public boolean suaKhachHang(String ma, String ten, String sdt, String email,String maloai, String diachi) throws SQLException;
	
}
