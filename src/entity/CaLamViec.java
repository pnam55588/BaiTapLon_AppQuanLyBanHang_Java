package entity;

public class CaLamViec {
	private String maCa;
	private String loaiCa;
	private String timeStart;
	private String timeEnd;
	public String getMaCa() {
		return maCa;
	}
	public void setMaCa(String maCa) {
		this.maCa = maCa;
	}
	public String getLoaiCa() {
		return loaiCa;
	}
	public void setLoaiCa(String loaiCa) {
		this.loaiCa = loaiCa;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maCa == null) ? 0 : maCa.hashCode());
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
		CaLamViec other = (CaLamViec) obj;
		if (maCa == null) {
			if (other.maCa != null)
				return false;
		} else if (!maCa.equals(other.maCa))
			return false;
		return true;
	}
	public CaLamViec(String maCa, String loaiCa, String timeStart, String timeEnd) {
		super();
		this.maCa = maCa;
		this.loaiCa = loaiCa;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
	public CaLamViec() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CaLamViec(String maCa) {
		super();
		this.maCa = maCa;
	}
	@Override
	public String toString() {
		return "CaLamViec [maCa=" + maCa + ", loaiCa=" + loaiCa + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd
				+ "]";
	}
	
}
