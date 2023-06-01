package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.button.Button;
import components.panelRound.PanelRound;
import components.roundJTextField.RoundJTextField;
import entity.LoaiNV;
import entity.LoaiSP;
import entity.NhanVien;
import entity.SanPham;
import service.ICaLamViecService;
import service.ILoaiNVService;
import service.INhanVienService;
import service.ITaiKhoanService;
import service.impl.CaLamViecImpl;
import service.impl.KhachHangImpl;
import service.impl.LoaiNVImpl;
import service.impl.LoaiSPServiceImpl;
import service.impl.NhanVienImpl;
import service.impl.TaiKhoanImpl;
import utils.Utils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QLNV extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private Button btnTaoHoaDon;
	private Button btnQLSP;
	private Button btnQLKH;
	private Button btnQLNV;
	private Button btnTK;
	private JPanel pnlNhanVien;
	private RoundJTextField txtSDT;
	private RoundJTextField txtTenNV;
	private RoundJTextField txtMaNV;
	private Button btnTim;
	private Button btnUpdate;
	private Button btnThem;
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private JScrollPane scr;
	private INhanVienService nv_dao;
	private ILoaiNVService loainv_dao;
	private ITaiKhoanService tk_dao;
	private ICaLamViecService ca_dao;
	private Button btnXoa;
	private Button btnRefresh;
	private NhanVien nhanVienDangNhap;
	private JComboBox cbbChao;
	private JLabel lblQLNV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLNV frame = new QLNV();
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
	public QLNV() throws SQLException, Exception {
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
		lblAvatar1.setIcon(new ImageIcon(QLNV.class.getResource("/avatar1.jpg")));
		lblAvatar1.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar1);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(QLNV.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1214, 20, 39, 39);
		pnlHeader.add(lblAvatar2);

		PanelRound pnlMenu = new PanelRound(20);
		pnlMenu.setBackground(new Color(222, 217, 214));
		pnlMenu.setBounds(0, 90, 250, 593);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setIcon(new ImageIcon(QLNV.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		btnTaoHoaDon.setBackground(new Color(255, 255, 255));
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setIcon(new ImageIcon(QLNV.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBackground(Color.WHITE);
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setIcon(new ImageIcon(QLNV.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBackground(Color.WHITE);
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setForeground(new Color(230, 230, 230));
		btnQLNV.setBorderBtnColor(new Color(84, 80, 75));
		btnQLNV.setColorClick(new Color(84, 80, 75));
		btnQLNV.setColorOver(new Color(84, 80, 75));
		btnQLNV.setColor(new Color(84, 80, 75));
		btnQLNV.setBorderColor(new Color(84, 80, 75));
		btnQLNV.setIcon(new ImageIcon(QLNV.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBackground(new Color(84, 80, 75));
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setIcon(new ImageIcon(QLNV.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBackground(Color.WHITE);
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);

		PanelRound pnlNhanVien = new PanelRound(20);
		pnlNhanVien.setBackground(new Color(84, 80, 75));
		pnlNhanVien.setBounds(250, 90, 1176, 593);
		contentPane.add(pnlNhanVien);
		pnlNhanVien.setLayout(null);

		txtMaNV = new RoundJTextField(30);
		txtMaNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaNV.setText("");
			}
		});
		txtMaNV.setForeground(new Color(84, 80, 75));
		txtMaNV.setBackground(new Color(243, 238, 231));
		txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtMaNV.setToolTipText("Nhập mã nhân viên");
		txtMaNV.setText("Mã nhân viên");
		txtMaNV.setBounds(170, 150, 200, 40);
		pnlNhanVien.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtTenNV = new RoundJTextField(30);
		txtTenNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTenNV.setText("");
			}
		});
		txtTenNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTenNV.setToolTipText("Nhập tên nhân viên");
		txtTenNV.setText("Tên nhân viên");
		txtTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNV.setForeground(new Color(84, 80, 75));
		txtTenNV.setColumns(10);
		txtTenNV.setBackground(new Color(243, 238, 231));
		txtTenNV.setBounds(380, 150, 300, 40);
		pnlNhanVien.add(txtTenNV);

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
		pnlNhanVien.add(txtSDT);
		
		btnRefresh = new Button("");
		btnRefresh.setIcon(new ImageIcon(QLNV.class.getResource("/Refresh.png")));
		btnRefresh.setToolTipText("Refresh bảng");
		btnRefresh.setRadius(39);
		btnRefresh.setColorClick(new Color(84, 80, 75));
		btnRefresh.setBounds(350, 220, 39, 39);
		pnlNhanVien.add(btnRefresh);

		btnUpdate = new Button("");
		btnUpdate.setRadius(39);
		btnUpdate.setColorClick(new Color(84, 80, 75));
		btnUpdate.setToolTipText("Sửa thông tin nhân viên");
		btnUpdate.setIcon(new ImageIcon(QLNV.class.getResource("/update.png")));
		btnUpdate.setBounds(510, 220, 39, 39);
		pnlNhanVien.add(btnUpdate);

		btnTim = new Button("");
		btnTim.setToolTipText("Tìm nhân viên");
		btnTim.setIcon(new ImageIcon(QLNV.class.getResource("/Search.png")));
		btnTim.setRadius(39);
		btnTim.setColorClick(new Color(84, 80, 75));
		btnTim.setBounds(430, 220, 39, 39);
		pnlNhanVien.add(btnTim);

		btnThem = new Button("");
		btnThem.setToolTipText("Thêm nhân viên");
		btnThem.setRadius(39);
		btnThem.setIcon(new ImageIcon(QLNV.class.getResource("/Add.png")));
		btnThem.setColorClick(new Color(84, 80, 75));
		btnThem.setBounds(590, 220, 39, 39);
		pnlNhanVien.add(btnThem);

		btnXoa = new Button("");
		btnXoa.setIcon(new ImageIcon(QLNV.class.getResource("/Trash.png")));
		btnXoa.setToolTipText("Xoá nhân viên");
		btnXoa.setRadius(39);
		btnXoa.setColorClick(new Color(84, 80, 75));
		btnXoa.setBounds(670, 220, 39, 39);
		pnlNhanVien.add(btnXoa);

		String[] colHeader = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Số điện thoại", "Loại nhân viên",
				"Ca làm", "Lương", "Số ID", "Ngày vào làm", "Tài khoản"};
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien) {
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
					return String.class;
				}
				case 7: {
					return Double.class;
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
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableNhanVien.setRowHeight(25);
		tableNhanVien.setForeground(new Color(0, 0, 0));
		scr = new JScrollPane(tableNhanVien);
		pnlNhanVien.add(scr);
		scr.setBounds(50, 300, 1080, 250);
		
		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setForeground(new Color(230, 230, 230));
		lblMaNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblMaNV.setBounds(172, 120, 200, 25);
		pnlNhanVien.add(lblMaNV);
		
		JLabel lblTenNV = new JLabel("Tên nhân viên");
		lblTenNV.setForeground(new Color(230, 230, 230));
		lblTenNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblTenNV.setBounds(382, 120, 200, 25);
		pnlNhanVien.add(lblTenNV);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setForeground(new Color(230, 230, 230));
		lblSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblSDT.setBounds(690, 120, 200, 25);
		pnlNhanVien.add(lblSDT);
		
		JLabel lblTable = new JLabel("Bảng quản lý nhân viên");
		lblTable.setForeground(new Color(230, 230, 230));
		lblTable.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 18));
		lblTable.setBounds(50, 270, 200, 25);
		pnlNhanVien.add(lblTable);
		
		lblQLNV = new JLabel("Quản lý nhân viên");
		lblQLNV.setForeground(new Color(230, 230, 230));
		lblQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblQLNV.setBounds(30, 30, 600, 70);
		pnlNhanVien.add(lblQLNV);
		

		// =================================
		// doc du lieu tu database SQL vao Jtable, tao ket noi dao
		nv_dao = new NhanVienImpl();
		loainv_dao = new LoaiNVImpl();
		tk_dao = new TaiKhoanImpl();
		ca_dao = new CaLamViecImpl();
		updateTable(nv_dao.getDsNhanVien());
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
		
		//add sự kiện
		btnTim.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnRefresh.addActionListener(this);
		tableNhanVien.addMouseListener(this);
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

	private void updateTable(List<NhanVien> ds) throws Exception {
		modelNhanVien.setRowCount(0);
		for (NhanVien nv : ds) {
			Object[] rowData = { nv.getMaNV(), nv.getTenNV(), nv.getNgaySinh(), nv.getGioiTinh(), nv.getSdt(),
					loainv_dao.timMa(nv.getLoai().getMaLoai()).getTenLoai(), ca_dao.timMa(nv.getCa().getMaCa()).getLoaiCa(), nv.getLuong(),
					nv.getSoID(), nv.getNgayVaoLam(), tk_dao.timMa(nv.getTaiKhoan().getUserName()).getUserName()};
			modelNhanVien.addRow(rowData);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				new ThemNV().setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnTim)) {
			String maNV = txtMaNV.getText().trim();
			if (maNV.compareToIgnoreCase("Mã nhân viên") == 0)
				maNV = "";
			String tenNV = txtTenNV.getText().trim();
			if (tenNV.compareToIgnoreCase("Tên nhân viên") == 0)
				tenNV = "";
			String sDT = txtSDT.getText().trim();
			if (sDT.compareToIgnoreCase("Số điện thoại") == 0)
				sDT = "";
			List<NhanVien> ds = null;
			try {
				ds = nv_dao.timKiem(maNV, tenNV, sDT);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (ds == null)
				try {
					ds = nv_dao.getDsNhanVien();
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
			int row = tableNhanVien.getSelectedRow();
			if (row != -1) {
				String maNV = txtMaNV.getText().trim();
				try {
					new CapNhatNV(maNV).setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(pnlNhanVien, "Chọn nhân viên để sửa thông tin!");
			}
		}
		if (o.equals(btnXoa)) {
			int row = tableNhanVien.getSelectedRow();
			if (row != -1) {
				int ask = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên này không?", "Xóa nhân viên",
						JOptionPane.YES_NO_OPTION);
				if (ask == JOptionPane.YES_OPTION) {
					String maNV = (String) tableNhanVien.getValueAt(row, 0);
					try {
						nv_dao.xoaNhanVien(maNV);
						modelNhanVien.removeRow(row);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (o.equals(btnRefresh)) {
			modelNhanVien.setRowCount(0);
			try {
				updateTable(nv_dao.getDsNhanVien());
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
		int row = tableNhanVien.getSelectedRow();
		if (row >= 0) {
			txtMaNV.setText(modelNhanVien.getValueAt(row, 0).toString());
			txtTenNV.setText(modelNhanVien.getValueAt(row, 1).toString());
			txtSDT.setText(modelNhanVien.getValueAt(row, 3).toString());
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
		txtMaNV.setText("Mã nhân viên");
		txtTenNV.setText("Tên nhân viên");
		txtSDT.setText("Số điện thoại");
	}
}
