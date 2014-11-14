package androidsandbox;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.androidsandbox.R;

import runnables.ConnectToServer;
import runnables.ReceiveData;
import runnables.SendData;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	//we only want to connect once so this variable is set outside all other methods so they can
	//use it once it's set
	private ConnectToServer toServer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//user taps login button
		Button loginButton = (Button)this.findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//connect to the server
				new Thread(toServer = new ConnectToServer()).start();
				
				//change to login screen
				setContentView(R.layout.activity_login);
				
				//login to server
				login();
			}
		});
		
		Button newAccountButton = (Button)this.findViewById(R.id.newAccountButton);
		newAccountButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//connect to the server
				new Thread(toServer = new ConnectToServer()).start();
				
				//change to new account screen
				setContentView(R.layout.activity_new_account);
				
				//create new account
				newAccount();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void login() {
		Button goButton = (Button)this.findViewById(R.id.goButton);
		goButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText uname = (EditText)findViewById(R.id.uname);
				final EditText pword = (EditText)findViewById(R.id.pword);
				final TextView responseView = (TextView)findViewById(R.id.responseView);
				//prepare the bean
				HashMap aDataMap = new HashMap();
				aDataMap.put("uname", uname.getText().toString());
				aDataMap.put("pword", pword.getText().toString());
				CommunicationBean aBean = new CommunicationBean();
				aBean.setCommand("login");
				aBean.setData(aDataMap);
				
				//send the data
				new Thread(new SendData(toServer.getToServer(), aBean)).start();
				
				//receive the data
				new Thread(new ReceiveData(toServer.getToServer(), responseView)).start();
			}
		});
	}
	
	public void newAccount() {
		Button createButton = (Button)this.findViewById(R.id.createButton);
		createButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final TextView responseView = (TextView)findViewById(R.id.responseView);
		
				//the numbers to the right of the variable represent their placement in aDataList
				final EditText firstName = (EditText)findViewById(R.id.firstName); //0
				final EditText middleInitial = (EditText)findViewById(R.id.middleInitial); //1
				final EditText lastName = (EditText)findViewById(R.id.lastName); //2
				final EditText uname = (EditText)findViewById(R.id.uname); //3
				final EditText pword = (EditText)findViewById(R.id.pword); //4
				final EditText email = (EditText)findViewById(R.id.email); //5
				final EditText street = (EditText)findViewById(R.id.street); //6
				final EditText street2 = (EditText)findViewById(R.id.street2); //7
				final EditText city = (EditText)findViewById(R.id.city); //8
				final EditText state = (EditText)findViewById(R.id.state); //9
				final EditText ZIP = (EditText)findViewById(R.id.ZIP); //10
				final EditText country = (EditText)findViewById(R.id.country); //11
				final EditText phoneNumber = (EditText)findViewById(R.id.phoneNumber); //12
		
				//prepare the bean
				HashMap aDataMap = new HashMap();
				aDataMap.put("firstName", firstName.getText().toString());
				aDataMap.put("middleInitial", middleInitial.getText().toString());
				aDataMap.put("lastName", lastName.getText().toString());
				aDataMap.put("uname", uname.getText().toString());
				aDataMap.put("pword", pword.getText().toString());
				aDataMap.put("email", email.getText().toString());
				aDataMap.put("street", street.getText().toString());
				aDataMap.put("street2", street2.getText().toString());
				aDataMap.put("city", city.getText().toString());
				aDataMap.put("state", state.getText().toString());
				aDataMap.put("zip", ZIP.getText().toString());
				aDataMap.put("country", country.getText().toString());
				aDataMap.put("phoneNumber", phoneNumber.getText().toString());
		
				CommunicationBean aBean = new CommunicationBean();
				aBean.setCommand("newUser");
				aBean.setData(aDataMap);
				
				//send the data
				new Thread (new SendData(toServer.getToServer(), aBean)).start();
		
				//receive the data
				new Thread(new ReceiveData(toServer.getToServer(), responseView)).start();
			}
		});
	}
}
