package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entity.CTHoaDon;
import entity.HoaDon;

public interface IHoaDonService {
	public boolean themHoaDon(HoaDon hd, String maNV, String maKH, List<CTHoaDon> dsCTHoaDon) throws SQLException;
	public List<HoaDon> getDsHoaDon() throws SQLException;
	public HoaDon timMa(String ma) throws SQLException;
	public List<HoaDon> timNgayLapHD(LocalDate ngayLapHoaDon) throws SQLException;
	public List<HoaDon> timThang_nam(int thang, int nam) throws SQLException;
	public List<HoaDon> timNam(int nam) throws SQLException;
	public List<HoaDon> timNgay_thang_nam(int ngay, int thang, int nam) throws SQLException;
	public List<HoaDon> timMaKH(String ma) throws SQLException;
	public List<HoaDon> timMaNV_thang_nam(String ma, int thang, int nam) throws SQLException;
	public List<HoaDon> timMaHD_ngayLapHD(String ma, LocalDate ngay) throws SQLException;
	public List<HoaDon> timMaNV_NgayLapHD(String ma, LocalDate ngay) throws SQLException;
}
