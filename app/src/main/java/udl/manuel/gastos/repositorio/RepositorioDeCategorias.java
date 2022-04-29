package udl.manuel.gastos.repositorio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RepositorioDeCategorias {
    private Context context;
    ConexionSqlite conexionSqliteHelper;

    public RepositorioDeCategorias(Context context) {
        this.context = context;
        conexionSqliteHelper = new ConexionSqlite(this.context, "db", null, 1);
    }

    public ArrayList<String> obtenerTodos(){
        ArrayList<String> lista;
        SQLiteDatabase database;
        Cursor cursor;

        database= conexionSqliteHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT  nombre FROM categoria ", null);

        lista = new ArrayList<>();
        while (cursor.moveToNext()){
            lista.add(cursor.getString(0));
        }
        cursor.close();
        database.close();

        return  lista;
    }
}
