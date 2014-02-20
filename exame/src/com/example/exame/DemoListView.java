package com.example.exame;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DemoListView extends Activity {
	Button btnOp1;
	TextView text;
	private String zo;
	private int zona;
	private String coste;
	private double precio;
	private int posi;
	private String conti;
	private int p;
	private int opcion;
	private String urge;
	private int calculo;
	private ListView lista;
	String paquete = "";

    private Destino [] datos = 
    	new Destino[]{
    		new Destino("Zona A", "Asia y oceania", 30),
    		new Destino("Zona B", "America y Africa", 20),    		
    		new Destino("Zona C", "Europa", 10),
    		};
    TextView resultado;
    @Override
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
          
        AdaptadorTitulares adaptador = 
        	new AdaptadorTitulares(this);
        lista = (ListView) findViewById(R.id.LstOpciones);
        btnOp1 = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.texto);
        
       final ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
       final RadioGroup grupoRadio =(RadioGroup)findViewById(R.id.gruporb);
       final CheckBox regalo = (CheckBox)findViewById(R.id.regalo);
       final CheckBox tarjeta = (CheckBox)findViewById(R.id.tarjeta);
       
        resultado = (TextView)findViewById(R.id.lblResultado);
        lstOpciones.setAdapter(adaptador);
           
        lista.setOnItemClickListener(new OnItemClickListener() { // se coje lo d la lista
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				Destino elegido = (Destino) pariente.getItemAtPosition(posicion);
								
				
				zo = elegido.getZona();				
				conti = elegido.getContinente();
				p = elegido.getPrecio();
				
				posi = posicion;

			}
        });
        
        grupoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	        public void onCheckedChanged(RadioGroup group, int seleccion) {
	        	
	        	int radioButtonID = grupoRadio.getCheckedRadioButtonId();
	        	
	        	
	        	View radioButton = grupoRadio.findViewById(radioButtonID);
	        	
	        	
	        	opcion = grupoRadio.indexOfChild(radioButton);
	        	
	           
	        }
        });
        
       
        text.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		text.setText("");
        	}
        });
        
        btnOp1.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				String peso = text.getText().toString();
				int numero = Integer.parseInt(peso);
				
				if(posi == 0){
					zona = 30;
				}else if(posi == 1){
					zona = 20;
				}else{
					zona = 10;
				}
								
				if (numero <= 5){
					precio = 1 * numero + zona;
				}else if(numero >= 6 && numero <= 10){
					precio = 1.5 * numero + zona;
				}else{
					precio = 2 * numero + zona;
				}
				
				if(opcion == 1){
					precio = (precio * 0.3) + precio;
				}
				
				if (regalo.isChecked()){
		        	paquete = paquete + "caja regalo";
		        }
		       
		       if (tarjeta.isChecked()){
		    	   paquete = paquete + " " + "con tarjeta dedicada";
		       }
		       
				
				Bundle b = new Bundle();
				Intent i1 = new Intent (DemoListView.this, Resultado.class);
				/*				
				b.putString("ZONA", zo);
				b.putString("CONTINENTE", conti);				
				b.putInt("PRECIO", p);
				*/				
				coste = String.valueOf(precio);
				urge = String.valueOf(opcion); // si sale 0 tarifa normal, si sale 1 urgente
				
				b.putString("ZONA", zo);			
				b.putString("PESO", peso);				
			//	b.putDouble("PRECIO", coste);
				b.putDouble("PRECIO", precio);
				b.putString("CONTINENTE", conti);
				b.putString("URGENTE", urge);
				b.putString("PAQUETE", paquete);
				i1.putExtras(b);
				startActivity(i1);
				paquete ="";
				
			}
		});
                
        }
    
    static class ViewHolder{
		TextView Zona;
		TextView Continente;
		TextView Precio;
	
	}
    
    class AdaptadorTitulares extends ArrayAdapter {
    	
    	Activity context;
    	
    	AdaptadorTitulares(Activity context) {
    		super(context, R.layout.listitem_titular, datos);
    		this.context = context;
    	}
    	
    	    	
    	public View getView(int position, View convertView, ViewGroup parent) 
    	
    	{ View item = convertView;
    	
    	ViewHolder holder;
    	
    	if(item == null)
    		
    	{	LayoutInflater inflater = context.getLayoutInflater();
    	item = inflater.inflate(R.layout.listitem_titular, null);
    	
		holder = new ViewHolder();
		
		holder.Zona = (TextView)item.findViewById(R.id.LblNombre);
		
		holder.Continente = (TextView)item.findViewById(R.id.LblApellido);
		
		holder.Precio = (TextView)item.findViewById(R.id.LblEdad);
		
		item.setTag(holder);
		    					
		} 
    	else 
    	{
    			holder = (ViewHolder)item.getTag();  
    			}
    	
    	holder.Zona.setText(datos[position].getZona());
    	holder.Continente.setText(datos[position].getContinente());
    	holder.Precio.setText(Integer.toString(datos[position].getPrecio()));
    	
    	return(item);
    	 
    	
    	}
    }
    
    public class MainActivity extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_list);
            
         
            final RadioGroup rg = (RadioGroup)findViewById(R.id.gruporb);

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    	        public void onCheckedChanged(RadioGroup group, int checkedId) {
    	       
    	        }
            });

        }
    }
    private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;		
	
	private TextView lblMensaje;
	
   
    public void onCreate2(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        lblMensaje = (TextView)findViewById(R.id.LblMensaje);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	    	
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
            	
        
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.MnuOpc1:
            lblMensaje.setText("Opcion 1 pulsada!");
            return true;
        case R.id.MnuOpc2:
        	Intent i1 = new Intent (DemoListView.this, Acerca.class);
        	startActivity(i1);
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 
}

