package service;

import java.sql.SQLException;
import java.util.List;

import entity.CTHoaDon;

public interface ICTHoaDonService {
	public boolean themCTHoaDon(String maHD, String maSP, int soLuong) throws Exception;
	public List<CTHoaDon> getDsCtHoaDon() throws SQLException;
	public List<CTHoaDon> timMaHD(String ma) throws SQLException;
	public List<CTHoaDon> timMaSP(String ma) throws SQLException;
}
