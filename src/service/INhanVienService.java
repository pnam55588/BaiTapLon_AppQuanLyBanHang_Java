package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entity.NhanVien;

public interface INhanVienService {
	public boolean themNhanVien(NhanVien nv, String maLoai,String maCa) throws Exception;
	public List<NhanVien> getDsNhanVien() throws SQLException;
	public boolean xoaNhanVien(String ma) throws SQLException;
	public NhanVien timMa(String ma) throws SQLException;
	public List<NhanVien> timKiem(String ma,String ten, String sdt) throws SQLException;
	public boolean suaNhanVien(String ma, String ten,String sdt, double luong, String maCa, String maLoai) throws SQLException;
	
}
