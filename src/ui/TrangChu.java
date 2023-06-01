package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.panelRound.PanelRound;
import entity.NhanVien;
import service.INhanVienService;
import service.impl.NhanVienImpl;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class TrangChu extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private AbstractButton lblBackground;
	private Button btnTaoHoaDon, btnQLNV, btnQLKH, btnQLSP, btnTK;
	private JComboBox cbbChao;
	public static NhanVien nhanVienDangNhap;
	public static String maNVDangNhap;
	private INhanVienService nv_dao = new NhanVienImpl();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public TrangChu(String maNVDangNhap) throws SQLException {
		nhanVienDangNhap = nv_dao.timMa(maNVDangNhap);
		this.maNVDangNhap = maNVDangNhap;
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
		lblAvatar1.setIcon(new ImageIcon(TrangChu.class.getResource("/avatar1.jpg")));
		lblAvatar1.setBounds(50, 20, 39, 39);
		pnlHeader.add(lblAvatar1);

		JLabel lblAvatar2 = new JLabel("");
		lblAvatar2.setIcon(new ImageIcon(TrangChu.class.getResource("/avatar1.jpg")));
		lblAvatar2.setBounds(1214, 20, 39, 39);
		pnlHeader.add(lblAvatar2);

		PanelRound pnlMenu = new PanelRound(20);
		pnlMenu.setBackground(new Color(222, 217, 214));
		pnlMenu.setBounds(0, 90, 250, 593);
		contentPane.add(pnlMenu);
		pnlMenu.setLayout(null);

		btnTaoHoaDon = new Button("Tạo hoá đơn");
		btnTaoHoaDon.setIcon(new ImageIcon(TrangChu.class.getResource("/Add to basket.png")));
		btnTaoHoaDon.setRadius(50);
		btnTaoHoaDon.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTaoHoaDon.setBounds(10, 10, 240, 60);
		btnTaoHoaDon.setBackground(new Color(255, 255, 255));
		pnlMenu.add(btnTaoHoaDon);

		btnQLSP = new Button("Quản lý sản phẩm");
		btnQLSP.setIcon(new ImageIcon(TrangChu.class.getResource("/Favourites.png")));
		btnQLSP.setRadius(50);
		btnQLSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLSP.setBackground(Color.WHITE);
		btnQLSP.setBounds(10, 80, 240, 60);
		pnlMenu.add(btnQLSP);

		btnQLKH = new Button("Quản lý khách hàng");
		btnQLKH.setIcon(new ImageIcon(TrangChu.class.getResource("/User.png")));
		btnQLKH.setRadius(50);
		btnQLKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLKH.setBackground(Color.WHITE);
		btnQLKH.setBounds(10, 150, 240, 60);
		pnlMenu.add(btnQLKH);

		btnQLNV = new Button("Quản lý nhân viên");
		btnQLNV.setIcon(new ImageIcon(TrangChu.class.getResource("/Users.png")));
		btnQLNV.setRadius(50);
		btnQLNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnQLNV.setBackground(Color.WHITE);
		btnQLNV.setBounds(10, 220, 240, 60);
		pnlMenu.add(btnQLNV);

		btnTK = new Button("Thống kê");
		btnTK.setIcon(new ImageIcon(TrangChu.class.getResource("/Price list.png")));
		btnTK.setRadius(50);
		btnTK.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		btnTK.setBackground(Color.WHITE);
		btnTK.setBounds(10, 290, 240, 60);
		pnlMenu.add(btnTK);

		PanelRound pnlBackGround = new PanelRound(20);
		pnlBackGround.setBounds(250, 90, 1176, 593);
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);

		JLabel lblTen = new JLabel("Vir - Urbanus et elegans");
		lblTen.setBackground(new Color(63, 63, 63));
		lblTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTen.setForeground(new Color(222, 217, 214));
		lblTen.setFont(new Font("000 Spellcaster [TeddyBear]", Font.BOLD, 24));
		lblTen.setBounds(891, 383, 279, 112);
		pnlBackGround.add(lblTen);

		JLabel lblDiaChi = new JLabel("12 Nguyễn Văn Bảo, phường 4, quận Gò Vấp, TP. HCM");
		lblDiaChi.setBackground(new Color(63, 63, 63));
		lblDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiaChi.setForeground(new Color(222, 217, 214));
		lblDiaChi.setFont(new Font("000 Marvin [TeddyBear]", Font.PLAIN, 15));
		lblDiaChi.setBounds(681, 454, 489, 57);
		pnlBackGround.add(lblDiaChi);

		JLabel lblBackGround = new JLabel("");
		lblBackGround.setBackground(new Color(255, 255, 255));
		lblTen.setLabelFor(lblBackGround);
		lblBackGround.setIcon(new ImageIcon(TrangChu.class.getResource("/background2 - Copy (2).jpg")));
		lblBackGround.setBounds(0, 0, 1176, 593);
		pnlBackGround.add(lblBackGround);
		
		
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

		/*
		 * event
		 *
		 */
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
}
