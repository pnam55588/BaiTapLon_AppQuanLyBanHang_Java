package service;

import java.util.List;

import entity.CTHoaDon;

public interface ITinhToan {
	public double tinhTongTien(List<CTHoaDon> dsCTHoaDon) throws Exception;
	public double tinhTienThua(double tienNhan, List<CTHoaDon> dsCTHoaDon) throws Exception ;
	public String maSanPhamMoi() throws Exception;
	public String maNhanVienMoi() throws Exception ;
	public String maKhachHangMoi() throws Exception;
	public String maHoaDonMoi() throws Exception;
}
