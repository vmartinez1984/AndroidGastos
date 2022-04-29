package udl.manuel.gastos.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import udl.manuel.gastos.entidades.Gasto;

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

    public int obtenerTotal(){
        int total;
        SQLiteDatabase database;
        Cursor cursor;

        total =0;
        database= conexionSqliteHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT  cantidad FROM gasto", null);
        while (cursor.moveToNext()){
            total += cursor.getInt(0);
        }
        cursor.close();
        database.close();

        return total;
    }

    public ArrayList<Gasto> obtenerTodos(){
        ArrayList<Gasto> lista;
        SQLiteDatabase database;
        Cursor cursor;

        database= conexionSqliteHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT * FROM gasto ", null);

        lista = new ArrayList<>();
        while (cursor.moveToNext()){
            lista.add(obtenerGasto(cursor));
        }
        cursor.close();
        database.close();

        return  lista;
    }

    private Gasto obtenerGasto(Cursor cursor) {
        Gasto gasto;

        gasto = new Gasto(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getInt(2),
            cursor.getString(3)
        );

        return  gasto;
    }

    public void actualizarGasto(int gastoId, String nombre, int cantidad, String categoria) {
        SQLiteDatabase database;
        String[] parametros;
        ContentValues contentValues;

        parametros = new String[]{gastoId+""};
        contentValues = new ContentValues();
        database = conexionSqliteHelper.getWritableDatabase();
        contentValues.put("nombre",nombre);
        contentValues.put("cantidad",cantidad);
        contentValues.put("categoria",categoria);

        database.update("gasto",contentValues,"id = ?",parametros);
        database.close();
    }

    public void borrarGasto(int gastoId){
        SQLiteDatabase database;
        String[] parametros;

        parametros = new String[]{gastoId+""};
        database = conexionSqliteHelper.getWritableDatabase();

        database.delete("gasto","id = ?",parametros);
        database.close();
    }
}
