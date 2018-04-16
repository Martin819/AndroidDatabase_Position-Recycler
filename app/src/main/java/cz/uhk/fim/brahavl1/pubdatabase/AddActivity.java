package cz.uhk.fim.brahavl1.pubdatabase;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.uhk.fim.brahavl1.pubdatabase.database.DatabaseHelper;

/**
 * Created by brahavl1 on 16.04.2018.
 */

public class AddActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @BindView(R.id.editName)
    EditText editName;

    @BindView(R.id.editLat)
    EditText editLat;


    @BindView(R.id.editLng)
    EditText editLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuItemAdd) {
            String name = editName.getText().toString();
            double lat = Double.parseDouble(editLat.getText().toString());
            double lng = Double.parseDouble(editLng.getText().toString());

            Pub pub = new Pub(name, lat, lng);

            if (databaseHelper.savePub(pub)) {
                finish();
            } else {
                Toast.makeText(this, "error", Toast.LENGTH_LONG);
            }

            databaseHelper.savePub(pub);

            return true;


        }
        return super.onOptionsItemSelected(item);
    }
}

