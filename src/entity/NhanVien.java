package entity;

import java.time.LocalDate;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String soID;
	private String sdt;
	private String gioiTinh;
	private LocalDate ngaySinh;
	private double luong;
	private LocalDate ngayVaoLam;
	private CaLamViec ca;
	private LoaiNV loai;
	private TaiKhoan taiKhoan;
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getSoID() {
		return soID;
	}
	public void setSoID(String soID) {
		this.soID = soID;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public LocalDate getNgayVaoLam() {
		return ngayVaoLam;
	}
	public void setNgayVaoLam(LocalDate ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}
	public CaLamViec getCa() {
		return ca;
	}
	public void setCa(CaLamViec ca) {
		this.ca = ca;
	}
	public LoaiNV getLoai() {
		return loai;
	}
	public void setLoai(LoaiNV loai) {
		this.loai = loai;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	public NhanVien(String maNV, String tenNV, String soID, String sdt, String gioiTinh, LocalDate ngaySinh,
			double luong, LocalDate ngayVaoLam, CaLamViec ca, LoaiNV loai, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.soID = soID;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.luong = luong;
		this.ngayVaoLam = ngayVaoLam;
		this.ca = ca;
		this.loai = loai;
		this.taiKhoan = taiKhoan;
	}
	
	public NhanVien(String maNV, String tenNV, String soID, String sdt, String gioiTinh, LocalDate ngaySinh,
			double luong, LocalDate ngayVaoLam) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.soID = soID;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.luong = luong;
		this.ngayVaoLam = ngayVaoLam;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", soID=" + soID + ", sdt=" + sdt + ", gioiTinh="
				+ gioiTinh + ", ngaySinh=" + ngaySinh + ", luong=" + luong + ", ngayVaoLam=" + ngayVaoLam + ", ca=" + ca
				+ ", loai=" + loai + ", taiKhoan=" + taiKhoan + "]";
	}
	
}
