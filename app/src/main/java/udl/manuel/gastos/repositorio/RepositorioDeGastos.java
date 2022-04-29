package udl.manuel.gastos.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class RepositorioDeGastos {
    private Context context;
    ConexionSqlite conexionSqliteHelper;

    public RepositorioDeGastos(Context context) {
        this.context = context;
        conexionSqliteHelper = new ConexionSqlite(this.context, "db", null, 1);
    }

    public Long agregarGasto(String nombre, int cantidad, String categoria) {
        Long idResultante;

        SQLiteDatabase database = conexionSqliteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("cantidad", cantidad);
        contentValues.put("categoria", categoria);

        //Tambien se puede por hacer por un query
        // String query = "insert into..";
        // database.execSQL(query);

        idResultante = database.insert("gasto", null, contentValues);
        database.close();

        return idResultante;
    }
}
