package service;

import java.util.List;

import entity.NhaCungCap;

public interface INhaCungCapService {
	public boolean themNhaCungCap(NhaCungCap ncc) throws Exception;
	public List<NhaCungCap> getDsNhaCungCap() throws Exception;
	public NhaCungCap timMa(String ma) throws Exception;
	
	
}
