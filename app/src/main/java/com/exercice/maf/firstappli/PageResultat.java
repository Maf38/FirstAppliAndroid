package com.exercice.maf.firstappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class PageResultat extends AppCompatActivity {

    private ViewAnimator viewAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_resultat);
        viewAnimator = (ViewAnimator) this.findViewById(R.id.pageResultat);



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



        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "t'as selectionné l'item1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "t'as selectionné l'item2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.quitter:
                Toast.makeText(this, "t'aimerais bien quitter!...c'est bien", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;}



    public void rotation(View view)
    {
        // Pour lancer la rotation
        AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.LEFT_RIGHT);
    }
}
