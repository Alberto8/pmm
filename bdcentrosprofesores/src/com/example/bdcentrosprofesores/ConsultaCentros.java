package com.example.bdcentrosprofesores;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ConsultaCentros extends Activity {

	public static final int CODIGO_RESPUESTA = 8;
	CrearBase base;
	private Centros[] datos;

	class AdaptadorCentro extends ArrayAdapter<Centros> 
	{
		Activity a;
		AdaptadorCentro(Activity b) 
		{
			super(b, R.layout.listado_centros, datos);
			this.a = b;
		}
		public View getDropDownView (int position,View convertView,ViewGroup parent) {

			
			return getView (position, convertView, parent);
			}
		public View getView(int position,View convertView,ViewGroup parent) 
		{
			
			LayoutInflater inflater = a.getLayoutInflater();	
			View item = inflater.inflate(R.layout.listado_centros, null);

			final TextView lblCodigo=(TextView)item.findViewById(R.id.codigo);
			final TextView lblTipo = (TextView)item.findViewById(R.id.tipo);
			final TextView lblNombre = (TextView)item.findViewById(R.id.nombre);
			final TextView lblDireccion = (TextView)item.findViewById(R.id.direccion);
			final TextView lblTelefono = (TextView)item.findViewById(R.id.telefono);
			final TextView lblPlazas = (TextView)item.findViewById(R.id.plazas);

			lblNombre.setText(datos[position].getNomCentro());
			lblTipo.setText(datos[position].getTipoCentro());
			lblCodigo.setText(datos[position].getCodCentro());
			lblDireccion.setText(datos[position].getDireccion());
			lblTelefono.setText(datos[position].getTelefono());
			lblPlazas.setText(datos[position].getNumPlazas());

			return(item);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_centros);


		try 
		{

			String[] campos = new String[] {"cod_centro","tipo_centro","nombre","direccion","telefono","num_plazas"};

			base=new CrearBase(this,"dbase",null,1);
			SQLiteDatabase db=base.getReadableDatabase();


			Cursor rs=db.query("centros", campos, null,null,null,null,null);

			datos=new Centros[rs.getCount()+1];//Devuelve el numero de filas + 1 
			datos[0]= new Centros("Codigo","Tipo","Nombre","Direccion","Telefono","NumPlazas");
			int i=1;
	        if (rs.moveToFirst()) 
	        {
	                do 
	                {
	                		String cod=rs.getString(0);
	                		String tip=rs.getString(1);
	                		String nom=rs.getString(2);
	                        String dir=rs.getString(3);
	                        String tel=rs.getString(4);
	                        String num=rs.getString(5);

	                        datos[i]=new Centros(cod,tip,nom,dir,tel,num);
	                        i++;       
	                }
	                while (rs.moveToNext());
	        }

		}
		catch (Exception e) 
		{
			
		}

		final Spinner spinner=(Spinner)findViewById(R.id.centros);
		AdaptadorCentro adaptador =new AdaptadorCentro(this); 
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adaptador);


		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				Intent intent = new Intent(ConsultaCentros.this, MostrarCentro.class);

				Bundle b = new Bundle();
				String codigocentro = ((Centros)parent.getAdapter().getItem(position)).getCodCentro();
				String tipocentro = ((Centros)parent.getAdapter().getItem(position)).getTipoCentro();
				String nombrecentro = ((Centros)parent.getAdapter().getItem(position)).getNomCentro();
				String direccion = ((Centros)parent.getAdapter().getItem(position)).getDireccion();
				String telefono = ((Centros)parent.getAdapter().getItem(position)).getTelefono();
				String numplazas = ((Centros)parent.getAdapter().getItem(position)).getNumPlazas();

				b.putString("Codigo", codigocentro);
				b.putString("Nombre", nombrecentro);
				b.putString("Direccion", direccion);
				b.putString("Tipo", tipocentro);
				b.putString("Telefono", telefono);
				b.putString("NumPlazas", numplazas);

				intent.putExtras(b);


				if(position > 0)
				startActivityForResult(intent, CODIGO_RESPUESTA);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});


		Button insertar = (Button)findViewById(R.id.insertarCentro);

		insertar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ConsultaCentros.this, InsertarCentros.class);

				startActivityForResult(intent, CODIGO_RESPUESTA);

			}
		});


	}

	protected void onActivityResult(int requestCode,int resultCode, Intent pData)            
    {
        if ( requestCode == CODIGO_RESPUESTA )//Si el código de respuesta es igual al requestCode
            {
            if (resultCode == Activity.RESULT_OK )//Si resultCode es igual a ok
                {
                    final String dato = pData.getExtras().getString(MostrarCentro.DATO_SUBACTIVIDAD );//Obtengo el string de la subactividad
                    //Aquí se hara lo que se desee con el valor recuperado

                    SQLiteDatabase db=base.getWritableDatabase();
                    db.execSQL(dato);
                    
                    Intent intent = new Intent(ConsultaCentros.this, ConsultaCentros.class);
                    finish();
                    startActivity(intent);
                }
            }
    }

}
