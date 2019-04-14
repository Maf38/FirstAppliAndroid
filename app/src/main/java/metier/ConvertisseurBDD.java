package metier;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import dao.DatabaseManager;

public class ConvertisseurBDD  {



    private static Map conversionTable = new HashMap();



    public ConvertisseurBDD(Context ctx){
        DatabaseManager data = new DatabaseManager(ctx);
        conversionTable= data.lireDatabase();
    }

    public static double convertir(String source, String cible, double montant)
    {
        //The constants should probably be defined somewhere else
        double tauxSource = ((Double)conversionTable.get(source)).doubleValue() ;
        double tauxCible = ((Double)conversionTable.get(cible)).doubleValue() ;
        double tauxConversion = tauxCible/tauxSource;
        return (montant * tauxConversion) ;
    }


    public static Map getConversionTable()
    {
        return conversionTable;
    }

}
