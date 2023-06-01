package entity;

import service.ISanPhamService;
import service.impl.SanPhamImpl;

public class CTHoaDon {
	private HoaDon hoaDon;
	private SanPham sanPham;
	private int soLuong;
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((sanPham == null) ? 0 : sanPham.hashCode());
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
		CTHoaDon other = (CTHoaDon) obj;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (sanPham == null) {
			if (other.sanPham != null)
				return false;
		} else if (!sanPham.equals(other.sanPham))
			return false;
		return true;
	}
	public CTHoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}
	public CTHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CTHoaDon(HoaDon hoaDon, SanPham sanPham) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
	}
	@Override
	public String toString() {
		ISanPhamService util = new SanPhamImpl();
		try {
			SanPham sp = util.timMa(this.getSanPham().getMaSP());
			return String.format("%-50s%10d%20.2f%20.2f", sp.getTenSP(), this.getSoLuong(), sp.getDonGia(), this.tinhTien());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	
	public double tinhTien() throws Exception {
		ISanPhamService util = new SanPhamImpl();
		SanPham sp = util.timMa(this.getSanPham().getMaSP());
		return soLuong*sp.getDonGia();
	}
}
