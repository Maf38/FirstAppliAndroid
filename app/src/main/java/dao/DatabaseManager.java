package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager extends SQLiteOpenHelper {


        private static String DB_PATH = "/data/data/com.exercice.maf.firstappli/databases";
        private static String DB_NAME = "Monnaie.db";
        private static final int DATABASE_VERSION = 3;

        public DatabaseManager (Context context) {
            super(context, DB_NAME, null,DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String strSql = "CREATE TABLE "+"Monnaie"+" ('monnaie'	TEXT NOT NULL,'taux' REAL NOT NULL, PRIMARY KEY('monnaie'))";
            db.execSQL(strSql);
            Log.i( "DATABASE", "onCreate invoked" );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String strSql = "drop table Monnaie";
            db.execSQL( strSql );
            this.onCreate( db );
            Log.i( "DATABASE", "onUpgrade invoked" );
        }

        public void insertMoney( String monnaie, double taux ) {
        monnaie = monnaie.replace( "'", "''" );
        String strSql = "insert into Monnaie (monnaie, taux) values ('"
                + monnaie + "', " + taux + ")";
        this.getWritableDatabase().execSQL( strSql );
        Log.i( "DATABASE", "insertMoney invoked" );
        }

        public Map <String, Double> lireDatabase( ) {

            Map <String, Double> listeDevise = new HashMap<> ();

            //Ouverture de la base
            //String myPath = DB_PATH + DB_NAME;
            //SQLiteDatabase bdd = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
            //Récupère dans un Cursor les données contenues dans la BDD
            //Cursor c = bdd.query(DB_NAME, new String[]{"monnaie","taux"}, null, null, null, null, null);
            //balayage du Cursor

            String strSql = "select * from Monnaie";
            Cursor c = this.getReadableDatabase().rawQuery( strSql, null );

            c.moveToFirst();
            while(!c.isAfterLast()){
                listeDevise.put(c.getString(0),c.getDouble(1));
                Log.d("testDebug",c.getString(0));
                c.moveToNext();
            }
            //fermeture du cursor
            c.close();
            //on ferme la BDD
            //bdd.close();

            return listeDevise;
        }



}
