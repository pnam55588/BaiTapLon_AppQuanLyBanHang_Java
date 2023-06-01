package entity;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String soID;
	private String sdt;
	private double soTienMuaHang;
	private String gioiTinh;
	private String diaChi;
	private String email;
	private LoaiKH loai;
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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
	public double getSoTienMuaHang() {
		return soTienMuaHang;
	}
	public void setSoTienMuaHang(double soTienMuaHang) {
		this.soTienMuaHang = soTienMuaHang;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LoaiKH getLoai() {
		return loai;
	}
	public void setLoai(LoaiKH loai) {
		this.loai = loai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		return true;
	}
	public KhachHang(String maKH, String tenKH, String soID, String sdt, String gioiTinh,
			String diaChi, String email, LoaiKH loai) {
		
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soID = soID;
		this.sdt = sdt;
		this.soTienMuaHang = 0;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.email = email;
		this.loai = loai;
	}
	
	public KhachHang(String maKH, String tenKH, String soID, String sdt, String gioiTinh,
			String diaChi, String email) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soID = soID;
		this.sdt = sdt;
		this.soTienMuaHang = 0;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.email = email;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", soID=" + soID + ", sdt=" + sdt + ", soTienMuaHang="
				+ soTienMuaHang + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", email=" + email + ", loai="
				+ loai + "]";
	}
	
}
