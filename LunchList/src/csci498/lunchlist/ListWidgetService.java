package csci498.lunchlist;

import android.annotation.TargetApi;
import android.content.Intent;
import android.widget.ListView;
import android.widget.RemoteViewsService;

@TargetApi(11)
public class ListWidgetService extends RemoteViewsService {

	@TargetApi(11)
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return (new ListViewsFactory(this.getApplicationContext(),intent));
	}

}
