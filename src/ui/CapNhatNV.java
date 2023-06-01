package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.roundJTextField.RoundJTextField;
import entity.CaLamViec;
import entity.LoaiKH;
import entity.LoaiNV;
import entity.NhanVien;
import service.ICaLamViecService;
import service.ILoaiNVService;
import service.INhanVienService;
import service.ITaiKhoanService;
import service.impl.CaLamViecImpl;
import service.impl.LoaiNVImpl;
import service.impl.NhanVienImpl;
import service.impl.TaiKhoanImpl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CapNhatNV extends JFrame implements ActionListener {

	private JPanel contentPane;
	private RoundJTextField txtMaNV;
	private RoundJTextField txtTenNV;
	private RoundJTextField txtSDT;
	private RoundJTextField txtNgaySinh;
	private RoundJTextField txtLuong;
	private Button btnLuu;
	private Button btnHuy;
	private Button btnThoat;
	private RoundJTextField txtSoID;
	private RoundJTextField txtTaiKhoan;
	private RoundJTextField txtNgayLam;
	private JComboBox cbbLoaiNV;
	private JComboBox cbbCaLam;
	private JComboBox cbbGioiTinh;
	private NhanVien nv;
	private INhanVienService nv_dao = new NhanVienImpl();
	private ILoaiNVService loaiNV_dao = new LoaiNVImpl();
	private ICaLamViecService ca_dao = new CaLamViecImpl();
	private ITaiKhoanService tk_dao = new TaiKhoanImpl();
	private JLabel lblNewLabel;
	private JLabel lblTnNhnVin;
	private JLabel lblSinThoi;
	private JLabel lblLng;
	private JLabel lblSId;
	private JLabel lblNgySinh;
	private JLabel lblTiKhaonr;
	private JLabel lblNgyVoLm;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public CapNhatNV(String maNV) throws Exception {
		nv = nv_dao.timMa(maNV);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("VIR - Urbanus et elegans");
		setResizable(false);
		setBounds(295, 170, 1195, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 80, 75));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCapNhat = new JLabel("Thông tin nhân viên");
		lblCapNhat.setForeground(new Color(230, 230, 230));
		lblCapNhat.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblCapNhat.setBounds(50, 50, 400, 70);
		contentPane.add(lblCapNhat);

		txtMaNV = new RoundJTextField(30);
		txtMaNV.setEnabled(false);
		txtMaNV.setEditable(false);
		txtMaNV.setForeground(new Color(84, 80, 75));
		txtMaNV.setBackground(new Color(243, 238, 231));
		txtMaNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaNV.setFont(
				new Font("000 Chinacat [TeddyBear]", txtMaNV.getFont().getStyle(), txtMaNV.getFont().getSize() + 5));
		txtMaNV.setToolTipText("Nhập mã nhân viên");
		txtMaNV.setText(nv.getMaNV());
		txtMaNV.setBounds(200, 170, 200, 40);
		contentPane.add(txtMaNV);
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
		txtTenNV.setText(nv.getTenNV());
		txtTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenNV.setForeground(new Color(84, 80, 75));
		txtTenNV.setColumns(10);
		txtTenNV.setBackground(new Color(243, 238, 231));
		txtTenNV.setBounds(450, 170, 450, 40);
		contentPane.add(txtTenNV);

		txtSDT = new RoundJTextField(30);
		txtSDT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSDT.setText("");
			}
		});
		txtSDT.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSDT.setToolTipText("Nhập số điện thoại");
		txtSDT.setText(nv.getSdt());
		txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
		txtSDT.setForeground(new Color(84, 80, 75));
		txtSDT.setColumns(10);
		txtSDT.setBackground(new Color(243, 238, 231));
		txtSDT.setBounds(200, 250, 200, 40);
		contentPane.add(txtSDT);

		LocalDate localDate = nv.getNgaySinh();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		String ngay = localDate.format(dateFormatter);
		txtNgaySinh = new RoundJTextField(30);
		txtNgaySinh.setEnabled(false);
		txtNgaySinh.setEditable(false);
		txtNgaySinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNgaySinh.setToolTipText("Nhập ngày sinh");
		txtNgaySinh.setText(ngay);
		txtNgaySinh.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgaySinh.setForeground(new Color(84, 80, 75));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBackground(new Color(243, 238, 231));
		txtNgaySinh.setBounds(200, 328, 200, 40);
		contentPane.add(txtNgaySinh);

		txtLuong = new RoundJTextField(30);
		txtLuong.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtLuong.setToolTipText("lương");
		txtLuong.setText(String.valueOf(String.format("%.2f", nv.getLuong())));
		txtLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtLuong.setForeground(new Color(84, 80, 75));
		txtLuong.setColumns(10);
		txtLuong.setBackground(new Color(243, 238, 231));
		txtLuong.setBounds(450, 250, 200, 40);
		contentPane.add(txtLuong);

		cbbLoaiNV = new JComboBox();
		cbbLoaiNV.setModel(new DefaultComboBoxModel(new String[] { "Nhân viên", "Quản lý" }));
		cbbLoaiNV.setSelectedItem(loaiNV_dao.timMa(nv.getLoai().getMaLoai()).getTenLoai());
		cbbLoaiNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbLoaiNV.setBackground(new Color(243, 238, 231));
		cbbLoaiNV.setBounds(300, 401, 100, 30);
		contentPane.add(cbbLoaiNV);

		JLabel lblLoaiNV = new JLabel("Loại nhân viên:");
		lblLoaiNV.setForeground(new Color(230, 230, 230));
		lblLoaiNV.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblLoaiNV.setBounds(200, 401, 100, 30);
		contentPane.add(lblLoaiNV);

		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setForeground(new Color(230, 230, 230));
		lblCaLam.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblCaLam.setBounds(709, 401, 80, 30);
		contentPane.add(lblCaLam);

		cbbCaLam = new JComboBox();
		cbbCaLam.setModel(new DefaultComboBoxModel(new String[] { "Sáng", "Chiều", "Tối" }));
		cbbCaLam.setSelectedItem(ca_dao.timMa(nv.getCa().getMaCa()).getLoaiCa());
		cbbCaLam.setBackground(new Color(243, 238, 231));
		cbbCaLam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbCaLam.setBounds(789, 401, 100, 30);
		contentPane.add(cbbCaLam);

		btnLuu = new Button("");
		btnLuu.setRadius(39);
		btnLuu.setColorClick(new Color(84, 80, 75));
		btnLuu.setToolTipText("Cập nhật sản phẩm");
		btnLuu.setIcon(new ImageIcon(CapNhatNV.class.getResource("/update.png")));
		btnLuu.setBounds(410, 512, 39, 39);
		contentPane.add(btnLuu);

		btnHuy = new Button("");
		btnHuy.setRadius(39);
		btnHuy.setColorClick(new Color(84, 80, 75));
		btnHuy.setToolTipText("Huỷ thao tác");
		btnHuy.setIcon(new ImageIcon(CapNhatNV.class.getResource("/Delete.png")));
		btnHuy.setBounds(734, 512, 39, 39);
		contentPane.add(btnHuy);

		btnThoat = new Button("");
		btnThoat.setIcon(new ImageIcon(CapNhatNV.class.getResource("/Exit.png")));
		btnThoat.setToolTipText("Quay lại");
		btnThoat.setRadius(39);
		btnThoat.setColorClick(new Color(84, 80, 75));
		btnThoat.setBounds(1132, 10, 39, 39);
		contentPane.add(btnThoat);

		txtSoID = new RoundJTextField(30);
		txtSoID.setEnabled(false);
		txtSoID.setEditable(false);
		txtSoID.setToolTipText("Nhập số ID");
		txtSoID.setText(nv.getSoID());
		txtSoID.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoID.setForeground(new Color(84, 80, 75));
		txtSoID.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoID.setColumns(10);
		txtSoID.setBackground(new Color(243, 238, 231));
		txtSoID.setBounds(700, 250, 200, 40);
		contentPane.add(txtSoID);

		txtTaiKhoan = new RoundJTextField(30);
		txtTaiKhoan.setEnabled(false);
		txtTaiKhoan.setEditable(false);
		txtTaiKhoan.setToolTipText("Nhập tài khoản");
		txtTaiKhoan.setText(tk_dao.timMa(nv.getTaiKhoan().getUserName()).getUserName());
		txtTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		txtTaiKhoan.setForeground(new Color(84, 80, 75));
		txtTaiKhoan.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTaiKhoan.setColumns(10);
		txtTaiKhoan.setBackground(new Color(243, 238, 231));
		txtTaiKhoan.setBounds(450, 328, 200, 40);
		contentPane.add(txtTaiKhoan);

		LocalDate localDate2 = nv.getNgayVaoLam();
		String ngayLam = localDate2.format(dateFormatter);
		txtNgayLam = new RoundJTextField(30);
		txtNgayLam.setEnabled(false);
		txtNgayLam.setEditable(false);
		txtNgayLam.setToolTipText("Nhập ngày vào làm");
		txtNgayLam.setText(ngayLam);
		txtNgayLam.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgayLam.setForeground(new Color(84, 80, 75));
		txtNgayLam.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNgayLam.setColumns(10);
		txtNgayLam.setBackground(new Color(243, 238, 231));
		txtNgayLam.setBounds(700, 328, 200, 40);
		contentPane.add(txtNgayLam);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setForeground(new Color(230, 230, 230));
		lblGioiTinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblGioiTinh.setBounds(460, 401, 80, 30);
		contentPane.add(lblGioiTinh);

		cbbGioiTinh = new JComboBox();
		cbbGioiTinh.setEnabled(false);
		cbbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cbbGioiTinh.setSelectedItem(nv.getGioiTinh());
		cbbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbGioiTinh.setBackground(new Color(243, 238, 231));
		cbbGioiTinh.setBounds(540, 401, 100, 30);
		contentPane.add(cbbGioiTinh);
		
		lblNewLabel = new JLabel("Mã nhân viên");
		lblNewLabel.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(243, 238, 231));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(240, 150, 130, 20);
		contentPane.add(lblNewLabel);
		
		lblTnNhnVin = new JLabel("Tên nhân viên");
		lblTnNhnVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblTnNhnVin.setForeground(new Color(243, 238, 231));
		lblTnNhnVin.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblTnNhnVin.setBounds(612, 154, 130, 20);
		contentPane.add(lblTnNhnVin);
		
		lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSinThoi.setForeground(new Color(243, 238, 231));
		lblSinThoi.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSinThoi.setBounds(240, 230, 130, 20);
		contentPane.add(lblSinThoi);
		
		lblLng = new JLabel("Lương");
		lblLng.setHorizontalAlignment(SwingConstants.CENTER);
		lblLng.setForeground(new Color(243, 238, 231));
		lblLng.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblLng.setBounds(480, 234, 130, 20);
		contentPane.add(lblLng);
		
		lblSId = new JLabel("Số ID");
		lblSId.setHorizontalAlignment(SwingConstants.CENTER);
		lblSId.setForeground(new Color(243, 238, 231));
		lblSId.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSId.setBounds(733, 234, 130, 20);
		contentPane.add(lblSId);
		
		lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgySinh.setForeground(new Color(243, 238, 231));
		lblNgySinh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNgySinh.setBounds(240, 310, 130, 20);
		contentPane.add(lblNgySinh);
		
		lblTiKhaonr = new JLabel("Tài khoản");
		lblTiKhaonr.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiKhaonr.setForeground(new Color(243, 238, 231));
		lblTiKhaonr.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblTiKhaonr.setBounds(480, 314, 130, 20);
		contentPane.add(lblTiKhaonr);
		
		lblNgyVoLm = new JLabel("Ngày vào làm");
		lblNgyVoLm.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgyVoLm.setForeground(new Color(243, 238, 231));
		lblNgyVoLm.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNgyVoLm.setBounds(734, 310, 130, 20);
		contentPane.add(lblNgyVoLm);

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
			if (validData()) {
				String maNV = txtMaNV.getText();
				String tenNV = txtTenNV.getText();
				Double luong = Double.parseDouble(txtLuong.getText());
				String sdt = txtSDT.getText();
				String ca = (String) cbbCaLam.getSelectedItem();
				String loai = (String) cbbLoaiNV.getSelectedItem();
				String maLoai = "";
				String maCa = "";
				try {
					if (!loai.equals("")) {

						for (LoaiNV x : loaiNV_dao.getDsLoaiNV()) {
							if (x.getTenLoai().compareToIgnoreCase(loai) == 0)
								maLoai = x.getMaLoai();
						}
					}
					if (!ca.equals("")) {
						for (CaLamViec y : ca_dao.getDsCaLamViec()) {
							if (y.getLoaiCa().compareToIgnoreCase(ca) == 0)
								maCa = y.getMaCa();
						}
					}
					nv_dao.suaNhanVien(maNV, tenNV, sdt, luong, maCa, maLoai);
					JOptionPane.showMessageDialog(contentPane, "Cập nhật thông tin nhân viên thành công");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private boolean validData() {
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		String luong = txtLuong.getText();
		String sdt = txtSDT.getText();
		String ca = (String) cbbCaLam.getSelectedItem();
		String loai = (String) cbbLoaiNV.getSelectedItem();

		if (tenNV.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập tên nhân viên!");
			return false;
		}
		if (sdt.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập số điện thoại của nhân viên!");
			return false;
		}
		if (luong.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập lương của nhân viên!");
			return false;
		}
		return true;
	}

	private void clearTextfield() {
		dispose();
	}
}
