package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CTHoaDonDao;
import entity.CTHoaDon;
import service.ICTHoaDonService;

public class CTHoaDonImpl implements ICTHoaDonService{
	private CTHoaDonDao ctHoaDonDao;
	public CTHoaDonImpl() {
		ctHoaDonDao = new CTHoaDonDao();
	}
	public boolean themCTHoaDon(String maHD, String maSP, int soLuong) throws Exception {
		if(maHD == null)
			return false;
		if(maSP == null)
			return false;
		if(soLuong == 0)
			return false;
		return ctHoaDonDao.themCTHoaDon(maHD, maSP, soLuong);
	}

	public List<CTHoaDon> getDsCtHoaDon() throws SQLException {
		return ctHoaDonDao.getDsCtHoaDon();
	}

	//TODO
	public List<CTHoaDon> timMaHD(String ma) throws SQLException {
		List<CTHoaDon> ds = new ArrayList<CTHoaDon>();
		for(CTHoaDon cthd : getDsCtHoaDon()) {
			if(cthd.getHoaDon().getMaHD().compareToIgnoreCase(ma.trim())==0)
				ds.add(cthd);
		}
		return ds;
	}

	public List<CTHoaDon> timMaSP(String ma) throws SQLException {
		List<CTHoaDon> ds = new ArrayList<CTHoaDon>();
		for(CTHoaDon cthd : getDsCtHoaDon()) {
			if(cthd.getSanPham().getMaSP().compareToIgnoreCase(ma.trim())==0)
				ds.add(cthd);
		}
		return ds;
	}


	
	
}
