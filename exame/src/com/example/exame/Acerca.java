package com.example.exame;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Acerca extends Activity {

   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca);
          
        TextView txtMensaje = (TextView)findViewById(R.id.texto); 
        
        txtMensaje.setText("Hola me llamo Alberto Pertusa Ros, " +"\n" +
        		"estoy estudiando en el IES Serpis y " + "\n" +
        		"tengo 22 años" );
}
}
