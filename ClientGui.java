import java.awt.EventQueue;

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

public class ClientGui {

	private JFrame frame;
	private JTextField tfIP;
	private JTextField tfPort;
	JTextField tfMessage;
	JTextArea taBoard;
	ClientGui window;
	Client cs;
	ServerGui cg2;


	
	int isConnect = 1;

	public ClientGui() {

	}

	void setWindow(ClientGui window) {
		this.window = window;
		frame.setVisible(true);
		
	}

	void initialize() {
		
		ServerGui cg2 = new ServerGui();
		frame = new JFrame();
		frame.setTitle("Two Talkers: CLIENT");
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

		JLabel lblNewLabel_1 = new JLabel("Server Port");
		lblNewLabel_1.setBounds(211, 21, 72, 15);
		frame.getContentPane().add(lblNewLabel_1);

		tfPort = new JTextField();
		tfPort.setText("3001");
		tfPort.setBounds(282, 18, 51, 21);
		frame.getContentPane().add(tfPort);
		tfPort.setColumns(10);


		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isConnect = 0;
				Socket s = null;
//				Socket s2 = null;
				try {
					s = new Socket(tfIP.getText(), Integer.parseInt(tfPort
							.getText()));
//					s2 = new Socket(cg2.tfIPText, Integer.parseInt(cg2.tfPortText));
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				cs = new Client(window,cg2.window, s);
				cs.start();
				
//				c2 = new Client2(cg2.window, s2);
//				c2.start();
			}
		});

		btnConnect.setBounds(369, 17, 87, 23);
		frame.getContentPane().add(btnConnect);
		
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
				if (e.getKeyCode() == e.VK_ENTER)
					cs.send_flag = 1;
			}
		});
		
	}
}
