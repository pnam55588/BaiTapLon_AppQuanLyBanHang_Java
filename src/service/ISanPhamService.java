package service;

import java.sql.SQLException;
import java.util.List;

import entity.NhanVien;
import entity.SanPham;

public interface ISanPhamService {
	public boolean themSanPham(SanPham sp, String maLoai, String maNCC) throws Exception;
	public List<SanPham> getDsSanPham() throws Exception;
	public boolean xoaSanPham(String ma) throws Exception; // 
	public SanPham timMa(String ma) throws Exception;
	public List<SanPham> timKiem(String ma, String ten, String mau, String size, String chatLieu, double giaMin, double giaMax, String maLoai) throws Exception;
	public boolean kiemTraSoLuong(String maSP, int soLuong) throws Exception;
	public boolean suaSanPham(String ma, String ten, double giaNhapKho, int soLuong, 
			String mau, String size, String anh, String maNCC, String chatLieu) throws Exception;

}
