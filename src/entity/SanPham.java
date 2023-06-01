package entity;

import java.time.LocalDate;

public class SanPham {
	private String maSP;
	private String tenSP;
	private int soLuong;
	private double giaNhapKho;
	private double donGia;
	private LocalDate ngaySanXuat;
	private String mau;
	private String size;
	private String anh;
	private LoaiSP loai;
	private NhaCungCap ncc;
	private String chatLieu;
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaNhapKho() {
		return giaNhapKho;
	}
	public void setGiaNhapKho(double giaNhapKho) {
		this.giaNhapKho = giaNhapKho;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public LocalDate getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(LocalDate ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public String getMau() {
		return mau;
	}
	public void setMau(String mau) {
		this.mau = mau;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getAnh() {
		return anh;
	}
	public void setAnh(String anh) {
		this.anh = anh;
	}
	public LoaiSP getLoai() {
		return loai;
	}
	public void setLoai(LoaiSP loai) {
		this.loai = loai;
	}
	public NhaCungCap getNcc() {
		return ncc;
	}
	public void setNcc(NhaCungCap ncc) {
		this.ncc = ncc;
	}
	public String getChatLieu() {
		return chatLieu;
	}
	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSP == null) ? 0 : maSP.hashCode());
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
		SanPham other = (SanPham) obj;
		if (maSP == null) {
			if (other.maSP != null)
				return false;
		} else if (!maSP.equals(other.maSP))
			return false;
		return true;
	}
	public SanPham(String maSP, String tenSP, int soLuong, double giaNhapKho, LocalDate ngaySanXuat,
			String mau, String size, String anh, LoaiSP loai, NhaCungCap ncc, String chatLieu) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.giaNhapKho = giaNhapKho;
		this.donGia = giaNhapKho*110/100;
		this.ngaySanXuat = ngaySanXuat;
		this.mau = mau;
		this.size = size;
		this.anh = anh;
		this.loai = loai;
		this.ncc = ncc;
		this.chatLieu = chatLieu;
	}
	
	public SanPham(String maSP, String tenSP, int soLuong, double giaNhapKho, LocalDate ngaySanXuat,
			String mau, String size, String anh, String chatLieu) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.giaNhapKho = giaNhapKho;
		this.donGia = giaNhapKho*110/100;
		this.ngaySanXuat = ngaySanXuat;
		this.mau = mau;
		this.size = size;
		this.anh = anh;
		this.chatLieu = chatLieu;
	}
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", giaNhapKho=" + giaNhapKho
				+ ", donGia=" + donGia + ", ngaySanXuat=" + ngaySanXuat + ", mau=" + mau + ", size=" + size + ", anh="
				+ anh + ", loai=" + loai + ", ncc=" + ncc + ", chatLieu=" + chatLieu + "]";
	}
	
	
	
}
