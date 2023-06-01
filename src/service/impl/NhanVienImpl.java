package service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.NhanVienDao;
import entity.NhanVien;
import service.INhanVienService;

public class NhanVienImpl implements INhanVienService{
	private NhanVienDao nhanVienDao;
	public NhanVienImpl() {
		nhanVienDao = new NhanVienDao();
	}
	public boolean themNhanVien(NhanVien nv, String maLoai, String maCa) throws Exception {
		if(nv == null)
			return false;
		if(maLoai == null)
			return false;
		if(maCa == null)
			return false;
		return nhanVienDao.themNhanVien(nv, maLoai, maCa);
	}

	public List<NhanVien> getDsNhanVien() throws SQLException {
		return nhanVienDao.getDsNhanVien();
	}

	public boolean xoaNhanVien(String ma) throws SQLException {
		if(ma == null)
			return false;
		return nhanVienDao.xoaNhanVien(ma);
	}

	public NhanVien timMa(String ma) throws SQLException {
		if(ma == null)
			return null;
		for(NhanVien nv : getDsNhanVien()) {
			if(nv.getMaNV().compareToIgnoreCase(ma.trim())==0) {
				return nv;
			}
		}
		return null;
	}
	public List<NhanVien> timKiem(String ma,String ten, String sdt) throws SQLException {
		List<NhanVien> ds = getDsNhanVien();
		List<NhanVien> dsTimKiem = new ArrayList<NhanVien>();
		
		if(ma != null && !ma.equals("")) {
			NhanVien nv = timMa(ma);
			if(nv != null) {
				dsTimKiem.add(nv);
				return dsTimKiem;
			}
		}
		
		String re = ".*" + ten.toLowerCase() + ".*";
		String re2 = ".*" + sdt + ".*";
		if(ten ==null && sdt == null)
			return dsTimKiem;
		for(NhanVien nv : ds) {
			if( (nv.getTenNV().toLowerCase().matches(re) || ten==null || ten.equals(""))
				&& (nv.getSdt().matches(re2) || sdt == null || sdt.equals(""))
			) {
				dsTimKiem.add(nv);
			}
		}
		return dsTimKiem;
	}
	public boolean suaNhanVien(String ma, String ten, String sdt, double luong,
			String maCa, String maLoai) throws SQLException {
		NhanVien nv = timMa(ma);
		if(nv == null)
			return false;
		if(ten == null || ten.equals(""))
			ten = nv.getTenNV();
		if(sdt == null || sdt.equals(""))
			sdt = nv.getSdt();
		if(luong == 0.0)
			luong = nv.getLuong();
		if(maCa == null || maCa.equals(""))
			maCa = nv.getCa().getMaCa();
		if(maLoai == null || maLoai.equals(""))
			maLoai = nv.getLoai().getMaLoai();
		nhanVienDao.suaNhanVien(ma, ten, sdt, luong, maCa, maLoai);
		return true;
	}
	
}
