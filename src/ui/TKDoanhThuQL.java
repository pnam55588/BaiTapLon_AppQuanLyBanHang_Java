package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.button.Button;
import components.panelRound.PanelRound;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;
import service.ICTHoaDonService;
import service.IHoaDonService;
import service.INhaCungCapService;
import service.INhanVienService;
import service.ISanPhamService;
import service.ITinhToan;
import service.impl.CTHoaDonImpl;
import service.impl.HoaDonImpl;
import service.impl.NhaCungCapImpl;
import service.impl.NhanVienImpl;
import service.impl.SanPhamImpl;
import service.impl.TinhToanImpl;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TKDoanhThuQL extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private NhanVien nv;
	private INhanVienService nv_dao = new NhanVienImpl();
	private INhaCungCapService ncc_dao = new NhaCungCapImpl();
	private ISanPhamService sp_dao = new SanPhamImpl();
	private ITinhToan tinhToan_dao = new TinhToanImpl();
	private ICTHoaDonService cthd_dao = new CTHoaDonImpl();
	private IHoaDonService hd_dao = new HoaDonImpl();
	private JPanel pnlMenu;
	private Button btnTaoHoaDon;
	private Button btnQLSP;
	private Button btnQLKH;
	private Button btnQLNV;
	private Button btnTK;
	private PanelRound pnlHeader;
	private Button btnTKe;
	private Button btnCTHD;
	private JTable tableDoanhThu;
	private DefaultTableModel model;
	private NhanVien nhanVienDangNhap;
	private JComboBox cbbChao;
	private JComboBox cbbThang;
	private JComboBox cbbThang_1;
	private JLabel lblTinhDoanhThu;
	private JLabel lblTinhHoaDon;
	private JMenuItem menuNhanVien;
	private JMenuItem menuDoanhThu;
	private PanelRound pnlBackGround;
	private JTable table;
	private JTable tableSanPham;
	private DefaultTableModel model1;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public TKDoanhThuQL() throws Exception {
		nhanVienDangNhap = TrangChu.nhanVienDangNhap;
		setResizable(false);
		setForeground(new Color(0, 0, 0));
		setTitle("VIR - Urbanus et elegans");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1440, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		pnlHeader = new PanelRound(20);
		pnlHeader.setBounds(0, 0, 1426, 90);
		pnlHeader.setBackground(new Color(230, 230, 230));
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblAvatar1 = new JLabel("");
		lblAvatar1.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/avatar1.jpg")));
		lblAvatar1.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar1);

		JLabel lblTenShop = new JLabel("VIR");
		lblTenShop.setFont(new Font("000 FM Atlas", Font.PLAIN, 24));
		lblTenShop.setBounds(100, 20, 50, 30);
		pnlHeader.add(lblTenShop);

		JLabel lblSlogan = new JLabel("Urbanus et elegans");
		lblSlogan.setFont(new Font("000 Hand Of Sean Pro iCiel", Font.PLAIN, 15));
		lblSlogan.setBounds(100, 40, 140, 20);
		pnlHeader.add(lblSlogan);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1214, 20, 39, 39);
		pnlHeader.add(lblAvatar2);

		cbbChao = new JComboBox();
		cbbChao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbbChao.setModel(
				new DefaultComboBoxModel(new String[] { nhanVienDangNhap.getTenNV(), "Đổi mật khẩu", "Đăng xuất" }));
		cbbChao.setBounds(1260, 30, 140, 25);
		pnlHeader.add(cbbChao);

		PanelRound pnlMenu = new PanelRound(20);
		pnlMenu.setBackground(new Color(222, 217, 214));
		pnlMenu.setBounds(0, 90, 250, 593);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		btnTaoHoaDon.setBackground(new Color(255, 255, 255));
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBackground(Color.WHITE);
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBackground(Color.WHITE);
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBackground(Color.WHITE);
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setForeground(new Color(230, 230, 230));
		btnTK.setBorderBtnColor(new Color(84, 80, 75));
		btnTK.setColorOver(new Color(84, 80, 75));
		btnTK.setColorClick(new Color(84, 80, 75));
		btnTK.setColor(new Color(84, 80, 75));
		btnTK.setBorderColor(new Color(84, 80, 75));
		btnTK.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBackground(new Color(84, 80, 75));
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);

		pnlBackGround = new PanelRound(20);
		pnlBackGround.setBackground(new Color(84, 80, 75));
		pnlBackGround.setBounds(250, 90, 1176, 593);
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);

		JPanel pnlDoanhThu = new JPanel();
		pnlDoanhThu.setBackground(new Color(222, 217, 214));
		pnlDoanhThu.setBounds(10, 50, 630, 533);
		pnlBackGround.add(pnlDoanhThu);
		pnlDoanhThu.setLayout(null);

		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblThang.setBounds(10, 71, 56, 35);
		pnlDoanhThu.add(lblThang);

		cbbThang = new JComboBox();
		cbbThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbThang.setBackground(new Color(243, 238, 231));
		cbbThang.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cbbThang.setBounds(63, 71, 62, 34);
		pnlDoanhThu.add(cbbThang);

		JLabel lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNam.setBounds(135, 69, 40, 39);
		pnlDoanhThu.add(lblNam);

		cbbThang_1 = new JComboBox();
		cbbThang_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbThang_1.setBackground(new Color(243, 238, 231));
		cbbThang_1.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024" }));
		cbbThang_1.setBounds(185, 71, 70, 35);
		pnlDoanhThu.add(cbbThang_1);

		btnTKe = new Button("");
		btnTKe.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Search.png")));
		btnTKe.setToolTipText("Thống kê");
		btnTKe.setColorClick(new Color(84, 80, 75));
		btnTKe.setRadius(39);
		btnTKe.setBounds(265, 67, 39, 39);
		pnlDoanhThu.add(btnTKe);

		btnCTHD = new Button("Xem chi tiết hoá đơn");
		btnCTHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCTHD.setToolTipText("Xem chi tiết hoá đơn");
		btnCTHD.setColorClick(new Color(84, 80, 75));
		btnCTHD.setForeground(new Color(0, 0, 0));
		btnCTHD.setBackground(new Color(255, 255, 255));
		btnCTHD.setColor(new Color(255, 255, 255));
		btnCTHD.setRadius(39);
		btnCTHD.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Numbered list.png")));
		btnCTHD.setBounds(349, 67, 235, 39);
		pnlDoanhThu.add(btnCTHD);

		String[] colHeader = { "Mã hoá đơn", "Ngày lập", "Thành tiền", "Tên nhân viên" };
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
					return String.class;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableDoanhThu.setRowHeight(25);
		tableDoanhThu.setForeground(new Color(0, 0, 0));
		JScrollPane scr = new JScrollPane(tableDoanhThu);
		pnlDoanhThu.add(scr);
		scr.setBounds(10, 115, 610, 393);

		JLabel lblTongDoanhThu = new JLabel("Thống kê doanh thu của cửa hàng:");
		lblTongDoanhThu.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Price list.png")));
		lblTongDoanhThu.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTongDoanhThu.setBounds(0, 10, 555, 40);
		pnlDoanhThu.add(lblTongDoanhThu);

		JPanel pnlTong = new JPanel();
		pnlTong.setBackground(new Color(222, 217, 214));
		pnlTong.setBounds(660, 50, 250, 160);
		pnlBackGround.add(pnlTong);
		pnlTong.setLayout(null);

		JLabel lblDoanhThu = new JLabel("Tổng doanh thu");
		lblDoanhThu.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Price list.png")));
		lblDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoanhThu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 25));
		lblDoanhThu.setBounds(0, 10, 250, 40);
		pnlTong.add(lblDoanhThu);

		lblTinhDoanhThu = new JLabel("0.0");
		lblTinhDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTinhDoanhThu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 30));
		lblTinhDoanhThu.setBounds(0, 90, 250, 50);
		pnlTong.add(lblTinhDoanhThu);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setBackground(new Color(222, 217, 214));
		pnlHoaDon.setBounds(920, 50, 250, 160);
		pnlBackGround.add(pnlHoaDon);
		pnlHoaDon.setLayout(null);

		JLabel lblHoaDon = new JLabel("Số hoá đơn đã bán");
		lblHoaDon.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/List.png")));
		lblHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 25));
		lblHoaDon.setBounds(0, 10, 250, 40);
		pnlHoaDon.add(lblHoaDon);

		lblTinhHoaDon = new JLabel("0");
		lblTinhHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTinhHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 30));
		lblTinhHoaDon.setBounds(0, 90, 250, 50);
		pnlHoaDon.add(lblTinhHoaDon);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1180, 40);
		pnlBackGround.add(menuBar);

		menuDoanhThu = new JMenuItem("Doanh Thu");
		menuDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuDoanhThu.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/Price list.png")));
		menuDoanhThu.setForeground(new Color(230, 230, 230));
		menuDoanhThu.setBackground(new Color(84, 80, 75));
		menuDoanhThu.setHorizontalAlignment(SwingConstants.CENTER);
		menuDoanhThu.setSelected(true);
		menuBar.add(menuDoanhThu);

		menuNhanVien = new JMenuItem("Nhân viên");
		menuNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuNhanVien.setIcon(new ImageIcon(TKDoanhThuQL.class.getResource("/User.png")));
		menuNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuNhanVien);

		JPanel pnlSanPham = new JPanel();
		pnlSanPham.setLayout(null);
		pnlSanPham.setBackground(new Color(222, 217, 214));
		pnlSanPham.setBounds(660, 238, 510, 345);
		pnlBackGround.add(pnlSanPham);

		JLabel lblSP = new JLabel("Sản phẩm sắp hết hàng");
		lblSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 25));
		lblSP.setBounds(0, 10, 500, 40);
		pnlSanPham.add(lblSP);

		String[] colHeader1 = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Nhà cung cấp" };
		model1 = new DefaultTableModel(colHeader1, 0);
		tableSanPham = new JTable(model1) {
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
					return Integer.class;
				}
				case 3: {
					return String.class;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableSanPham.setRowHeight(25);
		tableSanPham.setForeground(new Color(0, 0, 0));
		JScrollPane scr1 = new JScrollPane(tableSanPham);
		pnlSanPham.add(scr1);
		scr1.setBounds(10, 60, 490, 253);

		// =================================
		// doc du lieu tu database SQL vao Table tổng doanh thu, tao ket noi dao
		nv_dao = new NhanVienImpl();
		updateTable(hd_dao.getDsHoaDon());
		updateTable1(sp_dao.getDsSanPham());
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
		
		
		
		btnCTHD.addActionListener(this);
		btnTKe.addActionListener(this);
		btnTaoHoaDon.addActionListener(this);
		btnQLKH.addActionListener(this);
		btnQLNV.addActionListener(this);
		btnQLSP.addActionListener(this);
		btnTK.addActionListener(this);
		cbbChao.addActionListener(this);
		menuNhanVien.addActionListener(this);

		if (nhanVienDangNhap.getLoai().getMaLoai().equals("QL")) {
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

	private void updateTable(List<HoaDon> ds) throws Exception {
		model.setRowCount(0);
		for (HoaDon hd : ds) {
			if (hd.getNhanVien().getMaNV() != null) {
				Object[] rowData = { hd.getMaHD(), hd.getNgayLapHD(), hd.tinhTongTien(),
						nv_dao.timMa(hd.getNhanVien().getMaNV()).getTenNV() };
				model.addRow(rowData);
			} else {
				Object[] rowData = { hd.getMaHD(), hd.getNgayLapHD(), hd.tinhTongTien(), "nhân viên đã xóa" };
				model.addRow(rowData);
			}
		}
	}

	private void updateTable1(List<SanPham> ds) throws Exception {
		model1.setRowCount(0);
		for (SanPham sp : ds) {
			if (sp.getSoLuong() <= 30) {
				if (sp.getNcc().getMaNCC() != null) {
					Object[] rowData = { sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(),
							ncc_dao.timMa(sp.getNcc().getMaNCC()).getTenNCC() };
					model1.addRow(rowData);
				} else {
					Object[] rowData = { sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), "nhà cung cấp đã xoá" };
					model1.addRow(rowData);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnTKe)) {
			int thang = Integer.parseInt(cbbThang.getSelectedItem().toString());
			int nam = Integer.parseInt(cbbThang_1.getSelectedItem().toString());
			List<HoaDon> ds = new ArrayList<HoaDon>();
			try {
				ds = hd_dao.timThang_nam(thang, nam);
				updateTable(ds);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			double dt = 0.0;
			int soHD = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				dt += (double) model.getValueAt(i, 2);
			}
			soHD = model.getRowCount();
			lblTinhDoanhThu.setText(String.format("%.2f", dt));
			lblTinhHoaDon.setText(String.valueOf(soHD));
		}
		if (o.equals(btnCTHD)) {
			try {
				List<CTHoaDon> ds = cthd_dao.timMaHD(model.getValueAt(tableDoanhThu.getSelectedRow(), 0).toString());
				String s = "";
				for (CTHoaDon cthd : ds) {
					s += cthd.toString() + "\n";
				}
				JOptionPane.showMessageDialog(contentPane, s, "chi tiết hóa đơn", -1);
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

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
		if (o.equals(menuNhanVien)) {
			TKDoanhThuNVQL frame = null;
			try {
				frame = new TKDoanhThuNVQL();
				frame.setVisible(true);
				dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		if (o.equals(btnTK)) {
			TKDoanhThuQL frame1 = null;
			ThongKeNV frame2 = null;
			try {
				frame1 = new TKDoanhThuQL();
				frame2 = new ThongKeNV();
				if (nhanVienDangNhap.getLoai().getMaLoai().equals("QL"))
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

	}
}
