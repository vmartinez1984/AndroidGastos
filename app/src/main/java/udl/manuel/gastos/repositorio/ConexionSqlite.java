package udl.manuel.gastos.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class ConexionSqlite extends SQLiteOpenHelper {
    final String CREAR_TABLA_DE_CATEGORIA = "CREATE TABLE categoria (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT);";
    final String CREAR_TABLA_DE_GASTO = "CREATE TABLE gasto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad INTEGER, categoria TEXT);";
    final String CREAR_TABLA_DE_INGRESO = "CREATE TABLE ingreso (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad INTEGER);";
    //final String CREAR_TABLA_DE_USUARIO = "CREATE TABLE gasto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad INTEGER);";

    public ConexionSqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_DE_GASTO);
        db.execSQL(CREAR_TABLA_DE_INGRESO);
        db.execSQL(CREAR_TABLA_DE_CATEGORIA);
        db.execSQL("INSERT INTO categoria (nombre) values('Alimentos')");
        db.execSQL("INSERT INTO categoria (nombre) values('Servicios')");
        db.execSQL("INSERT INTO categoria (nombre) values('Ahorros')");
        db.execSQL("INSERT INTO categoria (nombre) values('Pasajes')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS categoria");
    }
}
