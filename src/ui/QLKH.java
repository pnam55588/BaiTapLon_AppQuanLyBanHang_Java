package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.button.Button;
import components.panelRound.PanelRound;
import components.roundJTextField.RoundJTextField;
import entity.KhachHang;
import entity.NhanVien;
import service.IKhachHangService;
import service.ILoaiKHService;
import service.impl.KhachHangImpl;
import service.impl.LoaiKHImpl;

public class QLKH extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private Button btnTaoHoaDon;
	private Button btnQLSP;
	private Button btnQLKH;
	private Button btnQLNV;
	private Button btnTK;
	private PanelRound pnlKhachHang;
	private RoundJTextField txtMaKH;
	private RoundJTextField txtTenKH;
	private RoundJTextField txtSDT;
	private Button btnUpdate;
	private Button btnTim;
	private Button btnThem;
	private DefaultTableModel modelKhachHang;
	private JTable tableKhachHang;
	private IKhachHangService kh_dao;
	private ILoaiKHService loaikh_dao;
	private JComboBox cbbChao;
	private Button btnXoa;
	private Button btnRefresh;
	private NhanVien nhanVienDangNhap;
	private JLabel lblMaKH;
	private JLabel lblTenKH;
	private JLabel lblSDT;
	private JLabel lblTable;
	private JLabel lblQLKH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLKH frame = new QLKH();
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
	 * @throws SQLException
	 */
	public QLKH() throws SQLException, Exception {
		nhanVienDangNhap = TrangChu.nhanVienDangNhap;
		setResizable(false);
		setTitle("VIR - Urbanus et elegans");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1440, 720);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		lblAvatar1.setIcon(new ImageIcon(QLKH.class.getResource("/avatar1.jpg")));
		lblAvatar1.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar1);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(QLKH.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1214, 20, 39, 39);
		pnlHeader.add(lblAvatar2);

		PanelRound pnlMenu = new PanelRound(20);
		pnlMenu.setBackground(new Color(222, 217, 214));
		pnlMenu.setBounds(0, 90, 250, 593);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setIcon(new ImageIcon(QLKH.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		btnTaoHoaDon.setBackground(new Color(255, 255, 255));
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setIcon(new ImageIcon(QLKH.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBackground(Color.WHITE);
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setColorOver(new Color(84, 80, 75));
		btnQLKH.setColorClick(new Color(84, 80, 75));
		btnQLKH.setColor(new Color(84, 80, 75));
		btnQLKH.setBorderColor(new Color(84, 80, 75));
		btnQLKH.setIcon(new ImageIcon(QLKH.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBackground(new Color(84, 80, 75));
		btnQLKH.setForeground(new Color(230, 230, 230));
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon(QLKH.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBackground(Color.WHITE);
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setIcon(new ImageIcon(QLKH.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBackground(Color.WHITE);
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);

		pnlKhachHang = new PanelRound(20);
		pnlKhachHang.setBackground(new Color(84, 80, 75));
		pnlKhachHang.setBounds(250, 90, 1176, 593);
		contentPane.add(pnlKhachHang);
		pnlKhachHang.setLayout(null);

		txtMaKH = new RoundJTextField(30);
		txtMaKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaKH.setText("");
			}
		});
		txtMaKH.setForeground(new Color(84, 80, 75));
		txtMaKH.setBackground(new Color(243, 238, 231));
		txtMaKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtMaKH.setToolTipText("Nhập mã khách hàng");
		txtMaKH.setText("Mã khách hàng");
		txtMaKH.setBounds(170, 150, 200, 40);
		pnlKhachHang.add(txtMaKH);
		txtMaKH.setColumns(10);

		txtTenKH = new RoundJTextField(30);
		txtTenKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTenKH.setText("");
			}
		});
		txtTenKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTenKH.setToolTipText("Nhập tên khách hàng");
		txtTenKH.setText("Tên khách hàng");
		txtTenKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKH.setForeground(new Color(84, 80, 75));
		txtTenKH.setColumns(10);
		txtTenKH.setBackground(new Color(243, 238, 231));
		txtTenKH.setBounds(380, 150, 300, 40);
		pnlKhachHang.add(txtTenKH);

		txtSDT = new RoundJTextField(30);
		txtSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSDT.setText("");
			}
		});
		txtSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSDT.setToolTipText("Nhập số điện thoại");
		txtSDT.setText("Số điện thoại");
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDT.setForeground(new Color(84, 80, 75));
		txtSDT.setColumns(10);
		txtSDT.setBackground(new Color(243, 238, 231));
		txtSDT.setBounds(690, 150, 300, 40);
		pnlKhachHang.add(txtSDT);

		btnRefresh = new Button("");
		btnRefresh.setIcon(new ImageIcon(QLKH.class.getResource("/Refresh.png")));
		btnRefresh.setToolTipText("Refresh bảng");
		btnRefresh.setRadius(39);
		btnRefresh.setColorClick(new Color(84, 80, 75));
		btnRefresh.setBounds(350, 220, 39, 39);
		pnlKhachHang.add(btnRefresh);

		btnUpdate = new Button("");
		btnUpdate.setRadius(39);
		btnUpdate.setColorClick(new Color(84, 80, 75));
		btnUpdate.setToolTipText("Sửa thông tin khách hàng");
		btnUpdate.setIcon(new ImageIcon(QLKH.class.getResource("/update.png")));
		btnUpdate.setBounds(510, 220, 39, 39);
		pnlKhachHang.add(btnUpdate);

		btnTim = new Button("");
		btnTim.setToolTipText("Tìm khách hàng");
		btnTim.setIcon(new ImageIcon(QLKH.class.getResource("/Search.png")));
		btnTim.setRadius(39);
		btnTim.setColorClick(new Color(84, 80, 75));
		btnTim.setBounds(430, 220, 39, 39);
		pnlKhachHang.add(btnTim);

		btnThem = new Button("");
		btnThem.setToolTipText("Thêm khách hàng");
		btnThem.setRadius(39);
		btnThem.setIcon(new ImageIcon(QLKH.class.getResource("/Add.png")));
		btnThem.setColorClick(new Color(84, 80, 75));
		btnThem.setBounds(590, 220, 39, 39);
		pnlKhachHang.add(btnThem);

		btnXoa = new Button("");
		btnXoa.setIcon(new ImageIcon(QLKH.class.getResource("/Trash.png")));
		btnXoa.setToolTipText("Xoá khách hàng");
		btnXoa.setRadius(39);
		btnXoa.setColorClick(new Color(84, 80, 75));
		btnXoa.setBounds(670, 220, 39, 39);
		pnlKhachHang.add(btnXoa);

		String[] colHeader = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Địa chỉ",
				"Loại khách hàng", "Số tiền mua hàng", "Số ID", "Email" };
		modelKhachHang = new DefaultTableModel(colHeader, 0);
		tableKhachHang = new JTable(modelKhachHang) {
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
					return String.class;
				}
				case 3: {
					return String.class;
				}
				case 4: {
					return String.class;
				}
				case 5: {
					return String.class;
				}
				case 6: {
					return Double.class;
				}
				case 7: {
					return String.class;
				}
				case 8: {
					return String.class;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableKhachHang.setRowHeight(25);
		tableKhachHang.setForeground(new Color(0, 0, 0));
		JScrollPane sc = new JScrollPane(tableKhachHang);
		pnlKhachHang.add(sc);
		sc.setBounds(50, 300, 1080, 250);
		
		lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setForeground(new Color(230, 230, 230));
		lblMaKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblMaKH.setBounds(170, 120, 200, 25);
		pnlKhachHang.add(lblMaKH);
		
		lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setForeground(new Color(230, 230, 230));
		lblTenKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblTenKH.setBounds(380, 120, 200, 25);
		pnlKhachHang.add(lblTenKH);
		
		lblSDT = new JLabel("Số điện thoại");
		lblSDT.setForeground(new Color(230, 230, 230));
		lblSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblSDT.setBounds(690, 120, 200, 25);
		pnlKhachHang.add(lblSDT);
		
		lblTable = new JLabel("Bảng quản lý khách hàng");
		lblTable.setForeground(new Color(230, 230, 230));
		lblTable.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblTable.setBounds(50, 270, 250, 25);
		pnlKhachHang.add(lblTable);
		
		lblQLKH = new JLabel("Quản lý khách hàng");
		lblQLKH.setForeground(new Color(230, 230, 230));
		lblQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblQLKH.setBounds(30, 30, 416, 70);
		pnlKhachHang.add(lblQLKH);

		// =================================
		// doc du lieu tu database SQL vao Jtable, tao ket noi dao
		kh_dao = new KhachHangImpl();
		loaikh_dao = new LoaiKHImpl();
		updateTable(kh_dao.getDsKhachHang());
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
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRefresh.addActionListener(this);
		tableKhachHang.addMouseListener(this);
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

	private void selectedRow(int row) {
		if (row != -1) {
			tableKhachHang.setRowSelectionInterval(row, row);
			tableKhachHang.scrollRectToVisible(tableKhachHang.getCellRect(row, row, true));
		}
	}

	private void updateTable(List<KhachHang> ds) throws Exception {
		modelKhachHang.setRowCount(0);
		for (KhachHang kh : ds) {
			Object[] rowData = { kh.getMaKH(), kh.getTenKH(), kh.getGioiTinh(), kh.getSdt(), kh.getDiaChi(),
					loaikh_dao.timMa(kh.getLoai().getMaLoai()).getTenLoai(), kh.getSoTienMuaHang(), kh.getSoID(),
					kh.getEmail() };
			modelKhachHang.addRow(rowData);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				new ThemKH().setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnTim)) {
			String maKH = txtMaKH.getText().trim();
			if (maKH.compareToIgnoreCase("Mã khách hàng") == 0)
				maKH = "";
			String tenKH = txtTenKH.getText().trim();
			if (tenKH.compareToIgnoreCase("Tên khách hàng") == 0)
				tenKH = "";
			String sDT = txtSDT.getText().trim();
			if (sDT.compareToIgnoreCase("Số điện thoại") == 0)
				sDT = "";
			List<KhachHang> ds = null;
			try {
				ds = kh_dao.timKiem(maKH, tenKH, sDT);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (ds == null)
				try {
					ds = kh_dao.getDsKhachHang();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			try {
				updateTable(ds);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnUpdate)) {
			int row = tableKhachHang.getSelectedRow();
			if (row != -1) {
				String maKH = txtMaKH.getText().trim();
				try {
					new CapNhatKH(maKH).setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(pnlKhachHang, "Chọn khách hàng để sửa thông tin!");
			}
		}
		if (o.equals(btnXoa)) {
			int row = tableKhachHang.getSelectedRow();
			if (row != -1) {
				int ask = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khách hàng này không?", "Xóa khách hàng",
						JOptionPane.YES_NO_OPTION);
				if (ask == JOptionPane.YES_OPTION) {
					String maKH = (String) tableKhachHang.getValueAt(row, 0);
					try {
						kh_dao.xoaKhachHang(maKH);
						modelKhachHang.removeRow(row);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (o.equals(btnRefresh)) {
			modelKhachHang.setRowCount(0);
			try {
				updateTable(kh_dao.getDsKhachHang());
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
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableKhachHang.getSelectedRow();
		if (row >= 0) {
			txtMaKH.setText(modelKhachHang.getValueAt(row, 0).toString());
			txtTenKH.setText(modelKhachHang.getValueAt(row, 1).toString());
			txtSDT.setText(modelKhachHang.getValueAt(row, 3).toString());
		}
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
		txtMaKH.setText("Mã khách hàng");
		txtTenKH.setText("Tên khách hàng");
		txtSDT.setText("Số điện thoại");
	}
}
