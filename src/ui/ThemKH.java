package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import components.button.Button;
import components.roundJTextField.RoundJTextField;
import entity.KhachHang;
import entity.LoaiKH;
import entity.LoaiSP;
import entity.NhaCungCap;
import entity.SanPham;
import service.IKhachHangService;
import service.ILoaiKHService;
import service.ILoaiSPService;
import service.INhaCungCapService;
import service.ITinhToan;
import service.impl.KhachHangImpl;
import service.impl.LoaiKHImpl;
import service.impl.LoaiSPServiceImpl;
import service.impl.NhaCungCapImpl;
import service.impl.TinhToanImpl;

public class ThemKH extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private ITinhToan utilTt;
	private RoundJTextField txtMaKH, txtTenKH, txtSoID, txtSDT, txtEmail, txtDiaChi;
	private Button btnLuu, btnHuy, btnThoat;
	private KhachHangImpl utilKH;
	private JComboBox cbbLoaiKH, cbbGioiTinh;
	private ButtonGroup bgGioiTinh;
	private RoundJTextField txtThongBao;
	private Component lblGioiTinh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemKH frame = new ThemKH();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ThemKH() throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 567, 516);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 80, 75));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblThem = new JLabel("Thêm khách hàng");
		lblThem.setForeground(new Color(230, 230, 230));
		lblThem.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblThem.setBounds(50, 21, 333, 70);
		contentPane.add(lblThem);
		
		utilTt = new TinhToanImpl();
		txtMaKH = new RoundJTextField(30);
		txtMaKH.setForeground(new Color(84, 80, 75));
		txtMaKH.setBackground(new Color(243, 238, 231));
		txtMaKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaKH.setFont(new Font("000 Chinacat [TeddyBear]", txtMaKH.getFont().getStyle(), txtMaKH.getFont().getSize() + 5));
		txtMaKH.setToolTipText("Nhập mã khách hàng");
		txtMaKH.setText(utilTt.maKhachHangMoi());
		txtMaKH.setBounds(182, 118, 270, 27);
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);
		txtMaKH.setEditable(false); 
		
		txtTenKH = new RoundJTextField(30);
		txtTenKH.setFont(new Font("000 Chinacat [TeddyBear]", txtTenKH.getFont().getStyle(), txtTenKH.getFont().getSize() + 5));
		txtTenKH.setToolTipText("Nhập tên khách hàng");
		txtTenKH.setText("Trần Anh Thư");
		txtTenKH.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenKH.setForeground(new Color(84, 80, 75));
		txtTenKH.setColumns(10);
		txtTenKH.setBackground(new Color(243, 238, 231));
		txtTenKH.setBounds(182, 192, 270, 27);
		contentPane.add(txtTenKH);
		
		txtSoID = new RoundJTextField(30);
		txtSoID.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoID.setToolTipText("Nhập số ID");
		txtSoID.setText("272829210");
		txtSoID.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoID.setForeground(new Color(84, 80, 75));
		txtSoID.setColumns(10);
		txtSoID.setBackground(new Color(243, 238, 231));
		txtSoID.setBounds(182, 155, 270, 27);
		contentPane.add(txtSoID);
		
		txtSDT = new RoundJTextField(30);
		txtSDT.setText("0971557969");
		txtSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSDT.setToolTipText("Nhập số điện thoại");
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDT.setForeground(new Color(84, 80, 75));
		txtSDT.setColumns(10);
		txtSDT.setBackground(new Color(243, 238, 231));
		txtSDT.setBounds(182, 303, 270, 27);
		contentPane.add(txtSDT);
				
		JLabel lblLoaiKH= new JLabel("Loại khách hàng");
		lblLoaiKH.setForeground(new Color(255, 255, 255));
		lblLoaiKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblLoaiKH.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiKH.setBounds(50, 393, 118, 27);
		contentPane.add(lblLoaiKH);
		
		cbbLoaiKH = new JComboBox();
		cbbLoaiKH.setModel(new DefaultComboBoxModel(new String[] {"Thường", "VIP"}));
		cbbLoaiKH.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbLoaiKH.setBackground(new Color(243, 238, 231));
		cbbLoaiKH.setBounds(182, 391, 270, 30);
		contentPane.add(cbbLoaiKH);

		txtDiaChi = new RoundJTextField(30);
		txtDiaChi.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtDiaChi.setToolTipText("Nhập email");
		txtDiaChi.setText("hahhaa@gmail.com");
		txtDiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiaChi.setForeground(new Color(84, 80, 75));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBackground(new Color(243, 238, 231));
		txtDiaChi.setBounds(182, 266, 270, 27);
		contentPane.add(txtDiaChi);

		txtEmail = new RoundJTextField(30);
		txtEmail.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtEmail.setToolTipText("Nhập địa chỉ");
		txtEmail.setText("24/1 Quận 1");
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setForeground(new Color(84, 80, 75));
		txtEmail.setColumns(10);
		txtEmail.setBackground(new Color(243, 238, 231));
		txtEmail.setBounds(182, 229, 270, 27);
		contentPane.add(txtEmail);
		
		txtThongBao = new RoundJTextField(30);
		txtThongBao.setToolTipText("Thông báo");
		txtThongBao.setText("Thông báo");
		
		btnLuu = new Button("");
		btnLuu.setRadius(39);
		btnLuu.setColorClick(new Color(84, 80, 75));
		btnLuu.setToolTipText("Lưu nhân viên");
		btnLuu.setIcon(new ImageIcon(ThemKH.class.getResource("/Add.png")));
		btnLuu.setBounds(142, 429, 41, 40);
		contentPane.add(btnLuu);
		
		btnHuy = new Button("");
		btnHuy.setRadius(39);
		btnHuy.setColorClick(new Color(84, 80, 75));
		btnHuy.setToolTipText("Huỷ thao tác");
		btnHuy.setIcon(new ImageIcon(ThemKH.class.getResource("/Delete.png")));
		btnHuy.setBounds(325, 431, 41, 40);
		contentPane.add(btnHuy);
		
		btnThoat = new Button("");
		btnThoat.setIcon(new ImageIcon(ThemKH.class.getResource("/Exit.png")));
		btnThoat.setToolTipText("Quay lại");
		btnThoat.setRadius(39);
		btnThoat.setColorClick(new Color(84, 80, 75));
		btnThoat.setBounds(448, 27, 57, 51);
		contentPane.add(btnThoat);
		
		cbbGioiTinh = new JComboBox();
		cbbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbbGioiTinh.setBackground(new Color(243, 238, 231));
		cbbGioiTinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbGioiTinh.setBounds(182, 348, 270, 30);
		contentPane.add(cbbGioiTinh);

		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setBounds(50, 343, 71, 40);
		lblGioiTinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		contentPane.add(lblGioiTinh);
		
		JLabel lblSo = new JLabel("Số điện thoại:");
		lblSo.setForeground(new Color(255, 255, 255));
		lblSo.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSo.setBounds(50, 296, 120, 40);
		contentPane.add(lblSo);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEmail.setBounds(48, 259, 120, 40);
		contentPane.add(lblEmail);
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setForeground(new Color(255, 255, 255));
		lblaCh.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblaCh.setBounds(50, 222, 120, 40);
		contentPane.add(lblaCh);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setForeground(new Color(255, 255, 255));
		lblTnKhchHng.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTnKhchHng.setBounds(50, 185, 120, 40);
		contentPane.add(lblTnKhchHng);
		
		JLabel lblSCmndcccd = new JLabel("Số cmnd/cccd:");
		lblSCmndcccd.setForeground(new Color(255, 255, 255));
		lblSCmndcccd.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSCmndcccd.setBounds(48, 148, 120, 40);
		contentPane.add(lblSCmndcccd);
		
		JLabel lblMKhchHng = new JLabel("Mã khách hàng:");
		lblMKhchHng.setForeground(new Color(255, 255, 255));
		lblMKhchHng.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblMKhchHng.setBounds(50, 111, 120, 40);
		contentPane.add(lblMKhchHng);
		
		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
	}
	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			clearTextfield();
		}
		if (o.equals(btnThoat)) {
			this.setVisible(false);
		}
		if(o.equals(btnLuu)) {
			if(validData()) {
				String maKH = txtMaKH.getText().trim();
				String tenKH = txtTenKH.getText().trim();
				String soID = txtSoID.getText().trim();
				String sDT = txtSDT.getText().trim();
				String email = txtEmail.getText().trim();
				String diaChi = txtDiaChi.getText().trim();
				String gioiTinh =  cbbGioiTinh.getSelectedItem().toString();
				System.out.println(gioiTinh);
				utilKH = new KhachHangImpl();
				KhachHang kh = new KhachHang(maKH, tenKH, soID, sDT, gioiTinh, email, diaChi);
				String loai = cbbLoaiKH.getSelectedItem().toString();
				ILoaiKHService utilLoai = new LoaiKHImpl();
				String maLoai = null;
				try {
					for(LoaiKH l: utilLoai.getDsLoaiKH()) {
						if(l.getTenLoai().compareToIgnoreCase(loai)==0) {
							maLoai = l.getMaLoai();
						}
					}

					utilKH.themKhachHang(kh, maLoai);
					showMessage("Thêm thành công", txtThongBao);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	private boolean validData() {
		String maKH = txtMaKH.getText().trim();
		String tenKH = txtTenKH.getText().trim();
		String soID = txtSoID.getText().trim();
		String sDT = txtSDT.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		IKhachHangService utilKT = new KhachHangImpl();
		String maLoai = null;

		if (maKH.length() == 0) {
			showMessage("Nhập mã nhân viên", txtMaKH);
			return false;
		}
		if (!maKH.matches("^(KH\\d{3})$")) {
			showMessage(
					"Mã khách hàng phải bắt đầu bằng KH, theo sau là 3 số bất kỳ",
					txtMaKH);
			return false;
		}
		if (tenKH.equals("")) {
			showMessage("Nhập tên khách hàng", txtTenKH);
			return false;
		}
		if (soID.length() == 0) {
			showMessage("Nhập số ID", txtSoID);
			return false;
		}
		try {
			Long.parseLong(soID);
		} catch (Exception e) {
			showMessage("Số ID phải là số", txtSoID);
			return false;
		}
		try {
			for(KhachHang l2: utilKT.getDsKhachHang()) {
				if(l2.getSoID().equals(soID)) {
					showMessage("Đã tồn tại số ID này", txtSoID);
					return false;
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		if (sDT.length() == 0 || sDT.length() > 10) {
			showMessage("sdt phải đủ 10 số", txtSDT);
			return false;
		}
		try {
			Integer.parseInt(sDT);
		} catch (Exception e) {
			showMessage("Nhập số điện thoại", txtSDT);
			return false;
		}
		try {
			for(KhachHang l2: utilKT.getDsKhachHang()) {
				if(l2.getSdt().equals(sDT)) {
					showMessage("Đã tồn tại số điện thoại này", txtSDT);
					return false;
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		if(sDT.equals("")) {
			
		}
		if (email.equals("")) {
			showMessage("Nhập email", txtEmail);
			return false;
		}		
		try {
			for(KhachHang l2: utilKT.getDsKhachHang()) {
				if(l2.getEmail().equals(email)) {
					showMessage("Đã tồn tại email này", txtEmail);
					return false;
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		if (diaChi.equals("")) {
			showMessage("Nhập địa chỉ", txtDiaChi);
			return false;
		}

		return true;
	}
	private void showMessage(String message, JTextField jTextField) {
		JOptionPane.showMessageDialog(this, message);
		jTextField.setText("");
		jTextField.requestFocus();
	}
	
	private void clearTextfield() {
		try {
			txtMaKH.setText(utilTt.maKhachHangMoi());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTenKH.setText("Tên khách hàng");
		txtSoID.setText("Số ID");
		txtSDT.setText("Số điện thoại");
		txtDiaChi.setText("Email");
		txtEmail.setText("Địa chỉ");
		cbbLoaiKH.setSelectedIndex(0);
	}
}

