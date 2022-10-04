package com.example.recibirinformacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recibirinformacion.modelos.CocheModel;

public class AddCoches extends AppCompatActivity {

    //Variables de la Vista
    private EditText txtMarca;
    private EditText txtModelo;
    private EditText txtColor;
    private Button btnCancelar;
    private Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coches);

        inicializaVariables();

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String marca = txtMarca.getText().toString();
                String modelo = txtModelo.getText().toString();
                String color = txtColor.getText().toString();

                if (!marca.isEmpty() && !modelo.isEmpty() && !color.isEmpty()){
                    CocheModel coche = new CocheModel(marca,modelo,color);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("COCHE",coche);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    Toast.makeText(AddCoches.this,"Faltan Datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializaVariables() {
        txtMarca = findViewById(R.id.txtMarcaAddCoche);
        txtModelo = findViewById(R.id.txtModeloAddCoche);
        txtColor = findViewById(R.id.txtColorAddCoche);
        btnCrear = findViewById(R.id.btnCrearAddCoche);
        btnCancelar = findViewById(R.id.btnCancelarAddBici);
    }
}