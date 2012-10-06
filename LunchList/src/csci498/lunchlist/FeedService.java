package csci498.lunchlist;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSReader;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class FeedService extends IntentService {
	public static final String EXTRA_URL = "csci498.lunchlist.EXTRA_URL";
	
	public FeedService() {
		super("FeedService");
	}

	@Override
	protected void onHandleIntent(Intent i) {
		RSSReader reader = new RSSReader();
		
		try {
			RSSFeed result = reader.load(i.getStringExtra(EXTRA_URL));
		} catch (Exception e) {
			Log.e(getString(R.string.app_name),"Exception parsing feed",e);
		}
	}
}
