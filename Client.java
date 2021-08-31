import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class Client extends Thread {
	ClientGui gui;
	ServerGui gui2;
	Socket s;

	boolean send = true;
	int send_flag = 0;
	int send_flag2 = 0;

	Client(ClientGui gui, ServerGui gui2, Socket s) {
		super();
		this.gui = gui;
		this.gui2 = gui2;
		this.s = s;
		
	}
	
	

	public void run() {
		ServerGui gui2 = new ServerGui();//按connect後出現第二個視窗server
		gui2.initialize();
		gui2.setWindow(gui2);
		
		OutputStream out;
		InputStream in;
		
		try {
			out = s.getOutputStream();
			in = s.getInputStream();

			int n;
			byte[] buf = new byte[1024];

			while (true) {
				System.out.print("");//一定要寫，算是傳遞前的準備，不寫傳不出去
				if (send_flag == 1) {
					String message = gui.tfMessage.getText();
					gui.taBoard.append("Client: " + message + "\n");
					out.write(message.getBytes());
					n = in.read(buf);
					String returnedMessage = new String(buf, 0, n);
					gui2.taBoard.append("Client: " + returnedMessage + "\n");//呈現於Server的gui
					send_flag = 0;
					gui.tfMessage.setText("");
				}
				if (gui2.send_flag2 == 1) {//當server輸入完按enter後
					String message2 = gui2.tfMessage.getText();
					gui2.taBoard.append("Server: " + message2 + "\n");
					out.write(message2.getBytes());
					n = in.read(buf);		
					String returnedMessage = new String(buf, 0, n);
					gui.taBoard.append("Server: " + returnedMessage + "\n");
					gui2.send_flag2 = 0;
					gui2.tfMessage.setText("");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
