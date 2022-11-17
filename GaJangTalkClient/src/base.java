
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class base extends JFrame {
	private static JPanel rightPanel = new JPanel(); // ������ �г�
// ���� ȭ��, �ٸ� �޼ҵ忡�� �۾��� �� ȭ�鳻���� �ٲٱ� ������ ��������� ����
	
	public base(String username, String ip_addr, String port_no) {
		JFrame frame = new JFrame();
		frame.setSize(460,750);//�������� ũ��
		frame.setLocation(500, 300);
		frame.setPreferredSize(new Dimension(500, 300));
		Container test = frame.getContentPane();
		JPanel viewPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		test.add(viewPanel);
		
		viewPanel.setLayout(new BorderLayout());
		viewPanel.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // ����(����) �г��� ���̾ƿ� ����
		//ActionListener listener = (ActionListener) new base(username, ip_addr, port_no); // ��Ŭ�������� �۾��� �߱⿡ �׼Ǹ����ʸ� �� Ŭ������
		for (int i = 1; i < 4; i++) { // �ܼ� ��ư ����� �׼� �߰�
			JButton btn = new JButton(i + "��° �г� ȣ��");
			leftPanel.add(btn);
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		firstRight(); // �ʱ�ȭ��
		viewPanel.add(rightPanel);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	static void firstRight() { // �ʱ� �� ù��° �г��� ȭ��
		rightPanel.setBackground(Color.getHSBColor(200, 100, 200));
		//"#A9D0F5"
		rightPanel.add(new JLabel("ù��° �г�"));
	}

	static void secondRight() { // �ι�° �г��� ȭ��
		rightPanel.add(new JLabel("�ι�° �г�"));
	}

	static void thirdRight() { // ����° �г��� ȭ��
		
		rightPanel.add(new JLabel("����° �г�"));
		JPanel page1=new JPanel() {
	        Image background=new ImageIcon(StartScreen.class.getResource("main_color.png")).getImage();
	            public void paint(Graphics g) {//�׸��� �Լ�
	                g.drawImage(background, 0, 0, null);//background�� �׷���
	            }
	        };
	        //setSize(460,750);//�������� ũ��
	        //setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
	        page1.setLayout(null); //���̾ƿ� �����ϱ� ����.
	        page1.setBounds(0, 0, 1400, 1600);//�г��� ��ġ�� ũ��.
	        //add(page1);
	}

	public void actionPerformed(ActionEvent e) { // �׼� ������ ����
		String btnVal = e.getActionCommand(); // ��ư�� �ޱ�
		rightPanel.removeAll(); // ������ �г� �����
		if (btnVal.equals("1��° �г� ȣ��")) {
			firstRight(); // ������ �г� ȭ�� �ٽ� �߰�
		} else if (btnVal.equals("2��° �г� ȣ��")) {
			secondRight();
		} else {
			thirdRight();
		}
		rightPanel.updateUI(); // �г� ȭ�� ������Ʈ

//		  �гκ��� ���� ����, �� �����̳ʿ����� updateUI�� �������� �ʴ´�.
//		  �߰� �� setVisible(b) �� ���� ���ٰ�, �ٽ� ���ִ� ������� �ؾ��Ѵ�.
//		 ex)
//		 contentPane.removeAll();
//		 noticeWritePanel();
//		 contentPane.setVisible(false);
//		 contentPane.setVisible(true);

		}
	
	
	}