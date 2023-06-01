package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.LoaiSPDao;
import entity.LoaiSP;
import service.ILoaiSPService;

public class LoaiSPServiceImpl implements ILoaiSPService{
	private LoaiSPDao loaiSPDao;
	public LoaiSPServiceImpl() {
		loaiSPDao = new LoaiSPDao();
	}
	
	public boolean themLoaiSP(LoaiSP loai) throws SQLException {
		List<LoaiSP> ds = loaiSPDao.getDSLoaiSP();
		if(ds.contains(loai)) {
			return false;
		}
		loaiSPDao.themLoaiSP(loai);
		return true;
	}

	public List<LoaiSP> getDSLoaiSP() throws SQLException {
		List<LoaiSP> ds = loaiSPDao.getDSLoaiSP();
		return ds;
	}

	public LoaiSP timMa(String ma) throws SQLException {
		if(ma ==null)
			return null;
		for(LoaiSP loai: getDSLoaiSP()) {
			if(loai.getMaLoai().compareToIgnoreCase(ma.trim())==0)
				return loai;
		}
		return null;
	}
}
