package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.TableView.TableRow;
import javax.xml.crypto.URIReferenceException;

import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import components.button.Button;
import components.panelRound.PanelRound;
import components.roundJTextField.RoundJTextField;
import dao.HoaDonDao;
import dao.SanPhamDao;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiSP;
import entity.NhanVien;
import entity.SanPham;
import service.IHoaDonService;
import service.IKhachHangService;
import service.ILoaiSPService;
import service.INhaCungCapService;
import service.ISanPhamService;
import service.ITinhToan;
import service.impl.HoaDonImpl;
import service.impl.KhachHangImpl;
import service.impl.LoaiSPServiceImpl;
import service.impl.NhaCungCapImpl;
import service.impl.SanPhamImpl;
import service.impl.TinhToanImpl;

import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import javax.swing.JMenuItem;
import java.awt.Dimension;

public class TaoHoaDon extends JFrame implements ActionListener, MouseListener, KeyListener{

	private JPanel contentPane;
	private RoundJTextField txtMaSP;
	private RoundJTextField txtTenSp;
	private JTable tableSanPham;
	private RoundJTextField txtSoLuong;
	private RoundJTextField txtSdt;
	private RoundJTextField txtTenKH;
	private RoundJTextField txtMaHD;
	private RoundJTextField txtMaKH, txtTenKH2, txtMaNV;
	private RoundJTextField txtThanhTien;
	private RoundJTextField txtTienNhan;
	private RoundJTextField txtTienThua;
	private Button btnTaoHoaDon, btnQLNV, btnQLKH, btnQLSP, btnTK, btnTim, btnTimKH, btnThemVaoHD, btnHuy,
			btnThanhToan;
	private JRadioButton rdbtnIn;
	private DefaultTableModel modelSanPham;
	private DefaultTableModel modelHoaDon;
	private Component tableHoaDon;
	private IHoaDonService hoaDon_dao;
	private ISanPhamService sp_dao;
	private JPanel panel;
	private JPopupMenu popupMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private INhaCungCapService ncc_dao;
	private JComboBox cbbDanhMuc;
	private JComboBox cbbChatLieu;
	private JComboBox cbbSize;
	private ILoaiSPService loaisp_dao;
	private IKhachHangService kh_dao;
	private ITinhToan tinhToan_dao = new TinhToanImpl();
	private List<CTHoaDon> dsCTHD = new ArrayList<>();
	private String maHDMoi = tinhToan_dao.maHoaDonMoi();
	private HoaDon hoadon = new HoaDon(maHDMoi);
	private String maNVDangNhap;
	private NhanVien nhanVienDangNhap;
	private JComboBox cbbChao;
	private JLabel lblNewLabel;
	private JLabel lblTnKhchHng;
	private JLabel lblThnhTin;
	private JLabel lblTngTinSau;
	private RoundJTextField txtTienSauThue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaoHoaDon frame = new TaoHoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TaoHoaDon() throws Exception {
		maNVDangNhap  = TrangChu.maNVDangNhap;
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

		PanelRound pnlHeader = new PanelRound(20);
		pnlHeader.setBounds(0, 0, 1426, 90);
		pnlHeader.setBackground(new Color(230, 230, 230));
		contentPane.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblAvatar = new JLabel("");
		lblAvatar.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/avatar1.jpg")));
		lblAvatar.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar);

		JLabel lblTenShop = new JLabel("VIR");
		lblTenShop.setFont(new Font("000 FM Atlas", Font.PLAIN, 24));
		lblTenShop.setBounds(100, 20, 50, 30);
		pnlHeader.add(lblTenShop);

