package com.exercice.maf.firstappli;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import metier.ConvertisseurSOAP;

public class AsyncTaskConvertir extends AsyncTask<Void, Void, Boolean>
{
    // On a besoin du contexte pour replacer l'AsyncTask
    private Context context;
    // On récupère l'activité d'appel, au cas où besoin dans le traitement
    private Activity activity;
    private String source;
    private String cible;
    private String montant;
    private String resultat;


    ProgressDialog progressDialog;

    /*
     * Constructeur de l'asyncTask.
     * @param context
     */
    public AsyncTaskConvertir(Activity activity, String source,String cible, String montant) {
        this.context = activity.getApplicationContext();
        this.activity = activity;
        this.source=source;
        this.cible=cible;
        this.montant=montant;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /*
         * Cette fonction contiendra le code exécuté au préalable, par exemple:
         *  -Affichage d'une ProgressBar
         *      =rond qui tourne pour indiquer une attente
         *      =Barre de progression
         *  -...
         */
         progressDialog = ProgressDialog.show(activity, "Please wait...","Convertion in progress...", true);

    }

    @Override
    protected Boolean doInBackground(Void... arg0) {

        ConvertisseurSOAP soap= new ConvertisseurSOAP();
        resultat=soap.Convertir(source,cible,montant);
        Log.d("testsoap","soap.convertir ="+resultat);
        return null;
        /*
         * Ici, le code qui doit être exécuté dans l'AsyncTask, par exemple:
         *  -Une requête de base de données
         *  -Un appel à un Web Service
         *  -...45
         */


    }

    @Override
    protected void onPostExecute(final Boolean success) {
        /*
         * Ici, le code exécuté une fois le traitement terminé, par exemple:
         *  -Mise à jour de l'affichage
         *  -Affichage d'une pop-up indiquant la fin du traitement
         *  -Désactivation de la ProgressBar
         *  -...
         */

        //arret de la progressbarre
        progressDialog.dismiss();
        Intent intent = new Intent(context, PageResultat.class);
        intent.putExtra("deviseDepart",source);
        intent.putExtra("deviseArrivee",cible);
        intent.putExtra("resultat",resultat);
        intent.putExtra("montantInitial", montant);

        context.startActivity(intent);
    }

    @Override
    protected void onCancelled() {

    }

}
