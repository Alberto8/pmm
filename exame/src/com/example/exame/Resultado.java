package com.example.exame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Resultado extends Activity{
	private String ur;
	TextView txtpaquete;
	Button btnOp1;	
	private double dinero;
	
	
		@Override		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.resultado);
			btnOp1 = (Button) findViewById(R.id.button);			
			
			
			TextView txtnombre = (TextView)findViewById(R.id.textView1);
			TextView txturgente =(TextView)findViewById(R.id.textView2);
			TextView txtpeso =(TextView)findViewById(R.id.textView3);
			txtpaquete = (TextView)findViewById(R.id.textView4);
			TextView txtprecio =(TextView)findViewById(R.id.textView5);
			TextView txtdescripcion =(TextView)findViewById(R.id.textView6);
			
						
			
			Bundle bundle = getIntent().getExtras();	
			
			if ((Integer.parseInt(bundle.getString("URGENTE"))) == 0){ // tarifa normal
				ur = "normal";
			}else{
				ur = "urgente";
			}
						
			txtnombre.setText(bundle.getString("ZONA")+ ": (" + (bundle.getString("CONTINENTE") + ")"));
			txturgente.setText("Tarifa: " + ur);
		//	txtdescripcion.setText(bundle.getString("CONTINENTE"));
			txtpeso.setText("Peso: "+bundle.getString("PESO"));			
			//txturgente.setText(bundle.getString("URGENTE"));
			txtpaquete.setText("Decoración: "+bundle.getString("PAQUETE"));
			txtprecio.setText("COSTE FINAL: "+bundle.getDouble("PRECIO")+"€"); //getString
																     	 
			dinero = bundle.getDouble("PRECIO");	
			
	    	 
	    	 /*
	    	  * Context context = getApplicationContext();
	    	 CharSequence text = "\ndinero = " +dinero +"\nPrecio = " +precio;
	    	 
	    	 int duration = Toast.LENGTH_SHORT;

	    	 Toast toast = Toast.makeText(context, text, duration);
	    	 toast.show();
	    	  * 
	    	  */		

		 btnOp1.setOnClickListener(new OnClickListener(){  
				public void onClick(View v){					
										
					 TextView devolucion = (TextView)findViewById(R.id.textView6);
			    	 
			    	 int bille=(int)dinero;
			    	 double monedas = dinero - bille;
			    	 
			    	 int b500, b200, b100, b50, b20, b10, b5, m2, m1, m050, m020, m010;
			    	 			    	 
			    	 
			    	 b500 = bille / 500; //billetes de 500
			    	 bille = bille % 500; //el resto
			    	 b200 = bille / 200; //billetes de 200
			    	 bille = bille % 200; //el resto
			    	 b100 = bille / 100; //billetes de 100
			    	 bille = bille % 100; //el resto
			    	 b50 = bille / 50; //billetes de 50
			    	 bille = bille % 50; //el resto
			    	 b20 = bille / 20; //billetes de 20
			    	 bille = bille % 20; //el resto
			    	 b10 = bille / 10; //billetes de 10
			    	 bille = bille % 10; //el resto
			    	 b5 = bille / 5; //billetes de 5
			    	 bille = bille % 5; //el resto
			    	 m2 = bille / 2; //monedas de 2
			    	 bille = bille % 2; //el resto
			    	 m1 = bille / 1; //monedas de 1
			    	 bille = bille % 1; //el resto			    	 
			    	
			    	 int mone = (int)(monedas * 1000 + 0.5);
			    	 m050 = mone / 500; //monedas de 0,50
			    	 mone = mone % 500; //el resto
			    	 m020 = mone / 200; //monedas de 0,20
			    	 mone = mone % 200; //el resto
			    	 m010 = mone / 100; //monedas de 0,10
			    	 mone = mone % 100; //el resto			    	
			    	   
			    	 
			    	 devolucion.setText("\nBilletes de 500 euros: " + b500 + 
			    			 "\nBilletes de 200 euros: " + b200 + 
			    			 "\nBilletes de 100 euros: " + b100 +
			    			 "\nBilletes de 50 euros:  " + b50 +
			    			 "\nBilletes de 20 euros:  " + b20 +
			    			 "\nBilletes de 10 euros:  " + b10 +
			    			 "\nBilletes de 5 euros:   " + b5 +
			    			 "\nMonedas de 2 euros:   " + m2 +
			    			 "\nMonedas de 1 euro:    " + m1 +
			    			 "\nMonedas de 50 centimos: " + m050 +
			    			 "\nMonedas de 20 centimos: " + m020 +
			    			 "\nMonedas de 10 centimos: " + m010);					
					}
				
			});
		 }	
	}

