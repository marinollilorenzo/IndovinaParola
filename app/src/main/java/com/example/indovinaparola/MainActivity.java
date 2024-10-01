package com.example.indovinaparola;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String parola = "forchetta";

    String nuovaStringa;

    TextView errori, indovina, win;

    EditText letteraDaInserire;

    Button bottone;

    int mosse;

    ArrayList<String> lettere;

    boolean finito;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errori = findViewById(R.id.errori);
        win = findViewById(R.id.win);
        letteraDaInserire = findViewById(R.id.editText);
        bottone = findViewById(R.id.button);
        indovina = findViewById(R.id.indovina);
        errori.setText("" + mosse);
        indovina.setText("");
        for(int i = 0; i < parola.length(); i ++)
            indovina.setText(indovina.getText() + "-");
        lettere = new ArrayList<>();
        nuovaStringa = indovina.getText().toString();
        finito = false;

        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finito) {
                    gioca();
                    if (checkWin()) {
                        finito = true;
                        win.setText("HAI VINTO!");
                    } else if (checkLose()) {
                        finito = true;
                        win.setText("HAI PERSO!");
                    }
                }

            }
        });
    }
    public void gioca(){
        String lettera = letteraDaInserire.getText().toString();
        boolean testIndovina = true, testDoppia = checkDouble(lettera);
        if(!testDoppia) {
            for (int i = 0; i < parola.length(); i++)
                if (parola.substring(i, i + 1).equals(lettera)) {
                    mostraLettera(i, lettera);
                    testIndovina = false;
                    lettere.add(lettera);
                }
        }
        if (testIndovina || testDoppia) {
            mosse++;
            errori.setText("" + mosse);
        }
    }
    public void mostraLettera(int x, String lettera){
        nuovaStringa = nuovaStringa.substring(0, x) + lettera + nuovaStringa.substring(x+1);
        indovina.setText(nuovaStringa);
    }
    public boolean checkDouble(String lettera){
        for(int i = 0; i < lettere.size(); i ++)
            if(lettere.get(i).equals(lettera))
                return true;
        return false;
    }
    public boolean checkWin() {
        return nuovaStringa.equals(parola);
    }
    public boolean checkLose() {
        return mosse == 5;
    }
}
