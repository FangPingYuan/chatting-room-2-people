
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class EchoServer extends Thread {
	public static void main(String[] args) {
		new EchoServer().start();
	}

	public void run() {
		byte[] buf = new byte[1024];
		int n = 0;
		ServerSocket svs = null;
		Socket s = null;
		try {

			svs = new ServerSocket(3001);
			s = svs.accept();
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			while (true) {
				n = in.read(buf);
				String message = new String(buf, 0, n);
//				message += " (Echo from Server)";
				out.write(message.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			svs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
