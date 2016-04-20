package com.example.mycontactlist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;

public class ContactSettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_settings);


		
		initSettings();
		initSortByClick();
		initSortOrderClick();
		initListButton();
		initMapButton();
		initSettingsButton();
		initColorScrollView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_settings, menu);
		return true;
	}

	private void initSettings() {
		String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield", "contactname");
		String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder", "ASC");
		String colorOrder=getSharedPreferences("MyContactListPreferences",Context.MODE_PRIVATE).getString("colorpicker","green");

		RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
		RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
		RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthday);
		RadioButton rbGreen = (RadioButton) findViewById(R.id.radioGreen);
		RadioButton rbYellow = (RadioButton) findViewById(R.id.radioYellow);
		RadioButton rbTan= (RadioButton) findViewById(R.id.radioTan);

		ScrollView scrollView=(ScrollView) findViewById(R.id.scrollView2);


		if (sortBy.equalsIgnoreCase("contactname")) {
			rbName.setChecked(true);

		}
		else if (sortBy.equalsIgnoreCase("city")) {
			rbCity.setChecked(true);
		}
		else {
			rbBirthDay.setChecked(true);
		}			

		RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
		RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
		if (sortOrder.equalsIgnoreCase("ASC")) {
			rbAscending.setChecked(true);

		}
		else {
			rbDescending.setChecked(true);
		}
		if(colorOrder.equalsIgnoreCase("green")){
			rbGreen.setChecked(true);
			scrollView.setBackgroundColor(getResources().getColor(R.color.green_background));



		}
		else if(colorOrder.equalsIgnoreCase("tan")){
			scrollView.setBackgroundColor(getResources().getColor(R.color.tan_background));
			rbTan.setChecked(true);
		}
		else{
			scrollView.setBackgroundColor(getResources().getColor(R.color.yellow_background));
			rbYellow.setChecked(true);
		}
	}
	
	private void initSortByClick() {
		RadioGroup rgSortBy = (RadioGroup) findViewById(R.id.radioGroup1);
		rgSortBy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbName = (RadioButton) findViewById(R.id.radioName);
				RadioButton rbCity = (RadioButton) findViewById(R.id.radioCity);
//				RadioButton rbBirthDay = (RadioButton) findViewById(R.id.radioBirthdate);
				if (rbName.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "contactname").commit();
				}
				else if (rbCity.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "city").commit();
				}
				else {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortfield", "birthday").commit();
				}			
			}		
		});
	}

	private void initSortOrderClick() {
		RadioGroup rgSortOrder = (RadioGroup) findViewById(R.id.radioGroup2);
		rgSortOrder.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				RadioButton rbAscending = (RadioButton) findViewById(R.id.radioAscending);
//				RadioButton rbDescending = (RadioButton) findViewById(R.id.radioDescending);
				if (rbAscending.isChecked()) {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").commit();
				}
				else {
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").commit();
				}			
			}		
		});
	}

	private  void initColorScrollView(){
		RadioGroup rgColorPicker=(RadioGroup) findViewById(R.id.radioGroup3);
		rgColorPicker.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				ScrollView scrollView=(ScrollView) findViewById(R.id.scrollView2);
				RadioButton rbGreen=(RadioButton) findViewById(R.id.radioGreen);
				RadioButton rbYellow = (RadioButton) findViewById(R.id.radioYellow);
				RadioButton rbTan= (RadioButton) findViewById(R.id.radioTan);
				if(rbGreen.isChecked()){
					scrollView.setBackgroundColor(getResources().getColor(R.color.green_background));

					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("colorpicker", "green").commit();

				}
				else if(rbTan.isChecked()){
					scrollView.setBackgroundColor(getResources().getColor(R.color.tan_background));
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("colorpicker", "tan").commit();


				}
				else{
					scrollView.setBackgroundColor(getResources().getColor(R.color.yellow_background));
					getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).edit().putString("colorpicker", "yellow").commit();

				}

			}
		});

	}

	private void initListButton() {
        ImageButton list = (ImageButton) findViewById(R.id.imageButtonList);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
    			Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
   			startActivity(intent);
            }
        });
	}

	private void initSettingsButton() {
        ImageButton list = (ImageButton) findViewById(R.id.imageButtonSettings);
        list.setEnabled(false);
	}

	private void initMapButton() {
        ImageButton list = (ImageButton) findViewById(R.id.imageButtonMap);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
    			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		   		startActivity(intent);
            }
        });
	}

	
}
