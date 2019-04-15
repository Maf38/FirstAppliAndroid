package com.exercice.maf.firstappli;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import java.util.ArrayList;

import metier.ConvertisseurSOAP;

public class AsyncTaskGetListeDevise extends AsyncTask<Void, Void, Boolean>
{
    // On a besoin du contexte pour replacer l'AsyncTask
    private Context context;
    // On récupère l'activité d'appel, au cas où besoin dans le traitement
    private Activity activity;
    private ArrayList<String> listeDevise;
    private ConvertisseurSOAP soap;

    ProgressDialog progressDialog;

    /*
     * Constructeur de l'asyncTask.
     * @param context
     */
    public AsyncTaskGetListeDevise(Activity activity) {
        this.context = activity.getApplicationContext();
        this.activity = activity;
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
         progressDialog = ProgressDialog.show(activity, "Please wait...","Loading Currencies spinner...", true);

    }

    @Override
    protected Boolean doInBackground(Void... arg0) {

       soap= new ConvertisseurSOAP();


        return null;
        /*
         * Ici, le code qui doit être exécuté dans l'AsyncTask, par exemple:
         *  -Une requête de base de données
         *  -Un appel à un Web Service
         *  -...
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

        listeDevise= soap.getConversionTable();

        //arret de la progressbarre
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {

    }

    public ArrayList<String> getListeDevise() {
        return listeDevise;
    }

    public void setListeDevise(ArrayList<String> listeDevise) {
        this.listeDevise = listeDevise;
    }
}
