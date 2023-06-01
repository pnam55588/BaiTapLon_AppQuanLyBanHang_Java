package service;

import java.sql.SQLException;
import java.util.List;

import entity.LoaiSP;

public interface ILoaiSPService {
	public boolean themLoaiSP(LoaiSP loai) throws SQLException;
	public List<LoaiSP> getDSLoaiSP() throws SQLException;
	public LoaiSP timMa(String ma) throws SQLException;
}
