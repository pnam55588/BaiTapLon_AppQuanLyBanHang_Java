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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.SystemColor;

public class Help extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Button btnTaoHoaDon, btnQLKH, btnQLNV, btnQLSP, btnTK;
	private DefaultTableModel model;
	private JPanel pnlMenu;
	private JPanel pnlBackGround;
	private JPanel pnlDoanhThu;
	private DefaultTableModel model2;
	private DefaultTableModel model3;
	private INhanVienService nv_dao = new NhanVienImpl();
	private ITinhToan tinhToan_dao = new TinhToanImpl();
	private ICTHoaDonService cthd_dao = new CTHoaDonImpl();
	private IHoaDonService hd_dao = new HoaDonImpl();
	private NhanVien nv;
	private String maNV, tenNV;
	private NhanVien nhanVien;
	private JComboBox cbbChao;
	private JComboBox comboBoxThang;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */

	public Help() throws SQLException, Exception {
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
		lblAvatar1.setIcon(new ImageIcon(Help.class.getResource("/avatar1.jpg")));
		lblAvatar1.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar1);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(Help.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1214, 20, 39, 39);
		pnlHeader.add(lblAvatar2);

		PanelRound pnlMenu = new PanelRound(20);
		pnlMenu.setBackground(new Color(222, 217, 214));
		pnlMenu.setBounds(0, 90, 250, 593);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setIcon(new ImageIcon(Help.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		btnTaoHoaDon.setBackground(new Color(255, 255, 255));
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setIcon(new ImageIcon(Help.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBackground(Color.WHITE);
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setIcon(new ImageIcon(Help.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBackground(Color.WHITE);
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon(Help.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBackground(Color.WHITE);
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setColor(new Color(255, 255, 255));
		btnTK.setColorOver(new Color(255, 255, 255));
		btnTK.setColorClick(new Color(255, 255, 255));
		btnTK.setBorderBtnColor(new Color(255, 255, 255));
		btnTK.setForeground(new Color(0, 0, 0));
		btnTK.setBorderColor(new Color(255, 255, 255));
		btnTK.setIcon(new ImageIcon(Help.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBackground(new Color(255, 255, 255));
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);
		
		Button btnHelp = new Button();
		btnHelp.setColor(Color.DARK_GRAY);
		btnHelp.setColorOver(Color.DARK_GRAY);
		btnHelp.setColorClick(Color.DARK_GRAY);
		btnHelp.setForeground(Color.WHITE);
		btnHelp.setBorderColor(Color.DARK_GRAY);
		btnHelp.setBorderBtnColor(Color.DARK_GRAY);
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
		btnHelp.setBackground(Color.DARK_GRAY);
		btnHelp.setBounds(10, 360, 240, 60);
		pnlMenu.add(btnHelp);

		pnlBackGround = new PanelRound(20);
		pnlBackGround.setBackground(new Color(84, 80, 75));
		pnlBackGround.setBounds(250, 90, 1176, 593);
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);

		pnlDoanhThu = new JPanel();
		pnlDoanhThu.setBackground(new Color(222, 217, 214));
		pnlDoanhThu.setBounds(10, 50, 1135, 533);
		pnlBackGround.add(pnlDoanhThu);
		pnlDoanhThu.setLayout(null);

		JLabel lblCamOn = new JLabel("Nhấn vào link sau để mở tài liệu hướng dẫn sử dụng ứng dụng:");
		lblCamOn.setBackground(new Color(128, 64, 0));
		lblCamOn.setFont(new Font("Dialog", Font.BOLD, 21));
		lblCamOn.setBounds(10, 0, 788, 39);
		pnlDoanhThu.add(lblCamOn);
		
		JLabel hyperlink = new JLabel("<html><a href='https://docs.google.com/document/d/1yAJCJinvjZ7tY-aFhlad8IA5Tz7T1JB4/edit'>https://docs.google.com/document/d/1yAJCJinvjZ7tY-aFhlad8IA5Tz7T1JB4/edit</a></html>");
		hyperlink.setForeground(SystemColor.textHighlight);
		hyperlink.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hyperlink.setBounds(10, 42, 718, 71);
		pnlDoanhThu.add(hyperlink);
		hyperlink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					URI uri = new URI("https://docs.google.com/document/d/1yAJCJinvjZ7tY-aFhlad8IA5Tz7T1JB4/edit");
					try {
						Desktop.getDesktop().browse(uri);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});


		// =================================
		// doc du lieu tu database SQL vao Table tổng doanh thu, tao ket noi dao
		nv_dao = new NhanVienImpl();
		// =================================

		// add sự kiện
		
		
		btnTaoHoaDon.addActionListener(this);
		btnQLKH.addActionListener(this);
		btnQLNV.addActionListener(this);
		btnQLSP.addActionListener(this);
		btnTK.addActionListener(this);
		cbbChao.addActionListener(this);

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
//		if()
	}

	public static void main(String[] args) throws SQLException, Exception {
		TKDoanhThuNVQL app = new TKDoanhThuNVQL();
		app.setVisible(true);
	}
}