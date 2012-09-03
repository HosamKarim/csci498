package csci498.lunchlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class LunchList extends Activity {
	List <Restaurant> model = new ArrayList <Restaurant>();
	RestaurantAdapter adapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_list);
        
        Button save = (Button)findViewById(R.id.save);
        
        save.setOnClickListener(onSave);
        
        Spinner list = (Spinner)findViewById(R.id.restaurants);
        
        adapter = new RestaurantAdapter();
        
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.addr);
        
        list.setAdapter(adapter);
		
        /*RadioGroup rg = (RadioGroup)findViewById(R.id.types);
		for(int i=1;i<9;i++)
		{
			RadioButton rb = new RadioButton(getBaseContext());
			rb.setText("Test#"+i);
			rb.setTextColor(Color.rgb(5*i, 5*i, 5*i));
			rg.addView(rb);
		}*/
        
    }
    
    private View.OnClickListener onSave = new View.OnClickListener() {
    	public void onClick(View c){
    		Restaurant r = new Restaurant();
    		//model.add(r);
    		EditText name = (EditText)findViewById(R.id.name);
    		EditText address = (EditText)findViewById(R.id.addr);
    		
    		r.setName(name.getText().toString());
    		r.setAddress(address.getText().toString());
    		
    		RadioGroup types = (RadioGroup)findViewById(R.id.types);
    		
    		
    		
    		switch (types.getCheckedRadioButtonId()) {	
    			case R.id.sit_down:
    				r.setType("sit_down");
    				break;
    			
    			case R.id.take_out: 
    				r.setType("take_out"); 
    				break;
    			
    			case R.id.delivery: 
    				r.setType("delivery"); 
    				break;

    		}
    		adapter.add(r);
    	}
    	
    };
    
    class RestaurantAdapter extends ArrayAdapter<Restaurant>{
    	RestaurantAdapter(){
    		super(LunchList.this,android.R.layout.simple_list_item_1,model);
    	}
    		public View getView (int position, View convertView, ViewGroup parent){
    			View row = convertView;
    			
    			if(row==null){
    				LayoutInflater inflater = getLayoutInflater();
    				
    				row =  inflater.inflate(R.layout.row, null);
    			}
    			
    			Restaurant r = model.get(position);
    			((TextView)row.findViewById(R.id.title)).setText(r.getName());
    			((TextView)row.findViewById(R.id.address)).setText(r.getAddress());
    			
    			ImageView icon = (ImageView) row.findViewById(R.id.icon);
    			
    			if (r.getType().equals("sit_down")){
    				icon.setImageResource(R.drawable.ball_red);
    			}
    			else if (r.getType().equals("take_out")){
    				icon.setImageResource(R.drawable.ball_yellow);
    			} 
    			else{
    				icon.setImageResource(R.drawable.ball_green);
    			}
    			return (row);
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
    
    
		
	public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_lunch_list, menu);
        return true;
    }
}
