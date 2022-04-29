package udl.manuel.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import udl.manuel.gastos.entidades.Gasto;
import udl.manuel.gastos.repositorio.RepositorioDeCategorias;
import udl.manuel.gastos.repositorio.RepositorioDeGastos;

public class AgregarGastoActivity extends AppCompatActivity {
    Spinner spinnerDeCategoria;
    ArrayList<String> categorias;
    String categoria;
    EditText gastoNombre;
    EditText gastoCantidad;
    Button botonGuardarGasto;
    TextView totalDeGastos;
    ListView listViewDeGastos;
    int gastoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_gasto);
        gastoId =0;
        enlazarElementosDeLaVista();
        llenarCategorias();
        inicializarElSpinner();
        colocarTotalDeGastos();
        llenarListViewDeGastos();
    }

    private void llenarListViewDeGastos() {
        RepositorioDeGastos repositorioDeGastos;
        ArrayList<Gasto> listaDeGastos;
        ArrayAdapter<Gasto> arrayAdapter;

        repositorioDeGastos = new RepositorioDeGastos(this);
        listaDeGastos = repositorioDeGastos.obtenerTodos();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaDeGastos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.listViewDeGastos.setAdapter(arrayAdapter);
        this.listViewDeGastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Gasto gasto;

                gasto = (Gasto) adapterView.getItemAtPosition(position);
                if (gasto.getId() == 0) {

                } else {
                    gastoId = gasto.getId();
                    gastoNombre.setText(gasto.getNombre());
                    gastoCantidad.setText(gasto.getCantidad()+ "");
                    spinnerDeCategoria.setSelection(categorias.indexOf(gasto.getCategoria()));
                    Toast.makeText(getApplicationContext(),gasto.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void colocarTotalDeGastos() {
        RepositorioDeGastos repositorioDeGastos;

        repositorioDeGastos = new RepositorioDeGastos(this);

        this.totalDeGastos.setText("$ "+repositorioDeGastos.obtenerTotal());
    }

    private void enlazarElementosDeLaVista() {
        this.spinnerDeCategoria = findViewById(R.id.spinnerDeCategoria);
        this.gastoNombre = findViewById(R.id.gastoNombre);
        this.gastoCantidad = findViewById(R.id.gastoCantidad);
        this.botonGuardarGasto = findViewById(R.id.botonGuardarGasto);
        this.totalDeGastos = findViewById(R.id.totalDeGastos);
        this.listViewDeGastos = findViewById(R.id.listViewDeGastos);
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

            repositorioDeGastos = new RepositorioDeGastos(this);
            if(this.gastoId == 0) {
                long id;

                id = repositorioDeGastos.agregarGasto(gastoNombre.getText().toString(), Integer.parseInt(gastoCantidad.getText().toString()), this.categoria);

                Toast.makeText(getApplicationContext(), id + "", Toast.LENGTH_LONG).show();
            }else{
                repositorioDeGastos.actualizarGasto(this.gastoId, gastoNombre.getText().toString(), Integer.parseInt(gastoCantidad.getText().toString()), this.categoria);

                Toast.makeText(getApplicationContext(),"Gasto "+gastoNombre.getText() +" actualizado", Toast.LENGTH_LONG).show();
            }

            colocarTotalDeGastos();
            limpiarCampos();
            llenarListViewDeGastos();
        }
    }

    public void onClick_botonBorrarGasto(View view){
        if(this.gastoId == 0){
            Toast.makeText(getApplicationContext(), "Seleccione un gasto de la lista", Toast.LENGTH_LONG).show();
        }else {
            RepositorioDeGastos repositorioDeGastos;

            repositorioDeGastos = new RepositorioDeGastos(this);
            repositorioDeGastos.borrarGasto(this.gastoId);
            this.gastoId = 0;

            Toast.makeText(getApplicationContext(), "Gasto eliminado", Toast.LENGTH_LONG).show();
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
        this.gastoId = 0;
    }
}