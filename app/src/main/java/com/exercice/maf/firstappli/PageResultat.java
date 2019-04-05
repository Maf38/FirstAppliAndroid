package com.exercice.maf.firstappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PageResultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_resultat);
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
}
