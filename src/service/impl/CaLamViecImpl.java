package service.impl;

import java.util.List;

import dao.CaLamViecDao;
import entity.CaLamViec;
import service.ICaLamViecService;

public class CaLamViecImpl implements ICaLamViecService{
	private CaLamViecDao caLamViecDao;
	public CaLamViecImpl() {
		caLamViecDao = new CaLamViecDao();
	}
	public List<CaLamViec> getDsCaLamViec() throws Exception {
		return caLamViecDao.getDsCaLamViec();
	}

	public CaLamViec timMa(String ma) throws Exception {
		if(ma == null)
			return null;
		for(CaLamViec ca : getDsCaLamViec()) {
			if(ca.getMaCa().compareToIgnoreCase(ma.trim())==0) {
				return ca;
			}
		}
		return null;
	}

}
