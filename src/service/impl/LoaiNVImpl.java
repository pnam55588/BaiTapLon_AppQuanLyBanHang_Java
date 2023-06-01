package service.impl;

import java.util.List;

import dao.LoaiNVDao;
import entity.LoaiNV;
import service.ILoaiNVService;

public class LoaiNVImpl implements ILoaiNVService{
	private LoaiNVDao loaiNVDao;
	public LoaiNVImpl() {
		loaiNVDao = new LoaiNVDao();
	}

	public List<LoaiNV> getDsLoaiNV() throws Exception {
		return loaiNVDao.getDsLoaiNV();
	}

	public LoaiNV timMa(String ma) throws Exception {
		if(ma ==null)
			return null;
		for(LoaiNV loai: getDsLoaiNV()) {
			if(loai.getMaLoai().compareToIgnoreCase(ma.trim()) == 0)
				return loai;
		}
		return null;
	}
	
}
