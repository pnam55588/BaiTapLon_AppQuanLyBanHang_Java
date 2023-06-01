package service.impl;

import java.util.List;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import service.INhaCungCapService;

public class NhaCungCapImpl implements INhaCungCapService{

	private NhaCungCapDao nhaCungCapDao;
	public NhaCungCapImpl() {
		nhaCungCapDao = new NhaCungCapDao();
	}
	public boolean themNhaCungCap(NhaCungCap ncc) throws Exception {
		if(ncc == null)
			return false;
		if(getDsNhaCungCap().contains(ncc))
			return false;
		nhaCungCapDao.themNhaCungCap(ncc);
		return true;
	}

	public List<NhaCungCap> getDsNhaCungCap() throws Exception {
		return nhaCungCapDao.getDsNhaCungCap();
	}
	public NhaCungCap timMa(String ma) throws Exception {
		if(ma ==null)
			return null;
		for(NhaCungCap ncc: getDsNhaCungCap()) {
			if(ncc.getMaNCC().compareToIgnoreCase(ma.trim())==0)
				return ncc;
		}
		return null;
	}

}
