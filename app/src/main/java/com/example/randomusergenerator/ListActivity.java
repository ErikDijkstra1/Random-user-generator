package com.example.randomusergenerator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, PersonAdapter.OnItemClickListener {
    private static final String LOGTAG = MainActivity.class.getName();
    private RecyclerView personRecyclerView;
    private SearchView filterView;
    private PersonAdapter personAdapter;
    private ArrayList<Person> personList;
    private ImageView logo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        PersonManager.setAppContext(getApplicationContext());

        personList = PersonManager.getPeople();
        Log.d(LOGTAG, personList.size() + " persons");

        personRecyclerView = findViewById(R.id.personListView);
        personAdapter = new PersonAdapter(this, personList, this);
        personRecyclerView.setAdapter(personAdapter);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        filterView = findViewById(R.id.listFilter);

        filterView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        logo = findViewById(R.id.logo);
//        logo.setImageResource();        //TODO make logo

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {      //TODO change prints
        Log.d(LOGTAG, "AdapterView.OnItemClickListener version of onItemClick() called with position = " + position);
        navigateToDetailActivity(position);
    }

    @Override
    public void onItemClick(int clickedPosition) {      //TODO change prints
        Log.d(LOGTAG, "PersonAdapter.OnItemClickListener version of onItemClick() called with position = " + clickedPosition);
        navigateToDetailActivity(clickedPosition);
    }

    private void navigateToDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.PERSON_ID, position);
        startActivity(intent);
    }

    private void filterList(String filter) {
        ArrayList<Person> filteredList = new ArrayList<>();
        for (Person person : personList) {
            if (person.getLastName().contains(filter)) {
                filteredList.add(person);
            }
        }
        personAdapter = new PersonAdapter(this, filteredList, this);
        personRecyclerView.setAdapter(personAdapter);

    }

}
