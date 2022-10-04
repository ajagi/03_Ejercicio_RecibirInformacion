package com.example.recibirinformacion;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recibirinformacion.modelos.BiciModel;
import com.example.recibirinformacion.modelos.CocheModel;
import com.example.recibirinformacion.modelos.MotoModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Atributtos Vista

    private TextView lblCantidadCoches;
    private TextView lblCantidadMotos;
    private TextView lblCantidadBicis;
    private Button btnCrearCoche;
    private Button btnCrearMoto;
    private Button btnCrearBici;

    //Atributos Logica

    private ArrayList<CocheModel> listaCoches;
    private ArrayList<MotoModel> listaMoto;
    private ArrayList<BiciModel> listaBici;

    //Atributos Eventos

    private ActivityResultLauncher<Intent> crearCocheLauncher;
    private ActivityResultLauncher<Intent> crearMotoLauncher;
    private ActivityResultLauncher<Intent> crearBiciLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVariables();

        inicializarLauncher();

        btnCrearCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearCocheLauncher.launch(new Intent(MainActivity.this, AddCoches.class));

            }
        });
    }

    private void inicializarLauncher() {

        crearCocheLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                CocheModel coche = (CocheModel) result.getData().getExtras().getSerializable("COCHE");
                                if (coche != null) {
                                    listaCoches.add(coche);
                                    lblCantidadCoches.setText("Coche : " + listaCoches.size());
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "NO ESTAN LOS DATOS", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Ventana Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        crearMotoLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                MotoModel moto = (MotoModel) result.getData().getExtras().getSerializable("MOTO");
                                if (moto != null) {
                                    listaMoto.add(moto);
                                    lblCantidadMotos.setText("Motos : " + listaMoto.size());
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "NO ESTAN LOS DATOS", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Ventana Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        crearBiciLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null && result.getData().getExtras() != null) {
                                BiciModel bici = (BiciModel) result.getData().getExtras().getSerializable("BICI");
                                if (bici != null) {
                                    listaBici.add(bici);
                                    lblCantidadBicis.setText("Bicis : " + listaBici.size());
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "NO ESTAN LOS DATOS", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Ventana Cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }

    private void inicializaVariables() {

        lblCantidadCoches = findViewById(R.id.txtAddCocheMain);
        lblCantidadMotos = findViewById(R.id.txtAddMotoMain);
        lblCantidadBicis = findViewById(R.id.txtAddBiciMain);
        btnCrearCoche = findViewById(R.id.btnAddCocheMain);
        btnCrearMoto = findViewById(R.id.btnAddMotoMain);
        btnCrearBici = findViewById(R.id.btnAddBiciMain);

        listaBici = new ArrayList<>();
        listaMoto = new ArrayList<>();
        listaCoches = new ArrayList<>();
    }
}