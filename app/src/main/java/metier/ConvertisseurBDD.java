package metier;

//Aide = https://www.supinfo.com/articles/single/5151-developpement-android-base-donnees-sqlite
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class ConvertisseurBDD extends SQLiteOpenHelper {

    private static Map conversionTable = new HashMap();
    private static String DB_PATH = "/data/data/com.exercice.maf.firstappli";
    private static String DB_NAME = "monnaie";

    public ConvertisseurBDD (Context context) {
        super(context, "MONNAIES", null, 1);
        lire(conversionTable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_NAME+" ('monnaie'	INTEGER NOT NULL,'taux' REAL NOT NULL, PRIMARY KEY('monnaie'))");
        onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void lire(Map <String, Double> conversionTable) {
        //Ouverture de la base
        String myPath = DB_PATH + DB_NAME;
        SQLiteDatabase bdd = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        //Récupère dans un Cursor les données contenues dans la BDD
        Cursor c = bdd.query("MONNAIES", new String[]{"monnaie",
                "taux"}, null, null, null, null, null);
        //balayage du Cursor
        //fermeture du cursor
        c.close();
        //on ferme la BDD
        bdd.close();
    }

}
