package com.example.usuario.jsonacdat.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.usuario.jsonacdat.R;
import com.example.usuario.jsonacdat.network.RestClient;
import com.example.usuario.jsonacdat.ui.contactosGSON.Contact;
import com.example.usuario.jsonacdat.ui.contactosGSON.Person;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ListaContactosGSON extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private static String WEB = "https://portadaalta.mobi/acceso/contacts.json";
    private Button btnObtenerContactos;
    private ListView lwContactos;
    ArrayAdapter<Contact> adapter;
    Gson gson;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos_gson);
        btnObtenerContactos = findViewById(R.id.btnMostrar);
        btnObtenerContactos.setOnClickListener(this);
        lwContactos = findViewById(R.id.lwContactosGSON);
        lwContactos.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnObtenerContactos) {
            descarga(WEB);
        }
    }

    private void descarga(String web) {
        final ProgressDialog progreso = new ProgressDialog(this);
        RestClient.get(web, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(true);
                progreso.show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                progreso.dismiss();
                showError("ERROR: "+statusCode+ "\n" + throwable.getMessage());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    gson = new Gson();
                    person = gson.fromJson(response.toString(), Person.class);
                    mostrar();
                }
                catch (Exception e){
                    showError("ERROR: "+statusCode+ "\n" + e.getMessage());
                }
                progreso.dismiss();

            }
        } );
    }

    private void mostrar() {
        if (person != null) {
            if (adapter == null) {
                adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, person.getContacts());
                lwContactos.setAdapter(adapter);
            }
            else {
                adapter.clear();
                adapter.addAll(person.getContacts());
            }
        } else
            Toast.makeText(getApplicationContext(), "Error al crear la lista", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Móvil: " + person.getContacts().get(position).getPhone().getMobile(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo para hacer un toast corto para que el codigo sea mas facil
     * @param message
     */
    public void toastShort(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Para mostrar errores hara un Toast y aparte mostrara el error en pantalla
     * @param message
     */
    public void showError(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
