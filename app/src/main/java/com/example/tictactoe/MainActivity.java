package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<int[]> listaCombinaciones = new ArrayList<>();
    private int[] cajasPosiciones = {0,0,0,0,0,0,0,0,0}; //9 zero
    private int turnoJugador = 1;
    private int cajasSelect = 1;


    // Método llamado al iniciar la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // combinaciones ganadoras posibles en el juego
        listaCombinaciones.add(new int[] {0,1,2});
        listaCombinaciones.add(new int[] {3,4,5});
        listaCombinaciones.add(new int[] {6,7,8});
        listaCombinaciones.add(new int[] {0,3,6});
        listaCombinaciones.add(new int[] {1,4,7});
        listaCombinaciones.add(new int[] {2,5,8});
        listaCombinaciones.add(new int[] {2,4,6});
        listaCombinaciones.add(new int[] {0,4,8});

        //nombres de jugadores
        String jugadorUno = getIntent().getStringExtra("playerOne");
        String jugadorDos = getIntent().getStringExtra("playerTwo");

        binding.playerOneName.setText(jugadorUno);
        binding.playerTwoName.setText(jugadorDos);


        //listeners de clic para cada caja en el tablero
        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)){
                    performAction((ImageView) view, 0);
                }
            }
        });

        binding.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)){
                    performAction((ImageView) view, 1);
                }
            }
        });
        binding.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)){
                    performAction((ImageView) view, 2);
                }
            }
        });
        binding.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)){
                    performAction((ImageView) view, 3);
                }
            }
        });
        binding.image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)){
                    performAction((ImageView) view, 4);
                }
            }
        });
        binding.image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)){
                    performAction((ImageView) view, 5);
                }
            }
        });
        binding.image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)){
                    performAction((ImageView) view, 6);
                }
            }
        });
        binding.image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)){
                    performAction((ImageView) view, 7);
                }
            }
        });
        binding.image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)){
                    performAction((ImageView) view, 8);
                }
            }
        });

    }

    // Método llamado al hacer clic en una caja en el tablero
    private void performAction(ImageView  imageView, int selectedBoxPosition) {
        cajasPosiciones[selectedBoxPosition] = turnoJugador;
        // Marcar la posición seleccionada con el turno del jugador actual
        if (turnoJugador == 1) {
            imageView.setImageResource(R.drawable.ximage);
            // Verificar si hay un ganador o empate, y cambiar al siguiente turno
            if (resultados()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerOneName.getText().toString()
                + " Es el Ganador!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if(cajasSelect == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Empate", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                cambiarDeTurno(2);
                cajasSelect++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (resultados()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerTwoName.getText().toString()
                        + " Es el ganador!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if(cajasSelect == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Empate", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                cambiarDeTurno(1);
                cajasSelect++;
            }
        }
    }

    // Método para cambiar al turno del otro jugador(cambia el marco del player)
    private void cambiarDeTurno(int currentPlayerTurn) {
        turnoJugador = currentPlayerTurn;
        if (turnoJugador == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    // Método para verificar si hay un ganador
    private boolean resultados(){
        boolean response = false;
        for (int i = 0; i < listaCombinaciones.size(); i++){
            final int[] combination = listaCombinaciones.get(i);

            if (cajasPosiciones[combination[0]] == turnoJugador && cajasPosiciones[combination[1]] == turnoJugador &&
            cajasPosiciones[combination[2]] == turnoJugador) {
                response = true;
            }
        }
        return response;
    }

    // Método para verificar si una caja en una posición específica es seleccionable
    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;
        if (cajasPosiciones[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    //Resetea los valores para empezar otra partida
    public void repetirPartida(){
        cajasPosiciones = new int[] {0,0,0,0,0,0,0,0,0}; //9 zero
        turnoJugador = 1;
        cajasSelect = 1;

        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        binding.image9.setImageResource(R.drawable.white_box);
    }
}