
package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.button.Button;
import components.panelRound.PanelRound;
import components.roundJTextField.RoundJTextField;
import entity.NhanVien;
import entity.TaiKhoan;
import service.INhanVienService;
import service.ITaiKhoanService;
import service.impl.NhanVienImpl;
import service.impl.TaiKhoanImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;

public class DangNhap extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private RoundJTextField txtUser, txtPass;
	private Button btnLogin;
	private ITaiKhoanService tk_dao = new TaiKhoanImpl();
	private INhanVienService nv_dao = new NhanVienImpl();
	private JLabel lblQuen;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
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
	public DangNhap() {
		setResizable(false);
		setTitle("VIR - Urbanus et elegans");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1440, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to Vir!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(new Color(222, 217, 214));
		lblWelcome.setFont(new Font("000 Chinacat [TeddyBear]", Font.BOLD | Font.ITALIC, 30));
		lblWelcome.setBounds(215, 348, 300, 60);
		contentPane.add(lblWelcome);
		
		PanelRound pnlLogin = new PanelRound(30);
		pnlLogin.setBackground(new Color(230, 230, 230));
		pnlLogin.setBounds(750, 100, 500, 400);
		contentPane.add(pnlLogin);
		pnlLogin.setLayout(null);
		
		btnLogin = new Button("Đăng nhập");
		btnLogin.setRadius(20);
		btnLogin.setColor(new Color(84, 80, 75));
		btnLogin.setColorOver(new Color(230, 230, 230));
		btnLogin.setColorClick(new Color(222, 217, 214));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 25));
		btnLogin.setBounds(100, 300, 300, 50);
		pnlLogin.add(btnLogin);
		
		JLabel lblLogin = new JLabel("Đăng nhập");
		lblLogin.setFont(new Font("000 Chinacat [TeddyBear]", Font.BOLD | Font.ITALIC, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(100, 60, 300, 60);
		pnlLogin.add(lblLogin);
		
		JLabel lblUser = new JLabel("Username:");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblUser.setBounds(20, 150, 100, 30);
		pnlLogin.add(lblUser);
		
		JLabel lblPass = new JLabel("Password:");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		lblPass.setBounds(20, 200, 100, 30);
		pnlLogin.add(lblPass);
		
		txtUser = new RoundJTextField(30);
		txtUser.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		txtUser.setToolTipText("Nhập username");
		txtUser.setForeground(new Color(255, 255, 255));
		txtUser.setBackground(new Color(84, 80, 75));
		txtUser.setBounds(150, 150, 320, 30);
		pnlLogin.add(txtUser);
		txtUser.setColumns(10);
		txtUser.setText("NV001");
		
		txtPass = new RoundJTextField(30);
		txtPass.setFont(new Font("000 Chinacat [TeddyBear]", Font.PLAIN, 20));
		txtPass.setToolTipText("Nhập mật khẩu");
		txtPass.setForeground(new Color(255, 255, 255));
		txtPass.setBackground(new Color(84, 80, 75));
		txtPass.setColumns(10);
		txtPass.setBounds(150, 200, 320, 30);
		txtPass.setText("1");
		pnlLogin.add(txtPass);
		
		lblQuen = new JLabel("Quên mật khẩu?");
		lblQuen.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuen.setFont(new Font("000 Chinacat [TeddyBear]", Font.ITALIC, 15));
		lblQuen.setBounds(329, 240, 141, 60);
		pnlLogin.add(lblQuen);
		
		JLabel lblAvatar = new JLabel();
		lblAvatar.setIcon(new ImageIcon(DangNhap.class.getResource("/avatar2.jpg")));
		lblAvatar.setBounds(322, 216, 120, 120);
		contentPane.add(lblAvatar);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setBounds(0, 0, 1426, 683);
		lblBackGround.setIcon(new ImageIcon(DangNhap.class.getResource("/background2 - Copy.jpg")));
		contentPane.add(lblBackGround);
		
		btnLogin.addActionListener(this);
		lblQuen.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(lblQuen)) {
			QuenMatKhau frame = new QuenMatKhau();
			frame.setVisible(true);
			
		}
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
		if (o.equals(btnLogin)) {
			String user = txtUser.getText();
			String pass = txtPass.getText();
			String maNV = "";
			if (!user.equals("")) {
				try {
					for (TaiKhoan x : tk_dao.getDsTaiKhoan() ) {
						if (x.getUserName().compareToIgnoreCase(user) == 0 && x.getPassword().compareToIgnoreCase(pass) == 0) {
							maNV = x.getUserName();
							new TrangChu(maNV).setVisible(true);
							dispose();
						} 
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if(maNV.equals(""))
				JOptionPane.showMessageDialog(contentPane, "Sai tài khoản hoặc mật khẩu!");
		}	
	}

	
	private void clearTextfield() {
		txtUser.setText("");
		txtPass.setText("");
		txtUser.requestFocus();
	}
}
