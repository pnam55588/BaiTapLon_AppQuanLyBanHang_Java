package service.impl;

import java.util.List;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import service.ITaiKhoanService;

public class TaiKhoanImpl implements ITaiKhoanService{
	private TaiKhoanDao taiKhoanDao;
	public TaiKhoanImpl() {
		taiKhoanDao = new TaiKhoanDao();
	}
	public boolean themTaiKhoan(TaiKhoan tk) throws Exception {
		if(tk== null)
			return false;
		if(getDsTaiKhoan().contains(tk))
			return false;
		return taiKhoanDao.themTaiKhoan(tk);
	}
	public List<TaiKhoan> getDsTaiKhoan() throws Exception {
		return taiKhoanDao.getDsTaiKhoan();
	}
	public boolean doiMatKhau(String maTK, String mkMoi) throws Exception {
		if(timMa(maTK)==null)
			return false;
		return taiKhoanDao.doiMatKhau(maTK, mkMoi);
	}
	public TaiKhoan timMa(String ma) throws Exception {
		if(ma==null)
			return null;
		for(TaiKhoan tk: getDsTaiKhoan()) {
			if(tk.getUserName().compareTo(ma.trim())==0)
				return tk;
		}
		return null;
	}
	
}
