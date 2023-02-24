package com.example.lab__7;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Character> characters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        new GetCharactersTask().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Check if FrameLayout is phone or tablet
                FrameLayout frameLayout = findViewById(R.id.details_container);
                if (frameLayout == null) {
                    // Phone mode
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("characterName", characters.get(position).getName());
                    intent.putExtra("characterHeight", characters.get(position).getHeight());
                    intent.putExtra("characterMass", characters.get(position).getMass());
                    startActivity(intent);
                } else {
                    // Tablet mode
                    DetailsFragment detailsFragment = new DetailsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("characterName", characters.get(position).getName());
                    bundle.putString("characterHeight", characters.get(position).getHeight());
                    bundle.putString("characterMass", characters.get(position).getMass());
                    detailsFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.details_container, detailsFragment)
                            .commit();
                }
            }
        });
    }
    private class GetCharactersTask extends AsyncTask<Void, Void, List<Character>> {

        @Override
        protected List<Character> doInBackground(Void... voids) {
            List<Character> characters = new ArrayList<>();

            try {
                URL url = new URL("https://swapi.dev/api/people/?format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray resultsArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject characterObject = resultsArray.getJSONObject(i);
                    String name = characterObject.getString("name");
                    String height = characterObject.getString("height");
                    String mass = characterObject.getString("mass");
                    characters.add(new Character(name, height, mass));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return characters;
        }

        @Override
        protected void onPostExecute(List<Character> characters) {
            MainActivity.this.characters = characters;
            CharacterListAdapter adapter = new CharacterListAdapter(MainActivity.this, characters);
            listView.setAdapter(adapter);
        }
    }
}