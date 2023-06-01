package service;

import java.util.List;

import entity.LoaiNV;


public interface ILoaiNVService {
	public List<LoaiNV> getDsLoaiNV() throws Exception;
	public LoaiNV timMa(String ma) throws Exception;
}
