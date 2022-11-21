import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FriendLabel extends JPanel {
		public FriendLabel() {
		setBackground(new Color(128, 128, 192));
		setBounds(0, 0, 170, 60);
		
		JButton btnIcon = new JButton("New button");
		btnIcon.setText("");
		btnIcon.setLayout(null);
		btnIcon.setBounds(0, 0, 170, 60);
		add(btnIcon);
		
		JLabel lblUserName = new JLabel("New label");
		lblUserName.setBackground(new Color(255, 255, 255));
		lblUserName.setFont(new Font("±¼¸²Ã¼", Font.PLAIN, 12));
		lblUserName.setText("<dynamite>");
		lblUserName.setLayout(null);
		lblUserName.setBounds(0, 0, 170, 60);
		add(lblUserName);
		
		JLabel lblTime = new JLabel("New label");
		lblTime.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 9));
		lblTime.setBackground(new Color(255, 255, 255));
		lblTime.setText(null);
		lblTime.setLayout(null);
		lblTime.setBounds(48, 25, 69, 17);
		add(lblTime);
	}
	
}
