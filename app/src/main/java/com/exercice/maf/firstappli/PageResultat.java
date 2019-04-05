package com.exercice.maf.firstappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class PageResultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_resultat);

        //Une petite flèche apparait alors dans l’action bar à gauche du titre de l’application
        getSupportActionBar().setDisplayHomeAsUpEnabled(true) ;

        //On récupère l’Intent que l’on a reçu
        Intent thisIntent = getIntent();
        //On récupère les parametres passés
        String deviseDepart = thisIntent.getExtras().getString("deviseDepart");
        String deviseArrivee = thisIntent.getExtras().getString("deviseArrivee");
        String resultat =thisIntent.getExtras().getString("resultat");
        String montantInitial =thisIntent.getExtras().getString("montantInitial");


        TextView affichage = (TextView) findViewById(R.id.txtResult);

        affichage.setText("  "+ montantInitial+ " "+deviseDepart+" font "+ resultat+ " "+ deviseArrivee+ ".");
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
    //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menumaf, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
    //On teste l’Id de l’item cliqué et on déclenche une action

        boolean ok=false;

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "t'as selectionné l'item1", Toast.LENGTH_SHORT).show();
                ok = true;
            case R.id.item2:
                Toast.makeText(this, "t'as selectionné l'item2", Toast.LENGTH_SHORT).show();
                ok = true;
            case R.id.quitter:
                Toast.makeText(this, "t'aimerais bien quitter!...c'est bien", Toast.LENGTH_SHORT).show();
                ok=true;
        }
        return ok;}
}
