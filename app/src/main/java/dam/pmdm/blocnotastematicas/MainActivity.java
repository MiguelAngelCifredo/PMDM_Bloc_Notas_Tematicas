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
        // Configuración de la rejilla según orientación
        GridLayout gridLayout = findViewById(R.id.miGridLayout);
        boolean esLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        gridLayout.setColumnCount(esLandscape ? 5 : 2);
        gridLayout.setRowCount(esLandscape ? 2 : 5);
    }

    private void configurarBoton(int viewId) {
        findViewById(viewId).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            intent.putExtra("ID_CATEGORIA", v.getId());
            startActivity(intent);
        });
    }
}