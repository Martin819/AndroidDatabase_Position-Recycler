package cz.uhk.fim.brahavl1.pubdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;

import butterknife.BindView;
import cz.uhk.fim.brahavl1.pubdatabase.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    public android.support.v7.widget.RecyclerView recyclerView;
    private RecyclerViewAdapter adapter; //staticka trida - vezme data - seznam a sazi obsah dat do ui komponentu
    private android.support.v7.widget.RecyclerView.LayoutManager layoutManager;
    private PubData pubData;

    DatabaseHelper databaseHelper;

//    private PersonsData personsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, AddActivity.class );
                startActivity(intent);
            }
        });

        databaseHelper = new DatabaseHelper(this); //tady vytvářím databaze heleper - pustí se poprve on create metoda

        recyclerView = findViewById(R.id.recyclerView); //vytahnuli jsme recycler view do javy

        layoutManager = new LinearLayoutManager(this); //vytovreni layoutu a nastaveni
        recyclerView.setLayoutManager(layoutManager); //propojeni


        pubData = databaseHelper.getAllPubs();


//        pubData = new PubData(); //prazdny
//        pubData = new PubData().newTestInstance(); //testovaci data

        // ADAPTER
        adapter = new RecyclerViewAdapter(pubData);
//        adapter.setOnItemDeleteClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        pubData.clear();
        pubData.addAll(databaseHelper.getAllPubs());
        adapter.notifyDataSetChanged();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, MapsActivity.class); //todo
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
