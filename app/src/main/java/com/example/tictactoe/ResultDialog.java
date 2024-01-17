package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultDialog extends Dialog {

    private final String mensaje;
    private final MainActivity mainActivity;

    public ResultDialog(@NonNull Context context, String mensaje, MainActivity mainActivity) {
        super(context);
        this.mensaje = mensaje;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dialog);

        //Enlazando elementos
        TextView mensajeTexto = findViewById(R.id.messageText);
        Button empezarDeNuevo = findViewById(R.id.startAgainButton);

        mensajeTexto.setText(mensaje);

        empezarDeNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.repetirPartida();
                dismiss();
            }
        });
    }
}