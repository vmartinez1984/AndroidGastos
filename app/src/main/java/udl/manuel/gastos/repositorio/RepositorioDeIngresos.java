package udl.manuel.gastos.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import udl.manuel.gastos.entidades.Ingreso;

public class RepositorioDeIngresos {
    private Context context;
    ConexionSqlite conexionSqliteHelper;
    private static final String tabla = "ingreso";

    public RepositorioDeIngresos(Context context) {
        this.context = context;
        conexionSqliteHelper = new ConexionSqlite(this.context, "db", null, 1);
    }

    public Long agregar(String nombre, int cantidad) {
        Long idResultante;

        SQLiteDatabase database = conexionSqliteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("cantidad", cantidad);

        //Tambien se puede por hacer por un query
        // String query = "insert into..";
        // database.execSQL(query);

        idResultante = database.insert(tabla, null, contentValues);
        database.close();

        return idResultante;
    }

    public int obtenerTotal(){
        int total;
        SQLiteDatabase database;
        Cursor cursor;

        total =0;
        database= conexionSqliteHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT  cantidad FROM "+ tabla, null);
        while (cursor.moveToNext()){
            total += cursor.getInt(0);
        }
        cursor.close();
        database.close();

        return total;
    }

    public ArrayList<Ingreso> obtenerTodos(){
        ArrayList<Ingreso> lista;
        SQLiteDatabase database;
        Cursor cursor;

        database= conexionSqliteHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT * FROM "+tabla, null);

        lista = new ArrayList<>();
        while (cursor.moveToNext()){
            lista.add(obtener(cursor));
        }
        cursor.close();
        database.close();

        return  lista;
    }

    private Ingreso obtener(Cursor cursor) {
        Ingreso item;

        item = new Ingreso(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2)
        );

        return  item;
    }

    public void actualizar(int gastoId, String nombre, int cantidad) {
        SQLiteDatabase database;
        String[] parametros;
        ContentValues contentValues;

        parametros = new String[]{gastoId+""};
        contentValues = new ContentValues();
        database = conexionSqliteHelper.getWritableDatabase();
        contentValues.put("nombre",nombre);
        contentValues.put("cantidad",cantidad);

        database.update(tabla,contentValues,"id = ?",parametros);
        database.close();
    }

    public void borrarGasto(int gastoId){
        SQLiteDatabase database;
        String[] parametros;

        parametros = new String[]{gastoId+""};
        database = conexionSqliteHelper.getWritableDatabase();

        database.delete(tabla,"id = ?",parametros);
        database.close();
    }
}
