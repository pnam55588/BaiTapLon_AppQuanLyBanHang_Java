package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import components.button.Button;
import components.panelRound.PanelRound;
import entity.HoaDon;
import entity.LoaiSP;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;
import service.ICTHoaDonService;
import service.IHoaDonService;
import service.INhanVienService;
import service.ITinhToan;
import service.impl.CTHoaDonImpl;
import service.impl.HoaDonImpl;
import service.impl.LoaiSPServiceImpl;
import service.impl.NhaCungCapImpl;
import service.impl.NhanVienImpl;
import service.impl.SanPhamImpl;
import service.impl.TinhToanImpl;

import javax.swing.JToolBar;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Component;

public class ThongKeNV extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Button btnTaoHoaDon, btnQLKH, btnQLNV, btnQLSP, btnTK;
	private JTable tableDoanhThu;
	private DefaultTableModel model;
	private JPanel pnlMenu;
	private JPanel pnlBackGround;
	private JPanel pnlDoanhThu;
	private JTable tableTop;
	private DefaultTableModel model2;
	private JTable tableKoDoanhThu;
	private DefaultTableModel model3;
	private INhanVienService nv_dao = new NhanVienImpl();
	private ITinhToan tinhToan_dao = new TinhToanImpl();
	private ICTHoaDonService cthd_dao = new CTHoaDonImpl();
	private IHoaDonService hd_dao = new HoaDonImpl();
	private NhanVien nv;
	private String maNV, tenNV;
	private NhanVien nhanVien;
	private JComboBox cbbChao;
	private Button btnTim;
	private JTextField textTong;
	private JComboBox comboBoxThang;
	private JComboBox comboBoxNV;
	private JTextField txtNgay;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */

	public ThongKeNV() throws SQLException, Exception {
		maNV = TrangChu.maNVDangNhap;
		nhanVien = TrangChu.nhanVienDangNhap;
		nv = nhanVien;
		tenNV = nv.getTenNV();
		setResizable(false);
		setTitle("VIR - Urbanus et elegans");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1440, 720);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);

		PanelRound pnlHeader = new PanelRound(20);
		pnlHeader.setBackground(new Color(230, 230, 230));
		pnlHeader.setBounds(0, 0, 1426, 90);
		contentPane.setBackground(new Color(230, 230, 230));
		contentPane.setLayout(null);
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTenShop = new JLabel("VIR");
		lblTenShop.setBounds(100, 20, 56, 27);
		lblTenShop.setFont(new Font("000 FM Atlas", Font.PLAIN, 24));
		pnlHeader.add(lblTenShop);

		JLabel lblSlogan = new JLabel("Urbanus et elegans");
		lblSlogan.setBounds(100, 40, 140, 20);
		lblSlogan.setFont(new Font("000 Hand Of Sean Pro iCiel", Font.PLAIN, 15));
		pnlHeader.add(lblSlogan);

		cbbChao = new JComboBox();
		cbbChao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbbChao.setModel(new DefaultComboBoxModel(new String[] { nhanVien.getTenNV(), "Đổi mật khẩu", "Đăng xuất" }));
		cbbChao.setBounds(1263, 28, 140, 21);
		pnlHeader.add(cbbChao);

		JLabel lblAvatar1 = new JLabel("");
		lblAvatar1.setIcon(new ImageIcon(ThongKeNV.class.getResource("/avatar1.jpg")));
		lblAvatar1.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar1);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(ThongKeNV.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1214, 20, 39, 39);
		pnlHeader.add(lblAvatar2);

		PanelRound pnlMenu = new PanelRound(20);
		pnlMenu.setBackground(new Color(222, 217, 214));
		pnlMenu.setBounds(0, 90, 250, 593);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		btnTaoHoaDon.setBackground(new Color(255, 255, 255));
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBackground(Color.WHITE);
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setIcon(new ImageIcon(ThongKeNV.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBackground(Color.WHITE);
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBackground(Color.WHITE);
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setColor(Color.DARK_GRAY);
		btnTK.setColorOver(Color.DARK_GRAY);
		btnTK.setColorClick(Color.DARK_GRAY);
		btnTK.setBorderBtnColor(Color.DARK_GRAY);
		btnTK.setForeground(Color.WHITE);
		btnTK.setBorderColor(Color.DARK_GRAY);
		btnTK.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBackground(Color.DARK_GRAY);
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);

		pnlBackGround = new PanelRound(20);
		pnlBackGround.setBackground(new Color(84, 80, 75));
		pnlBackGround.setBounds(250, 90, 1176, 593);
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);

		pnlDoanhThu = new JPanel();
		pnlDoanhThu.setBackground(new Color(222, 217, 214));
		pnlDoanhThu.setBounds(10, 50, 630, 533);
		pnlBackGround.add(pnlDoanhThu);
		pnlDoanhThu.setLayout(null);

		JLabel lblTong = new JLabel("Tổng:");
		lblTong.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Price list.png")));
		lblTong.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblTong.setBounds(10, 493, 86, 30);
		pnlDoanhThu.add(lblTong);

		String[] colHeader = { "Mã hoá đơn", "Ngày lập hoá đơn", "Thành tiền", "Tổng tiền sau thuế (10%)" };
		model = new DefaultTableModel(colHeader, 0);
		tableDoanhThu = new JTable(model) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0: {
					return String.class;
				}
				case 1: {
					return String.class;
				}
				case 2: {
					return Double.class;
				}
				case 3: {
					return Double.class;
				}
				default:
					return String.class;
				}
			}
		};
		tableDoanhThu.setRowHeight(25);
		tableDoanhThu.setForeground(new Color(0, 0, 0));
		JScrollPane sc = new JScrollPane(tableDoanhThu);
		pnlDoanhThu.add(sc);
		sc.setBounds(10, 122, 610, 352);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblTenNV.setBounds(10, 64, 100, 30);
		pnlDoanhThu.add(lblTenNV);

		btnTim = new Button("");
		btnTim.setColorOver(new Color(84, 80, 75));
		btnTim.setColorClick(new Color(84, 80, 75));
		btnTim.setRadius(39);
		btnTim.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Search.png")));
		btnTim.setBounds(521, 55, 39, 39);
		pnlDoanhThu.add(btnTim);

		comboBoxNV = new JComboBox();
		comboBoxNV.setBounds(107, 64, 141, 28);
		pnlDoanhThu.add(comboBoxNV);
		String[] dsTen = new String[nv_dao.getDsNhanVien().size()];
		for (int i = 0; i < nv_dao.getDsNhanVien().size(); i++) {
			dsTen[i] = nv_dao.getDsNhanVien().get(i).getTenNV();
		}
		comboBoxNV.setModel(new DefaultComboBoxModel(dsTen));

		JLabel lblThng = new JLabel("Ngày-Tháng-Năm:");
		lblThng.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblThng.setBounds(290, 62, 151, 30);
		pnlDoanhThu.add(lblThng);

		JLabel lblThngKDoanh = new JLabel("Thống kê doanh số bán hàng trong ngày của nhân viên");
		lblThngKDoanh.setBackground(new Color(128, 64, 0));
		lblThngKDoanh.setFont(new Font("Dialog", Font.BOLD, 21));
		lblThngKDoanh.setBounds(10, 0, 597, 39);
		pnlDoanhThu.add(lblThngKDoanh);

		textTong = new JTextField();
		textTong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textTong.setBounds(122, 493, 198, 30);
		pnlDoanhThu.add(textTong);
		textTong.setColumns(10);

		JLabel lblVnd = new JLabel("VND");
		lblVnd.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblVnd.setBounds(329, 493, 56, 30);
		pnlDoanhThu.add(lblVnd);

		JLabel lblDanhSchCc = new JLabel("Danh sách các hóa đơn đã bán:");
		lblDanhSchCc.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDanhSchCc.setBounds(10, 90, 516, 39);
		pnlDoanhThu.add(lblDanhSchCc);
		
		txtNgay = new JTextField("12-11-2022");
		txtNgay.setBounds(422, 64, 86, 30);
		txtNgay.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 11));
		pnlDoanhThu.add(txtNgay);
		txtNgay.setColumns(10);

		JPanel pnlTopNV = new JPanel();
		pnlTopNV.setBackground(new Color(222, 217, 214));
		pnlTopNV.setBounds(670, 50, 500, 250);
		pnlBackGround.add(pnlTopNV);
		pnlTopNV.setLayout(null);

		JLabel lblTopNV = new JLabel("Doanh thu của tất cả nhân viên trong ngày");
		lblTopNV.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Best.png")));
		lblTopNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblTopNV.setBounds(10, 210, 400, 30);
		pnlTopNV.add(lblTopNV);

		String[] colHeader1 = { "Mã nhân viên", "Tên nhân viên", "Tổng doanh thu" };
		model2 = new DefaultTableModel(colHeader1, 0);
		tableTop = new JTable(model2) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0: {
					return String.class;
				}
				case 1: {
					return String.class;
				}
				case 2: {
					return Double.class;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableTop.setRowHeight(25);
		tableTop.setAutoCreateRowSorter(true);
		tableTop.setForeground(new Color(0, 0, 0));
		JScrollPane scr = new JScrollPane(tableTop);
		pnlTopNV.add(scr);
		scr.setBounds(10, 10, 480, 190);

		JPanel pnlKoDoanhThu = new JPanel();
		pnlKoDoanhThu.setBackground(new Color(222, 217, 214));
		pnlKoDoanhThu.setBounds(670, 333, 500, 250);
		pnlBackGround.add(pnlKoDoanhThu);
		pnlKoDoanhThu.setLayout(null);

		JLabel lblKoDoanhThu = new JLabel("Nhân viên không có doanh thu");
		lblKoDoanhThu.setIcon(new ImageIcon(ThongKeNV.class.getResource("/Delete.png")));
		lblKoDoanhThu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblKoDoanhThu.setBounds(10, 210, 250, 30);
		pnlKoDoanhThu.add(lblKoDoanhThu);

		String[] colHeader2 = { "Mã nhân viên", "Tên nhân viên", "Tổng doanh thu" };
		model3 = new DefaultTableModel(colHeader2, 0);
		tableKoDoanhThu = new JTable(model3) {
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0: {
					return String.class;
				}
				case 1: {
					return String.class;
				}
				case 2: {
					return Double.class;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableKoDoanhThu.setRowHeight(25);
		tableKoDoanhThu.setForeground(new Color(0, 0, 0));
		JScrollPane scr2 = new JScrollPane(tableKoDoanhThu);
		scr2.setBounds(10, 10, 480, 190);
		pnlKoDoanhThu.add(scr2);

		// =================================
		// doc du lieu tu database SQL vao Table tổng doanh thu, tao ket noi dao
		nv_dao = new NhanVienImpl();
		// =================================

		
		Button btnHelp = new Button();
		btnHelp.setColor(Color.WHITE);
		btnHelp.setColorClick(Color.WHITE);
		btnHelp.setColorOver(Color.WHITE);
		btnHelp.setForeground(Color.DARK_GRAY);
		btnHelp.setBorderColor(Color.WHITE);
		btnHelp.setBorderBtnColor(Color.WHITE);
		btnHelp.setText("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Help().setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnHelp.setRadius(50);
		btnHelp.setFont(new Font("Dialog", Font.PLAIN, 20));
		btnHelp.setBackground(Color.WHITE);
		btnHelp.setBounds(10, 360, 240, 60);
		pnlMenu.add(btnHelp);
		
		// add sự kiện
		btnTaoHoaDon.addActionListener(this);
		btnQLKH.addActionListener(this);
		btnQLNV.addActionListener(this);
		btnQLSP.addActionListener(this);
		btnTK.addActionListener(this);
		cbbChao.addActionListener(this);
		btnTim.addActionListener(this);

		if (nhanVien.getLoai().getMaLoai().equals("QL")) {
			btnTaoHoaDon.setBackground(Color.gray);
			btnTaoHoaDon.setEnabled(false);
			btnQLKH.setBackground(Color.gray);
			btnQLKH.setEnabled(false);
		} else {
			btnQLSP.setBackground(Color.GRAY);
			btnQLSP.setEnabled(false);
			btnQLNV.setBackground(Color.GRAY);
			btnQLNV.setEnabled(false);
		}
	}

	private void updateTable1(List<HoaDon> ds) throws Exception {
		model.setRowCount(0);
		for (HoaDon hd : ds) {
			Object[] rowData = { hd.getMaHD(), hd.getNgayLapHD(), hd.tinhTongTien() / 1.1, hd.tinhTongTien() };
			model.addRow(rowData);
		}
	}

	private void updateTable2(LocalDate ngay) throws Exception {
		model2.setRowCount(0);
		for (NhanVien nv : nv_dao.getDsNhanVien()) {
			double dt = 0;
			for(HoaDon hd : hd_dao.timMaNV_NgayLapHD(nv.getMaNV(), ngay)) {
				dt += hd.tinhTongTien();
			}
			Object row[] = {nv.getMaNV(), nv.getTenNV(), dt};
			model2.addRow(row);	
			System.out.println(dt);
		}
	}

	private void updateTable3(LocalDate ngay) throws Exception {
		model3.setRowCount(0);
		for (NhanVien nv : nv_dao.getDsNhanVien()) {
			double dt = 0;
			for(HoaDon hd : hd_dao.timMaNV_NgayLapHD(nv.getMaNV(), ngay)) {
				dt += hd.tinhTongTien();
				System.out.println(hd);
			}
			if(dt==0) {
				Object row[] = {nv.getMaNV(), nv.getTenNV(), dt};
				model3.addRow(row);
				
			}
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTaoHoaDon)) {
			TaoHoaDon frame = null;
			try {
				frame = new TaoHoaDon();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			frame.setVisible(true);
			dispose();
		}
		if (o.equals(btnQLKH)) {
			QLKH frame = null;
			try {
				frame = new QLKH();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			frame.setVisible(true);
			dispose();
		}
		if (o.equals(btnQLNV)) {
			QLNV frame = null;
			try {
				frame = new QLNV();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			frame.setVisible(true);
			dispose();
		}
		if (o.equals(btnQLSP)) {
			QLSP frame = null;
			try {
				frame = new QLSP();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			frame.setVisible(true);
			dispose();
		}
		if (o.equals(btnTK)) {
			TKDoanhThuQL frame1 = null;
			ThongKeNV frame2 = null;
			try {
				frame1 = new TKDoanhThuQL();
				frame2 = new ThongKeNV();
				if (nhanVien.getLoai().getMaLoai().equals("QL"))
					frame1.setVisible(true);
				else
					frame2.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dispose();
		}
		if (o.equals(cbbChao)) {
			String s = cbbChao.getSelectedItem().toString();
			if (s.equalsIgnoreCase("Đăng xuất")) {
				DangNhap frame = new DangNhap();
				frame.setVisible(true);
				dispose();
			}
			if (s.equalsIgnoreCase("Đổi mật khẩu")) {
				DoiMatKhau frame = new DoiMatKhau();
				frame.setVisible(true);
			}
		}
		if (o.equals(btnTim)) {
			String ma = "";
			String ngaylap = txtNgay.getText().trim();
			LocalDate ngay = null;
			try {
				ngay = LocalDate.parse(ngaylap, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(contentPane,"Nhập đúng định dạng dd-MM-yyyy");
			}
			
			try {
				for (NhanVien nv : nv_dao.getDsNhanVien()) {
					if (nv.getTenNV().equals(comboBoxNV.getSelectedItem().toString())) {
						ma = nv.getMaNV();
						break;
					}
				}
				List<HoaDon> ds = new ArrayList<HoaDon>();
				for (HoaDon hd : hd_dao.timNgayLapHD(ngay)) {
					System.out.println(hd);
					if (hd.getNhanVien().getMaNV().equals(ma)) {
						ds.add(hd);
					}
				}
				updateTable1(ds);
				updateTable2(ngay);
				updateTable3(ngay);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			double tong = 0.0;
			for (int i = 0; i < model.getRowCount(); i++) {
				tong += (double) model.getValueAt(i, 3);
			}
			textTong.setText(String.format("%.0f", tong));
		}
	}

	public static void main(String[] args) throws SQLException, Exception {
		TKDoanhThuNVQL app = new TKDoanhThuNVQL();
		app.setVisible(true);
	}
}