package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.KhachHangDao;
import dao.LoaiKHDao;
import entity.KhachHang;
import entity.NhanVien;
import service.IKhachHangService;
import service.ILoaiKHService;

public class KhachHangImpl implements IKhachHangService{
	private KhachHangDao khachHangDao;
	private ILoaiKHService iLoaiKHService = new LoaiKHImpl();
	
	public KhachHangImpl() {
		khachHangDao = new KhachHangDao();
	}

	public boolean themKhachHang(KhachHang kh, String maLoai) throws SQLException {
		if(kh == null)
			return false;
		if(maLoai == null)
			return false;
		if(getDsKhachHang().contains(kh))
			return false;
		if(iLoaiKHService.timMa(maLoai)==null)
			return false;
		khachHangDao.themKhachHang(kh, maLoai);
		return true;
	}

	public List<KhachHang> getDsKhachHang() throws SQLException {
		return khachHangDao.getDsKhachHang();
	}

	public boolean xoaKhachHang(String ma) throws SQLException {
		if(timMa(ma)==null)
			return false;
		return khachHangDao.xoaKhachHang(ma);
	}

	public KhachHang timMa(String ma) throws SQLException {
		for(KhachHang kh : getDsKhachHang()) {
			if(kh.getMaKH().compareToIgnoreCase(ma.trim())==0)
				return kh;
		}
		return null;
	}
	
	public List<KhachHang> timKiem(String ma, String ten, String sdt) throws SQLException {
		List<KhachHang> ds = getDsKhachHang();
		List<KhachHang> dsTimKiem = new ArrayList<KhachHang>();
		
		if(ma != null && !ma.equals("")) {
			KhachHang kh = timMa(ma);
			if(kh != null) {
				dsTimKiem.add(kh);
				return dsTimKiem;
			}
		}
		
		String re = ".*" + ten.toLowerCase() + ".*"; // 
		String re2 = ".*" +  sdt + ".*"; // nhap 12 no sẽ kiếm hết sdt có chua số 12 trong đó, vậy thêm cái tìm tên các thứ như v luôn
		if(ten ==null && sdt == null)
			return dsTimKiem;
		for(KhachHang kh : ds) {
			if( (kh.getTenKH().toLowerCase().matches(re) || ten==null || ten.equals(""))
				&& (kh.getSdt().matches(re2) || sdt == null || sdt.equals(""))
			) {
				dsTimKiem.add(kh);
			}
		}
		return dsTimKiem;
	}

	public boolean suaKhachHang(String ma, String ten, String sdt, String email, String maloai, String diachi)
			throws SQLException {
		KhachHang kh = timMa(ma);
		if(kh == null)
			return false;
		if(ten == null || ten.equals(""))
			ten = kh.getTenKH();
		if(sdt == null || sdt.equals(""))
			sdt = kh.getSdt();
		if(email == null || email.equals(""))
			email = kh.getEmail();
		if(maloai == null || maloai.equals(""))
			maloai = kh.getLoai().getMaLoai();
		if(diachi == null || diachi.equals(""))
			diachi = kh.getDiaChi();
		
		khachHangDao.suaKhachHang(ma, ten, sdt, email, maloai, diachi);
		return true;
	}

}
