package csci498.lunchlist;

import java.util.ArrayList;
import java.util.List;

//import android.app.Activity;
//import android.view.View.OnClickListener;
//import android.graphics.Color;
//import android.app.AlertDialog;
//import android.widget.Spinner;
//import android.widget.RadioButton;
import android.app.TabActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TabHost;
import android.widget.AdapterView;

public class LunchList extends TabActivity {
	List <Restaurant> model = new ArrayList <Restaurant>();
	RestaurantAdapter adapter = null;
	EditText name = null;
	EditText address = null;
	EditText notes = null;
	DatePicker dPicker = null;
	RadioGroup types = null;
	Restaurant current = null;
	int progress;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_lunch_list);
        
        name = (EditText)findViewById(R.id.name);
        address = (EditText)findViewById(R.id.addr);
        types = (RadioGroup)findViewById(R.id.types);
        notes = (EditText)findViewById(R.id.notes);
        dPicker = (DatePicker)findViewById(R.id.date);
        
        Button save = (Button)findViewById(R.id.save);
        
        save.setOnClickListener(onSave);
        
        ListView list = (ListView)findViewById(R.id.restaurants);
        
        adapter = new RestaurantAdapter();
        
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.addr);
        
        list.setAdapter(adapter);
        
        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List", getResources().getDrawable(R.drawable.list));
        
        getTabHost().addTab(spec);
        
        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details", getResources().getDrawable(R.drawable.restaurant));
        
        getTabHost().addTab(spec);
        
        getTabHost().setCurrentTab(0);
        
        list.setOnItemClickListener(onListClick);
    }
    
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
    	public void onItemClick (AdapterView<?> parent,View view, int position, long id){
    		 current = model.get(position);
    		
    		name.setText(current.getName());
    		address.setText(current.getAddress());
    		notes.setText(current.getNotes());
    		dPicker.updateDate(current.getYear(), current.getMonth(), current.getDay());
    		
    		if(current.getType().equals("sit_down")){
    			types.check(R.id.sit_down);
    		}
    		else if (current.getType().equals("take_out")){
    			types.check(R.id.take_out);
    		}
    		else {
    			types.check(R.id.delivery);
    		}
    		
    		getTabHost().setCurrentTab(1);
    	}
	};
    
    private View.OnClickListener onSave = new View.OnClickListener() {
    	public void onClick(View c){
    		 current = new Restaurant();
    		
    		EditText name = (EditText)findViewById(R.id.name);
    		EditText address = (EditText)findViewById(R.id.addr);
    		EditText notes = (EditText)findViewById(R.id.notes);
    		DatePicker dPicker = (DatePicker)findViewById(R.id.date);
    		
    		current.setDay(dPicker.getDayOfMonth());
    		current.setMonth(dPicker.getMonth());
    		current.setYear(dPicker.getYear());
    		current.setName(name.getText().toString());
    		current.setAddress(address.getText().toString());
    		current.setNotes(notes.getText().toString());
    		
    		RadioGroup types = (RadioGroup)findViewById(R.id.types);
    		
    		switch (types.getCheckedRadioButtonId()) {	
    			case R.id.sit_down:
    				current.setType("sit_down");
    				break;
    			
    			case R.id.take_out: 
    				current.setType("take_out"); 
    				break;
    			
    			case R.id.delivery: 
    				current.setType("delivery"); 
    				break;

    		}
    		adapter.add(current);
    	}
    	
    };
    
    class RestaurantAdapter extends ArrayAdapter<Restaurant>{
    	RestaurantAdapter(){
    		super(LunchList.this,android.R.layout.simple_list_item_1,model);
    	}
    	public View getView(int position, View convertView, ViewGroup parent) {
    		View row=convertView;
    		RestaurantHolder holder=null;
    		
    		if (row==null) {
    			LayoutInflater inflater=getLayoutInflater();
    			row=inflater.inflate(R.layout.row, parent, false); 
    			holder=new RestaurantHolder(row); 
    			row.setTag(holder);
    		}
    		else {
    			holder=(RestaurantHolder)row.getTag(); 
    		}
    		
    		holder.populateFrom(model.get(position)); 
    			
    		return(row);
    		}
    	
    }
    
    static class RestaurantHolder {
    	private TextView name = null;
    	private TextView address = null;
    	private ImageView icon = null;
    	
    	RestaurantHolder(View row){
    		name = (TextView) row.findViewById(R.id.title);
    		address = (TextView) row.findViewById(R.id.address);
    		icon = (ImageView) row.findViewById(R.id.icon);
    	}
    	
    	void populateFrom (Restaurant r){
    		name.setText(r.getName());
    		address.setText(r.getAddress());
    	
    		if (r.getType().equals("sit_down")){
    			icon.setImageResource(R.drawable.ball_red);
    		}
    		else if (r.getType().equals("take_out")) { 
    			icon.setImageResource(R.drawable.ball_yellow);
    		}
    		else {
    			icon.setImageResource(R.drawable.ball_green); 
    		}
    	}
    	
    }
    
    private void doSomeLongWork(final int incr){
    	runOnUiThread(new Runnable(){
    		public void run() {
    			progress+=incr;
    			setProgress(progress);
    		}
    	});
    	
    	SystemClock.sleep(250);
    }
    
    private Runnable longTask = new Runnable() {
    	public void run() {
    		for (int i=0;i<20;i++) {
    			doSomeLongWork(500);
    		}
    		
    		runOnUiThread(new Runnable() { 
    			public void run() {
    				setProgressBarVisibility(false); 
    			}
    		});
    	}
    };
		
	public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	new MenuInflater(this).inflate(R.menu.option, menu); 
    	return(super.onCreateOptionsMenu(menu));
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if (item.getItemId()==R.id.toast) {
    		String message="No restaurant selected";
    		
    		if (current!=null) { 
    			message=current.getNotes();
    		}
    	
    		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    	
    		return(true);
    	}
    	else if (item.getItemId()==R.id.run){
    		setProgressBarVisibility(true);
    		progress=0;
    		new Thread(longTask).start();
    		return(true);
    	}
    	
    	else if (item.getItemId()==R.id.change){
    		if(getTabHost().getCurrentTabTag().equals("tag1"))
    			getTabHost().setCurrentTab(1);
    		else if(getTabHost().getCurrentTabTag().equals("tag2"))
    			getTabHost().setCurrentTab(0);
    		
    	}
    	
    	return(super.onOptionsItemSelected(item)); }
}
