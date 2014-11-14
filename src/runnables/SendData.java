package runnables;

import java.io.IOException;
import java.net.Socket;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONOutputStream;

import androidsandbox.CommunicationBean;

public class SendData implements Runnable {
	private Socket toServer;
	private CommunicationBean aBean;
	
	public SendData(Socket toServer, CommunicationBean aBean) {
		this.toServer = toServer;
		this.aBean = aBean;
	}
	@Override
	public void run() {
		try {
		final JSONOutputStream outToServer =
				new JSONOutputStream(toServer.getOutputStream());
			outToServer.writeObject(aBean);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
