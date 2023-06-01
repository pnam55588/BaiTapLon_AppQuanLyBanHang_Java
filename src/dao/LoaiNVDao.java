package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.CaLamViec;
import entity.LoaiNV;

public class LoaiNVDao {
	private Connection con;
	public LoaiNVDao() {
		con = DBConnection.getInstance().getCon();
	}
	public List<LoaiNV> getDsLoaiNV(){
		String sql = "select * from loainv";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<LoaiNV> ds = new ArrayList<LoaiNV>();
			while(rs.next()) {
				LoaiNV loai = new LoaiNV();
				loai.setMaLoai(rs.getString(1));
				loai.setTenLoai(rs.getString(2));
				loai.setMoTa(rs.getString(3));
				ds.add(loai);
			}
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
