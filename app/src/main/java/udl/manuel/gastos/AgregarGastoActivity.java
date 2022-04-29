package udl.manuel.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import udl.manuel.gastos.repositorio.RepositorioDeCategorias;
import udl.manuel.gastos.repositorio.RepositorioDeGastos;

public class AgregarGastoActivity extends AppCompatActivity {
    Spinner spinnerDeCategoria;
    ArrayList<String> categorias;
    String categoria;
    EditText gastoNombre;
    EditText gastoCantidad;
    Button botonGuardarGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto);
        enlazarElementosDeLaVista();
        llenarCategorias();
        inicializarElSpinner();
    }

    private void enlazarElementosDeLaVista() {
        this.spinnerDeCategoria = findViewById(R.id.spinnerDeCategoria);
        this.gastoNombre = findViewById(R.id.gastoNombre);
        this.gastoCantidad = findViewById(R.id.gastoCantidad);
        this.botonGuardarGasto = findViewById(R.id.botonGuardarGasto);
    }

    private void llenarCategorias() {
        RepositorioDeCategorias repositorioDeCategorias;

        repositorioDeCategorias = new RepositorioDeCategorias(this);
        this.categorias = repositorioDeCategorias.obtenerTodos();
        this.categorias.add(0,"Seleccione");
    }

    private void inicializarElSpinner() {
        ArrayAdapter<String> arrayAdapter;

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.categorias);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerDeCategoria.setAdapter(arrayAdapter);
        this.spinnerDeCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(position)+"",Toast.LENGTH_LONG).show();
                categoria = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public  void onClick_botonGuardarGasto(View view){
        if(esValidoGasto()){
            RepositorioDeGastos repositorioDeGastos;
            long id;

            repositorioDeGastos = new RepositorioDeGastos(this);
            id =  repositorioDeGastos.agregarGasto(gastoNombre.getText().toString(), Integer.parseInt(gastoCantidad.getText().toString()), this.categoria);
            Toast.makeText(getApplicationContext(), id+ "",Toast.LENGTH_LONG).show();

            limpiarCampos();
        }
    }

    private boolean esValidoGasto() {
        if(this.categoria == "Seleccione"){
            Toast.makeText(getApplicationContext(), "Seleccione una categoria",Toast.LENGTH_LONG).show();
            return false;
        }
        if(this.gastoNombre.getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Seleccione una categoria",Toast.LENGTH_LONG).show();
            return false;
        }
        return  true;
    }

    private void limpiarCampos(){
        this.gastoNombre.setText("");
        this.gastoCantidad.setText("");
        this.spinnerDeCategoria.setSelection(0);
    }
}