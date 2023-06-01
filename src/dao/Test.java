package dao;

import java.sql.SQLException;
import java.util.List;

import entity.HoaDon;
import service.IHoaDonService;
import service.impl.HoaDonImpl;

public class Test {
	public static void main(String[] args) throws SQLException {
		HoaDonDao hd_dao = new HoaDonDao();
		List<HoaDon> l = hd_dao.getDsHoaDon();
		l.forEach(hd -> System.out.println(hd));
	}
}
