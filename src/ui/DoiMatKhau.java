package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.beans.Statement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import components.button.Button;
import components.panelRound.PanelRound;
import components.roundJTextField.RoundJTextField;
import entity.TaiKhoan;
import service.ITaiKhoanService;
import service.impl.TaiKhoanImpl;

public class DoiMatKhau extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtUser;
	private RoundJTextField txtThongBao;
	private ITaiKhoanService utilTK = new TaiKhoanImpl();
	private Button btnDoiMatKhau, btnThoat;
	private JPasswordField txtPassword, txtNPassword, txtCfPassword;
	@SuppressWarnings("unchecked")
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMatKhau frame = new DoiMatKhau();
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
	public DoiMatKhau() {
		setSize(557, 326);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelRound pnlDoiMatKhau = new PanelRound(30);
		pnlDoiMatKhau.setBackground(new Color(230, 230, 230));
		pnlDoiMatKhau.setBounds(0, 0, 541, 287);
		contentPane.add(pnlDoiMatKhau);
		pnlDoiMatKhau.setLayout(null);
		
		JLabel lblDoiMatKhau = new JLabel("Đổi mật khẩu");
		lblDoiMatKhau.setFont(new Font("000 Chinacat [TeddyBear]", Font.BOLD, 30));
		lblDoiMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoiMatKhau.setBounds(114, 11, 300, 47);
		pnlDoiMatKhau.add(lblDoiMatKhau);
		
		JLabel lblUser = new JLabel("Username:");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblUser.setBounds(20, 82, 100, 30);
		pnlDoiMatKhau.add(lblUser);
		
		JLabel lblOPass = new JLabel("Old_Password:");
		lblOPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblOPass.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblOPass.setBounds(20, 123, 149, 30);
		pnlDoiMatKhau.add(lblOPass);
		
		txtUser = new JTextField(30);
		txtUser.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		txtUser.setToolTipText("Nhập username");
		txtUser.setForeground(new Color(255, 255, 255));
		txtUser.setBackground(new Color(84, 80, 75));
		txtUser.setBounds(206, 84, 320, 26);
		pnlDoiMatKhau.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		txtPassword.setToolTipText("Nhập old password");
		txtPassword.setForeground(new Color(255, 255, 255));
		txtPassword.setBackground(new Color(84, 80, 75));
		txtPassword.setBounds(206, 125, 320, 26);
		txtPassword.setColumns(10);
		pnlDoiMatKhau.add(txtPassword);
		
		JLabel lblNPass = new JLabel("New_Password:");
		lblNPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblNPass.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblNPass.setBounds(20, 164, 149, 30);
		pnlDoiMatKhau.add(lblNPass);
		
		txtNPassword = new JPasswordField();
		txtNPassword.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		txtNPassword.setToolTipText("Nhập new password");
		txtNPassword.setForeground(new Color(255, 255, 255));
		txtNPassword.setBackground(new Color(84, 80, 75));
		txtNPassword.setBounds(206, 166, 320, 26);
		txtNPassword.setColumns(10);
		pnlDoiMatKhau.add(txtNPassword);
		
		JLabel lblCfPassword = new JLabel("CfPassword:");
		lblCfPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblCfPassword.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblCfPassword.setBounds(20, 211, 149, 27);
		pnlDoiMatKhau.add(lblCfPassword);
		
		txtCfPassword = new JPasswordField();
		txtCfPassword.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		txtCfPassword.setToolTipText("Nhập lại new password");
		txtCfPassword.setForeground(new Color(255, 255, 255));
		txtCfPassword.setBackground(new Color(84, 80, 75));
		txtCfPassword.setBounds(206, 211, 320, 26);
		txtCfPassword.setColumns(10);
		pnlDoiMatKhau.add(txtCfPassword);
		
		btnDoiMatKhau = new Button("Đổi mật khẩu");
		btnDoiMatKhau.setRadius(39);
		btnDoiMatKhau.setBounds(206, 250, 110, 26);
		btnDoiMatKhau.setColor(new Color(84, 80, 75));
		btnDoiMatKhau.setColorOver(new Color(230, 230, 230));
		btnDoiMatKhau.setColorClick(new Color(222, 217, 214));
		btnDoiMatKhau.setForeground(new Color(255, 255, 255));
		pnlDoiMatKhau.add(btnDoiMatKhau);
		
		btnThoat = new Button("Hủy");
		btnThoat.setToolTipText("Hủy");
		btnThoat.setRadius(39);
		btnThoat.setColorClick(new Color(84, 80, 75));
		btnThoat.setColor(new Color(84, 80, 75));
		btnThoat.setColorOver(new Color(230, 230, 230));
		btnThoat.setColorClick(new Color(222, 217, 214));
		btnThoat.setForeground(new Color(255, 255, 255));
		btnThoat.setBounds(326, 250, 88, 27);
		pnlDoiMatKhau.add(btnThoat);
		
		txtThongBao = new RoundJTextField(30);
		txtThongBao.setToolTipText("Thông báo");
		txtThongBao.setText("Thông báo");
		
		btnThoat.addActionListener(this);
		btnDoiMatKhau.addActionListener(this);
		
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
		if (o.equals(btnThoat)) {
			this.setVisible(false);
		}
		if(o.equals(btnDoiMatKhau)) {
			if(validData()) {
		String user = txtUser.getText().trim();
		String pass = "";
		ITaiKhoanService utilTK = new TaiKhoanImpl();
		String maTK = null;
		if (txtNPassword.getText().trim().equals(txtCfPassword.getText().trim())) {
			pass = txtNPassword.getText().trim();
		} else {
			//showMessage("Nhập lại", txtThongBao);
			pass = txtPassword.getText().trim();
		}
		try {
			for(TaiKhoan tk: utilTK.getDsTaiKhoan()) {
				if(tk.getUserName().compareToIgnoreCase(user)==0) {
					maTK = tk.getUserName();
				}
			}
			for(TaiKhoan mk: utilTK.getDsTaiKhoan()) {
				if(mk.getPassword().compareToIgnoreCase(pass)==0) {
					pass = mk.getPassword();
				}
			}
			utilTK.doiMatKhau(maTK, pass);
			showMessage("Đổi mật khẩu thành công", txtThongBao);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			}
		}
	}
	
	private boolean validData() {
		String user = txtUser.getText();
		String pass = txtPassword.getText();
		int flat = 0;
		if (!user.equals("")) {
			try {
				for (TaiKhoan x : utilTK.getDsTaiKhoan() ) {
					if (x.getUserName().compareToIgnoreCase(user) == 0 && x.getPassword().compareToIgnoreCase(pass) == 0) {
						flat = 1;
					} 
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(flat == 0) {
			JOptionPane.showMessageDialog(contentPane, "Sai tài khoản hoặc mật khẩu!");
			return false;
		}
		if (txtUser.getText().equals("")) {
			showMessage("Nhập tên tài khoản", txtUser);
			return false;
		}
		if (txtPassword.getText().equals("")) {
			showMessage("Nhập mật khẩu cũ", txtPassword);
			return false;
		}
		if (txtCfPassword.getText().trim().equals("")) {
			showMessage("Nhập lại mật khẩu mới", txtCfPassword);
			return false;
		}
		if (txtNPassword.getText().equals("") || txtCfPassword.getText().trim().equals("")) {
			showMessage("Nhập mật khẩu mới", txtNPassword);
			return false;
		}
		if (!txtNPassword.getText().trim().equals(txtCfPassword.getText().trim())) {
			showMessage("Mật khẩu mới và nhập lại mật khẩu không giống", txtThongBao);
			return false;
		}
		if (txtNPassword.getText().trim().equals(txtPassword.getText().trim())) {
			showMessage("Mật khẩu mới và mật khẩu cũ không được trùng", txtThongBao);
			return false;
		}

		return true;
	}
	
	private void showMessage(String message, JTextField jTextField) {
		JOptionPane.showMessageDialog(this, message);
		jTextField.setText("");
		jTextField.requestFocus();
	}
}
