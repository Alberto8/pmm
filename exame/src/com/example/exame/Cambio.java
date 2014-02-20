package com.example.exame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class Cambio extends Activity{
	
		@Override		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.cambio);
			
						
			TextView txtcambio = (TextView)findViewById(R.id.textView1);
									
			
			Bundle bundle = getIntent().getExtras();	
			
			
						
			txtcambio.setText(bundle.getString("COSTE"));
			
		
						
		}

	}

