package csci498.lunchlist;

import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;

public class OnAlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context ctxt, Intent arg1) {
		Intent i=new Intent(ctxt, AlarmActivity.class);
		
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		ctxt.startActivity(i);
		
	}

}
