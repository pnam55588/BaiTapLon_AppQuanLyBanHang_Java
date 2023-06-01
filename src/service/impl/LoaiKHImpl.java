package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.LoaiKHDao;
import db.DBConnection;
import entity.LoaiKH;
import service.ILoaiKHService;

public class LoaiKHImpl implements ILoaiKHService{
	LoaiKHDao loaiKHDao;
	public LoaiKHImpl() {
		loaiKHDao = new LoaiKHDao();
	}
	public boolean themLoaiKH(LoaiKH loai) throws SQLException{
		if(loai.getMaLoai() == null || loai.getTenLoai()==null||loai.getMoTa()==null)
			return false;
		if(getDsLoaiKH().contains(loai))
			return false;
		loaiKHDao.themLoaiKH(loai);
		return true;
		
	}
	public List<LoaiKH> getDsLoaiKH() throws SQLException {
		return loaiKHDao.getDsLoaiKH();
	}
	public LoaiKH timMa(String ma) throws SQLException{
		if(ma ==null)
			return null;
		for(LoaiKH loai : getDsLoaiKH()) {
			if(loai.getMaLoai().compareToIgnoreCase(ma.trim())==0)
				return loai;
		}
		return null;
	}
	
}
