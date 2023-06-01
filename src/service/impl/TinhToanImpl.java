package service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import service.IHoaDonService;
import service.IKhachHangService;
import service.INhanVienService;
import service.ISanPhamService;
import service.ITinhToan;

public class TinhToanImpl implements ITinhToan{

	public double tinhTongTien(List<CTHoaDon> dsCTHoaDon) throws Exception {
		double tongTien = 0;
		for(CTHoaDon cthd: dsCTHoaDon) {
			tongTien += cthd.tinhTien();
		}
		return tongTien;
	}

	public double tinhTienThua(double tienNhan, List<CTHoaDon> dsCTHoaDon) throws Exception {
		double tongTien = 0;
		try {
			tongTien = tinhTongTien(dsCTHoaDon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  tienNhan - tongTien;
	}
	public String maSanPhamMoi() throws Exception {
		ISanPhamService util = new SanPhamImpl();
		List<SanPham> ds = util.getDsSanPham();
		Random random = new Random();
		String maMoi = null;
		int num = random.nextInt(100);
		DecimalFormat format = new DecimalFormat("000");
		maMoi = "SP" + format.format(num);
		while(util.timMa(maMoi)!=null) {
			maMoi = "SP"  + format.format(num);;
		}
		return maMoi;
	}
	public String maNhanVienMoi() throws Exception {
		INhanVienService util = new NhanVienImpl();
		List<NhanVien> ds = util.getDsNhanVien();
		Random random = new Random();
		String maMoi = null;
		int num = random.nextInt(100);
		DecimalFormat format = new DecimalFormat("000");
		maMoi = "NV" + format.format(num);
		while(util.timMa(maMoi)!=null) {
			maMoi = "NV"  + format.format(num);;
		}
		return maMoi;
	}
	public String maKhachHangMoi() throws Exception{
		IKhachHangService util = new KhachHangImpl();
		List<KhachHang> ds = util.getDsKhachHang();
		Random random = new Random();
		String maMoi = null;
		int num = random.nextInt(100);
		DecimalFormat format = new DecimalFormat("000");
		maMoi = "KH" + format.format(num);
		while(util.timMa(maMoi)!=null) {
			maMoi = "KH"  + format.format(num);;
		}
		return maMoi;
	}
	public String maHoaDonMoi() throws Exception{
		IHoaDonService util = new HoaDonImpl();
		List<HoaDon> ds = util.getDsHoaDon();
		Random random = new Random();
		String maMoi = null;
		int num = random.nextInt(10000);
		DecimalFormat format = new DecimalFormat("00000");
		maMoi = "HD" + format.format(num);
		while(util.timMa(maMoi)!=null) {
			maMoi = "HD"  + format.format(num);;
		}
		return maMoi;
	}
}
