package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        // Obtener referencias a las vistas del diseño (nombres de jugadores y boton de comienzo)
        EditText playerOne = findViewById(R.id.playerOne);
        EditText playerTwo = findViewById(R.id.playerTwo);
        Button empezarButton = findViewById(R.id.startGameButton);

        empezarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                //Valida si los nombres existen
                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Por favor ingrese el nombre del jugador", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });

    }
}