package runnables;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;

import android.os.Handler;
import android.widget.TextView;

public class ReceiveData implements Runnable {
	private Socket toServer;
	private HashMap aMap;
	private Handler myHandler = new Handler();
	private TextView responseView;
	
	public ReceiveData(Socket toServer, TextView responseView) {
		this.toServer = toServer;
		this.responseView = responseView;
	}
	
	@Override
	public void run() {
		try {
			final JSONInputStream inFromServer = 
					new JSONInputStream(toServer.getInputStream());
			this.aMap = (HashMap) inFromServer.readObject();
			final HashMap aDataMap = (HashMap) aMap.get("data");
			//update UI
			myHandler.post(new Runnable() {
				@Override
				public void run() {
					String message = aDataMap.get("message").toString();
					responseView.setText(message);		
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public HashMap getaMap() {
		return aMap;
	}

	public void setaMap(HashMap aMap) {
		this.aMap = aMap;
	}
}
