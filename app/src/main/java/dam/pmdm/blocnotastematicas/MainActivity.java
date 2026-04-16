package dam.pmdm.blocnotastematicas;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        configurarGiroDePantalla();

        configurarBoton(R.id.cat_01);
        configurarBoton(R.id.cat_02);
        configurarBoton(R.id.cat_03);
        configurarBoton(R.id.cat_04);
        configurarBoton(R.id.cat_05);
        configurarBoton(R.id.cat_06);
        configurarBoton(R.id.cat_07);
        configurarBoton(R.id.cat_08);
        configurarBoton(R.id.cat_09);
        configurarBoton(R.id.cat_10);
    }

    private void configurarGiroDePantalla() {
        int orientacion = getResources().getConfiguration().orientation;
        int filas;
        int columnas;

        if (orientacion == Configuration.ORIENTATION_LANDSCAPE) {
            filas = 2;
            columnas = 5;
        } else {
            filas = 5;
            columnas = 2;
        }
        GridLayout grid = findViewById(R.id.miGridLayout);
        grid.setColumnCount(columnas);
        grid.setRowCount(filas);
    }

    private void configurarBoton(int viewId) {
        findViewById(viewId).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            intent.putExtra("ID_CATEGORIA", v.getId());
            startActivity(intent);
        });
    }
}