//server gui
import java.awt.EventQueue;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ServerGui {

	public JFrame frame;
	public JTextField tfIP;
	public JTextField tfPort2;
	public static String tfIPText;
	public static String tfPortText;
	JTextField tfMessage;
	JTextArea taBoard;
	ServerGui window;
	Client cs;
	public int send_flag2 = 0;

	ClientGui cg;

	public ServerGui() {

	}

	void setWindow(ServerGui window) {
		this.window = window;
		frame.setVisible(true);
	}

	void initialize() {

		frame = new JFrame();
		frame.setTitle("Two Talkers: SERVER");
		frame.setBounds(100, 100, 506, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Server IP");
		lblNewLabel.setBounds(10, 21, 65, 15);
		frame.getContentPane().add(lblNewLabel);

		tfIP = new JTextField();
		tfIP.setText("127.0.0.1");
		tfIP.setBounds(74, 18, 105, 21);
		frame.getContentPane().add(tfIP);
		tfIP.setColumns(10);
		tfIPText = tfIP.getText();

		JLabel lblNewLabel_1 = new JLabel("Server Port");
		lblNewLabel_1.setBounds(211, 21, 72, 15);
		frame.getContentPane().add(lblNewLabel_1);

		tfPort2 = new JTextField();
		tfPort2.setText("3001");
		tfPort2.setBounds(282, 18, 51, 21);
		frame.getContentPane().add(tfPort2);
		tfPort2.setColumns(10);
		tfPortText = tfPort2.getText();

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(369, 47, 87, 23);
		frame.getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});

		taBoard = new JTextArea();
		taBoard.setBounds(22, 71, 446, 247);
		frame.getContentPane().add(taBoard);

		tfMessage = new JTextField();
		tfMessage.setBounds(22, 342, 446, 21);
		frame.getContentPane().add(tfMessage);
		tfMessage.setColumns(10);

		tfMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {

					send_flag2 = 1;
				}
			}
		});
	}
}
