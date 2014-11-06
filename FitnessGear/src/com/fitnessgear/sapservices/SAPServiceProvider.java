package com.fitnessgear.sapservices;

import java.io.IOException;
import java.util.HashMap;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.accessory.SA;
import com.samsung.android.sdk.accessory.SAAgent;
import com.samsung.android.sdk.accessory.SAPeerAgent;
import com.samsung.android.sdk.accessory.SASocket;

public class SAPServiceProvider extends SAAgent {

	public final static String TAG = "SAPServiceProvider";

	public final static int SAP_SERVICE_CHANNEL_ID = 123;

	private final IBinder mIBinder = new LocalBinder();

	HashMap<Integer, SAPServiceProviderConnection> connectionMap = null;

	public SAPServiceProvider() {
		super(TAG, SAPServiceProviderConnection.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFindPeerAgentResponse(SAPeerAgent arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onServiceConnectionResponse(SASocket thisConnection,
			int result) {
		if (result == CONNECTION_SUCCESS) {
			if (thisConnection != null) {
				SAPServiceProviderConnection myConnection = (SAPServiceProviderConnection) thisConnection;

				if (connectionMap == null) {
					connectionMap = new HashMap<Integer, SAPServiceProviderConnection>();
				}

				myConnection.connectionID = (int) (System.currentTimeMillis() & 255);

				connectionMap.put(myConnection.connectionID, myConnection);
				
				Toast.makeText(getBaseContext(), "CONNECTION ESTABLISHED", Toast.LENGTH_LONG).show();
				
			}
		} else if (result == CONNECTION_ALREADY_EXIST) {
			Log.e(TAG, "onServiceConnectionResponse, CONNECTION_ALREADY_EXIST");
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mIBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		SA mAccessory = new SA();
        try {
        	mAccessory.initialize(this);
        } catch (SsdkUnsupportedException e) {
        	// Error Handling
        } catch (Exception e1) {
            e1.printStackTrace();
            stopSelf();
        }
		
	}

	@Override
	protected void onServiceConnectionRequested(SAPeerAgent peerAgent) {
		acceptServiceConnectionRequest(peerAgent);
	}

	public class LocalBinder extends Binder {
		public SAPServiceProvider getService() {
			return SAPServiceProvider.this;
		}
	}

	public class SAPServiceProviderConnection extends SASocket {

		private int connectionID;

		protected SAPServiceProviderConnection() {
			super(SAPServiceProviderConnection.class.getName());
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onError(int channelID, String errorString, int error) {
			Log.e(TAG, "ERROR: " + errorString + " | " + error);
		}

		@Override
		public void onReceive(int channelID, byte[] data) {
			// TODO Auto-generated method stub
			final String message = "";

			final SAPServiceProviderConnection uHandler = connectionMap
					.get(Integer.parseInt(String.valueOf(connectionID)));
			if (uHandler == null) {
				return;
			}
			new Thread(new Runnable() {
				public void run() {
					try {
						uHandler.send(SAP_SERVICE_CHANNEL_ID,
								message.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		@Override
		protected void onServiceConnectionLost(int arg0) {
			if (connectionMap != null) {
				connectionMap.remove(connectionID);
			}
		}

	}
}
