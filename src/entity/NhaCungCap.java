package entity;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String sdt;
	private String diaChi;
	private String website;
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNCC == null) ? 0 : maNCC.hashCode());
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
		NhaCungCap other = (NhaCungCap) obj;
		if (maNCC == null) {
			if (other.maNCC != null)
				return false;
		} else if (!maNCC.equals(other.maNCC))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", sdt=" + sdt + ", diaChi=" + diaChi
				+ ", website=" + website + "]";
	}
	public NhaCungCap(String maNCC, String tenNCC, String sdt, String diaChi, String website) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.website = website;
	}
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}
	
	
}
