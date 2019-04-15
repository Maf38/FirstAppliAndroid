package com.exercice.maf.firstappli;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;



import java.util.ArrayList;


import metier.ConvertisseurXML;


public class MainActivity extends AppCompatActivity {


private static final String PREFS_NAME = "MyPrefsFile";
private Spinner spinDepart;
private Spinner spinArrivee;
private EditText montant;
private ImageView optionView;
private ArrayAdapter <String> adapter;
private boolean fond = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBouton();



        //Creation d'une BDD à l'arrache!!!...puis mis en commentaire (mon telephone n'est pas rooté donc je ne peux pas acceder facilement à la BDD et l'editer)..pas le temps de trouver une soluce
        /*
        DatabaseManager databaseManager= new DatabaseManager(this);
        databaseManager= new DatabaseManager(this);
        databaseManager.insertMoney("Yuan",6.71);
        databaseManager.insertMoney("Dollard US", 1.0);
        databaseManager.insertMoney("Couronne Suédoise", 9.27);
        databaseManager.insertMoney("Bitcoin",0.0002);
        databaseManager.insertMoney("Yen", 112.02);
        databaseManager.insertMoney("Roupie Népalaise", 110.75);
        databaseManager.insertMoney("Franc Suisse", 1.0);
        databaseManager.insertMoney("Euro", 0.88);

        */
        //Toast.makeText(this,listeDevise.get("Yuan").toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {


        super.onRestart();


    }

    private void initBouton(){

        //on récupère la liste des devises sur le web service SOAP
        AsyncTaskGetListeDevise asyncTaskGetListeDevise = new AsyncTaskGetListeDevise(this);
        asyncTaskGetListeDevise.execute();

        //on récupère le fichier de préférences
        SharedPreferences settings=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        //on crée un objet Editor pour travailler sur les preferences
        SharedPreferences.Editor editor = settings.edit();



        //creation des views
        spinDepart = (Spinner) findViewById(R.id.spinDepart);
        spinArrivee =(Spinner) findViewById(R.id.spinArrivee);
        montant = (EditText) findViewById(R.id.editMontant);
        optionView= (ImageView) findViewById(R.id.imageViewOption);






        //*********Conversion XML
            ConvertisseurXML conv= new ConvertisseurXML(this);
            ArrayList <String> toto = new ArrayList<>(conv.getConversionTable().keySet());
        //***************************

        //**********Conversion avec web Service sOAP*************
        //ArrayList <String> toto= asyncTaskGetListeDevise.getListeDevise();


        adapter = new ArrayAdapter <String> (this,android.R.layout.simple_spinner_item, toto);
        //Définir le style des éléments de l'adapteur
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinDepart.setAdapter(adapter);
        spinArrivee.setAdapter(adapter);

        //Toast.makeText(this,"settings spinner depart =" + settings.getString("spinDepart","defValue") , Toast.LENGTH_SHORT).show();

        //Positionnement des spinners suivant les paramètres
        if (!settings.getString("spinDepart","defValue").equals("defValue")){//Le paramètre defValue est retourné si aucune valeur existe
            //cas où on a trouvé une préférence pour le spinner départ
            int spinnerPosition = adapter.getPosition(settings.getString("spinDepart","defValue"));
            spinDepart.setSelection(spinnerPosition);
        }

        if (!settings.getString("spinArrivee","defValue").equals("defValue")){//Le paramètre defValue est retourné si aucune valeur existe
            //cas où on a trouvé une préférence pour le spinner arrivée
            int spinnerPosition = adapter.getPosition(settings.getString("spinArrivee","defValue"));
            spinArrivee.setSelection(spinnerPosition);
        }

        registerForContextMenu(findViewById(R.id.imageViewOption));
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menuprincipale, menu);
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menuprincipale, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {
        //On teste l’Id de l’item cliqué et on déclenche une action



        switch (item.getItemId()) {
            case R.id.menuChangerFond:

                changerFond();
                //intentPrincipale.putExtra("skinPrincipale", "verte") ;
                //Toast.makeText(this, "skin principale = " + intentPrincipale.getExtras().getString("skinPrincipale"), Toast.LENGTH_SHORT).show();
                //finish();

                return true;
            case R.id.menuChangerLangue:
                Intent changerLangue = new Intent( Settings.ACTION_LOCALE_SETTINGS) ;
                startActivity(changerLangue);
                return true;
            case R.id.switchOrientation:
                Switch swFond = (Switch) item;

                swFond.setShowText(true);
                 /*if (swFond.isChecked()){
                     Toast.makeText(this, "t'as selectionné l'orientation isChecked", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     Toast.makeText(this, "t'as selectionné l'orientation is not Checked", Toast.LENGTH_SHORT).show();
                 }*/


                //etRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;

            case R.id.menuQuitter:
                finish();
                return true;
        }
        return false;}


    public boolean onOptionsItemSelected(MenuItem item) {
        //On teste l’Id de l’item cliqué et on déclenche une action



        switch (item.getItemId()) {
            case R.id.menuChangerFond:

                changerFond();


                return true;
            case R.id.menuChangerLangue:
                Intent changerLangue = new Intent( Settings.ACTION_LOCALE_SETTINGS) ;
                startActivity(changerLangue);
                return true;
            case R.id.switchOrientation:
                 Switch swFond = (Switch) item;

                 swFond.setShowText(true);
                 /*if (swFond.isChecked()){
                     Toast.makeText(this, "t'as selectionné l'orientation isChecked", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     Toast.makeText(this, "t'as selectionné l'orientation is not Checked", Toast.LENGTH_SHORT).show();
                 }*/


                //etRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;

            case R.id.menuQuitter:
                finish();
                return true;
        }
        return false;}



    public boolean calculer (View v)
    {




        boolean ok= true;

        Double resultat;

        String deviseDepart = spinDepart.getSelectedItem().toString();
        String deviseArrivee = spinArrivee.getSelectedItem().toString();

        //on récupère le fichier de préférences
        SharedPreferences settings=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        //on crée un objet Editor pour travailler sur les preferences
        SharedPreferences.Editor editor = settings.edit();
        //stockage dans les preferences des positions des spinners
        editor.putString("spinDepart",deviseDepart);
        editor.putString("spinArrivee",deviseArrivee);
        editor.commit();

        if (!montant.getText().toString().isEmpty() && !montant.getText().toString().equals(".")) { //cas où on a saisi un montant
            Double montantAConvertir = Double.parseDouble(montant.getText().toString());
            resultat = ConvertisseurXML.convertir(deviseDepart, deviseArrivee, montantAConvertir);

            Toast.makeText(this, resultat.toString(), Toast.LENGTH_SHORT).show();

            //on fait la conversion sur le web service SOAP
            AsyncTaskConvertir asyncTaskConvertir = new AsyncTaskConvertir(this,deviseDepart,deviseArrivee,montant.getText().toString());
            asyncTaskConvertir.execute();


            Intent intent = new Intent(this, PageResultat.class);
            intent.putExtra("deviseDepart",deviseDepart);
            intent.putExtra("deviseArrivee",deviseArrivee);
            intent.putExtra("resultat",resultat.toString());
            intent.putExtra("montantInitial", montantAConvertir.toString());

            startActivity(intent);
        }
        else{//montant non saisi
            ok= false;
            Toast.makeText(this, "Veuillez saisir un montant!", Toast.LENGTH_SHORT).show();
        }

        return ok;
    }

    public void changerFond (){

       if (fond) {
           setContentView(R.layout.activity_main2);
           fond = false;
       }
       else {
           setContentView(R.layout.activity_main);
           fond = true;
       }
      initBouton();

    }



}
