package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.NhaCungCap;

public class NhaCungCapDao {
	private static Connection con;
	public NhaCungCapDao() {
		this.con = DBConnection.getInstance().getCon();
	}
	public void themNhaCungCap(NhaCungCap ncc) throws SQLException {
		String sql = "insert into NhaCungCap values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ncc.getMaNCC());
		stmt.setString(2, ncc.getTenNCC());
		stmt.setString(3, ncc.getSdt());
		stmt.setString(4, ncc.getDiaChi());
		stmt.setString(5, ncc.getWebsite());
		stmt.execute();
	}
	public List<NhaCungCap> getDsNhaCungCap() throws SQLException {
		List<NhaCungCap> ds = new ArrayList<NhaCungCap>();
		String sql = "select * from NhaCungCap";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			NhaCungCap ncc = new NhaCungCap();
			ncc.setMaNCC(rs.getString("maNCC"));
			ncc.setTenNCC(rs.getString("tenNCC"));
			ncc.setDiaChi(rs.getString("diaChi"));
			ncc.setWebsite(rs.getString("website"));
			ds.add(ncc);
		}
		return ds;
	}
	
}
