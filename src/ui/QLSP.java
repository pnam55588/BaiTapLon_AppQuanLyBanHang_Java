package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.button.Button;
import components.panelRound.PanelRound;
import components.roundJTextField.RoundJTextField;
import entity.LoaiSP;
import entity.NhanVien;
import entity.SanPham;
import service.ILoaiSPService;
import service.INhaCungCapService;
import service.ISanPhamService;
import service.impl.LoaiSPServiceImpl;
import service.impl.NhaCungCapImpl;
import service.impl.SanPhamImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QLSP extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private Button btnTK;
	private Button btnQLNV;
	private Button btnQLSP;
	private Button btnQLKH;
	private Button btnTaoHoaDon;
	private DefaultTableModel modelSanPham;
	private JTable tableSanPham;
	private SanPhamImpl sp_dao;
	private RoundJTextField txtMaSP;
	private RoundJTextField txtTenSP;
	private RoundJTextField txtDonGiaMin;
	private Button btnTim;
	private ISanPhamService utilSanPham;
	private Button btnUpdate;
	private INhaCungCapService ncc_dao;
	private ILoaiSPService loaisp_dao;
	private RoundJTextField txtMau;
	private RoundJTextField txtDonGiaMax;
	private Button btnThem;
	private JComboBox cbbChatLieu;
	private JComboBox cbbSize;
	private JComboBox cbbDanhMuc;
	private JPanel pnlMenu;
	private JComboBox cbbChao;
	private Button btnRefresh;
	private NhanVien nhanVienDangNhap;
	private JLabel lblMaSP;
	private JLabel lblTenSP;
	private JLabel lblMau;
	private JLabel lblDonGiaMax;
	private JLabel lblQLSP;
	private PanelRound pnlSanPham;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLSP frame = new QLSP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public QLSP() throws Exception {
		nhanVienDangNhap = TrangChu.nhanVienDangNhap;
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
		cbbChao.setModel(new DefaultComboBoxModel(
				new String[] { nhanVienDangNhap.getTenNV(), "Đổi mật khẩu","Đăng xuất" }));
		cbbChao.setBounds(1263, 28, 140, 21);
		pnlHeader.add(cbbChao);

		JLabel lblAvatar1 = new JLabel("");
		lblAvatar1.setIcon(new ImageIcon(QLSP.class.getResource("/avatar1.jpg")));
		lblAvatar1.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar1);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(QLSP.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1214, 20, 39, 39);
		pnlHeader.add(lblAvatar2);

		PanelRound pnlMenu = new PanelRound(20);
		pnlMenu.setBackground(new Color(222, 217, 214));
		pnlMenu.setBounds(0, 90, 250, 593);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setIcon(new ImageIcon(QLSP.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		btnTaoHoaDon.setBackground(new Color(255, 255, 255));
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setBorderBtnColor(new Color(84, 80, 75));
		btnQLSP.setColorClick(new Color(84, 80, 75));
		btnQLSP.setColorOver(new Color(84, 80, 75));
		btnQLSP.setColor(new Color(84, 80, 75));
		btnQLSP.setBorderColor(new Color(84, 80, 75));
		btnQLSP.setIcon(new ImageIcon(QLSP.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBackground(new Color(84, 80, 75));
		btnQLSP.setForeground(new Color(230, 230, 230));
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setIcon(new ImageIcon(QLSP.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBackground(Color.WHITE);
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon(QLSP.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBackground(Color.WHITE);
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setIcon(new ImageIcon(QLSP.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBackground(Color.WHITE);
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);

		pnlSanPham = new PanelRound(20);
		pnlSanPham.setBackground(new Color(84, 80, 75));
		pnlSanPham.setBounds(250, 90, 1176, 593);
		contentPane.add(pnlSanPham);
		pnlSanPham.setLayout(null);

		txtMaSP = new RoundJTextField(30);
		txtMaSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaSP.setText("");
			}
		});
		txtMaSP.setForeground(new Color(84, 80, 75));
		txtMaSP.setBackground(new Color(243, 238, 231));
		txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtMaSP.setToolTipText("Nhập mã sản phẩm");
		txtMaSP.setText("Mã sản phẩm");
		txtMaSP.setBounds(160, 120, 200, 40);
		pnlSanPham.add(txtMaSP);
		txtMaSP.setColumns(10);

		txtTenSP = new RoundJTextField(30);
		txtTenSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTenSP.setText("");
			}
		});
		txtTenSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTenSP.setToolTipText("Nhập tên sản phẩm");
		txtTenSP.setText("Tên sản phẩm");
		txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenSP.setForeground(new Color(84, 80, 75));
		txtTenSP.setColumns(10);
		txtTenSP.setBackground(new Color(243, 238, 231));
		txtTenSP.setBounds(160, 170, 200, 40);
		pnlSanPham.add(txtTenSP);

		txtDonGiaMin = new RoundJTextField(30);
		txtDonGiaMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDonGiaMin.setText("");
			}
		});
		txtDonGiaMin.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtDonGiaMin.setToolTipText("Đơn giá nhỏ nhất");
		txtDonGiaMin.setText("0.0");
		txtDonGiaMin.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonGiaMin.setForeground(new Color(84, 80, 75));
		txtDonGiaMin.setColumns(10);
		txtDonGiaMin.setBackground(new Color(243, 238, 231));
		txtDonGiaMin.setBounds(460, 120, 150, 40);
		pnlSanPham.add(txtDonGiaMin);

		cbbDanhMuc = new JComboBox();
		cbbDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDanhMuc.setModel(
				new DefaultComboBoxModel(new String[] { "Tất cả", "Quần", "Áo", "Giày", "phụ kiện", "Khác" }));
		cbbDanhMuc.setBackground(new Color(243, 238, 231));
		cbbDanhMuc.setBounds(890, 120, 100, 30);
		pnlSanPham.add(cbbDanhMuc);

		JLabel lblDanhMuc = new JLabel("Danh mục:");
		lblDanhMuc.setForeground(new Color(230, 230, 230));
		lblDanhMuc.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblDanhMuc.setBounds(810, 122, 80, 30);
		pnlSanPham.add(lblDanhMuc);

		cbbSize = new JComboBox();
		cbbSize.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "S", "M", "L", "X", "XL", "XXL"}));
		cbbSize.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbSize.setBackground(new Color(243, 238, 231));
		cbbSize.setBounds(670, 174, 90, 30);
		pnlSanPham.add(cbbSize);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setForeground(new Color(230, 230, 230));
		lblSize.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSize.setBounds(620, 174, 40, 30);
		pnlSanPham.add(lblSize);

		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setForeground(new Color(230, 230, 230));
		lblChatLieu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblChatLieu.setBounds(810, 170, 80, 30);
		pnlSanPham.add(lblChatLieu);

		cbbChatLieu = new JComboBox();
		cbbChatLieu.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "len", "kaki", "vải kaki", "da",
				"vải âu", "vải thun", "vải cotton", "vải", "kim cương", "bạc" }));
		cbbChatLieu.setBackground(new Color(243, 238, 231));
		cbbChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbChatLieu.setBounds(890, 170, 100, 30);
		pnlSanPham.add(cbbChatLieu);

		btnUpdate = new Button("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String masp = (String) tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 0);
				CapNhatSP frame2 = null;
				try {
					frame2 = new CapNhatSP(masp);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frame2.setVisible(true);
//				dispose();
			}
		});
		btnUpdate.setRadius(39);
		btnUpdate.setColorClick(new Color(84, 80, 75));
		btnUpdate.setToolTipText("Cập nhật sản phẩm");
		btnUpdate.setIcon(new ImageIcon(QLSP.class.getResource("/update.png")));
		btnUpdate.setBounds(530, 230, 39, 39);
		pnlSanPham.add(btnUpdate);

		btnTim = new Button("");
		btnTim.setToolTipText("Tìm sản phẩm");
		btnTim.setIcon(new ImageIcon(QLSP.class.getResource("/Search.png")));
		btnTim.setRadius(39);
		btnTim.setColorClick(new Color(84, 80, 75));
		btnTim.setBounds(460, 230, 39, 39);
		pnlSanPham.add(btnTim);

		JLabel lblDonGiaMin = new JLabel("Đơn giá min:");
		lblDonGiaMin.setForeground(new Color(230, 230, 230));
		lblDonGiaMin.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblDonGiaMin.setBounds(370, 124, 100, 30);
		pnlSanPham.add(lblDonGiaMin);

		btnThem = new Button("");
		btnThem.setToolTipText("Thêm sản phẩm");
		btnThem.setRadius(39);
		btnThem.setIcon(new ImageIcon(QLSP.class.getResource("/Add.png")));
		btnThem.setColorClick(new Color(84, 80, 75));
		btnThem.setBounds(600, 230, 39, 39);
		pnlSanPham.add(btnThem);

		String[] colHeader = { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Giá nhập kho", "Số lượng", "Danh mục", "Màu",
				"Size", "Nhà cung cấp", "Ngày sản xuất", "Chất liệu", "Ảnh" };
		modelSanPham = new DefaultTableModel(colHeader, 0);
		tableSanPham = new JTable(modelSanPham) {
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
				case 4: {
					return Integer.class;
				}
				case 5: {
					return String.class;
				}
				case 6: {
					return String.class;
				}
				case 7: {
					return String.class;
				}
				case 8: {
					return String.class;
				}
				case 9: {
					return String.class;
				}
				case 10: {
					return String.class;
				}
				case 11: {
					return String.class;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableSanPham.setRowHeight(25);
		tableSanPham.setForeground(new Color(0, 0, 0));
		JScrollPane sc = new JScrollPane(tableSanPham);
		pnlSanPham.add(sc);
		sc.setBounds(50, 300, 1080, 250);

		txtDonGiaMax = new RoundJTextField(30);
		txtDonGiaMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDonGiaMax.setText("");
			}
		});
		txtDonGiaMax.setToolTipText("Đơn giá lớn nhất");
		txtDonGiaMax.setText("0.0");
		txtDonGiaMax.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonGiaMax.setForeground(new Color(84, 80, 75));
		txtDonGiaMax.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtDonGiaMax.setColumns(10);
		txtDonGiaMax.setBackground(new Color(243, 238, 231));
		txtDonGiaMax.setBounds(460, 170, 150, 40);
		pnlSanPham.add(txtDonGiaMax);

		txtMau = new RoundJTextField(30);
		txtMau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMau.setText("");
			}
		});
		txtMau.setToolTipText("Nhập màu");
		txtMau.setText("Màu");
		txtMau.setHorizontalAlignment(SwingConstants.CENTER);
		txtMau.setForeground(new Color(84, 80, 75));
		txtMau.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtMau.setColumns(10);
		txtMau.setBackground(new Color(243, 238, 231));
		txtMau.setBounds(670, 120, 90, 40);
		pnlSanPham.add(txtMau);
		
		btnRefresh = new Button("");
		btnRefresh.setToolTipText("Refresh bảng");
		btnRefresh.setIcon(new ImageIcon(QLSP.class.getResource("/Refresh.png")));
		btnRefresh.setRadius(39);
		btnRefresh.setColorClick(new Color(84, 80, 75));
		btnRefresh.setBounds(670, 230, 39, 39);
		pnlSanPham.add(btnRefresh);
		
		lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setForeground(new Color(230, 230, 230));
		lblMaSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblMaSP.setBounds(50, 124, 100, 30);
		pnlSanPham.add(lblMaSP);
		
		lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setForeground(new Color(230, 230, 230));
		lblTenSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblTenSP.setBounds(50, 174, 100, 30);
		pnlSanPham.add(lblTenSP);
		
		lblMau = new JLabel("Màu:");
		lblMau.setForeground(new Color(230, 230, 230));
		lblMau.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblMau.setBounds(620, 124, 50, 30);
		pnlSanPham.add(lblMau);
		
		lblDonGiaMax = new JLabel("Đơn giá max:");
		lblDonGiaMax.setForeground(new Color(230, 230, 230));
		lblDonGiaMax.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblDonGiaMax.setBounds(370, 174, 100, 30);
		pnlSanPham.add(lblDonGiaMax);
		
		lblQLSP = new JLabel("Quản lý sản phẩm");
		lblQLSP.setForeground(new Color(230, 230, 230));
		lblQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblQLSP.setBounds(30, 30, 416, 70);
		pnlSanPham.add(lblQLSP);

		// =================================
		// doc du lieu tu database SQL vao Jtable, tao ket noi dao
		sp_dao = new SanPhamImpl();
		ncc_dao = new NhaCungCapImpl();
		loaisp_dao = new LoaiSPServiceImpl();
		updateTable(sp_dao.getDsSanPham());
		// =================================
		// doc du lieu tu database SQL vao Jtable
		utilSanPham = new SanPhamImpl();
		updateTable(sp_dao.getDsSanPham());
		;

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
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnRefresh.addActionListener(this);
		tableSanPham.addMouseListener(this);
		btnTaoHoaDon.addActionListener(this);
		btnQLKH.addActionListener(this);
		btnQLNV.addActionListener(this);
		btnQLSP.addActionListener(this);
		btnTK.addActionListener(this);
		cbbChao.addActionListener(this);
		
		if(nhanVienDangNhap.getLoai().getMaLoai().equals("QL")) {
			btnTaoHoaDon.setBackground(Color.gray);
			btnTaoHoaDon.setEnabled(false);
			btnQLKH.setBackground(Color.gray);
			btnQLKH.setEnabled(false);
		}else {
			btnQLSP.setBackground(Color.GRAY);
			btnQLSP.setEnabled(false);
			btnQLNV.setBackground(Color.GRAY);
			btnQLNV.setEnabled(false);
		}
	}

	private void updateTable(List<SanPham> ds) throws Exception {
		modelSanPham.setRowCount(0);
		for (SanPham sp : ds) {
			Object[] rowData = { sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getGiaNhapKho(), sp.getSoLuong(),
					loaisp_dao.timMa(sp.getLoai().getMaLoai()).getTenLoai(), sp.getMau(), sp.getSize(),
					ncc_dao.timMa(sp.getNcc().getMaNCC()).getTenNCC(), sp.getNgaySanXuat(), sp.getChatLieu(),
					sp.getAnh() };
			modelSanPham.addRow(rowData);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				new ThemSanPham().setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnTim)) {
			if (validData()) {
				
				String maSP = txtMaSP.getText().trim();
				if (maSP.compareToIgnoreCase("Mã sản phẩm") == 0)
					maSP = "";
				String tenSP = txtTenSP.getText().trim();
				if (tenSP.compareToIgnoreCase("Tên sản phẩm") == 0)
					tenSP = "";
				String loaiSP = (String) cbbDanhMuc.getSelectedItem();
				if (loaiSP.compareToIgnoreCase("Tất cả") == 0)
					loaiSP = "";
				String chatLieu = (String) cbbChatLieu.getSelectedItem();
				if (chatLieu.compareToIgnoreCase("Tất cả") == 0)
					chatLieu = "";
				String size = (String) cbbSize.getSelectedItem();
				if (size.compareToIgnoreCase("Tất cả") == 0)
					size = "";
				String mau = txtMau.getText().trim();
				if (mau.compareToIgnoreCase("Màu") == 0)
					mau = "";
				String donGiaMin = txtDonGiaMin.getText().trim();
				if (donGiaMin.compareToIgnoreCase("Min") == 0 || donGiaMin.equals(""))
					donGiaMin = "0.0";
				String donGiaMax = txtDonGiaMax.getText().trim();
				if (donGiaMax.compareToIgnoreCase("Max") == 0 || donGiaMax.equals(""))
					donGiaMax = "0.0";
				Double min = Double.parseDouble(donGiaMin);
				Double max = Double.parseDouble(donGiaMax);
				
				String maLoai = "";
				try {
					if (!loaiSP.equals("")) {
						
						for (LoaiSP loai : loaisp_dao.getDSLoaiSP()) {
							if (loai.getTenLoai().compareToIgnoreCase(loaiSP) == 0)
								maLoai = loai.getMaLoai();
						}
					}
					List<SanPham> ds = sp_dao.timKiem(maSP, tenSP, mau, size, chatLieu, min, max, maLoai);
					if (ds == null)
						ds = sp_dao.getDsSanPham();
					updateTable(ds);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnUpdate)) {
			int row = tableSanPham.getSelectedRow();
			if (row != -1) {
				String maSP = txtMaSP.getText().trim();
				try {
					new CapNhatSP(maSP).setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(pnlSanPham, "Chọn sản phẩm để sửa thông tin!");
			}
		}
		if (o.equals(btnRefresh)) {
			modelSanPham.setRowCount(0);
			try {
				updateTable(sp_dao.getDsSanPham());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			clearTextField();
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
		if (o.equals(btnTK)) {
			TKDoanhThuQL frame1 = null;
			ThongKeNV frame2 = null;
			try {
				frame1 = new TKDoanhThuQL();
				frame2 = new ThongKeNV();
				if(nhanVienDangNhap.getLoai().getMaLoai().equals("QL"))
					frame1.setVisible(true);
				else
					frame2.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dispose();
		}
		if(o.equals(cbbChao)) {
			String s = cbbChao.getSelectedItem().toString();
			if(s.equalsIgnoreCase("Đăng xuất")) {
				DangNhap frame = new DangNhap();
				frame.setVisible(true);
				dispose();
			}
			if(s.equalsIgnoreCase("Đổi mật khẩu")) {
				DoiMatKhau frame = new DoiMatKhau();
				frame.setVisible(true);
			}
		}
		
	}

	private boolean validData() {
		Double min= 0.0;
		Double max = 0.0;
		try {
			min = Double.parseDouble(txtDonGiaMin.getText().trim());
			max = Double.parseDouble(txtDonGiaMax.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Giá min, max của sản phẩm phải là số");
			return false;
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		int i = tableSanPham.getSelectedRow();
//		if (i >= 0) {
//			txtMaSP.setText(tableSanPham.getModel().getValueAt(i, 0).toString());
//			txtTenSP.setText(tableSanPham.getModel().getValueAt(i, 1).toString());
//			txtDonGiaMax.setText(tableSanPham.getModel().getValueAt(i, 2).toString());
//			txtDonGiaMin.setText(tableSanPham.getModel().getValueAt(i, 2).toString());
//			txtMau.setText(tableSanPham.getModel().getValueAt(i, 6).toString());
//			cbbChatLieu.setSelectedItem(tableSanPham.getModel().getValueAt(i, 10));
//			cbbDanhMuc.setSelectedItem(tableSanPham.getModel().getValueAt(i, 5));
//			cbbSize.setSelectedItem(tableSanPham.getModel().getValueAt(i, 7));
//		}
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
	public void clearTextField() {
		txtMaSP.setText("Mã sản phẩm");
		txtTenSP.setText("Tên sản phẩm");
		txtMau.setText("Màu");
		txtDonGiaMax.setText("0.0");
		txtDonGiaMin.setText("0.0");
		cbbDanhMuc.setSelectedItem("Tất cả");
		cbbChatLieu.setSelectedItem("Tất cả");
		cbbSize.setSelectedItem("Tất cả");
	}
}
