package entity;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import service.ICTHoaDonService;
import service.impl.CTHoaDonImpl;

public class HoaDon {
	private String maHD;
	private LocalDate ngayLapHD;
	private double tienNhan;
	private double tienThua;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public double getTienNhan() {
		return tienNhan;
	}
	public void setTienNhan(double tienNhan) {
		this.tienNhan = tienNhan;
	}
	public double getTienThua() {
		return tienThua;
	}
	public void setTienThua(double tienThua) {
		this.tienThua = tienThua;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHD == null) ? 0 : maHD.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		if (maHD == null) {
			if (other.maHD != null)
				return false;
		} else if (!maHD.equals(other.maHD))
			return false;
		return true;
	}
	public HoaDon(String maHD, LocalDate ngayLapHD, double tienNhan, double tienThua, NhanVien nhanVien,
			KhachHang khachHang) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.tienNhan = tienNhan;
		this.tienThua = tienThua;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}
	
	public HoaDon(String maHD, LocalDate ngayLapHD, double tienNhan, double tienThua) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.tienNhan = tienNhan;
		this.tienThua = tienThua;
	}
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", tienNhan=" + tienNhan + ", tienThua=" + tienThua
				+ ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + "]";
	}
	
	public double tinhTongTien() throws Exception {
		ICTHoaDonService util = new CTHoaDonImpl();
		List<CTHoaDon> ds = util.timMaHD(this.maHD);
		double tongTien = 0;
		for(CTHoaDon cthd : ds) {
			tongTien += cthd.tinhTien();
		}
		return tongTien;
		
	}
}
