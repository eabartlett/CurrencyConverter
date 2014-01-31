package com.example.currencyconverter;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
		
		EditText editText = (EditText) findViewById(R.id.value_to_convert);
		editText.addTextChangedListener(new TextWatcher(){
			@Override
			public void afterTextChanged(Editable s){
				setAmount();
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// Do nothing
				return;
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// Do nothing
				return;
				
			}
		});
		
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

		//set spinner's listener
		convertFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
				switch (pos) {
					case 0:
						convertFrom = DC;
						break;
					case 1:
						convertFrom = BC;
						break;
					case 2:
						convertFrom = USD;
						break;
					default:
						break;
				}
				setAmount();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent){
				return;
			}
		});
		convertToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
				switch (pos) {
					case 0:
						convertTo = DC;
						break;
					case 1:
						convertTo = BC;
						break;
					case 2:
						convertTo = USD;
						break;
					default:
						break;
				}
				setAmount();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent){
				return;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.converter_main, menu);
		return true;
	}
	
	private double convertCurrency(double amount){
		// ratio of two currencies
		double conversionRate = convertFrom / convertTo;		
		// Divide by the worth of currency being converted to
		return conversionRate * amount;
	}

	private void setAmount() {
		EditText editText = (EditText) findViewById(R.id.value_to_convert);
		String stringAmount = editText.getText().toString();
		double amount;
		try {
			amount = Double.valueOf(stringAmount);
		} catch (NumberFormatException e) {
			amount = 0.0;
		}
		
		double convertedAmount = convertCurrency(amount);
		
		TextView text = (TextView) findViewById(R.id.converted_value);
		text.setText(String.valueOf(convertedAmount));
	}
	
}
