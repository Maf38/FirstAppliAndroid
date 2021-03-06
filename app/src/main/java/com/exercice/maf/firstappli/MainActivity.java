package com.exercice.maf.firstappli;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import static metier.Convertisseur.convertir;
import static metier.Convertisseur.getConversionTable;

public class MainActivity extends AppCompatActivity {

private Spinner spinDepart;
private Spinner spinArrivee;
private EditText montant;
private ImageView optionView;
private ArrayAdapter <String> adapter;
private Intent intentPrincipale ;
private boolean fond = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initBouton();



        //intentPrincipale = new Intent(this, MainActivity.class);
        //intentPrincipale.putExtra("skinPrincipale", "default");

    }

    @Override
    protected void onRestart() {


        super.onRestart();
       /* if (intentPrincipale.getExtras().getString("skinPrincipale").equals("default")) {
            setContentView(R.layout.activity_main);
            initBouton();
        } else {
            setContentView(R.layout.activity_main2);
            initBouton();
        }
        */

    }

    private void initBouton(){
        spinDepart = (Spinner) findViewById(R.id.spinDepart);
        spinArrivee =(Spinner) findViewById(R.id.spinArrivee);
        montant = (EditText) findViewById(R.id.editMontant);
        optionView= (ImageView) findViewById(R.id.imageViewOption);

        ArrayList <String> toto = new ArrayList<>(getConversionTable().keySet());
        adapter = new ArrayAdapter <String> (this,android.R.layout.simple_spinner_item, toto);
        //Définir le style des éléments de l'adapteur
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinDepart.setAdapter(adapter);
        spinArrivee.setAdapter(adapter);

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



    public boolean calculer (View v)
    {

        boolean ok= true;

        Double resultat;

        String deviseDepart = spinDepart.getSelectedItem().toString();
        String deviseArrivee = spinArrivee.getSelectedItem().toString();

        if (!montant.getText().toString().isEmpty() && !montant.getText().toString().equals(".")) { //cas où on a saisi un montant
            Double montantAConvertir = Double.parseDouble(montant.getText().toString());
            resultat = convertir(deviseDepart, deviseArrivee, montantAConvertir);
            //Toast.makeText(this, resultat.toString(), Toast.LENGTH_SHORT).show();

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
