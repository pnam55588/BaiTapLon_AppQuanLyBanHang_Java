package service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.HoaDonDao;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import service.ICTHoaDonService;
import service.IHoaDonService;

public class HoaDonImpl implements IHoaDonService{
	private HoaDonDao hoaDonDao;
	public HoaDonImpl() {
		hoaDonDao = new HoaDonDao();
	}
	public boolean themHoaDon(HoaDon hd, String maNV, String maKH, List<CTHoaDon> dsCTHoaDon) throws SQLException {
		if(hd== null)
			return false;
		hoaDonDao.themHoaDon(hd, maNV, maKH);
		ICTHoaDonService util = new CTHoaDonImpl();
		for(CTHoaDon ctHoaDon : dsCTHoaDon) {
			if(!ctHoaDon.getHoaDon().getMaHD().equalsIgnoreCase(hd.getMaHD()))
				return false;
			try {
				util.themCTHoaDon(ctHoaDon.getHoaDon().getMaHD(), ctHoaDon.getSanPham().getMaSP(), ctHoaDon.getSoLuong());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public List<HoaDon> getDsHoaDon() throws SQLException {
		return hoaDonDao.getDsHoaDon();
	}

	public HoaDon timMa(String ma) throws SQLException {
		if(ma == null)
			return null;
		for(HoaDon hd : getDsHoaDon()) {
			if(hd.getMaHD().compareToIgnoreCase(ma.trim())==0)
				return hd;
		}
		return null;
	}
	public List<HoaDon> timNgayLapHD(LocalDate ngay) throws SQLException {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		for(HoaDon hd : getDsHoaDon()) {
			if(hd.getNgayLapHD().equals(ngay)) {
				ds.add(hd);
			}
		}
		return ds;
	}
	public List<HoaDon> timThang_nam(int thang, int nam) throws SQLException {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		for(HoaDon hd : getDsHoaDon()) {
			if(
				hd.getNgayLapHD().getMonthValue() == thang
				&& hd.getNgayLapHD().getYear()	== nam
			) {
				ds.add(hd);
			}
		}
		return ds;
	}
	public List<HoaDon> timNam(int nam) throws SQLException {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		for(HoaDon hd : getDsHoaDon()) {
			if(hd.getNgayLapHD().getYear() == nam) {
				ds.add(hd);
			}
		}
		return ds;
	}
	public List<HoaDon> timNgay_thang_nam(int ngay, int thang, int nam) throws SQLException{ // này cũng trả về null khi thiếu thì phải
		List<HoaDon> ds = new ArrayList<HoaDon>();
		for(HoaDon hd : getDsHoaDon()) {
			if(hd.getNgayLapHD().getDayOfMonth() == ngay &&
				hd.getNgayLapHD().getMonthValue() == thang &&
				hd.getNgayLapHD().getYear() == ngay
			) {
				ds.add(hd);
			}
		}
		return ds;
	}
	public List<HoaDon> timMaKH(String ma) throws SQLException { // tui tạo nhiều dạng tìm để sau làm thống kê
		List<HoaDon> ds = new ArrayList<HoaDon>();
		if(ma == null || ma.equals(""))
			return ds;
		for(HoaDon hd : getDsHoaDon()) {
			if(		
					hd.getKhachHang().getMaKH() != null 
					&& hd.getKhachHang().getMaKH().compareToIgnoreCase(ma.trim())==0
			) {
				ds.add(hd);
			}
		}
		return ds;
	}
	public List<HoaDon> timMaNV_thang_nam(String ma, int thang, int nam) throws SQLException {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		if(ma == null || ma.equals("") || thang == 0 || nam == 0) // nhập thiếu ko trả về null
			return ds;
		
		for(HoaDon hd : getDsHoaDon()) {
			if(
					hd.getNhanVien().getMaNV() !=null
					&& hd.getNhanVien().getMaNV().compareToIgnoreCase(ma) == 0  
					&& thang == hd.getNgayLapHD().getMonthValue()
					&& nam == hd.getNgayLapHD().getYear()
			) {
				ds.add(hd);
			}
		}
		return ds;
	}
	public List<HoaDon> timMaNV_NgayLapHD(String ma, LocalDate ngay) throws SQLException {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		if(ma == null || ma.equals("") || ngay.equals("")) 
			return ds;
		for(HoaDon hd : getDsHoaDon()) {
			if(
					hd.getNhanVien().getMaNV() !=null
					&& hd.getNhanVien().getMaNV().compareToIgnoreCase(ma) == 0  
					&& ngay.equals(hd.getNgayLapHD())
			) {
				ds.add(hd);
			}
		}
		return ds;
	}
	public List<HoaDon> timMaHD_ngayLapHD(String ma, LocalDate ngay) throws SQLException {
		List<HoaDon> ds = new ArrayList<HoaDon>();
		if(ma == null && ngay == null)
			return ds;
		for(HoaDon hd : getDsHoaDon()) {
			if(
					(hd.getMaHD().compareToIgnoreCase(ma) == 0 || ma == null || ma.equals("")) 
					&& (hd.getNgayLapHD() == ngay || ngay == null)
			) {
				ds.add(hd);
			}
		}
		return ds;
	}
	

}
