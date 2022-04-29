package udl.manuel.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import udl.manuel.gastos.repositorio.RepositorioDeGastos;
import udl.manuel.gastos.repositorio.RepositorioDeIngresos;

public class MainActivity extends AppCompatActivity {
    Button botonDeMenuDeIngreso;
    Button botonDeMenuDeGasto;
    TextView totalDeGastosMain;
    TextView totalDeIngresosMain;
    TextView textViewBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        colocarTotalDeIngresos();
        colocarTotalDeGastos();
        colocarBalance();
    }

    private void colocarBalance() {
        int balance;
        RepositorioDeIngresos repositorioDeIngresos;
        RepositorioDeGastos repositorioDeGastos;

        repositorioDeIngresos = new RepositorioDeIngresos(this);
        repositorioDeGastos = new RepositorioDeGastos(this);
        balance = repositorioDeIngresos.obtenerTotal() - repositorioDeGastos.obtenerTotal();
        this.textViewBalance.setText("$ " + balance);
    }

    private void colocarTotalDeIngresos() {
        RepositorioDeIngresos repositorio;

        repositorio = new RepositorioDeIngresos(this);

        this.totalDeIngresosMain.setText("$ " + repositorio.obtenerTotal());
    }

    private void colocarTotalDeGastos() {
        RepositorioDeGastos repositorioDeGastos;

        repositorioDeGastos = new RepositorioDeGastos(this);

        this.totalDeGastosMain.setText("$ "+ repositorioDeGastos.obtenerTotal());
    }

    private void inicializar() {
        this.botonDeMenuDeIngreso = findViewById((R.id.botonDeMenuIngreso));
        this.botonDeMenuDeGasto = findViewById(R.id.botonDeMenuGasto);
        this.totalDeGastosMain = findViewById(R.id.totalDeGastosMain);
        this.totalDeIngresosMain = findViewById(R.id.totalDeIngresosMain);
        this.textViewBalance = findViewById(R.id.textViewBalance);
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