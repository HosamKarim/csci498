package csci498.lunchlist;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DetailForm extends Activity { 
	EditText name = null;
	EditText address = null;
	EditText notes = null;
	RadioGroup types = null;
	RestaurantHelper helper = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.detail_form);
		
		helper = new RestaurantHelper(this);
		name = (EditText)findViewById(R.id.name); 
		address = (EditText)findViewById(R.id.addr); 
		notes = (EditText)findViewById(R.id.notes); 
		types = (RadioGroup)findViewById(R.id.types);
		
		Button save = (Button)findViewById(R.id.save); 
		save.setOnClickListener(onSave);
		}
	private View.OnClickListener onSave = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String type = null;
			
			switch (types.getCheckedRadioButtonId()){
				case R.id.sit_down:
					type = "sit_down";
				case R.id.take_out:
					type = "take_out";
				case R.id.delivery:
					type = "delivery";
			}
		}
	};
	
	}