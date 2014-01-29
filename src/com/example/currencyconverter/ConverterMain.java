package com.example.currencyconverter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ConverterMain extends Activity {
	
	private static final double USD = 1.0;
	private static final double BC = 818.7;
	private static final double DC = 0.00178;
	
	private double convertFrom = DC;
	private double convertTo = DC;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_converter_main);
		Spinner convertFromSpinner = (Spinner) findViewById(R.id.convert_from);
		Spinner convertToSpinner = (Spinner) findViewById(R.id.convert_to);
		//Create Adapter Array from string array
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
				R.array.currency_names, android.R.layout.simple_spinner_item);
		//Define layout for when displaying choices
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//Apply adapter to spinners
		convertFromSpinner.setAdapter(adapter);
		convertToSpinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.converter_main, menu);
		return true;
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
		if (parent.equals(R.id.convert_from)){
			switch (pos) {
			case 0:
				convertFrom = USD;
			case 1:
				convertFrom = BC;
			case 2:
				convertFrom = DC;
			}
		}else {
			switch (pos) {
			case 0:
				convertTo = USD;
			case 1:
				convertTo = BC;
			case 2:
				convertTo = DC;
			}
			
		}
	}
}
