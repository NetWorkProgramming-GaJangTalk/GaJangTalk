	import java.awt.EventQueue;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	
	import java.awt.*;
	import javax.swing.*;
	
	import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.OutputStream;
	import java.io.UnsupportedEncodingException;
	import java.net.Socket;
	 
	public class MainScreen extends JFrame {
		private JPanel contentPane;
		private JTextField txtInput;
		private String UserName;
		private static final  int BUF_LEN = 128; //  Windows 처럼 BUF_LEN 을 정의
		private Socket socket; // 연결소켓
		private InputStream is;
		private OutputStream os;
		private DataInputStream dis;
		private DataOutputStream dos;
		private JLabel lblUserName;
		private ImageIcon imgIcon, newImgIcon;
		private Image img, updatedImg;
	 
	    public MainScreen(String username, String ip_addr, String port_no) {
	    	
	    	setTitle("<dynamite>");
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 357, 630);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			// 메뉴바
			JPanel panelLeftMenu = new JPanel();
			panelLeftMenu.setLayout(null);
			panelLeftMenu.setBounds(0,0,50,600);
			panelLeftMenu.setBackground(Color.LIGHT_GRAY);
			contentPane.add(panelLeftMenu);
			
			//친구탭 이동 버튼
			JButton btnFriendList = new JButton();
			btnFriendList.setText("");
			imgIcon = new ImageIcon("src/profile.png");
			img = imgIcon.getImage();
			img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
			btnFriendList.setIcon(imgIcon);
			btnFriendList.setBounds(8,20,36,36);
			btnFriendList.setHorizontalAlignment(JLabel.CENTER);
			btnFriendList.setBorderPainted(false);
			btnFriendList.setContentAreaFilled(false);
			btnFriendList.setFocusPainted(false);
			panelLeftMenu.add(btnFriendList);
			btnFriendList.setEnabled(false);
	
			
			//채팅탭 이동 버튼
			JButton btnChatList = new JButton();
			btnChatList.setText("");
			imgIcon = new ImageIcon("src/chat.png");
			img = imgIcon.getImage();
			img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			imgIcon = new ImageIcon(img);
			btnChatList.setIcon(imgIcon);
			btnChatList.setBounds(8,80,36,36);
			btnChatList.setHorizontalAlignment(JLabel.CENTER);
			btnChatList.setBorderPainted(false);
			btnChatList.setContentAreaFilled(false);
			btnChatList.setFocusPainted(false);
			panelLeftMenu.add(btnChatList);
			
			
			
			//친구탭
			JPanel panelFriend = new JPanel();
			panelFriend.setLayout(null);
			panelFriend.setBounds(50,0,300,600);
			panelFriend.setBackground(Color.WHITE);
			contentPane.add(panelFriend);
			
			
			//친구탭안의 헤더
			JPanel panelFriendHeader = new JPanel();
			panelFriendHeader.setBackground(new Color(255, 255, 255));
			panelFriendHeader.setBounds(0,0,300,65);
			panelFriendHeader.setLayout(null);
			panelFriendHeader.setBorder(null);
			panelFriend.add(panelFriendHeader);
			
			/*
			 * imgIcon = new ImageIcon("src/user.png"); img = imgIcon.getImage(); img =
			 * img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); imgIcon = new
			 * ImageIcon(img);
			 */
			
			
			//친구탭의 헤더의 제목글
			JLabel lblFriend = new JLabel("\uCE5C\uAD6C");
			lblFriend.setSize(300, 65);
			lblFriend.setLocation(5, 0);
			panelFriendHeader.add(lblFriend);
			lblFriend.setBackground(new Color(255, 255, 255));
			lblFriend.setFont(new Font("굴림", Font.BOLD, 22));
			
			//친구탭 스크롤
		
			JScrollPane scrollPaneFriendList = new JScrollPane();
			scrollPaneFriendList.setSize(294, 535);
			scrollPaneFriendList.setLocation(0, 65);
			scrollPaneFriendList.setBackground(new Color(255, 255, 255));
			//scrollPaneFriendList.setBounds(0,65,140,465);
			panelFriend.add(scrollPaneFriendList);
			
			//친구탭 스크롤의 텍스트
			JTextPane textPaneFriendList = new JTextPane();
			scrollPaneFriendList.add(textPaneFriendList);
			
			//채팅탭
			JPanel panelChat = new JPanel();
			panelChat.setBackground(new Color(255, 255, 255));
			panelChat.setBounds(50,0,300,600);
			panelChat.setLayout(null);
			panelChat.setBorder(null);
			contentPane.add(panelChat);
			
			//채팅탭 안의 헤더
			JPanel panelChatHeader = new JPanel();
			panelChatHeader.setSize(300, 65);
			panelChatHeader.setBackground(new Color(255, 255, 255));
			panelChat.add(panelChatHeader);
			panelChatHeader.setLayout(null);
			
			//채팅탭의 헤더의 제목글
			JLabel lblChat = new JLabel("\uCC44\uD305");
			lblChat.setLocation(5, 0);
			lblChat.setSize(300, 65);
			panelChatHeader.add(lblChat);
			lblChat.setFont(new Font("굴림", Font.BOLD, 22));
			lblChat.setBackground(Color.WHITE);
			
			
			//채팅탭 스크롤
			JScrollPane scrollPaneChatList = new JScrollPane();
			scrollPaneChatList.setLocation(0, 65);
			scrollPaneChatList.setSize(294, 535);
			scrollPaneFriendList.setBackground(new Color(255, 255, 255));
			panelChat.add(scrollPaneChatList);
			
			//채팅탭의 스크롤의 텍스트
			JTextPane textPaneChatList = new JTextPane();
			scrollPaneChatList.add(textPaneChatList);

			
			
			
			
			
			//버튼클릭(FriendList)
			btnFriendList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnFriendList.setEnabled(false);
					btnChatList.setEnabled(true);
					panelFriend.setVisible(true);
					panelChat.setVisible(false);
				}
			});
			//버튼클릭(ChatList)
			btnChatList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnFriendList.setEnabled(true);
					btnChatList.setEnabled(false);
					panelFriend.setVisible(false);
					panelChat.setVisible(true);
				}
			});
			
			setVisible(true);
			
			
	    }
	}
