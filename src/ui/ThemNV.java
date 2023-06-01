package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.comboBox.ComboBox;
import components.roundJTextField.RoundJTextField;
import dao.LoaiSPDao;
import entity.CaLamViec;
import entity.KhachHang;
import entity.LoaiNV;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.SanPham;
import service.ICaLamViecService;
import service.ILoaiNVService;
import service.ILoaiSPService;
import service.INhaCungCapService;
import service.INhanVienService;
import service.ISanPhamService;
import service.ITinhToan;
import service.impl.CaLamViecImpl;
import service.impl.LoaiNVImpl;
import service.impl.NhaCungCapImpl;
import service.impl.NhanVienImpl;
import service.impl.TinhToanImpl;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

public class ThemNV extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private ITinhToan utilTt;
	private RoundJTextField txtMaNV, txtTenNV, txtSoID, txtSDT, txtLuong;
	private Button btnLuu, btnHuy, btnThoat;
	private JComboBox cbbCa, cbbLoaiNV, cbbGioiTinh;
	private INhanVienService utilNv = new NhanVienImpl();
	private JTextField txtNgaySinh, txtThangSinh, txtNamSinh, txtNgayVL, txtThangVL, txtNamVaoLam;
	private ButtonGroup bgGioiTinh;
	private RoundJTextField txtThongBao;
	private JLabel lblLng;
	private JLabel lblSId;
	private JLabel lblSt;
	private JLabel lblTn;
	private JLabel lblMNv;
	private JLabel lblDd;
	private JLabel lblThng;
	private JLabel lblNm;
	private JLabel lblDd_1;
	private JLabel lblThng_1;
	private JLabel lblNm_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemNV frame = new ThemNV();
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
	public ThemNV() throws Exception {
		setForeground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 559, 545);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 80, 75));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblThem = new JLabel("Thêm nhân viên");
		lblThem.setForeground(new Color(230, 230, 230));
		lblThem.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblThem.setBounds(44, 0, 300, 70);
		contentPane.add(lblThem);
		
		utilTt = new TinhToanImpl(); // muốn gọi hàm bên interface thì gọi kiểu này
		// nên để nó ở local cho đỡ phải gọi lại
		txtMaNV = new RoundJTextField(30);
		txtMaNV.setForeground(new Color(84, 80, 75));
		txtMaNV.setBackground(new Color(243, 238, 231));
		txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNV.setFont(new Font("000 Chinacat [TeddyBear]", txtMaNV.getFont().getStyle(), txtMaNV.getFont().getSize() + 5));
		txtMaNV.setToolTipText("Nhập mã nhân viên");
		txtMaNV.setText(utilTt.maNhanVienMoi());
		txtMaNV.setBounds(161, 84, 352, 27);
		contentPane.add(txtMaNV);
		txtMaNV.setColumns(10);
		txtMaNV.setEditable(false); 
		
		txtTenNV = new RoundJTextField(30);
		txtTenNV.setFont(new Font("000 Chinacat [TeddyBear]", txtTenNV.getFont().getStyle(), txtTenNV.getFont().getSize() + 5));
		txtTenNV.setToolTipText("Nhập tên nhân viên");
		txtTenNV.setText("Lê Thu Phương");
		txtTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNV.setForeground(new Color(84, 80, 75));
		txtTenNV.setColumns(10);
		txtTenNV.setBackground(new Color(243, 238, 231));
		txtTenNV.setBounds(161, 121, 352, 27);
		contentPane.add(txtTenNV);
		
		txtSoID = new RoundJTextField(30);
		txtSoID.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoID.setToolTipText("Nhập số ID");
		txtSoID.setText("8888343232");
		txtSoID.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoID.setForeground(new Color(84, 80, 75));
		txtSoID.setColumns(10);
		txtSoID.setBackground(new Color(243, 238, 231));
		txtSoID.setBounds(161, 195, 352, 27);
		contentPane.add(txtSoID);
		
		txtSDT = new RoundJTextField(30);
		txtSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSDT.setToolTipText("Nhập số điện thoại");
		txtSDT.setText("0961888834");
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDT.setForeground(new Color(84, 80, 75));
		txtSDT.setColumns(10);
		txtSDT.setBackground(new Color(243, 238, 231));
		txtSDT.setBounds(161, 158, 352, 27);
		contentPane.add(txtSDT);
		
		JLabel lblGioiTinh = new JLabel("Giới Tính:");
		lblGioiTinh.setForeground(new Color(255, 255, 255));
		lblGioiTinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setBounds(44, 341, 107, 27);
		contentPane.add(lblGioiTinh);
		
		cbbGioiTinh = new JComboBox();
		cbbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbbGioiTinh.setBackground(new Color(243, 238, 231));
		cbbGioiTinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbGioiTinh.setBounds(161, 335, 352, 28);;
		contentPane.add(cbbGioiTinh);
		
		txtLuong = new RoundJTextField(30);
		txtLuong.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtLuong.setToolTipText("Nhập lương");
		txtLuong.setText("2000000");
		txtLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtLuong.setColumns(10);
		txtLuong.setForeground(new Color(84, 80, 75));
		txtLuong.setBackground(new Color(243, 238, 231));
		txtLuong.setBounds(161, 232, 352, 27);
		contentPane.add(txtLuong);
		
		JLabel lblCa= new JLabel("Ca làm việc:");
		lblCa.setForeground(new Color(255, 255, 255));
		lblCa.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblCa.setHorizontalAlignment(SwingConstants.LEFT);
		lblCa.setBounds(44, 415, 107, 27);
		contentPane.add(lblCa);
		
		cbbCa = new JComboBox();
		cbbCa.setModel(new DefaultComboBoxModel(new String[] {"Ca sáng", "Ca tối"}));
		cbbCa.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbCa.setBackground(new Color(243, 238, 231));
		cbbCa.setBounds(161, 413, 352, 30);
		contentPane.add(cbbCa);
		
		JLabel lblLoaiNV= new JLabel("Loại nhân viên:");
		lblLoaiNV.setForeground(new Color(255, 255, 255));
		lblLoaiNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblLoaiNV.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoaiNV.setBounds(44, 378, 107, 27);
		contentPane.add(lblLoaiNV);
		
		cbbLoaiNV = new JComboBox();
		cbbLoaiNV.setModel(new DefaultComboBoxModel(new String[] {"Nhân viên bán hàng", "Quản lý"}));
		cbbLoaiNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbLoaiNV.setBackground(new Color(243, 238, 231));
		cbbLoaiNV.setBounds(161, 373, 352, 30);
		contentPane.add(cbbLoaiNV);

		txtThongBao = new RoundJTextField(30);
		txtThongBao.setToolTipText("Thông báo");
		txtThongBao.setText("Thông báo");
		
		btnLuu = new Button("");
		btnLuu.setRadius(39);
		btnLuu.setColorClick(new Color(84, 80, 75));
		btnLuu.setToolTipText("Lưu nhân viên");
		btnLuu.setIcon(new ImageIcon(ThemNV.class.getResource("/Add.png")));
		btnLuu.setBounds(179, 458, 45, 40);
		contentPane.add(btnLuu);
		
		btnHuy = new Button("");
		btnHuy.setRadius(39);
		btnHuy.setColorClick(new Color(84, 80, 75));
		btnHuy.setToolTipText("Huỷ thao tác");
		btnHuy.setIcon(new ImageIcon(ThemNV.class.getResource("/Delete.png")));
		btnHuy.setBounds(315, 458, 45, 40);
		contentPane.add(btnHuy);
		
		btnThoat = new Button("");
		btnThoat.setIcon(new ImageIcon(ThemNV.class.getResource("/Exit.png")));
		btnThoat.setToolTipText("Quay lại");
		btnThoat.setRadius(39);
		btnThoat.setColorClick(new Color(84, 80, 75));
		btnThoat.setBounds(456, 21, 57, 49);
		contentPane.add(btnThoat);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setForeground(new Color(255, 255, 255));
		lblNgaySinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNgaySinh.setBounds(44, 301, 87, 30);
		contentPane.add(lblNgaySinh);
		
		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm:");
		lblNgayVaoLam.setForeground(new Color(255, 255, 255));
		lblNgayVaoLam.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNgayVaoLam.setBounds(44, 269, 131, 22);
		contentPane.add(lblNgayVaoLam);
		
		txtNgaySinh = new JTextField("29");
		txtNgaySinh.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgaySinh.setBounds(232, 303, 45, 22);
		txtNgaySinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNgaySinh.setBackground(new Color(243, 238, 231));
		contentPane.add(txtNgaySinh);
		txtNgaySinh.setColumns(10);
		
		txtThangSinh = new JTextField("09");
		txtThangSinh.setHorizontalAlignment(SwingConstants.CENTER);
		txtThangSinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtThangSinh.setBounds(341, 302, 57, 23);
		txtThangSinh.setBackground(new Color(243, 238, 231));
		contentPane.add(txtThangSinh);
		txtThangSinh.setColumns(10);

		txtNamSinh = new JTextField("2001");
		txtNamSinh.setHorizontalAlignment(SwingConstants.CENTER);
		txtNamSinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNamSinh.setBounds(450, 303, 63, 22);
		txtNamSinh.setBackground(new Color(243, 238, 231));
		contentPane.add(txtNamSinh);
		txtNamSinh.setColumns(10);
		
		txtNgayVL = new JTextField("29");
		txtNgayVL.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgayVL.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNgayVL.setColumns(10);
		txtNgayVL.setBackground(new Color(243, 238, 231));
		txtNgayVL.setBounds(232, 269, 45, 22);
		contentPane.add(txtNgayVL);
		
		txtThangVL = new JTextField("11");
		txtThangVL.setHorizontalAlignment(SwingConstants.CENTER);
		txtThangVL.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtThangVL.setColumns(10);
		txtThangVL.setBackground(new Color(243, 238, 231));
		txtThangVL.setBounds(341, 269, 57, 22);
		contentPane.add(txtThangVL);
		
		txtNamVaoLam = new JTextField("2022");
		txtNamVaoLam.setHorizontalAlignment(SwingConstants.CENTER);
		txtNamVaoLam.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNamVaoLam.setColumns(10);
		txtNamVaoLam.setBackground(new Color(243, 238, 231));
		txtNamVaoLam.setBounds(450, 267, 63, 25);
		contentPane.add(txtNamVaoLam);
		
		lblLng = new JLabel("Lương:");
		lblLng.setForeground(new Color(255, 255, 255));
		lblLng.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblLng.setBounds(44, 237, 131, 22);
		contentPane.add(lblLng);
		
		lblSId = new JLabel("Số ID:");
		lblSId.setForeground(new Color(255, 255, 255));
		lblSId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSId.setBounds(44, 197, 131, 22);
		contentPane.add(lblSId);
		
		lblSt = new JLabel("SĐT:");
		lblSt.setForeground(new Color(255, 255, 255));
		lblSt.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblSt.setBounds(44, 163, 131, 22);
		contentPane.add(lblSt);
		
		lblTn = new JLabel("Tên:");
		lblTn.setForeground(new Color(255, 255, 255));
		lblTn.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblTn.setBounds(44, 126, 131, 22);
		contentPane.add(lblTn);
		
		lblMNv = new JLabel("Mã NV:");
		lblMNv.setForeground(new Color(255, 255, 255));
		lblMNv.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblMNv.setBounds(44, 89, 131, 22);
		contentPane.add(lblMNv);
		
		lblDd = new JLabel("ngày:");
		lblDd.setForeground(Color.WHITE);
		lblDd.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDd.setBounds(196, 269, 53, 22);
		contentPane.add(lblDd);
		
		lblThng = new JLabel("tháng:");
		lblThng.setForeground(Color.WHITE);
		lblThng.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblThng.setBounds(299, 269, 51, 22);
		contentPane.add(lblThng);
		
		lblNm = new JLabel("năm:");
		lblNm.setForeground(Color.WHITE);
		lblNm.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNm.setBounds(409, 269, 51, 22);
		contentPane.add(lblNm);
		
		lblDd_1 = new JLabel("ngày:");
		lblDd_1.setForeground(Color.WHITE);
		lblDd_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblDd_1.setBounds(193, 303, 45, 22);
		contentPane.add(lblDd_1);
		
		lblThng_1 = new JLabel("tháng:");
		lblThng_1.setForeground(Color.WHITE);
		lblThng_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblThng_1.setBounds(299, 303, 51, 22);
		contentPane.add(lblThng_1);
		
		lblNm_1 = new JLabel("năm:");
		lblNm_1.setForeground(Color.WHITE);
		lblNm_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNm_1.setBounds(408, 303, 51, 22);
		contentPane.add(lblNm_1);

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
		//bắt sự kiện ở đây
		if(o.equals(btnLuu)) {
			if(validData()) {
				//đầu tiên get các giá trị của file trước
				String maNV = txtMaNV.getText().trim();
				String tenNV = txtTenNV.getText().trim();
				String sDT = txtSDT.getText().trim();
				String soID = txtSoID.getText().trim();
				int  Luong = 0;
				String sNgaySinh = txtNamSinh.getText().trim() + "-" + txtThangSinh.getText().trim() + "-" + txtNgaySinh.getText().trim();
				LocalDate ngaySinh = LocalDate.parse(sNgaySinh);;
				String sNgayVL = txtNamVaoLam.getText().trim() + "-" + txtThangVL.getText().trim() + "-" + txtNgayVL.getText().trim();
				LocalDate ngayVL = LocalDate.parse(sNgaySinh);;
				String gioiTinh = cbbGioiTinh.getSelectedItem().toString();
				NhanVien nv = new NhanVien(maNV, tenNV, soID, sDT, gioiTinh, ngaySinh, Luong, ngayVL);
				
				String caLam = cbbCa.getSelectedItem().toString();
				String loai = cbbLoaiNV.getSelectedItem().toString();

				ICaLamViecService utilCa = new CaLamViecImpl();
				ILoaiNVService utilLoai = new LoaiNVImpl();
				String maCa = null;
				String maLoai = null;
				try {
					for(CaLamViec ca: utilCa.getDsCaLamViec()) {
						if(ca.getLoaiCa().compareToIgnoreCase(caLam)==0) {
							maCa = ca.getMaCa();
						}
					}
					for(LoaiNV l: utilLoai.getDsLoaiNV()) {
						if(l.getTenLoai().compareToIgnoreCase(loai)==0) {
							maLoai = l.getMaLoai();
						}
					}
					utilNv.themNhanVien(nv, maLoai, maCa);
					showMessage("Thêm thành công", txtThongBao);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	private boolean validData() {
		String maNV = txtMaNV.getText().trim();
		String tenNV = txtTenNV.getText().trim();
		String soID = txtSoID.getText().trim();
		String sDT = txtSDT.getText().trim();
		int  Luong = 0;
		String sNgaySinh = txtNamSinh.getText().trim() + "-" + txtThangSinh.getText().trim() + "-" + txtNgaySinh.getText().trim();
		LocalDate ngaySinh;
		String sNgayVaoLam = txtNamVaoLam.getText().trim() + "-" + txtThangVL.getText().trim() + "-" + txtNgayVL.getText().trim();
		LocalDate ngayVaoLam;
		INhanVienService utilKT = new NhanVienImpl();
		try {
			ngaySinh = LocalDate.parse(sNgaySinh);
		} catch (DateTimeException e) {
			txtNgaySinh.setText("ngày");
			txtThangSinh.setText("tháng");
			txtNamSinh.setText("năm");
			showMessage("Phải nhập đúng định dạng dd-MM-yyyy", txtNgaySinh);
			return false;
		}
		try {
			ngayVaoLam = LocalDate.parse(sNgayVaoLam);
		} catch (DateTimeException e) {
			txtNgayVL.setText("ngày");
			txtThangVL.setText("tháng");
			txtNamVaoLam.setText("năm");
			showMessage("Phải nhập đúng định dạng dd-MM-yyyy", txtNgayVL);
			return false;
		}
		try {
			Luong = Integer.parseInt(txtLuong.getText());
		} catch (Exception e2) {
			showMessage("Số lương phải là số", txtLuong);
			return false;
		}
		if (maNV.length() == 0) {
			showMessage("Nhập mã nhân viên", txtMaNV);
			return false;
		}
		if (!maNV.matches("^(NV\\d{3})$")) {
			showMessage(
					"Mã nhân viên phải bắt đầu bằng NV, theo sau là 3 số bất kỳ",
					txtMaNV);
			return false;
		}
		if (tenNV.equals("Tên nhân viên")) {
			showMessage("Nhập tên nhân viên", txtTenNV);
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
			for(NhanVien l2: utilKT.getDsNhanVien()) {
				if(l2.getSoID().equals(soID)) {
					showMessage("Đã tồn tại số ID này", txtSoID);
					return false;
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		if (sDT.length() == 0) {
			showMessage("Nhập số điện thoại", txtSDT);
			return false;
		}
		try {
			Integer.parseInt(sDT);
		} catch (Exception e) {
			showMessage("Số điện thoại phải là số", txtSDT);
			return false;
		}
		try {
			for(NhanVien l2: utilKT.getDsNhanVien()) {
				if(l2.getSdt().equals(sDT)) {
					showMessage("Đã tồn tại số điện thoại này", txtSDT);
					return false;
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
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
			txtMaNV.setText(utilTt.maNhanVienMoi());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTenNV.setText("Tên nhân viên");
		txtSoID.setText("Số ID");
		txtLuong.setText("Lương");	
		txtSDT.setText("Số điện thoại");
		txtNgaySinh.setText("ngày");
		txtThangSinh.setText("tháng");
		txtNamSinh.setText("năm");
		txtNgayVL.setText("ngày");
		txtThangVL.setText("tháng");
		txtNamVaoLam.setText("năm");
		cbbCa.setSelectedIndex(0);
		cbbLoaiNV.setSelectedIndex(0);

	}
}
