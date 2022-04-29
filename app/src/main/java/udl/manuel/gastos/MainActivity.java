package udl.manuel.gastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button botonDeMenuDeIngreso;
    Button botonDeMenuDeGasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.botonDeMenuDeIngreso = findViewById((R.id.botonDeMenuIngreso));
        this.botonDeMenuDeGasto = findViewById(R.id.botonDeMenuGasto);
    }

    public void onClick_botonDeMenuIngreso(View view){
        Intent intent;

        intent = new Intent(MainActivity.this, MenuIngresosActivity.class);
        startActivity(intent);
    }

    public void onClick_botonDeMenuGastos(View view){
        Intent intent;

        intent = new Intent(MainActivity.this, MenuGastosActivity.class);
        startActivity(intent);
    }
}