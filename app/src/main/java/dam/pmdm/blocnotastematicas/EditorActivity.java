package dam.pmdm.blocnotastematicas;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class EditorActivity extends AppCompatActivity {

    private String nombreFichero;
    private EditText etContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        EdgeToEdge.enable(this);


        etContenido = findViewById(R.id.etContenido);

        // Obtener el ID del Intent
        int idCategoria = getIntent().getIntExtra("ID_CATEGORIA", -1);

        if (idCategoria != -1) {
            configurarToolbar(idCategoria);
            leerFichero();
        } else {
            Toast.makeText(this, "Error: Categoría no encontrada", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void configurarToolbar(int id) {
        MaterialToolbar toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);

        int nombreId;
        int colorId;

        if (id == R.id.cat_01) {
            nombreId = R.string.str_urgente;
            colorId = R.color.col_urgente;
        } else if (id == R.id.cat_02) {
            nombreId = R.string.str_viajes;
            colorId = R.color.col_viajes;
        } else if (id == R.id.cat_03) {
            nombreId = R.string.str_conciertos;
            colorId = R.color.col_conciertos;
        } else if (id == R.id.cat_04) {
            nombreId = R.string.str_familia;
            colorId = R.color.col_familia;
        } else if (id == R.id.cat_05) {
            nombreId = R.string.str_amigos;
            colorId = R.color.col_amigos;
        } else if (id == R.id.cat_06) {
            nombreId = R.string.str_deportes;
            colorId = R.color.col_deportes;
        } else if (id == R.id.cat_07) {
            nombreId = R.string.str_comida;
            colorId = R.color.col_comida;
        } else if (id == R.id.cat_08) {
            nombreId = R.string.str_tecnologia;
            colorId = R.color.col_tecnologia;
        } else if (id == R.id.cat_09) {
            nombreId = R.string.str_ropa;
            colorId = R.color.col_ropa;
        } else if (id == R.id.cat_10) {
            nombreId = R.string.str_cine;
            colorId = R.color.col_cine;
        } else {
            nombreId = R.string.app_name;
            colorId = R.color.black;
        }

        // Asignamos el nombre del fichero basado en el recurso de texto
        String nombreStr = getString(nombreId);
        this.nombreFichero = nombreStr + ".txt";

        // Aplicamos el color al toolbar
        getSupportActionBar().setTitle(nombreStr);
        int color = getResources().getColor(colorId, getTheme());
        toolbar.setBackgroundColor(color);
        getWindow().setStatusBarColor(color);
    }

    // --- GESTIÓN DEL MENÚ ---

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            guardarFichero();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // --- LÓGICA DE FICHEROS ---
    // Usamos Try-with-resources ya que cierra el flujo automáticamente.

    private void leerFichero() {
        try (FileInputStream fis = openFileInput(nombreFichero);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)) {

            StringBuilder sb = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea).append("\n");
            }
            etContenido.setText(sb.toString());

        } catch (Exception e) {
            // Si el fichero no existe, no hacemos nada (primera vez)
        }
    }

    private void guardarFichero() {
        try (FileOutputStream fos = openFileOutput(nombreFichero, Context.MODE_PRIVATE);
             OutputStreamWriter osw = new OutputStreamWriter(fos)) {

            osw.write(etContenido.getText().toString());
            Toast.makeText(this, R.string.cambios_guardados, Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_al_guardar, Toast.LENGTH_SHORT).show();
        }
    }

}