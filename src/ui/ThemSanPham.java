package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components.button.Button;
import components.comboBox.ComboBox;
import components.roundJTextField.RoundJTextField;
import dao.LoaiSPDao;
import entity.LoaiSP;
import entity.NhaCungCap;
import entity.SanPham;
import dao.SanPhamDao;
import service.ILoaiSPService;
import service.INhaCungCapService;
import service.ISanPhamService;
import service.ITinhToan;
import service.impl.LoaiSPServiceImpl;
import service.impl.NhaCungCapImpl;
import service.impl.SanPhamImpl;
import service.impl.TinhToanImpl;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class ThemSanPham extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private Button btnLuu, btnHuy, btnNhapAnh, btnThoat;
	private RoundJTextField txtMaSP, txtTenSP, txtSoLuong, txtNgaySX, txtThangSX, txtNamSX, txtGiaNhapKho, txtThongBao;
	private JComboBox cbbChatLieu, cbbSize, cbbMau, cbbNCC, cbbDanhMuc;
	private ITinhToan utilTt;
	private ISanPhamService utilSp;
	private DefaultTableModel modelSanPham;
	private JLabel lblAnh;
	private JPanel panel;
	private JLabel lblGiNhpKho;
	private JLabel lblNgy;
	private JLabel lblThng;
	private JLabel lblNm;
	private JLabel lblNgayNhapKho_2;
	private JLabel lblNgayNhapKho_3;
	private JLabel lblNgayNhapKho_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemSanPham frame = new ThemSanPham();
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
	public ThemSanPham() throws Exception {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(295, 170, 609, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		//contentPane.setToolTipText("Anh");
		contentPane.setBackground(new Color(84, 80, 75));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("VIR - Urbanus et elegans");
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblThem = new JLabel("Thêm sản phẩm");
		lblThem.setForeground(new Color(230, 230, 230));
		lblThem.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 40));
		lblThem.setBounds(34, 0, 300, 70);
		contentPane.add(lblThem);
		
		utilTt = new TinhToanImpl(); // muốn gọi hàm bên interface thì gọi kiểu này
		// nên để nó ở local cho đỡ phải gọi lại
		txtMaSP = new RoundJTextField(30);
		txtMaSP.setForeground(new Color(84, 80, 75));
		txtMaSP.setBackground(new Color(243, 238, 231));
		txtMaSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaSP.setFont(new Font("000 Chinacat [TeddyBear]", txtMaSP.getFont().getStyle(), txtMaSP.getFont().getSize() + 5));
		txtMaSP.setToolTipText("Nhập mã sản phẩm");
		txtMaSP.setText(utilTt.maSanPhamMoi()); // mã sản phẩm mình set cứng
		txtMaSP.setBounds(175, 80, 373, 30);
		contentPane.add(txtMaSP);
		txtMaSP.setColumns(10);
		txtMaSP.setEditable(false); 
		
		txtTenSP = new RoundJTextField(30);
		txtTenSP.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtTenSP.setToolTipText("Nhập tên sản phẩm");
		txtTenSP.setText("Giày lười vải");
		txtTenSP.setHorizontalAlignment(SwingConstants.CENTER);
		txtTenSP.setForeground(new Color(84, 80, 75));
		txtTenSP.setColumns(10);
		txtTenSP.setBackground(new Color(243, 238, 231));
		txtTenSP.setBounds(175, 120, 373, 30);
		contentPane.add(txtTenSP);
		
		txtSoLuong = new RoundJTextField(30);
		txtSoLuong.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtSoLuong.setToolTipText("Nhập số lượng");
		txtSoLuong.setText("22");
		txtSoLuong.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoLuong.setForeground(new Color(84, 80, 75));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBackground(new Color(243, 238, 231));
		txtSoLuong.setBounds(175, 160, 373, 30);
		contentPane.add(txtSoLuong);
		
		txtGiaNhapKho = new RoundJTextField(30);
		txtGiaNhapKho.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtGiaNhapKho.setToolTipText("Nhập giá nhập kho");
		txtGiaNhapKho.setText("300000");
		txtGiaNhapKho.setHorizontalAlignment(SwingConstants.CENTER);
		txtGiaNhapKho.setForeground(new Color(84, 80, 75));
		txtGiaNhapKho.setColumns(10);
		txtGiaNhapKho.setBackground(new Color(243, 238, 231));
		txtGiaNhapKho.setBounds(175, 200, 373, 30);
		contentPane.add(txtGiaNhapKho);
		
		JLabel lblNgayNhapKho = new JLabel("Ngày sản xuất:");
		lblNgayNhapKho.setForeground(new Color(255, 255, 255));
		lblNgayNhapKho.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNgayNhapKho.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhapKho.setBounds(44, 240, 116, 30);
		contentPane.add(lblNgayNhapKho);
		
		txtNgaySX = new RoundJTextField(30);
		txtNgaySX.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNgaySX.setToolTipText("Nhập ngày sản xuất");
		txtNgaySX.setText("05");
		txtNgaySX.setHorizontalAlignment(SwingConstants.CENTER);
		txtNgaySX.setForeground(new Color(84, 80, 75));
		txtNgaySX.setColumns(10);
		txtNgaySX.setBackground(new Color(243, 238, 231));
		txtNgaySX.setBounds(217, 240, 57, 30);
		contentPane.add(txtNgaySX);
		
		txtThangSX = new RoundJTextField(30);
		txtThangSX.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtThangSX.setToolTipText("Nhập tháng sản xuất");
		txtThangSX.setText("05");
		txtThangSX.setHorizontalAlignment(SwingConstants.CENTER);
		txtThangSX.setForeground(new Color(84, 80, 75));
		txtThangSX.setColumns(10);
		txtThangSX.setBackground(new Color(243, 238, 231));
		txtThangSX.setBounds(358, 240, 60, 30);
		contentPane.add(txtThangSX);
		
		txtNamSX = new RoundJTextField(30);
		txtNamSX.setText("2022");
		txtNamSX.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		txtNamSX.setToolTipText("Nhập năm sản xuất");
		txtNamSX.setHorizontalAlignment(SwingConstants.CENTER);
		txtNamSX.setForeground(new Color(84, 80, 75));
		txtNamSX.setColumns(10);
		txtNamSX.setBackground(new Color(243, 238, 231));
		txtNamSX.setBounds(488, 240, 60, 30);
		contentPane.add(txtNamSX);
		
		cbbDanhMuc = new JComboBox();
		cbbDanhMuc.setModel(new DefaultComboBoxModel(new String[] {"Áo", "Quần", "Phụ kiện", "Giày", "Khác"}));
		cbbDanhMuc.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbDanhMuc.getModel();
		cbbDanhMuc.setBackground(new Color(243, 238, 231));
		cbbDanhMuc.setBounds(175, 360, 148, 30);
		contentPane.add(cbbDanhMuc);
		
		JLabel lblDanhMuc = new JLabel("Danh mục:");
		lblDanhMuc.setForeground(new Color(230, 230, 230));
		lblDanhMuc.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblDanhMuc.setBounds(45, 360, 80, 30);
		contentPane.add(lblDanhMuc);
		
		cbbNCC = new JComboBox();
		cbbNCC.setModel(new DefaultComboBoxModel(new String[] {"New brands", "Second brands", "Third brands", "The best", "Bad guy"}));
		cbbNCC.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbNCC.setBackground(new Color(243, 238, 231));
		cbbNCC.setBounds(175, 280, 148, 30);
		contentPane.add(cbbNCC);
		
		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setForeground(new Color(230, 230, 230));
		lblNCC.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblNCC.setBounds(45, 280, 100, 30);
		contentPane.add(lblNCC);
		
		cbbMau = new JComboBox();
		cbbMau.setModel(new DefaultComboBoxModel(new String[] {"Trắng", "Đen", "Nâu", "Xám", "Cam", "Vàng", "Xanh dương", "Xanh biển", "Xanh lam"}));
		cbbMau.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbMau.setBackground(new Color(243, 238, 231));
		cbbMau.setBounds(175, 320, 148, 30);
		contentPane.add(cbbMau);
		
		JLabel lblMau = new JLabel("Màu:");
		lblMau.setForeground(new Color(230, 230, 230));
		lblMau.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblMau.setBounds(45, 320, 40, 30);
		contentPane.add(lblMau);
		
		cbbSize = new JComboBox();
		cbbSize.setModel(new DefaultComboBoxModel(new String[] {"XS", "S", "M", "L", "XL", "XXL"}));
		cbbSize.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbSize.setBackground(new Color(243, 238, 231));
		cbbSize.setBounds(175, 400, 148, 30);
		contentPane.add(cbbSize);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setForeground(new Color(230, 230, 230));
		lblSize.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblSize.setBounds(45, 400, 40, 30);
		contentPane.add(lblSize);
		
		JLabel lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setForeground(new Color(230, 230, 230));
		lblChatLieu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		lblChatLieu.setBounds(45, 440, 71, 30);
		contentPane.add(lblChatLieu);
		
		cbbChatLieu = new JComboBox();
		cbbChatLieu.setModel(new DefaultComboBoxModel(new String[] {"Len", "Lụa", "Vải kaki", "Vải thun", "Vải nỉ", "Vải jean", "Vải thô", "Vải voan"}));
		cbbChatLieu.setBackground(new Color(243, 238, 231));
		cbbChatLieu.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		cbbChatLieu.setBounds(175, 440, 148, 30);
		contentPane.add(cbbChatLieu);
		
		btnLuu = new Button("");
		btnLuu.setRadius(39);
		btnLuu.setColorClick(new Color(84, 80, 75));
		btnLuu.setToolTipText("Lưu sản phẩm");
		btnLuu.setIcon(new ImageIcon(ThemSanPham.class.getResource("/Add.png")));
		btnLuu.setBounds(175, 477, 40, 40);
		contentPane.add(btnLuu);
		
		btnHuy = new Button("");
		btnHuy.setRadius(39);
		btnHuy.setColorClick(new Color(84, 80, 75));
		btnHuy.setToolTipText("Huỷ thao tác");
		btnHuy.setIcon(new ImageIcon(ThemSanPham.class.getResource("/Delete.png")));
		btnHuy.setBounds(347, 478, 39, 39);
		contentPane.add(btnHuy);
		
		btnNhapAnh = new Button("Nhập ảnh");
		btnNhapAnh.setRadius(30);
		btnNhapAnh.setColorClick(new Color(84, 80, 75));
		btnNhapAnh.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		btnNhapAnh.setToolTipText("Nhập ảnh sản phẩm");
		btnNhapAnh.setBounds(417, 440, 100, 30);
		contentPane.add(btnNhapAnh);
		
		btnThoat = new Button("");
		btnThoat.setIcon(new ImageIcon(ThemSanPham.class.getResource("/Exit.png")));
		btnThoat.setToolTipText("Quay lại");
		btnThoat.setRadius(39);
		btnThoat.setColorClick(new Color(84, 80, 75));
		btnThoat.setBounds(490, 11, 40, 40);
		contentPane.add(btnThoat);
		
		panel = new JPanel();
		panel.setToolTipText("Anh");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u1EA2nh", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(376, 282, 172, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblAnh = new JLabel("");
		lblAnh.setBounds(6, 16, 156, 138);
		panel.add(lblAnh);
		lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblGiNhpKho = new JLabel("Giá nhập kho");
		lblGiNhpKho.setForeground(new Color(255, 255, 255));
		lblGiNhpKho.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiNhpKho.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblGiNhpKho.setBounds(44, 200, 116, 30);
		contentPane.add(lblGiNhpKho);
		
		lblNgy = new JLabel("ngày:");
		lblNgy.setForeground(new Color(255, 255, 255));
		lblNgy.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgy.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNgy.setBounds(168, 240, 57, 30);
		contentPane.add(lblNgy);
		
		lblThng = new JLabel("tháng:");
		lblThng.setForeground(new Color(255, 255, 255));
		lblThng.setHorizontalAlignment(SwingConstants.CENTER);
		lblThng.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblThng.setBounds(306, 240, 57, 30);
		contentPane.add(lblThng);
		
		lblNm = new JLabel("năm:");
		lblNm.setForeground(new Color(255, 255, 255));
		lblNm.setHorizontalAlignment(SwingConstants.CENTER);
		lblNm.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNm.setBounds(428, 240, 57, 30);
		contentPane.add(lblNm);
		
		lblNgayNhapKho_2 = new JLabel("Số lượng");
		lblNgayNhapKho_2.setForeground(new Color(255, 255, 255));
		lblNgayNhapKho_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhapKho_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNgayNhapKho_2.setBounds(44, 160, 116, 30);
		contentPane.add(lblNgayNhapKho_2);
		
		lblNgayNhapKho_3 = new JLabel("Tên sản phẩm");
		lblNgayNhapKho_3.setForeground(new Color(255, 255, 255));
		lblNgayNhapKho_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhapKho_3.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNgayNhapKho_3.setBounds(44, 120, 116, 30);
		contentPane.add(lblNgayNhapKho_3);
		
		lblNgayNhapKho_4 = new JLabel("Mã sản phẩm:");
		lblNgayNhapKho_4.setForeground(new Color(255, 255, 255));
		lblNgayNhapKho_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayNhapKho_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNgayNhapKho_4.setBounds(44, 80, 116, 30);
		contentPane.add(lblNgayNhapKho_4);
		
		
		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);
		btnNhapAnh.addActionListener(this);
		btnThoat.addActionListener(this);
		
		txtThongBao = new RoundJTextField(30);
		txtThongBao.setToolTipText("Thông báo");
		txtThongBao.setText("Thông báo");
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
		if (o.equals(btnNhapAnh)) {
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
		if (o.equals(btnHuy)) {
			clearTextfield();
		}
		if (o.equals(btnThoat)) {
			this.setVisible(false);
		}
		// bắt sự kiện ở đây
		if(o.equals(btnLuu)) {
			if(validData()) {
				//đầu tiên get các giá trị của file trước
				String maSP = txtMaSP.getText().trim();
				String tenSP = txtTenSP.getText().trim();
				int  soLuong = 0;
				double donGia = 0;
				String sNgaySX= txtNamSX.getText().trim() + "-" + txtThangSX.getText().trim() + "-" + txtNgaySX.getText().trim();
				LocalDate ngaySX = LocalDate.parse(sNgaySX);
				String chatLieu = (String) cbbChatLieu.getSelectedItem().toString();
				String size = (String) cbbSize.getSelectedItem().toString();
				String mau = (String) cbbMau.getSelectedItem().toString();
				String nhaCC= cbbNCC.getSelectedItem().toString();
				double giaNhapKho = Integer.parseInt(txtGiaNhapKho.getText());
				String anh = lblAnh.getText().trim();
				utilSp = new SanPhamImpl();
				SanPham sp = new SanPham(maSP, tenSP, soLuong, giaNhapKho, ngaySX, mau, size, anh, chatLieu); // cho hết tham số vào đây
				
				String loai = cbbDanhMuc.getSelectedItem().toString();
				INhaCungCapService utilNcc = new NhaCungCapImpl();
				ILoaiSPService utilLoai = new LoaiSPServiceImpl();
				String maNcc = null;
				String maLoai = null;
				try {
					for(NhaCungCap ncc: utilNcc.getDsNhaCungCap()) {
						if(ncc.getTenNCC().compareToIgnoreCase(nhaCC)==0) {
							maNcc = ncc.getMaNCC();
						}
					}
					for(LoaiSP l: utilLoai.getDSLoaiSP()) {
						if(l.getTenLoai().compareToIgnoreCase(loai)==0) {
							maLoai = l.getMaLoai();
						}
					}
					utilSp.themSanPham(sp, maLoai, maNcc);
					showMessage("Thêm thành công", txtThongBao);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}}
		}
	
	private boolean validData() {
		String maSP = txtMaSP.getText().trim();
		String tenSP = txtTenSP.getText().trim();
		int  soLuong = 0;
		String sNgaySX= txtNamSX.getText().trim() + "-" + txtThangSX.getText().trim() + "-" + txtNgaySX.getText().trim();
		LocalDate ngaySX;
		double giaNhapKho = 0;

		try {
			giaNhapKho = Integer.parseInt(txtGiaNhapKho.getText());
		} catch (Exception e2) {
			showMessage("Giá nhập kho phải là số", txtGiaNhapKho);
			return false;
		};
		try {
			ngaySX = LocalDate.parse(sNgaySX);
		} catch (DateTimeException e) {
			showMessage("Phải nhập đúng định dạng dd-MM-yyyy", txtNgaySX);
			return false;
		} 
		try {
			soLuong = Integer.parseInt(txtSoLuong.getText());
		} catch (Exception e2) {
			showMessage("Số lượng phải là số", txtSoLuong);
			return false;
		};
		if (maSP.length() == 0) {
			showMessage("Nhập mã sản phẩm", txtMaSP);
			return false;
		}
		if (!maSP.matches("^(SP\\d{3})$")) {
			showMessage(
					"Mã sản phẩm phải bắt đầu bằng SP, theo sau là 3 số bất kỳ",
					txtMaSP);
			return false;
		}
		if (tenSP.equals("")) {
			showMessage("Nhập tên sản phẩm", txtTenSP);
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
			txtMaSP.setText(utilTt.maSanPhamMoi());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtTenSP.setText("Tên sản phẩm");
		txtGiaNhapKho.setText("Giá nhập kho");
		txtSoLuong.setText("Số lượng");	
		txtNgaySX.setText("ngày");
		txtThangSX.setText("tháng");
		txtNamSX.setText("năm");
		cbbChatLieu.setSelectedIndex(0);
		cbbMau.setSelectedIndex(0);
		cbbSize.setSelectedIndex(0);
		cbbNCC.setSelectedIndex(0);
		cbbDanhMuc.setSelectedIndex(0);
		lblAnh.setText("");
	}
}
