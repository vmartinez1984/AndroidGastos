package udl.manuel.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuGastosActivity extends AppCompatActivity {
    Button botonAgregarGasto;
    Button botonListaDeGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gastos);
        this.botonAgregarGasto = findViewById(R.id.botonAgregarGasto);
        this.botonListaDeGastos = findViewById(R.id.botonListaDeGastos);
    }

    public void onClick_botonAgregarGasto(View view){
        Intent intent;

        intent = new Intent(MenuGastosActivity.this, AgregarGastoActivity.class);
        startActivity(intent);
    }

    public void onClick_botonListaDeGastos(View view){
        Intent intent;

        intent = new Intent(MenuGastosActivity.this, ListaDeGastosActivity.class);
        startActivity(intent);
    }
}