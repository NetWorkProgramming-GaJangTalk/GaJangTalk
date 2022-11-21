// JavaChatClientView.java
// �������� ä�� â
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class MainScreen extends JFrame {
	private JPanel contentPane;
	private JTextField txtInput;
	private String UserName;
	private JButton btnSend;
	private static final  int BUF_LEN = 128; //  Windows ó�� BUF_LEN �� ����
	private Socket socket; // �������
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private JLabel lblUserName;
	//private JTextArea textArea;
	private JTextPane textArea;
	private ImageIcon imgIcon, newImgIcon;
	private Image img, updatedImg;
	
	/**
	 * Create the frame.
	 */
	public MainScreen(String username, String ip_addr, String port_no) {
		setTitle("<dynamite>");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 630);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		// �޴���
		JPanel panelLeftMenu = new JPanel();
		panelLeftMenu.setLayout(null);
		panelLeftMenu.setBounds(0,0,50,600);
		panelLeftMenu.setBackground(new Color(240, 240, 240));
		contentPane.add(panelLeftMenu);
		
		//ģ���� �̵� ��ư
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

		
		//ä���� �̵� ��ư
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
		
		
		
		//ģ����
		JPanel panelFriend = new JPanel();
		panelFriend.setLayout(null);
		panelFriend.setBounds(50,0,300,600);
		panelFriend.setBackground(Color.WHITE);
		contentPane.add(panelFriend);
		
		
		//ģ���Ǿ��� ���
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
		
		
		//ģ������ ����� �����
		JLabel lblFriend = new JLabel("\uCE5C\uAD6C");
		lblFriend.setSize(300, 65);
		lblFriend.setLocation(5, 0);
		panelFriendHeader.add(lblFriend);
		lblFriend.setBackground(new Color(255, 255, 255));
		lblFriend.setFont(new Font("����", Font.BOLD, 28));
		
		
		
		
		
		
		
		//ģ���� ��ũ��
	
		JScrollPane scrollPaneFriendList = new JScrollPane();
		scrollPaneFriendList.setBorder(null);
		scrollPaneFriendList.setLayout(null);
		scrollPaneFriendList.setSize(294, 535);
		scrollPaneFriendList.setLocation(0, 65);
		scrollPaneFriendList.setBackground(new Color(255, 255, 255));
		//scrollPaneFriendList.setBounds(0,65,140,465);
		panelFriend.add(scrollPaneFriendList);
		
		for(int i = 0; i<10 ; i++) {
			Friend view = new Friend(username);
			scrollPaneFriendList.add(view);
		}
		
		//ģ���� ��ũ���� �ؽ�Ʈ
		JTextPane textPaneFriendList = new JTextPane();
		scrollPaneFriendList.add(textPaneFriendList);
		
		//ä����
		JPanel panelChat = new JPanel();
		panelChat.setBackground(new Color(255, 255, 255));
		panelChat.setBounds(50,0,300,600);
		panelChat.setLayout(null);
		panelChat.setBorder(null);
		contentPane.add(panelChat);
		
		//ä���� ���� ���
		JPanel panelChatHeader = new JPanel();
		panelChatHeader.setSize(300, 65);
		panelChatHeader.setBackground(new Color(255, 255, 255));
		panelChat.add(panelChatHeader);
		panelChatHeader.setLayout(null);
		
		//ä������ ����� �����
		JLabel lblChat = new JLabel("\uCC44\uD305");
		lblChat.setLocation(5, 0);
		lblChat.setSize(300, 65);
		panelChatHeader.add(lblChat);
		lblChat.setFont(new Font("����", Font.BOLD, 28));
		lblChat.setBackground(Color.WHITE);
		
		
		//ä������ ����� ��ư
		JButton btnMakeRoom = new JButton("");
		btnMakeRoom.setBounds(240, 20, 20, 20);
		panelChatHeader.add(btnMakeRoom);
		btnMakeRoom.setFont(new Font("����", Font.PLAIN, 14));
		imgIcon = new ImageIcon("src/plus_black.png");
		img = imgIcon.getImage();
		img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		imgIcon = new ImageIcon(img);
		btnMakeRoom.setIcon(imgIcon);
		btnMakeRoom.setHorizontalAlignment(JLabel.CENTER);
		btnMakeRoom.setBorderPainted(false);
		btnMakeRoom.setContentAreaFilled(false);
		btnMakeRoom.setFocusPainted(false);
		
		
				
		
		
		//ä���� ��ũ��
		JScrollPane scrollPaneChatList = new JScrollPane();
		scrollPaneChatList.setBorder(null);
		scrollPaneChatList.setLayout(null);
		scrollPaneChatList.setLocation(0, 65);
		scrollPaneChatList.setSize(294, 535);
		scrollPaneChatList.setBackground(new Color(255, 255, 255));
		panelChat.add(scrollPaneChatList);
		
		//ä������ ��ũ���� �ؽ�Ʈ
		JTextPane textPaneChatList = new JTextPane();
		scrollPaneChatList.add(textPaneChatList);

		
		
		this.addWindowListener((WindowListener) new WindowAdapter(){
            public void windowClosing(WindowEvent e) { 
            	ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
                System.exit(0);
            }
    });
		
		
		//��ưŬ��(FriendList)
		btnFriendList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFriendList.setEnabled(false);
				btnChatList.setEnabled(true);
				panelFriend.setVisible(true);
				panelChat.setVisible(false);
			}
		});
		//��ưŬ��(ChatList)
		btnChatList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFriendList.setEnabled(true);
				btnChatList.setEnabled(false);
				panelFriend.setVisible(false);
				panelChat.setVisible(true);
			}
		});
		
		//��ưŬ��(MakeRoom)
				btnMakeRoom.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MakeRoom view = new MakeRoom(username, ip_addr, port_no);
						//setVisible(false);
					}
				});
		
		setVisible(true);
		
		
	
		//AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		//lblUserName.setText(username+">");
		
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			
			//SendMessage("/login " + UserName);
			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);
			
			ListenNetwork net = new ListenNetwork();
			net.start();
			Myaction action = new Myaction();
			//btnSend.addActionListener(action); // ����Ŭ������ �׼� �����ʸ� ��ӹ��� Ŭ������
			//txtInput.addActionListener(action);
			//txtInput.requestFocus();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}

	}
	// Server Message�� �����ؼ� ȭ�鿡 ǥ��
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {
					// String msg = dis.readUTF();
					byte[] b = new byte[BUF_LEN];
					int ret;
					ret = dis.read(b);
					if (ret < 0) {
						AppendText("dis.read() < 0 error");
						try {
							dos.close();
							dis.close();
							socket.close();
							break;
						} catch (Exception ee) {
							break;
						}// catch�� ��
					}
					String	msg = new String(b, "euc-kr");
					msg = msg.trim(); // �յ� blank NULL, \n ��� ����
//					AppendText(msg); // server ȭ�鿡 ���
				} catch (IOException e) {
					AppendText("dis.read() error");
					try {
						dos.close();
						dis.close();
						socket.close();
						break;
					} catch (Exception ee) {
						break;
					} // catch�� ��
				} // �ٱ� catch����
				
			}
		}
	}
	// keyboard enter key ġ�� ������ ����
	class Myaction implements ActionListener // ����Ŭ������ �׼� �̺�Ʈ ó�� Ŭ����
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// Send button�� �����ų� �޽��� �Է��ϰ� Enter key ġ��
			if (e.getSource() == btnSend || e.getSource() == txtInput) {
				String msg = null;
				msg = String.format("[%s] %s\n", UserName, txtInput.getText());
				SendMessage(msg);
				txtInput.setText(""); // �޼����� ������ ���� �޼��� ����â�� ����.
				txtInput.requestFocus(); // �޼����� ������ Ŀ���� �ٽ� �ؽ�Ʈ �ʵ�� ��ġ��Ų��
				if (msg.contains("/exit")) // ���� ó��
					/*
					 * ChatMsg exitmsg = new ChatMsg(UserName, "400", "Bye"); SendObject(msg);
					 */
					System.exit(0);
			}
		}
	}
	ImageIcon icon1 = new ImageIcon("src/icon1.jpg");
	public void AppendIcon(ImageIcon icon) {
//		int len = textArea.getDocument().getLength();
//		textArea.setCaretPosition(len); // place caret at the end (with no selection)
//		textArea.insertIcon(icon);	
	}
	// ȭ�鿡 ���
	public void AppendText(String msg) {
		//textArea.append(msg + "\n");
//		AppendIcon(icon1);
//		int len = textArea.getDocument().getLength(); // same value as
//        textArea.setCaretPosition(len); // place caret at the end (with no selection)
// 		textArea.replaceSelection(msg + "\n"); // there is no selection, so inserts at caret
 	}

	// Windows ó�� message ������ ������ �κ��� NULL �� ����� ���� �Լ�
	public byte[] MakePacket(String msg) {
		byte[] packet = new byte[BUF_LEN];
		byte[] bb = null;
		int i;
		for (i = 0; i < BUF_LEN; i++)
			packet[i] = 0;
		try {
			bb = msg.getBytes("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}

	// Server���� network���� ����
	public void SendMessage(String msg) {
		try {
			// dos.writeUTF(msg);
			byte[] bb;
			bb = MakePacket(msg);
			dos.write(bb, 0, bb.length);
		} catch (IOException e) {
			AppendText("dos.write() error");
			try {
				dos.close();
				dis.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
		}
	}
	public void SendObject(Object ob) { // ������ �޼����� ������ �޼ҵ�
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			// textArea.append("�޼��� �۽� ����!!\n");
			AppendText("SendObject Error");
		}
	}
}
