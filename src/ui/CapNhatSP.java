package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import components.button.Button;
import components.roundJTextField.RoundJTextField;
import entity.LoaiSP;
import entity.NhaCungCap;
import entity.SanPham;
import service.ILoaiSPService;
import service.INhaCungCapService;
import service.ISanPhamService;
import service.impl.LoaiSPServiceImpl;
import service.impl.NhaCungCapImpl;
import service.impl.SanPhamImpl;

import javax.swing.JTextField;

public class CapNhatSP extends JFrame implements ActionListener {

	private JPanel contentPane, pnlAnh;
	private Button btnLuu, btnHuy, btnNhapAnh, btnThoat;
	private RoundJTextField txtMaSP, txtTenSP, txtSoLuong, txtNgaySanXuat, txtDonGia, txtGiaNhapKho, txtMau;
	private ISanPhamService sp_dao = new SanPhamImpl();
	private ILoaiSPService loaiSP_dao = new LoaiSPServiceImpl();
	private INhaCungCapService ncc_dao = new NhaCungCapImpl();
	private JComboBox cbbDanhMuc;
	private JComboBox cbbNCC;
	private JComboBox cbbSize;
	private JComboBox cbbChatLieu;
	private SanPham sanPham;
	private JLabel lblAnh;
	private JLabel lblNewLabel;
	private JLabel lblTnSnPhm;
	private JLabel lblSLng;
	private JLabel lblGiNhpKho;
	private JLabel lblnGi;
	private JLabel lblNgySnXut;
	private JLabel lblMu;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public CapNhatSP(String maSanPham) throws Exception {
		sanPham = sp_dao.timMa(maSanPham);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("VIR - Urbanus et elegans");
		setResizable(false);
		setBounds(295, 170, 1195, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(84, 80, 75));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCapNhat = new JLabel("Thông tin sản phẩm");
		lblCapNhat.setForeground(new Color(230, 230, 230));
		lblCapNhat.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblCapNhat.setBounds(50, 50, 400, 70);
		contentPane.add(lblCapNhat);

		txtMaSP = new RoundJTextField(30);
		txtMaSP.setEnabled(false);
		txtMaSP.setEditable(false);
		txtMaSP.setForeground(new Color(84, 80, 75));
		txtMaSP.setBackground(new Color(243, 238, 231));
		txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaSP.setFont(
				new Font("000 Chinacat [TeddyBear]", txtMaSP.getFont().getStyle(), txtMaSP.getFont().getSize() + 5));
		txtMaSP.setToolTipText("Nhập mã sản phẩm");
		txtMaSP.setText(sanPham.getMaSP());
		txtMaSP.setBounds(50, 170, 200, 40);
		contentPane.add(txtMaSP);
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
		txtTenSP.setText(sanPham.getTenSP());
		txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenSP.setForeground(new Color(84, 80, 75));
		txtTenSP.setColumns(10);
		txtTenSP.setBackground(new Color(243, 238, 231));
		txtTenSP.setBounds(300, 170, 450, 40);
		contentPane.add(txtTenSP);

		txtSoLuong = new RoundJTextField(30);
		txtSoLuong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSoLuong.setText("");
			}
		});
		txtSoLuong.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoLuong.setToolTipText("Nhập số lượng");
		txtSoLuong.setText(String.valueOf(sanPham.getSoLuong()));
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setForeground(new Color(84, 80, 75));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBackground(new Color(243, 238, 231));
		txtSoLuong.setBounds(50, 240, 200, 40);
		contentPane.add(txtSoLuong);

		txtGiaNhapKho = new RoundJTextField(30);
		txtGiaNhapKho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtGiaNhapKho.setText("");
			}
		});
		txtGiaNhapKho.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtGiaNhapKho.setToolTipText("Nhập giá nhập kho");
		double giaNhap = 0;
		giaNhap = sanPham.getGiaNhapKho();
		String textGiaNhap = String.format("%.2f", giaNhap);
		txtGiaNhapKho.setText(textGiaNhap);
		txtGiaNhapKho.setHorizontalAlignment(SwingConstants.CENTER);
		txtGiaNhapKho.setForeground(new Color(84, 80, 75));
		txtGiaNhapKho.setColumns(10);
		txtGiaNhapKho.setBackground(new Color(243, 238, 231));
		txtGiaNhapKho.setBounds(300, 240, 200, 40);
		contentPane.add(txtGiaNhapKho);

		LocalDate localDate = sanPham.getNgaySanXuat();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		String ngay = localDate.format(dateFormatter);
		txtNgaySanXuat = new RoundJTextField(30);
		txtNgaySanXuat.setEnabled(false);
		txtNgaySanXuat.setEditable(false);
		txtNgaySanXuat.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNgaySanXuat.setToolTipText("Nhập ngày sản xuất");
		txtNgaySanXuat.setText(ngay);
		txtNgaySanXuat.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgaySanXuat.setForeground(new Color(84, 80, 75));
		txtNgaySanXuat.setColumns(10);
		txtNgaySanXuat.setBackground(new Color(243, 238, 231));
		txtNgaySanXuat.setBounds(50, 310, 200, 40);
		contentPane.add(txtNgaySanXuat);

		txtDonGia = new RoundJTextField(30);
		txtDonGia.setEnabled(false);
		txtDonGia.setEditable(false);
		txtDonGia.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtDonGia.setToolTipText("");
		double donGia = 0;
		donGia = sanPham.getDonGia();
		String textDonGia = String.format("%.2f", donGia);
		txtDonGia.setText(textDonGia);
		txtDonGia.setHorizontalAlignment(SwingConstants.CENTER);
		txtDonGia.setForeground(new Color(84, 80, 75));
		txtDonGia.setColumns(10);
		txtDonGia.setBackground(new Color(243, 238, 231));
		txtDonGia.setBounds(550, 240, 200, 40);
		contentPane.add(txtDonGia);

		cbbDanhMuc = new JComboBox();
		cbbDanhMuc.setEnabled(false);
		cbbDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbDanhMuc.setModel(new DefaultComboBoxModel(new String[] {"Quần", "Áo", "Giày", "phụ kiện", "Khác" }));
		cbbDanhMuc.setSelectedItem(loaiSP_dao.timMa(sanPham.getLoai().getMaLoai()).getTenLoai());
		cbbDanhMuc.setBackground(new Color(243, 238, 231));
		cbbDanhMuc.setBounds(140, 380, 110, 30);
		contentPane.add(cbbDanhMuc);

		JLabel lblDanhMuc = new JLabel("Danh mục:");
		lblDanhMuc.setForeground(new Color(230, 230, 230));
		lblDanhMuc.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblDanhMuc.setBounds(50, 380, 80, 30);
		contentPane.add(lblDanhMuc);

		cbbNCC = new JComboBox();
		cbbNCC.setEditable(true);
		cbbNCC.setModel(new DefaultComboBoxModel(
				new String[] {"new brands", "second brands", "third brands", "the best", "bad guy" }));
		cbbNCC.setSelectedItem(ncc_dao.timMa(sanPham.getNcc().getMaNCC()).getTenNCC());
		cbbNCC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbNCC.setBackground(new Color(243, 238, 231));
		cbbNCC.setBounds(361, 380, 139, 30);
		contentPane.add(cbbNCC);

		JLabel lblNCC = new JLabel("NCC:");
		lblNCC.setForeground(new Color(230, 230, 230));
		lblNCC.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNCC.setBounds(300, 380, 51, 30);
		contentPane.add(lblNCC);

		cbbSize = new JComboBox();
		cbbSize.setEditable(true);
		cbbSize.setModel(new DefaultComboBoxModel(new String[] {"S", "M", "L", "X", "XL", "XXL" }));
		cbbSize.setSelectedItem(sanPham.getSize());
		cbbSize.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbSize.setBackground(new Color(243, 238, 231));
		cbbSize.setBounds(630, 320, 120, 30);
		contentPane.add(cbbSize);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setForeground(new Color(230, 230, 230));
		lblSize.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSize.setBounds(550, 320, 40, 30);
		contentPane.add(lblSize);

		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setForeground(new Color(230, 230, 230));
		lblChatLieu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblChatLieu.setBounds(550, 380, 80, 30);
		contentPane.add(lblChatLieu);

		cbbChatLieu = new JComboBox();
		cbbChatLieu.setEditable(true);
		cbbChatLieu.setModel(new DefaultComboBoxModel(new String[] {"len", "kaki", "vải kaki", "da", "vải âu",
				"vải thun", "vải cotton", "vải", "kim cương", "bạc" }));
		cbbChatLieu.setSelectedItem(sanPham.getChatLieu());
		cbbChatLieu.setBackground(new Color(243, 238, 231));
		cbbChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbbChatLieu.setBounds(630, 380, 120, 30);
		contentPane.add(cbbChatLieu);

		btnLuu = new Button("");
		btnLuu.setRadius(39);
		btnLuu.setColorClick(new Color(84, 80, 75));
		btnLuu.setToolTipText("Cập nhật sản phẩm");
		btnLuu.setIcon(new ImageIcon(CapNhatSP.class.getResource("/update.png")));
		btnLuu.setBounds(350, 470, 39, 39);
		contentPane.add(btnLuu);

		btnHuy = new Button("");
		btnHuy.setRadius(39);
		btnHuy.setColorClick(new Color(84, 80, 75));
		btnHuy.setToolTipText("Huỷ thao tác");
		btnHuy.setIcon(new ImageIcon(CapNhatSP.class.getResource("/Delete.png")));
		btnHuy.setBounds(674, 470, 39, 39);
		contentPane.add(btnHuy);

		pnlAnh = new JPanel();
		pnlAnh.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlAnh.setBounds(860, 170, 180, 240);
		contentPane.add(pnlAnh);
		
		lblAnh = new JLabel("");
		lblAnh.setBounds(6, 16, 190, 240);
		pnlAnh.add(lblAnh);
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);

		btnNhapAnh = new Button("Nhập ảnh");
		btnNhapAnh.setRadius(30);
		btnNhapAnh.setColorClick(new Color(84, 80, 75));
		btnNhapAnh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		btnNhapAnh.setToolTipText("Nhập ảnh sản phẩm");
		btnNhapAnh.setBounds(900, 420, 100, 30);
		contentPane.add(btnNhapAnh);

		btnThoat = new Button("");
		btnThoat.setIcon(new ImageIcon(CapNhatSP.class.getResource("/Exit.png")));
		btnThoat.setToolTipText("Quay lại");
		btnThoat.setRadius(39);
		btnThoat.setColorClick(new Color(84, 80, 75));
		btnThoat.setBounds(1132, 10, 39, 39);
		contentPane.add(btnThoat);

		txtMau = new RoundJTextField(30);
		txtMau.setToolTipText("Nhập màu");
		txtMau.setText(sanPham.getMau());
		txtMau.setHorizontalAlignment(SwingConstants.CENTER);
		txtMau.setForeground(new Color(84, 80, 75));
		txtMau.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtMau.setColumns(10);
		txtMau.setBackground(new Color(243, 238, 231));
		txtMau.setBounds(300, 310, 200, 40);
		contentPane.add(txtMau);
		
		lblNewLabel = new JLabel("Mã sản phẩm");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(243, 238, 231));
		lblNewLabel.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNewLabel.setBounds(80, 150, 130, 20);
		contentPane.add(lblNewLabel);
		
		lblTnSnPhm = new JLabel("Tên sản phẩm");
		lblTnSnPhm.setHorizontalAlignment(SwingConstants.CENTER);
		lblTnSnPhm.setForeground(new Color(243, 238, 231));
		lblTnSnPhm.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblTnSnPhm.setBounds(460, 154, 130, 20);
		contentPane.add(lblTnSnPhm);
		
		lblSLng = new JLabel("Số lượng");
		lblSLng.setHorizontalAlignment(SwingConstants.CENTER);
		lblSLng.setForeground(new Color(243, 238, 231));
		lblSLng.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSLng.setBounds(80, 220, 130, 20);
		contentPane.add(lblSLng);
		
		lblGiNhpKho = new JLabel("Giá nhập kho");
		lblGiNhpKho.setHorizontalAlignment(SwingConstants.CENTER);
		lblGiNhpKho.setForeground(new Color(243, 238, 231));
		lblGiNhpKho.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblGiNhpKho.setBounds(337, 220, 130, 20);
		contentPane.add(lblGiNhpKho);
		
		lblnGi = new JLabel("Đơn giá");
		lblnGi.setHorizontalAlignment(SwingConstants.CENTER);
		lblnGi.setForeground(new Color(243, 238, 231));
		lblnGi.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblnGi.setBounds(583, 224, 130, 20);
		contentPane.add(lblnGi);
		
		lblNgySnXut = new JLabel("Ngày sản xuất");
		lblNgySnXut.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgySnXut.setForeground(new Color(243, 238, 231));
		lblNgySnXut.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNgySnXut.setBounds(80, 290, 130, 20);
		contentPane.add(lblNgySnXut);
		
		lblMu = new JLabel("Màu");
		lblMu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMu.setForeground(new Color(243, 238, 231));
		lblMu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblMu.setBounds(337, 294, 130, 20);
		contentPane.add(lblMu);

		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);
		btnNhapAnh.addActionListener(this);
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
				String maSP = txtMaSP.getText();
				String tenSP = txtTenSP.getText();
				double giaNhapKho = Double.parseDouble(txtGiaNhapKho.getText());
				int soLuong = Integer.parseInt(txtSoLuong.getText());
				String mau = txtMau.getText();
				String size = (String) cbbSize.getSelectedItem();
				String chatLieu = (String) cbbChatLieu.getSelectedItem();
				String ncc = (String) cbbNCC.getSelectedItem();
				String anh = lblAnh.getText().trim();
				String maNCC = "";
				try {
					if (!ncc.equals("")) {

						for (NhaCungCap x : ncc_dao.getDsNhaCungCap()) {
							if (x.getTenNCC().compareToIgnoreCase(ncc) == 0)
								maNCC = x.getMaNCC();
						}
					}
					sp_dao.suaSanPham(maSP, tenSP, giaNhapKho, soLuong, mau, size, anh, maNCC, chatLieu);
					JOptionPane.showMessageDialog(contentPane, "Cập nhật thông tin sản phẩm thành công!");
				} catch (Exception e1) {
					e1.printStackTrace();

				}
			}
		}
		if(o.equals(btnNhapAnh)) {
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        int returnValue = fileChooser.showOpenDialog(this);
	        if(returnValue == JFileChooser.APPROVE_OPTION){
	            File file = fileChooser.getSelectedFile();
	            // lay duong dan file de luu vao mot truong trong co so du lieu
	            String pathFile = file.getAbsolutePath();
	            //String pathFile1 = file.getAbsolutePath().replace("//", "--");
	            BufferedImage b;
	            try {
	                b = ImageIO.read(file);     
	                lblAnh.setIcon(new ImageIcon(b));
	                lblAnh.setText(pathFile); // đó phải có cái này xem thử lưu đc chưa sửa xong đoan jkai đã
	            } catch (Exception e3) {
	            }
	        }
		}
	}

	private boolean validData() {
		String maSP = txtMaSP.getText();
		String tenSP = txtTenSP.getText();
		String giaNhap = txtGiaNhapKho.getText();
		double giaNhapKho = 0;
		String soLuong = txtSoLuong.getText();
		String mau = txtMau.getText();
		String size = (String) cbbSize.getSelectedItem();
		String chatLieu = (String) cbbChatLieu.getSelectedItem();
		String ncc = (String) cbbNCC.getSelectedItem();
		String anh = null;
		if (tenSP.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập tên sản phẩm!");
			return false;
		}
		if (giaNhap.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập giá nhập kho của sản phẩm!");
			return false;
		}
		try {
			giaNhapKho = Double.parseDouble(txtGiaNhapKho.getText());
		} catch (Exception e2) {
			showMessage("Giá nhập kho phải là số", txtGiaNhapKho);
			return false;
		};
//		if (!giaNhapKho.matches("[0-9]*")) {
//			JOptionPane.showMessageDialog(contentPane, "Giá nhập kho phải là số");
//			return false;
//		}
		if (soLuong.length() == 0) {
			JOptionPane.showMessageDialog(contentPane, "Nhập số lượng của sản phẩm!");
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
		dispose();
	}
}
