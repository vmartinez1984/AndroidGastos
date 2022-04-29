package udl.manuel.gastos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import udl.manuel.gastos.entidades.Ingreso;
import udl.manuel.gastos.repositorio.RepositorioDeIngresos;

public class AgregarIngresoActivity extends AppCompatActivity {
    EditText ingresoNombre;
    EditText ingresoCantidad;
    Button botonGuardarIngreso;
    Button botonBorrarIngreso;
    ListView listView;
    TextView totalDeIngresos;
    int ingresoId;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_ingreso);
        inicializar();
        colocarTotalDeIngresos();
        llenarListView();
    }

    private void llenarListView() {
        RepositorioDeIngresos repositorio;
        ArrayList<Ingreso> listaDeGastos;
        ArrayAdapter<Ingreso> arrayAdapter;

        repositorio = new RepositorioDeIngresos(this);
        listaDeGastos = repositorio.obtenerTodos();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaDeGastos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.listView.setAdapter(arrayAdapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Ingreso ingreso;

                ingreso = (Ingreso) adapterView.getItemAtPosition(position);
                if (ingreso.getId() == 0) {

                } else {
                    ingresoId = ingreso.getId();
                    ingresoNombre.setText(ingreso.getNombre());
                    ingresoCantidad.setText(ingreso.getCantidad()+ "");
                    Toast.makeText(getApplicationContext(),ingreso.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void colocarTotalDeIngresos() {
        RepositorioDeIngresos repositorioDeIngresos;

        repositorioDeIngresos = new RepositorioDeIngresos(this);

        this.totalDeIngresos.setText("$ " + repositorioDeIngresos.obtenerTotal());
    }

    private void inicializar() {
        ingresoNombre = findViewById(R.id.ingresoNombre);
        ingresoCantidad = findViewById(R.id.ingresoCantidad);
        botonGuardarIngreso = findViewById(R.id.botonGuardarIngreso);
        botonBorrarIngreso = findViewById(R.id.botonBorrarIngreso);
        listView = findViewById(R.id.listViewDeIngresos);
        totalDeIngresos = findViewById(R.id.totalDeIngresos);
    }
    
    public void onClick_botonGuardarIngreso(View view){
        if(esValido()){
            RepositorioDeIngresos repositorio;

            repositorio = new RepositorioDeIngresos(this);
            if(this.ingresoId == 0) {
                long id;

                id = repositorio.agregar(ingresoNombre.getText().toString(), Integer.parseInt(ingresoCantidad.getText().toString()));

                Toast.makeText(getApplicationContext(), id + "", Toast.LENGTH_LONG).show();
            }else{
                repositorio.actualizar(this.ingresoId, ingresoNombre.getText().toString(), Integer.parseInt(ingresoCantidad.getText().toString()));

                Toast.makeText(getApplicationContext(),"Ingreso "+ingresoNombre.getText() +" actualizado", Toast.LENGTH_LONG).show();
            }

            colocarTotalDeIngresos();
            limpiarCampos();
            llenarListView();
        }
    }

    private boolean esValido() {
        if(this.ingresoNombre.getText().toString() == ""){
            Toast.makeText(getApplicationContext(), "Ingrese un nombre",Toast.LENGTH_LONG).show();
            return false;
        }
        if(this.ingresoCantidad.getText().toString() == "" || this.ingresoCantidad.getText().toString() == "0"){
            Toast.makeText(getApplicationContext(), "Ingrese una cantidad valida",Toast.LENGTH_LONG).show();
            return false;
        }

        return  true;
    }

    public void onClick_botonBorrarIngreso(View view){
        if(this.ingresoId == 0){
            Toast.makeText(getApplicationContext(), "Seleccione un ingreso de la lista", Toast.LENGTH_LONG).show();
        }else {
            RepositorioDeIngresos repositorio;

            repositorio = new RepositorioDeIngresos(this);
            repositorio.borrarGasto(this.ingresoId);
            this.ingresoId = 0;

            Toast.makeText(getApplicationContext(), "Ingreso eliminado", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiarCampos(){
        this.ingresoNombre.setText("");
        this.ingresoCantidad.setText("");
        this.ingresoId = 0;
    }
}