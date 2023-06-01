package service;

import java.util.List;

import entity.CaLamViec;

public interface ICaLamViecService {
	public List<CaLamViec> getDsCaLamViec() throws Exception;
	public CaLamViec timMa(String ma) throws Exception;
}
