package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LoaiSPDao;
import dao.SanPhamDao;
import entity.KhachHang;
import entity.SanPham;
import service.ILoaiSPService;
import service.INhaCungCapService;
import service.ISanPhamService;

public class SanPhamImpl implements ISanPhamService {
	private SanPhamDao sanPhamDao;
	private ILoaiSPService loaiSPService = new LoaiSPServiceImpl();
	private INhaCungCapService nhaCungCapService = new NhaCungCapImpl();

	public SanPhamImpl() {
		sanPhamDao = new SanPhamDao();
	}

	public boolean themSanPham(SanPham sp, String maLoai, String maNCC) throws Exception {
		if (sp == null || maLoai == null || maNCC == null)
			return false;
		if (loaiSPService.timMa(maLoai) == null)
			return false;
		if (nhaCungCapService.timMa(maNCC) == null)
			return false;
		if (getDsSanPham().contains(sp))
			return false;
		sanPhamDao.themSanPham(sp, maLoai, maNCC);
		return true;
	}

	public List<SanPham> getDsSanPham() throws Exception {
		return sanPhamDao.getDsSanPham();
	}

	public boolean xoaSanPham(String ma) throws Exception {
		if (timMa(ma) == null)
			return false;
		return sanPhamDao.xoaSanPham(ma);
	}

	public SanPham timMa(String ma) throws Exception {
		if (ma == null)
			return null;
		for (SanPham sp : getDsSanPham()) {
			if (sp.getMaSP().compareToIgnoreCase(ma.trim()) == 0)
				return sp;
		}
		return null;
	}

	public List<SanPham> timKiem(String ma, String ten, String mau, String size, String chatLieu, double giaMin,
			double giaMax, String maLoai) throws Exception {
		List<SanPham> ds = getDsSanPham();
		List<SanPham> dsTimKiem = new ArrayList<SanPham>();

		if (ma != null && !ma.equals("")) {
			SanPham kh = timMa(ma);
			if (kh != null) {
				dsTimKiem.add(kh);
				return dsTimKiem;
			}
		}

		String re = ".*" + ten.toLowerCase() + ".*";
		System.out.println(ten.toLowerCase());
		if (ten == null && mau == null && size == null && chatLieu == null && giaMin == 0.0 && giaMax == 0.0
				&& maLoai == null)
			return dsTimKiem;
		for (SanPham sp : ds) {
			if (true && (sp.getTenSP().toLowerCase().matches(re) || ten == null || ten.equals(""))
					&& (sp.getMau().toLowerCase().compareToIgnoreCase(mau.toLowerCase()) == 0 || mau == null
							|| mau.equals(""))
					&& (sp.getSize().trim().toLowerCase().compareToIgnoreCase(size) == 0 || size == null
							|| size.equals(""))
					&& (sp.getChatLieu().trim().compareToIgnoreCase(chatLieu) == 0 || chatLieu == null
							|| chatLieu.equals(""))
					&& ((sp.getDonGia() >= giaMin && sp.getDonGia() <= giaMax) || (giaMin == 0.0 && giaMax == 0.0)
							|| (sp.getDonGia() <= giaMax && giaMin == 0.0) || (sp.getDonGia() >= giaMin && giaMax == 0.0))
					&& (sp.getLoai().getMaLoai().compareToIgnoreCase(maLoai) == 0 || maLoai == null
							|| maLoai.equals(""))) {
				dsTimKiem.add(sp);
			}
			//dsTimKiem.add(sp);
		}
		return dsTimKiem;
	}

	public boolean kiemTraSoLuong(String maSP, int soLuong) throws Exception {
		if (soLuong == 0)
			return true;
		if (timMa(maSP) == null)
			return false;
		if (timMa(maSP).getSoLuong() >= soLuong)
			return true;
		else
			return false;
	}

	public boolean suaSanPham(String ma, String ten, double giaNhapKho, int soLuong, String mau, String size,
			String anh, String maNCC, String chatLieu) throws Exception {
		SanPham sp = timMa(ma);
		System.out.println(sp);
		if (sp == null)
			return false;
		if (ten == null || ten.equals(""))
			ten = sp.getTenSP();
		if (giaNhapKho == 0.0)
			giaNhapKho = sp.getGiaNhapKho();
		if (soLuong == 0)
			soLuong = sp.getSoLuong();
		if (mau == null || mau.equals(""))
			mau = sp.getMau();
		if (size == null || size.equals(""))
			size = sp.getSize();
		if (maNCC == null || maNCC.equals(""))
			maNCC = sp.getNcc().getMaNCC();
		System.out.println(maNCC);
		if (chatLieu == null || chatLieu.equals(""))
			chatLieu = sp.getChatLieu();
		sanPhamDao.suaSanPham(ma, ten, giaNhapKho, soLuong, mau, size, anh, maNCC, chatLieu);
		return true;
	}

}
