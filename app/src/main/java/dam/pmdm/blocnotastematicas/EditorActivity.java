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

        com.google.android.material.appbar.MaterialToolbar toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);

        etContenido = findViewById(R.id.etContenido);

        // Recuperar el nombre de la categoría. Si no se proporciona entonces finalizar.
        String categoria = getIntent().getStringExtra("CATEGORIA");
        if (categoria != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(categoria);
            }
            nombreFichero = categoria + ".txt";
            leerFichero();
        } else {
            finish();
        }
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