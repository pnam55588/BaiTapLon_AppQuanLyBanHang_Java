package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

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
import components.roundJTextField.RoundJTextField;
import dao.SanPhamDao;
import entity.KhachHang;
import entity.LoaiKH;
import entity.NhaCungCap;
import entity.SanPham;
import service.IKhachHangService;
import service.ILoaiKHService;
import service.impl.KhachHangImpl;
import service.impl.LoaiKHImpl;

import javax.swing.DropMode;

public class CapNhatKH extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Button btnLuu, btnHuy, btnThoat;
	private RoundJTextField txtMaKH, txtTenKH, txtSDT, txtSoID, txtEmail, txtSoTienMuaHang, txtDiaChi;
	private JComboBox cbbGioiTinh, cbbLoaiKH;
	private KhachHang kh;
	private IKhachHangService kh_dao = new KhachHangImpl();
	private ILoaiKHService loaiKH_dao = new LoaiKHImpl();
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CapNhatKH(String maKH) throws SQLException {
		kh = kh_dao.timMa(maKH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("VIR - Urbanus et elegans");
		setResizable(false);
		setBounds(295, 170, 1195, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 80, 75));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCapNhat = new JLabel("Thông tin khách hàng");
		lblCapNhat.setForeground(new Color(230, 230, 230));
		lblCapNhat.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblCapNhat.setBounds(50, 50, 600, 70);
		contentPane.add(lblCapNhat);
		
		txtMaKH = new RoundJTextField(30);
		txtMaKH.setEnabled(false);
		txtMaKH.setEditable(false);
		txtMaKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMaKH.setText("");
			}
		});
		txtMaKH.setForeground(new Color(84, 80, 75));
		txtMaKH.setBackground(new Color(243, 238, 231));
		txtMaKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaKH.setFont(new Font("000 Chinacat [TeddyBear]", txtMaKH.getFont().getStyle(), txtMaKH.getFont().getSize() + 5));
		txtMaKH.setToolTipText("Nhập mã khách hàng");
		txtMaKH.setText(kh.getMaKH());
		txtMaKH.setBounds(130, 170, 200, 40);
		contentPane.add(txtMaKH);
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
		txtTenKH.setText(kh.getTenKH());
		txtTenKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKH.setForeground(new Color(84, 80, 75));
		txtTenKH.setColumns(10);
		txtTenKH.setBackground(new Color(243, 238, 231));
		txtTenKH.setBounds(380, 170, 400, 40);
		contentPane.add(txtTenKH);
		
		txtSDT = new RoundJTextField(30);
		txtSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSDT.setText("");
			}
		});
		txtSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSDT.setToolTipText("Nhập số điện thoại");
		txtSDT.setText(kh.getSdt());
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDT.setForeground(new Color(84, 80, 75));
		txtSDT.setColumns(10);
		txtSDT.setBackground(new Color(243, 238, 231));
		txtSDT.setBounds(830, 170, 200, 40);
		contentPane.add(txtSDT);
		
		txtDiaChi = new RoundJTextField(30);
		txtDiaChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDiaChi.setText("");
			}
		});
		txtDiaChi.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtDiaChi.setToolTipText("Nhập địa chỉ");
		txtDiaChi.setText(kh.getDiaChi());
		txtDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiaChi.setForeground(new Color(84, 80, 75));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBackground(new Color(243, 238, 231));
		txtDiaChi.setBounds(130, 250, 200, 40);
		contentPane.add(txtDiaChi);
		
		txtSoID = new RoundJTextField(30);
		txtSoID.setEnabled(false);
		txtSoID.setEditable(false);
		txtSoID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSoID.setText("");
			}
		});
		txtSoID.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoID.setToolTipText("Nhập số ID");
		txtSoID.setText(kh.getSoID());
		txtSoID.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoID.setForeground(new Color(84, 80, 75));
		txtSoID.setColumns(10);
		txtSoID.setBackground(new Color(243, 238, 231));
		txtSoID.setBounds(830, 250, 200, 40);
		contentPane.add(txtSoID);
		
		txtSoTienMuaHang = new RoundJTextField(30);
		txtSoTienMuaHang.setEnabled(false);
		txtSoTienMuaHang.setEditable(false);
		txtSoTienMuaHang.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoTienMuaHang.setToolTipText("Nhập số tiền mua hàng");
		txtSoTienMuaHang.setText(String.valueOf(kh.getSoTienMuaHang()));
		txtSoTienMuaHang.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoTienMuaHang.setForeground(new Color(84, 80, 75));
		txtSoTienMuaHang.setColumns(10);
		txtSoTienMuaHang.setBackground(new Color(243, 238, 231));
		txtSoTienMuaHang.setBounds(380, 250, 400, 40);
		contentPane.add(txtSoTienMuaHang);
		
		cbbGioiTinh = new JComboBox();
		cbbGioiTinh.setEnabled(false);
		cbbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbbGioiTinh.setSelectedItem(kh.getGioiTinh());
		cbbGioiTinh.setBackground(new Color(243, 238, 231));
		cbbGioiTinh.setBounds(470, 340, 100, 30);
		contentPane.add(cbbGioiTinh);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(new Color(230, 230, 230));
		lblGioiTinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblGioiTinh.setBounds(380, 340, 80, 30);
		contentPane.add(lblGioiTinh);
		
		cbbLoaiKH = new JComboBox();
		cbbLoaiKH.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		cbbLoaiKH.setSelectedItem(loaiKH_dao.timMa(kh.getLoai().getMaLoai()).getTenLoai());
		cbbLoaiKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbLoaiKH.setBackground(new Color(243, 238, 231));
		cbbLoaiKH.setBounds(735, 340, 45, 30);
		contentPane.add(cbbLoaiKH);
		
		JLabel lblLoaiKH = new JLabel("Loại khách hàng:");
		lblLoaiKH.setForeground(new Color(230, 230, 230));
		lblLoaiKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblLoaiKH.setBounds(610, 340, 130, 30);
		contentPane.add(lblLoaiKH);
		
		btnLuu = new Button("");
		btnLuu.setRadius(39);
		btnLuu.setColorClick(new Color(84, 80, 75));
		btnLuu.setToolTipText("Cập nhật sản phẩm");
		btnLuu.setIcon(new ImageIcon(CapNhatKH.class.getResource("/update.png")));
		btnLuu.setBounds(387, 501, 39, 39);
		contentPane.add(btnLuu);
		
		btnHuy = new Button("");
		btnHuy.setRadius(39);
		btnHuy.setColorClick(new Color(84, 80, 75));
		btnHuy.setToolTipText("Huỷ thao tác");
		btnHuy.setIcon(new ImageIcon(CapNhatKH.class.getResource("/Delete.png")));
		btnHuy.setBounds(711, 501, 39, 39);
		contentPane.add(btnHuy);
		
		btnThoat = new Button("");
		btnThoat.setIcon(new ImageIcon(CapNhatKH.class.getResource("/Exit.png")));
		btnThoat.setToolTipText("Quay lại");
		btnThoat.setRadius(39);
		btnThoat.setColorClick(new Color(84, 80, 75));
		btnThoat.setBounds(1132, 10, 39, 39);
		contentPane.add(btnThoat);
		
		txtEmail = new RoundJTextField(30);
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEmail.setText("");
			}
		});
		txtEmail.setToolTipText("Nhập Email");
		txtEmail.setText(kh.getEmail());
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setForeground(new Color(84, 80, 75));
		txtEmail.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBackground(new Color(243, 238, 231));
		txtEmail.setBounds(130, 330, 200, 40);
		contentPane.add(txtEmail);
		
		JLabel lblMaKH = new JLabel("Mã khách hàng");
		lblMaKH.setForeground(new Color(243, 238, 231));
		lblMaKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblMaKH.setBounds(170, 150, 120, 20);
		contentPane.add(lblMaKH);
		
		JLabel lblTenKH = new JLabel("Tên khách hàng");
		lblTenKH.setForeground(new Color(243, 238, 231));
		lblTenKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblTenKH.setBounds(500, 150, 120, 20);
		contentPane.add(lblTenKH);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiaChi.setForeground(new Color(243, 238, 231));
		lblDiaChi.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblDiaChi.setBounds(170, 231, 120, 20);
		contentPane.add(lblDiaChi);
		
		JLabel lblSoTienMuaHang = new JLabel("Số tiền mua hàng");
		lblSoTienMuaHang.setForeground(new Color(243, 238, 231));
		lblSoTienMuaHang.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSoTienMuaHang.setBounds(500, 235, 120, 20);
		contentPane.add(lblSoTienMuaHang);
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setHorizontalAlignment(SwingConstants.CENTER);
		lblSDT.setForeground(new Color(243, 238, 231));
		lblSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSDT.setBounds(880, 154, 120, 20);
		contentPane.add(lblSDT);
		
		JLabel lblSOID = new JLabel("Số ID");
		lblSOID.setHorizontalAlignment(SwingConstants.CENTER);
		lblSOID.setForeground(new Color(243, 238, 231));
		lblSOID.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSOID.setBounds(880, 235, 120, 20);
		contentPane.add(lblSOID);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(new Color(243, 238, 231));
		lblEmail.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblEmail.setBounds(170, 312, 120, 20);
		contentPane.add(lblEmail);

		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			clearTextfield();
		}
		if (o.equals(btnThoat)) {
			this.setVisible(false);
		}
		if (o.equals(btnLuu)) {
			if(validData()) {
				String maKH = txtMaKH.getText();
				String tenKH = txtTenKH.getText();
				String sdt = txtSDT.getText();
				String email = txtEmail.getText();
				String diaChi = txtDiaChi.getText();
				String loai = (String) cbbLoaiKH.getSelectedItem();
				String maLoai = "";
				try {
					if (!loai.equals("")) {

						for (LoaiKH x : loaiKH_dao.getDsLoaiKH()) {
							if (x.getTenLoai().compareToIgnoreCase(loai) == 0)
								maLoai = x.getMaLoai();
						}
					}
					kh_dao.suaKhachHang(maKH, tenKH, sdt, email, maLoai, diaChi);
					JOptionPane.showMessageDialog(contentPane, "Cập nhật thông tin khách hàng thành công!");
				} catch (Exception e1) {
					e1.printStackTrace();
				
				}
			}
		}
	}
	
	private boolean validData() {
		String maKH = txtMaKH.getText();
		String tenKH = txtTenKH.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String diaChi = txtDiaChi.getText();
		String loai = (String) cbbLoaiKH.getSelectedItem();
		
		if(tenKH.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập tên khách hàng!");
			return false;
		}
		if(sdt.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập số điện thoại của khách hàng!");
			return false;
		}
		if(!email.matches("^(.+)@(.+).(.+)$")) {
			JOptionPane.showMessageDialog(contentPane, "Email có định dạng là xxx@yyy.zzz!");
			return false;
		}
		return true;
	}
	
	private void clearTextfield() {
		dispose();
	}
}
