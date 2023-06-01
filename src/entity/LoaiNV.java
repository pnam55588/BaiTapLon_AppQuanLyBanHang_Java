package entity;

public class LoaiNV {
	private String maLoai;
	private String tenLoai;
	private String moTa;
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLoai == null) ? 0 : maLoai.hashCode());
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
		LoaiNV other = (LoaiNV) obj;
		if (maLoai == null) {
			if (other.maLoai != null)
				return false;
		} else if (!maLoai.equals(other.maLoai))
			return false;
		return true;
	}
	public LoaiNV(String maLoai, String tenLoai, String moTa) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.moTa = moTa;
	}
	public LoaiNV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiNV(String maLoai) {
		super();
		this.maLoai = maLoai;
	}
	@Override
	public String toString() {
		return "LoaiNV [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", moTa=" + moTa + "]";
	}
	
}
