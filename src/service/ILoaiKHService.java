package service;

import java.sql.SQLException;
import java.util.List;

import entity.LoaiKH;

public interface ILoaiKHService {
	public boolean themLoaiKH(LoaiKH loai) throws SQLException;
	public List<LoaiKH> getDsLoaiKH() throws SQLException;
	public LoaiKH timMa(String ma) throws SQLException;
}
