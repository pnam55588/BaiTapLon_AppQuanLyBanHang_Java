package service;

import java.util.List;

import entity.TaiKhoan;

public interface ITaiKhoanService {
	public boolean themTaiKhoan(TaiKhoan tk) throws Exception;
	public List<TaiKhoan> getDsTaiKhoan() throws Exception;
	public boolean doiMatKhau(String maTK, String mkMoi) throws Exception;
	public TaiKhoan timMa(String ma) throws Exception;
}
