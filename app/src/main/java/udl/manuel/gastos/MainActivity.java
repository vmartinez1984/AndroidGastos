package udl.manuel.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import udl.manuel.gastos.repositorio.RepositorioDeGastos;

public class MainActivity extends AppCompatActivity {
    Button botonDeMenuDeIngreso;
    Button botonDeMenuDeGasto;
    TextView mainTotalDeGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        colocarTotalDeGastos();
    }

    private void colocarTotalDeGastos() {
        RepositorioDeGastos repositorioDeGastos;

        repositorioDeGastos = new RepositorioDeGastos(this);
        this.mainTotalDeGastos.setText("$ "+ repositorioDeGastos.obtenerTotal());
    }

    private void inicializar() {
        this.botonDeMenuDeIngreso = findViewById((R.id.botonDeMenuIngreso));
        this.botonDeMenuDeGasto = findViewById(R.id.botonDeMenuGasto);
        this.mainTotalDeGastos = findViewById(R.id.mainTotalDeGastos);
    }

    public void onClick_botonDeMenuIngreso(View view){
        Intent intent;

        intent = new Intent(MainActivity.this, AgregarIngresoActivity.class);
        startActivity(intent);
    }

    public void onClick_botonDeMenuGastos(View view){
        Intent intent;

        intent = new Intent(MainActivity.this, AgregarGastoActivity.class);
        startActivity(intent);
    }
}