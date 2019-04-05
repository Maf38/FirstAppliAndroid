package com.exercice.maf.firstappli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static metier.Convertisseur.convertir;
import static metier.Convertisseur.getConversionTable;

public class MainActivity extends AppCompatActivity {

private Spinner spinDepart;
private Spinner spinArrivee;
private EditText montant;
private ArrayAdapter <String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinDepart = (Spinner) findViewById(R.id.spinDepart);
        spinArrivee =(Spinner) findViewById(R.id.spinArrivee);
        montant = (EditText) findViewById(R.id.editMontant);

        ArrayList <String> toto = new ArrayList<>(getConversionTable().keySet());
        adapter = new ArrayAdapter <String> (this,android.R.layout.simple_spinner_item, toto);
        //Définir le style des éléments de l'adapte
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinDepart.setAdapter(adapter);
        spinArrivee.setAdapter(adapter);

    }

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

}
