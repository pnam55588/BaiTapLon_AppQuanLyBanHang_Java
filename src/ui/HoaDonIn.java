package ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.layout.element.Paragraph;

import entity.CTHoaDon;
import entity.SanPham;
import service.ICTHoaDonService;
import service.ISanPhamService;
import service.impl.CTHoaDonImpl;
import service.impl.SanPhamImpl;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class HoaDonIn extends JFrame implements ActionListener{
	private JTable table;
	private JTextField textMaHD;
	private JTextField textNgayLapHD;
	private JTextField textThanhTien;
	private JTextField textTongTien;
	private JTextField textMaNV;
	private JTextField textTenKH;
	private JTextField textMaKH;
	private JButton btnIn;

	public HoaDonIn(String maHD, LocalDate ngayLapHD, List<CTHoaDon> ds, double thanhTien, double tongTien,
			Double tienNhan, Double tienThua, String maKH, String maNV, String tenKH) {
		setSize(444, 630);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("HÓA ĐƠN BÁN HÀNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(81, 14, 262, 31);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(20, 68, 114, 31);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Ngày lập hóa đơn:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(20, 96, 142, 31);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Chi tiết hóa đơn:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(20, 147, 142, 31);
		getContentPane().add(lblNewLabel_1_1_1);

		table = new JTable();
		String HEADER[] = { "Tên sản phẩm", "Số lượng", "Tiền bán", "Tổng" };
		DefaultTableModel model = new DefaultTableModel(HEADER, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
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
					return Double.class;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + columnIndex);
				}
			}
		};
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 178, 379, 196);
		getContentPane().add(scrollPane);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Thành tiền:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(20, 384, 142, 31);
		getContentPane().add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Tổng tiền sau thuế (10%):");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_1.setBounds(20, 412, 206, 31);
		getContentPane().add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_1_1.setBounds(20, 468, 107, 31);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Khách hàng:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(20, 496, 107, 31);
		getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);

		textMaHD = new JTextField();
		textMaHD.setBounds(158, 72, 132, 19);
		getContentPane().add(textMaHD);
		textMaHD.setColumns(10);

		textNgayLapHD = new JTextField();
		textNgayLapHD.setColumns(10);
		textNgayLapHD.setBounds(158, 100, 132, 19);
		getContentPane().add(textNgayLapHD);

		textThanhTien = new JTextField();
		textThanhTien.setColumns(10);
		textThanhTien.setBounds(126, 392, 132, 19);
		getContentPane().add(textThanhTien);

		textTongTien = new JTextField();
		textTongTien.setColumns(10);
		textTongTien.setBounds(228, 420, 132, 19);
		getContentPane().add(textTongTien);

		textMaNV = new JTextField();
		textMaNV.setColumns(10);
		textMaNV.setBounds(126, 476, 132, 19);
		getContentPane().add(textMaNV);

		textTenKH = new JTextField();
		textTenKH.setColumns(10);
		textTenKH.setBounds(126, 504, 132, 19);
		getContentPane().add(textTenKH);

		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("VND");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_2.setBounds(267, 384, 57, 31);
		getContentPane().add(lblNewLabel_1_1_1_1_2);

		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("VND");
		lblNewLabel_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_2_1.setBounds(363, 412, 57, 31);
		getContentPane().add(lblNewLabel_1_1_1_1_2_1);

		JLabel lblNewLabel_1_1_1_1_2_2 = new JLabel("|");
		lblNewLabel_1_1_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1_1_2_2.setBounds(268, 496, 10, 31);
		getContentPane().add(lblNewLabel_1_1_1_1_2_2);

		textMaKH = new JTextField();
		textMaKH.setColumns(10);
		textMaKH.setBounds(288, 504, 132, 19);
		getContentPane().add(textMaKH);

		textMaHD.setText(maHD);
		textMaHD.setEditable(false);
		textNgayLapHD.setText(ngayLapHD.toString());
		textNgayLapHD.setEditable(false);
		textThanhTien.setText(String.valueOf(thanhTien));
		textThanhTien.setEditable(false);
		textTongTien.setText(getWarningString().valueOf(tongTien));
		textTongTien.setEditable(false);
		textMaNV.setText(maNV);
		textMaNV.setEditable(false);
		textTenKH.setText(tenKH);
		textTenKH.setEditable(false);
		textMaKH.setText(maKH);
		textMaKH.setEditable(false);

		 btnIn = new JButton("In hóa đơn");
		btnIn.setBackground(new Color(255, 255, 128));
		btnIn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIn.setBounds(140, 552, 150, 31);
		getContentPane().add(btnIn);


		for(CTHoaDon cthd : ds) {
			ISanPhamService util = new SanPhamImpl();
			try {
				SanPham sp = util.timMa(cthd.getSanPham().getMaSP());
				Object[] row = {sp.getTenSP(), cthd.getSoLuong(), sp.getDonGia(), cthd.tinhTien()};
				model.addRow(row);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		btnIn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(btnIn)) {
			dispose();
		}
	}
}
