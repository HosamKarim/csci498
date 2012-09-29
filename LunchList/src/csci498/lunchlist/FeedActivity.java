package csci498.lunchlist;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.util.Log;

public class FeedActivity extends ListActivity {
	
	public static class FeedTask extends AsyncTask<String, Void, Void> {
		
		private Exception e = null;
		private FeedActivity activity = null;
		
		FeedTask(FeedActivity activity) {
			attach(activity);
		}
		
		
		@Override
		protected Void doInBackground(String... urls) {
			try {
				Properties systemSettings = System.getProperties();
				systemSettings.put("http.proxyHost", "your.proxy.host.here");
				systemSettings.put("http.proxyPort", "8080");
				
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet getMethod = new HttpGet(urls[0]);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				String resposeBody = client.execute(getMethod, responseHandler);
				
				Log.d("FeedActivity", resposeBody);
				
			} catch (Exception e) {
				this.e = e;
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void unused) {
			if (e == null) {
				//ToDo
			}
			else {
				Log.e("LunchList", "Exception prasing feed", e);
				activity.goBlooey(e);	
			}
		}
	}
	
	private void goBlooey(Throwable t) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Exception!").setMessage(t.toString())
		.setPositiveButton("OK",null).show();
	}
	
}