		JLabel lblSlogan = new JLabel("Urbanus et elegans");
		lblSlogan.setFont(new Font("000 Hand Of Sean Pro iCiel", Font.PLAIN, 15));
		lblSlogan.setBounds(100, 40, 140, 20);
		pnlHeader.add(lblSlogan);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1210, 20, 39, 39);
		pnlHeader.add(lblAvatar2);
		
		cbbChao = new JComboBox();
		cbbChao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cbbChao.setModel(new DefaultComboBoxModel(
				new String[] { nhanVienDangNhap.getTenNV(), "Đổi mật khẩu","Đăng xuất" }));
		cbbChao.setBounds(1263, 28, 140, 21);
		pnlHeader.add(cbbChao);

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBounds(0, 90, 250, 593);
		pnlMenu.setBackground(new Color(222, 217, 214));
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setBorderBtnColor(new Color(84, 80, 75));
		btnTaoHoaDon.setColorClick(new Color(84, 80, 75));
		btnTaoHoaDon.setBorderColor(new Color(84, 80, 75));
		btnTaoHoaDon.setBackground(new Color(84, 80, 75));
		btnTaoHoaDon.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setBorderWidth(0);
		btnTaoHoaDon.setForeground(new Color(230, 230, 230));
		btnTaoHoaDon.setColor(new Color(84, 80, 75));
		btnTaoHoaDon.setColorOver(new Color(84, 80, 75));
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setColor(new Color(255, 255, 255));
		btnQLSP.setColorOver(new Color(84, 80, 75));
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setBackground(new Color(255, 255, 255));
		btnQLKH.setColorOver(new Color(84, 80, 75));
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setBackground(new Color(255, 255, 255));
		btnQLNV.setColorOver(new Color(84, 80, 75));
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setBackground(new Color(255, 255, 255));
		btnTK.setColorOver(new Color(84, 80, 75));
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);

		JPanel pnlSanPham = new JPanel();
		pnlSanPham.setBounds(246, 90, 880, 593);
		pnlSanPham.setBackground(new Color(84, 80, 75));
		contentPane.add(pnlSanPham);
		pnlSanPham.setLayout(null);

		txtMaSP = new RoundJTextField(30);
		txtMaSP.setBackground(new Color(243, 238, 231));
		txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaSP.setText("Mã sản phẩm");
		txtMaSP.setToolTipText("Mã sản phẩm");
		txtMaSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtMaSP.setBounds(71, 54, 120, 30);
		pnlSanPham.add(txtMaSP);
		txtMaSP.setColumns(10);

		txtTenSp = new RoundJTextField(30);
		txtTenSp.setBackground(new Color(243, 238, 231));
		txtTenSp.setToolTipText("Tên sản phẩm");
		txtTenSp.setText("Tên sản phẩm");
		txtTenSp.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenSp.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTenSp.setColumns(10);
		txtTenSp.setBounds(261, 54, 120, 30);
		pnlSanPham.add(txtTenSp);

		JLabel lblDanhMuc = new JLabel("Danh mục:");
		lblDanhMuc.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblDanhMuc.setForeground(new Color(255, 255, 255));
		lblDanhMuc.setBounds(391, 58, 80, 22);
		pnlSanPham.add(lblDanhMuc);

		cbbDanhMuc = new JComboBox();
		cbbDanhMuc.setBackground(new Color(243, 238, 231));
		cbbDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDanhMuc.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Áo", "Quần", "phụ kiện", "Giày", "Khác" }));
		cbbDanhMuc.setBounds(474, 54, 80, 30);
		pnlSanPham.add(cbbDanhMuc);

		cbbChatLieu = new JComboBox();
		cbbChatLieu.setBackground(new Color(243, 238, 231));
		cbbChatLieu.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "len", "kaki", "vải kaki", "da",
				"vải âu", "vải thun", "vải cotton", "vải", "kim cương", "bạc"}));
		cbbChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbChatLieu.setBounds(622, 54, 71, 30);
		pnlSanPham.add(cbbChatLieu);

		
		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setToolTipText("Chất liệu");
		lblChatLieu.setForeground(Color.WHITE);
		lblChatLieu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblChatLieu.setBounds(557, 58, 64, 22);
		pnlSanPham.add(lblChatLieu);

		cbbSize = new JComboBox();
		cbbSize.setBackground(new Color(243, 238, 231));
		cbbSize.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "S", "M", "L", "X", "XL", "XXL"}));
		cbbSize.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbSize.setBounds(742, 54, 80, 30);
		pnlSanPham.add(cbbSize);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setForeground(Color.WHITE);
		lblSize.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSize.setBounds(703, 58, 40, 22);
		pnlSanPham.add(lblSize);

		btnTim = new Button("");
		btnTim.setToolTipText("Tìm sản phẩm");
		btnTim.setHorizontalAlignment(SwingConstants.CENTER);
		btnTim.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/Search.png")));
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setRadius(39);
		btnTim.setBounds(832, 45, 39, 39);
		pnlSanPham.add(btnTim);
		
		
		String[] colHeader = { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Màu", "Size", "Nhà cung cấp" };
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
					return Integer.class;
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
				default:
					throw new IllegalArgumentException("Unexpected value: " + column);
				}
			}
		};
		tableSanPham.setRowHeight(25);
		tableSanPham.setForeground(new Color(0, 0, 0));
		JScrollPane sc = new JScrollPane(tableSanPham);
		pnlSanPham.add(sc);
		sc.setBounds(30, 94, 841, 212);
		

		// =================================
		// doc du lieu tu database SQL vao Jtable, tao ket noi dao
		hoaDon_dao = new HoaDonImpl();
		sp_dao = new SanPhamImpl();
		ncc_dao = new NhaCungCapImpl();
		loaisp_dao = new LoaiSPServiceImpl();
		kh_dao = new KhachHangImpl();
		updateTable(sp_dao.getDsSanPham());
		// =================================

		txtSoLuong = new RoundJTextField(30);
		txtSoLuong.setBackground(new Color(243, 238, 231));
		txtSoLuong.setToolTipText("Nhập số lượng bán");
		txtSoLuong.setText("Nhập số lượng bán");
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(181, 316, 200, 30);
		pnlSanPham.add(txtSoLuong);

		btnThemVaoHD = new Button("Thêm vào đơn hàng");
		btnThemVaoHD.setRadius(30);
		btnThemVaoHD.setColor(new Color(243, 238, 231));
		btnThemVaoHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThemVaoHD.setBounds(476, 316, 200, 30);
		pnlSanPham.add(btnThemVaoHD);

		String[] colHeader1 = { "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng mua"};
		modelHoaDon = new DefaultTableModel(colHeader1, 0) {
		 @Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 2: {
				return Double.class;
			}
			case 3: {
				return Integer.class;
			}
			default:
				return String.class;
			}
		}
		};
		tableHoaDon = new JTable(modelHoaDon);
		JScrollPane sc2 = new JScrollPane(tableHoaDon);
		sc2.setBounds(30, 356, 841, 212);
		pnlSanPham.add(sc2);
		
				btnHuy = new Button("Huỷ");
				btnHuy.setText("Reset");
				btnHuy.setBounds(622, 9, 100, 30);
				pnlSanPham.add(btnHuy);
				btnHuy.setBackground(new Color(243, 238, 231));
				btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnHuy.setRadius(30);
				
				JLabel lblMSp = new JLabel("Tên SP:");
				lblMSp.setForeground(Color.WHITE);
				lblMSp.setFont(new Font("Dialog", Font.PLAIN, 15));
				lblMSp.setBounds(201, 58, 63, 22);
				pnlSanPham.add(lblMSp);
				
				JLabel lblMSp_1 = new JLabel("Mã SP:");
				lblMSp_1.setForeground(Color.WHITE);
				lblMSp_1.setFont(new Font("Dialog", Font.PLAIN, 15));
				lblMSp_1.setBounds(10, 58, 49, 22);
				pnlSanPham.add(lblMSp_1);
				
						txtMaKH = new RoundJTextField(30);
						txtMaKH.setBounds(10, 9, 110, 30);
						pnlSanPham.add(txtMaKH);
						txtMaKH.setText("Mã hóa đơn:");
						txtMaKH.setHorizontalAlignment(SwingConstants.CENTER);
						txtMaKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
						txtMaKH.setColumns(10);
						txtMaKH.setBackground(new Color(243, 238, 30));
						txtMaKH.setEditable(false);
						
								
								txtMaHD = new RoundJTextField(30);
								txtMaHD.setBounds(124, 4, 140, 40);
								pnlSanPham.add(txtMaHD);
								txtMaHD.setToolTipText("Mã hoá đơn");
								txtMaHD.setText(maHDMoi);
								txtMaHD.setHorizontalAlignment(SwingConstants.CENTER);
								txtMaHD.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
								txtMaHD.setColumns(10);
								txtMaHD.setBackground(new Color(243, 238, 231));
								txtMaHD.setEditable(false);
								
								txtTenKH2 = new RoundJTextField(30);
								txtTenKH2.setBounds(302, 9, 100, 30);
								pnlSanPham.add(txtTenKH2);
								txtTenKH2.setText("Mã nhân viên");
								txtTenKH2.setHorizontalAlignment(SwingConstants.CENTER);
								txtTenKH2.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
								txtTenKH2.setColumns(10);
								txtTenKH2.setBackground(new Color(243, 238, 30));
								txtTenKH2.setEditable(false);
								
								txtMaNV = new RoundJTextField(30);
								txtMaNV.setBounds(412, 4, 140, 40);
								pnlSanPham.add(txtMaNV);
								txtMaNV.setToolTipText("Mã nhân viên");
								txtMaNV.setText(maNVDangNhap);
								txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
								txtMaNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
								txtMaNV.setColumns(10);
								txtMaNV.setBackground(new Color(243, 238, 231));
								txtMaNV.setEditable(false);
								txtMaHD.addMouseListener(this);
						txtMaKH.addMouseListener(this);
				btnHuy.addActionListener(this);

		JPanel pnlThanhToan = new JPanel();
		pnlThanhToan.setBackground(new Color(84, 80, 75));
		pnlThanhToan.setBounds(1127, 90, 336, 593);
		contentPane.add(pnlThanhToan);
		pnlThanhToan.setLayout(null);

		JLabel lblThanhToan = new JLabel("Thanh toán");
		lblThanhToan.setBounds(90, 10, 130, 30);
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setForeground(new Color(230, 230, 230));
		lblThanhToan.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 24));
		pnlThanhToan.add(lblThanhToan);

		txtSdt = new RoundJTextField(40);
		txtSdt.setBackground(new Color(243, 238, 231));
		txtSdt.setToolTipText("Số điện thoại");
		txtSdt.setText("0826669861");
		txtSdt.setHorizontalAlignment(SwingConstants.CENTER);
		txtSdt.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSdt.setColumns(10);
		txtSdt.setBounds(10, 83, 230, 40);
		pnlThanhToan.add(txtSdt);

		btnTimKH = new Button("");
		btnTimKH.setToolTipText("Tìm khách hàng");
		btnTimKH.setIcon(new ImageIcon(TaoHoaDon.class.getResource("/Search.png")));
		btnTimKH.setHorizontalAlignment(SwingConstants.CENTER);
		btnTimKH.setColor(Color.WHITE);
		btnTimKH.setRadius(39);
		btnTimKH.setBounds(250, 84, 39, 39);
		pnlThanhToan.add(btnTimKH);

		txtTenKH = new RoundJTextField(30);
		txtTenKH.setToolTipText("Tên khách hàng");
		txtTenKH.setText("Tên khách hàng");
		txtTenKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTenKH.setColumns(10);
		txtTenKH.setBackground(new Color(243, 238, 231));
		txtTenKH.setBounds(10, 154, 230, 40);
		txtTenKH.setEditable(false);
		pnlThanhToan.add(txtTenKH);

		txtThanhTien = new RoundJTextField(30);
		txtThanhTien.setToolTipText("Thành tiền");
		txtThanhTien.setText("Thành tiền");
		txtThanhTien.setHorizontalAlignment(SwingConstants.CENTER);
		txtThanhTien.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtThanhTien.setColumns(10);
		txtThanhTien.setBackground(new Color(243, 238, 231));
		txtThanhTien.setBounds(10, 263, 279, 40);
		txtThanhTien.setEditable(false);
		pnlThanhToan.add(txtThanhTien);

		txtTienNhan = new RoundJTextField(30);
		txtTienNhan.setToolTipText("Tiền nhận");
		txtTienNhan.setText("Tiền nhận");
		txtTienNhan.setHorizontalAlignment(SwingConstants.CENTER);
		txtTienNhan.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTienNhan.setColumns(10);
		txtTienNhan.setBackground(new Color(243, 238, 231));
		txtTienNhan.setBounds(10, 413, 279, 40);
		txtTienNhan.setEditable(false);
		pnlThanhToan.add(txtTienNhan);

		txtTienThua = new RoundJTextField(30);
		txtTienThua.setToolTipText("Tiền thừa");
		txtTienThua.setText("Tiền thừa");
		txtTienThua.setHorizontalAlignment(SwingConstants.CENTER);
		txtTienThua.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTienThua.setColumns(10);
		txtTienThua.setBackground(Color.cyan);
		txtTienThua.setBounds(10, 487, 279, 40);
		txtTienThua.setEditable(false);
		pnlThanhToan.add(txtTienThua);

		rdbtnIn = new JRadioButton("In hoá đơn");
		rdbtnIn.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		rdbtnIn.setForeground(new Color(255, 255, 255));
		rdbtnIn.setBackground(new Color(84, 80, 75));
		rdbtnIn.setBounds(10, 550, 115, 25);
		pnlThanhToan.add(rdbtnIn);

		btnThanhToan = new Button("Thanh toán");
		btnThanhToan.setBackground(new Color(243, 238, 231));
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThanhToan.setToolTipText("Nhấn để thanh toán");
		btnThanhToan.setRadius(25);
		btnThanhToan.setBounds(174, 550, 115, 25);
		pnlThanhToan.add(btnThanhToan);
		
		lblNewLabel = new JLabel("Nhập sđt khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 50, 180, 25);
		pnlThanhToan.add(lblNewLabel);
		
		lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setForeground(Color.WHITE);
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTnKhchHng.setBounds(10, 133, 180, 25);
		pnlThanhToan.add(lblTnKhchHng);
		
		lblThnhTin = new JLabel("Thành tiền:");
		lblThnhTin.setForeground(Color.WHITE);
		lblThnhTin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThnhTin.setBounds(10, 238, 180, 25);
		pnlThanhToan.add(lblThnhTin);
		
		lblTngTinSau = new JLabel("Tổng tiền sau thuế (10%):");
		lblTngTinSau.setForeground(Color.WHITE);
		lblTngTinSau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTngTinSau.setBounds(10, 313, 230, 25);
		pnlThanhToan.add(lblTngTinSau);
		
		txtTienSauThue = new RoundJTextField(30);
		txtTienSauThue.setToolTipText("Tiền sau thuế");
		txtTienSauThue.setText("Tiền sau thuế");
		txtTienSauThue.setHorizontalAlignment(SwingConstants.CENTER);
		txtTienSauThue.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtTienSauThue.setEditable(false);
		txtTienSauThue.setColumns(10);
		txtTienSauThue.setBackground(new Color(243, 238, 231));
		txtTienSauThue.setBounds(10, 337, 279, 40);
		pnlThanhToan.add(txtTienSauThue);
		
		JLabel lblTinNhn = new JLabel("Tiền nhận:");
		lblTinNhn.setForeground(Color.WHITE);
		lblTinNhn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTinNhn.setBounds(10, 387, 230, 25);
		pnlThanhToan.add(lblTinNhn);
		
		JLabel lblTinTha = new JLabel("Tiền thừa:");
		lblTinTha.setForeground(Color.WHITE);
		lblTinTha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTinTha.setBounds(10, 463, 230, 25);
		pnlThanhToan.add(lblTinTha);

		// add sự kiện
		btnQLKH.addActionListener(this);
		btnTK.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnQLNV.addActionListener(this);
		btnQLSP.addActionListener(this);
		btnTaoHoaDon.addActionListener(this);
		btnThemVaoHD.addActionListener(this);
		btnTim.addActionListener(this);
		btnTimKH.addActionListener(this);
		cbbChao.addActionListener(this);

		txtSoLuong.addMouseListener(this);
		txtSdt.addMouseListener(this);
		txtTenKH.addMouseListener(this);
		txtThanhTien.addMouseListener(this);
		txtTienNhan.addMouseListener(this);
		txtTienThua.addMouseListener(this);
		txtMaSP.addMouseListener(this);
		txtTenSp.addMouseListener(this);
		
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
		
		txtTienNhan.addKeyListener(this);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(txtSdt)) {
			txtSdt.setText("");
		}
		if (o.equals(txtMaSP)) {
			txtMaSP.setText("");
		}
		if (o.equals(txtTenSp)) {
			txtTenSp.setText("");
		}
		if (o.equals(txtTienNhan)) {
			txtTienNhan.setText("");
		}
		if (o.equals(txtSoLuong)) {
			txtSoLuong.setText("");
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
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			clearTextfield();
		}
		if (o.equals(btnTim)) {
			String maSP = txtMaSP.getText().trim();
			if(maSP.compareToIgnoreCase("Mã sản phẩm")==0)
				maSP = "";
			String tenSP = txtTenSp.getText().trim();
			if(tenSP.compareToIgnoreCase("Tên sản phẩm")==0)
				tenSP = "";
			String loaiSP = (String) cbbDanhMuc.getSelectedItem(); 
			if(loaiSP.compareToIgnoreCase("Tất cả")==0)
				loaiSP = "";
			String chatLieu = (String) cbbChatLieu.getSelectedItem();
			if(chatLieu.compareToIgnoreCase("Tất cả")==0)
				chatLieu = "";
			String size = (String) cbbSize.getSelectedItem();
			if(size.compareToIgnoreCase("Tất cả")==0)
				size = "";
			String maLoai = "";
			try {
				if(loaiSP != "") {
					for(LoaiSP loai : loaisp_dao.getDSLoaiSP()) {
						if(loai.getTenLoai().compareToIgnoreCase(loaiSP)==0)
							maLoai = loai.getMaLoai();
					}
				}
				List<SanPham> ds = sp_dao.timKiem(maSP, tenSP, "", size, chatLieu, 0, 0, maLoai);
				if(ds == null)
					ds = sp_dao.getDsSanPham();
				updateTable(ds);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		if(o.equals(btnThemVaoHD)) {
			try {
				if(validData()) {
					int  soLuong = 0;
					try {
						soLuong = Integer.parseInt(txtSoLuong.getText().trim());
					} catch (Exception e2) {
						
					}
					String id= (String) tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 0);
					SanPham sp;
					try {
						sp = sp_dao.timMa(id);
						int flat = 0;
						for(int i =0; i< modelHoaDon.getRowCount();i++) {
							if(modelHoaDon.getValueAt(i, 0).equals(id)){
								soLuong +=(int)modelHoaDon.getValueAt(i, 3);
								modelHoaDon.setValueAt(soLuong, i, 3);
								dsCTHD.get(i).setSoLuong(soLuong);
								flat = 1;
								break;
							}
						}
						if(flat == 0) {
							Object[] rowData = {sp.getMaSP(),sp.getTenSP(),sp.getDonGia(), soLuong};
							modelHoaDon.addRow(rowData);
							dsCTHD.add(new CTHoaDon(hoadon, new SanPham(id),soLuong));
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					double thanhTien = 0;
					try {
						thanhTien =  tinhToan_dao.tinhTongTien(dsCTHD);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					String textThanhTien = String.format("%.0f", thanhTien);
					txtThanhTien.setText(textThanhTien);
					txtTienSauThue.setText(String.format("%.0f", thanhTien*1.1));
					txtTienNhan.setEditable(true);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(o.equals(btnTimKH)) {
			try {
				String tenKH = "";
				for(KhachHang kh : kh_dao.getDsKhachHang()) {
					if(kh.getSdt().compareToIgnoreCase(txtSdt.getText().trim())==0) {
						tenKH = kh.getTenKH();
						txtTenKH.setText(tenKH);
						break;
					}
				}
				if(txtSdt.getText().trim().equals(""))
					txtTenKH.setText("Tên khách Hàng");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(o.equals(btnThanhToan)) {
			if(validThanhToan()) {
				String maHD = txtMaHD.getText().trim();
				LocalDate ngayLapHD = LocalDate.now();
				Double tienNhan = 0.0;
				Double tienThua = 0.0;
				try {
					tienNhan = Double.parseDouble(txtTienNhan.getText()) ;
					tienThua = Double.parseDouble(txtTienThua.getText());
				} catch (Exception e2) {
				}
				String maNV = txtMaNV.getText().trim();
				if(maNV.equals(""))
					maNV = null;
				String maKH = "";
				try {
					for(KhachHang kh: kh_dao.getDsKhachHang()) {
						if(kh.getTenKH().compareToIgnoreCase(txtTenKH.getText().trim())==0) {
							maKH = kh.getMaKH();
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				HoaDon hd = new HoaDon(maHD,ngayLapHD,tienNhan,tienThua);
				try {
					hoaDon_dao.themHoaDon(hd, maNV, maKH, dsCTHD);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(contentPane, "Tạo hóa đơn Thành công");
				if(rdbtnIn.isSelected()) {
					try {
						System.out.println(txtThanhTien.getText());
						double thanhTien = Double.parseDouble(txtThanhTien.getText());
						double tongTien = Double.parseDouble(txtTienSauThue.getText());
						inHoaDon(maHD, ngayLapHD, dsCTHD, thanhTien, tongTien, tienNhan, tienThua, maKH, maNV);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				clearTextfield();
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
			System.out.println(s);
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

	private void inHoaDon(String maHD, LocalDate ngayLapHD, List<CTHoaDon> ds, double thanhTien, double tongTien, Double tienNhan, Double tienThua, String maKH, String maNV) throws IOException, SQLException, URISyntaxException {
		String tenKH = txtTenKH.getText();
		if(tenKH.equals("Tên khách hàng")) {
			tenKH = "";
		}
//		new HoaDonIn(maHD, ngayLapHD, dsCTHD, thanhTien, tongTien, tienNhan, tienThua, maKH, maNV, tenKH).setVisible(true);
		PdfWriter pdfWriter = new PdfWriter(new FileOutputStream("HoaDon/HoaDon"+ maHD +".pdf"));
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		pdfDocument.setDefaultPageSize(PageSize.A5);
		PdfFont font = PdfFontFactory.createFont( FontProgramFactory.createFont("Font/000 Chinacat [TeddyBear].ttf"),PdfEncodings.IDENTITY_H);
		Paragraph tt = new Paragraph("HÓA ĐƠN BÁN HÀNG");
		tt.setFont(font).setMarginLeft(100).setFontSize(20);
		document.add(tt);
		Paragraph ma = new Paragraph("Mã hóa đơn: " + maHD);
		ma.setFont(font);
		document.add(ma);
		Paragraph ngay = new Paragraph("Ngày lập hóa đơn: " + ngayLapHD);
		ngay.setFont(font).setMarginBottom(50);
		document.add(ngay);
		Paragraph chiTietHD = new Paragraph("Chi Tiết Hóa Đơn");
		chiTietHD.setFont(font);
		document.add(chiTietHD);
		Paragraph line = new Paragraph("-------------------------------------------------------------------------------------");
		String space1 = "                                     ";
		String space2 = "             ";
		String space3 = "                    ";
		Paragraph header = new Paragraph("Tên sản phẩm"+ space1 + "số lượng"+ space2 + "giá"+ space3 + "thành tiền");
		header.setFont(font).setFontSize(9);
		document.add(header);
		document.add(line);
		for(CTHoaDon cthd: ds) {
			Paragraph row = new Paragraph(cthd.toString());
			row.setFont(font).setFontSize(9);
			document.add(row);
		}
		document.add(line);
		Paragraph tien = new Paragraph("Thành tiền: "+ String.valueOf(thanhTien) +  " VND");
		tien.setFont(font).setMarginTop(10).setFontSize(15);
		document.add(tien);
		Paragraph tong = new Paragraph("Tổng tiền sau thuế (10%): "+ String.valueOf(tongTien) + " VND");
		tong.setFont(font).setFontSize(15);
		document.add(tong);
		Paragraph nv = new Paragraph("Mã nhân viên: "+ maNV);
		nv.setFont(font).setMarginTop(50);
		document.add(nv);
		Paragraph kh = new Paragraph("Khách Hàng: " + tenKH + " | " + maKH );
		kh.setFont(font);
		document.add(kh);
		document.close();
		Desktop.getDesktop().open(new File("HoaDon/HoaDon"+ maHD +".pdf"));
	}

	private boolean validThanhToan() {
		Double tienNhan = 0.0;
		Double tienThua = 0.0;
		
		if(modelHoaDon.getColumnCount()==0) {
			JOptionPane.showMessageDialog(contentPane, "Hãy thêm sản phẩm vào hóa đơn");
		}
		try {
			tienNhan = Double.parseDouble(txtTienNhan.getText()) ;
			tienThua = Double.parseDouble(txtTienThua.getText());
		} catch (Exception e) {
			showMessage("Tiền nhận phải là số", txtTienNhan);
			return false;
		}
		if(tienThua<0) {
			JOptionPane.showMessageDialog(contentPane, "Chưa đủ tiền thanh toán");
			return false;
		}
		return true;
	}

	private void clearTextfield() {
		txtMaSP.setText("Mã sản phẩm");
		txtTenSp.setText("Tên sản phẩm");
		txtSoLuong.setText("Số lượng");
		txtThanhTien.setText("Thành tiền");
		txtTienNhan.setText("Tiền nhận");
		txtTienSauThue.setText("Tiền sau thuế");
		txtTienThua.setText("Tiền thừa");
		txtTienNhan.setEditable(false);
		txtSdt.setText("Số điện thoại");
		txtTenKH.setText("Tên khách hàng");
		dsCTHD.removeAll(dsCTHD);
		modelHoaDon.setRowCount(0);
		try {
			maHDMoi = tinhToan_dao.maHoaDonMoi();
			hoadon = new HoaDon(maHDMoi);
			txtMaHD.setText(maHDMoi);
			updateTable(sp_dao.getDsSanPham());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private void selectedRow(int row) {
		if (row != -1) {
			tableSanPham.setRowSelectionInterval(row, row);
			tableSanPham.scrollRectToVisible(tableSanPham.getCellRect(row, row, true));
		}
	}
	private void updateTable(List<SanPham> ds) throws Exception {
		modelSanPham.setRowCount(0);
		for (SanPham sp : ds) {
			Object[] rowData = { sp.getMaSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getMau(),
					sp.getSize(), ncc_dao.timMa(sp.getNcc().getMaNCC()).getTenNCC()  };
			modelSanPham.addRow(rowData);
		}
	}
	private boolean validData() throws Exception {
		int  soLuong = 0;
		int index = tableSanPham.getSelectedRow();
		String maSPSelected = (String) tableSanPham.getValueAt(index, 0);
		String[] s = new String[modelHoaDon.getRowCount()];
		
		if(index < 0 ) {
			JOptionPane.showMessageDialog(contentPane, "Hãy chọn sản phẩm muốn thêm vào hoá đơn");
			return false;
		}
		try {
			soLuong = Integer.parseInt(txtSoLuong.getText().trim());
		} catch (Exception e2) {
			showMessage("Số lượng phải là số", txtSoLuong);
			return false;
		}
		for(int i = 0; i< modelHoaDon.getRowCount(); i++) {
			s[i] = (String) modelHoaDon.getValueAt(i, 0);
			if(maSPSelected.equals(s[i])) {
				soLuong += (int) modelHoaDon.getValueAt(i,3);
				if(soLuong > sp_dao.timMa(maSPSelected).getSoLuong()) {
					JOptionPane.showMessageDialog(panel, "Vượt quá số lượng trong kho");
					return false;
				}
			}
		}
		if(soLuong == 0) {
			showMessage("Nhập số lượng", txtSoLuong);
			return false;
		}
		if(soLuong > sp_dao.timMa(maSPSelected).getSoLuong()) {
			showMessage("Số lượng sản phẩm trong kho không đủ", txtSoLuong);
			return false;
		}
		return true;
	}
	private void showMessage(String message, JTextField jTextField) {
		JOptionPane.showMessageDialog(this, message);
		jTextField.setText("");
		jTextField.requestFocus();
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		Object o = e.getSource();
		if(o.equals(txtTienNhan)) {
			double tienNhan = 0;
			tienNhan = Double.parseDouble(txtTienNhan.getText());
			double tienThua = tienNhan - Double.parseDouble(txtTienSauThue.getText());
			txtTienThua.setText(String.format("%.2f",tienThua));
		}
	}
}
