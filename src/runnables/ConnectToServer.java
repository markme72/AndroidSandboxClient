package runnables;

import java.io.IOException;
import java.net.Socket;

public class ConnectToServer implements Runnable {
	private Socket toServer;
	
	public ConnectToServer() {
	}
	
	@Override
	public void run() {
		try {
			toServer = new Socket("10.0.3.2", 9292);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getToServer() {
		return toServer;
	}

	public void setToServer(Socket toServer) {
		this.toServer = toServer;
	}
}
