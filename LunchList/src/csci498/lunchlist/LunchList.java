package csci498.lunchlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LunchList extends Activity {
	List <Restaurant> model = new ArrayList <Restaurant>();
	ArrayAdapter <Restaurant> adapter = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_list);
        
        Button save = (Button)findViewById(R.id.save);
        
        save.setOnClickListener(onSave);
        
        Spinner list = (Spinner)findViewById(R.id.restaurants);
        
        adapter = new ArrayAdapter<Restaurant>(this,android.R.layout.simple_list_item_1,model);
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
    		r.setName("Foo");
    		model.add(r);
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
    
    
    
		
	public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_lunch_list, menu);
        return true;
    }
}
