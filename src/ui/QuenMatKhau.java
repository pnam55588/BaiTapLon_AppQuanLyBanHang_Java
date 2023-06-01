package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import components.button.Button;
import components.panelRound.PanelRound;
import components.roundJTextField.RoundJTextField;
import entity.TaiKhoan;
import service.ITaiKhoanService;
import service.impl.TaiKhoanImpl;

import javax.swing.JTextField;

public class QuenMatKhau extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtUser;
	private ITaiKhoanService utilTK = new TaiKhoanImpl();
	private Button btnHuy, btnTimKiem;
	private RoundJTextField txtThongBao;
	private JPasswordField txtPasswordLV2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuenMatKhau frame = new QuenMatKhau();
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
	public QuenMatKhau() {
		setSize(450, 292);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelRound pnlQuenMatKhau = new PanelRound(30);
		pnlQuenMatKhau.setBackground(new Color(230, 230, 230));
		pnlQuenMatKhau.setBounds(0, 0, 432, 253);
		contentPane.add(pnlQuenMatKhau);
		pnlQuenMatKhau.setLayout(null);
		
		JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu");
		lblQuenMatKhau.setFont(new Font("000 Chinacat [TeddyBear]", Font.BOLD, 30));
		lblQuenMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuenMatKhau.setBounds(49, 11, 300, 47);
		pnlQuenMatKhau.add(lblQuenMatKhau);
		
		JLabel lblUser1 = new JLabel("Vui lòng nhập thông tin của bạn để tìm kiếm");
		lblUser1.setFont(new Font("000 Chinacat [TeddyBear]", Font.BOLD, 15));
		lblUser1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser1.setBounds(20, 56, 347, 35);
		pnlQuenMatKhau.add(lblUser1);
		
		JLabel lblUser = new JLabel("Username:");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblUser.setBounds(20, 100, 100, 30);
		pnlQuenMatKhau.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setBounds(179, 103, 244, 26);
		pnlQuenMatKhau.add(txtUser);
		txtUser.setColumns(10);
		txtUser.setToolTipText("Nhập username");
		txtUser.setForeground(new Color(255, 255, 255));
		txtUser.setBackground(new Color(84, 80, 75));
		txtUser.setColumns(10);
		
		btnTimKiem = new Button("Tìm kiếm");
		btnTimKiem.setBounds(315, 207, 108, 35);
		btnTimKiem.setForeground(new Color(255, 255, 255));
		btnTimKiem.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		btnTimKiem.setRadius(39);
		btnTimKiem.setColor(new Color(84, 80, 75));
		btnTimKiem.setColorOver(new Color(230, 230, 230));
		btnTimKiem.setColorClick(new Color(222, 2, 214));
		btnTimKiem.setForeground(new Color(255, 255, 255));
		btnTimKiem.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		pnlQuenMatKhau.add(btnTimKiem);
		
		btnHuy = new Button("Hủy");
		btnHuy.setBounds(232, 207, 71, 35);
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		btnHuy.setRadius(39);
		btnHuy.setColor(new Color(84, 80, 75));
		btnHuy.setColorOver(new Color(230, 230, 230));
		btnHuy.setColorClick(new Color(222, 2, 214));
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 15));
		pnlQuenMatKhau.add(btnHuy);
		
		JLabel lblPassLV2 = new JLabel("Password_LV2:");
		lblPassLV2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassLV2.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblPassLV2.setBounds(20, 138, 149, 30);
		pnlQuenMatKhau.add(lblPassLV2);
		
		txtPasswordLV2 = new JPasswordField();
		txtPasswordLV2.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		txtPasswordLV2.setToolTipText("Nhập password level 2");
		txtPasswordLV2.setForeground(new Color(255, 255, 255));
		txtPasswordLV2.setBackground(new Color(84, 80, 75));
		txtPasswordLV2.setBounds(179, 140, 244, 26);
		txtPasswordLV2.setColumns(10);
		pnlQuenMatKhau.add(txtPasswordLV2);
		
		txtThongBao = new RoundJTextField(30);
		txtThongBao.setToolTipText("Thông báo");
		txtThongBao.setText("Thông báo");
		
		btnTimKiem.addActionListener(this);
		btnHuy.addActionListener(this);
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
			this.setVisible(false);
		}
		if(o.equals(btnTimKiem)) {
			if(validData()) {
				String user = txtUser.getText().trim();
				String maTK = null;
				try {
					for(TaiKhoan tk: utilTK.getDsTaiKhoan()) {
						if(tk.getUserName().compareToIgnoreCase(user)==0) {
							maTK = tk.getUserName();
						}
					}
					utilTK.timMa(maTK);
					showMessage("Đã tìm thấy tài khoản của bạn!", txtThongBao);
					String newpass = JOptionPane.showInputDialog("Nhập mật khẩu mới");
					if (newpass.equals("") || newpass == null) {
						showMessage("Đổi mật khẩu thất bại", txtThongBao);
					} else {
					utilTK.doiMatKhau(maTK, newpass);
					showMessage("Đổi mật khẩu thành công", txtThongBao);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	private boolean validData() {
		String user = txtUser.getText().trim();
		String passlv2 = txtPasswordLV2.getText().trim();
		try {
			if (txtUser.getText().equals("")) {
				showMessage("Nhập tên tài khoản", txtThongBao);
				return false;
			}
			if (txtPasswordLV2.getText().equals("")) {
				showMessage("Nhập mật khẩu cấp 2", txtPasswordLV2);
				return false;
			}
		int flat = 0;
		if (!user.equals("")) {
			try {
				for (TaiKhoan x : utilTK.getDsTaiKhoan() ) {
					if (x.getUserName().compareToIgnoreCase(user) == 0) {
						flat = 1;
					} 
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(flat == 0) {
			JOptionPane.showMessageDialog(contentPane, "Tài khoản không hợp lệ");
			return false;
		}
		int flat2 = 0;
		if (!passlv2.equals("")) {
			try {
				for (TaiKhoan x : utilTK.getDsTaiKhoan() ) {
					if (x.getMkCap2().compareToIgnoreCase(passlv2) == 0) {
						flat2 = 1;
					} 
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		if(flat2 == 0) {
			JOptionPane.showMessageDialog(contentPane, "Mật khẩu cấp 2 không hợp lệ");
			return false;
		}
		int flat3 = 0;
		if (!user.equals("") && !passlv2.equals("")) {
			try {
				for (TaiKhoan x : utilTK.getDsTaiKhoan() ) {
					if (x.getUserName().compareToIgnoreCase(user) == 0 && x.getMkCap2().compareToIgnoreCase(passlv2) == 0) {
						flat3 = 1;
					} 
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(flat3 == 0) {
			JOptionPane.showMessageDialog(contentPane, "Tài khoản và mật khẩu cấp 2 không cùng một tài khoản");
			return false;
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
	
	
}
