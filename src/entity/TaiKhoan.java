package entity;

public class TaiKhoan {
	private String userName;
	private String password;
	private String mkCap2;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMkCap2() {
		return mkCap2;
	}
	public void setMkCap2(String mkCap2) {
		this.mkCap2 = mkCap2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		TaiKhoan other = (TaiKhoan) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	public TaiKhoan(String userName, String password, String mkCap2) {
		super();
		this.userName = userName;
		this.password = password;
		this.mkCap2 = mkCap2;
	}
	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String userName) {
		super();
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "TaiKhoan [userName=" + userName + ", password=" + password + ", mkCap2=" + mkCap2 + "]";
	}
	
}
