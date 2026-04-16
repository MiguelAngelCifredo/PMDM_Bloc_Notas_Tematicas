package dam.pmdm.blocnotastematicas;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        configurarBoton(R.id.cat_01, R.string.str_urgente);
        configurarBoton(R.id.cat_02, R.string.str_viajes);
        configurarBoton(R.id.cat_03, R.string.str_conciertos);
        configurarBoton(R.id.cat_04, R.string.str_familia);
        configurarBoton(R.id.cat_05, R.string.str_amigos);
        configurarBoton(R.id.cat_06, R.string.str_deportes);
        configurarBoton(R.id.cat_07, R.string.str_comida);
        configurarBoton(R.id.cat_08, R.string.str_tecnologia);
        configurarBoton(R.id.cat_09, R.string.str_ropa);
        configurarBoton(R.id.cat_10, R.string.str_cine);
    }

    private void configurarBoton(int viewId, int stringResId) {
        findViewById(viewId).setOnClickListener(v -> {
            String nombreCategoria = getString(stringResId);

            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
            intent.putExtra("CATEGORIA", nombreCategoria);
            startActivity(intent);
        });
    }
}