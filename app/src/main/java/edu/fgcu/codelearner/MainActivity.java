package edu.fgcu.codelearner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private String[] concepts = {
        "Program Structure", "Variables", "Boolean Logic",
            "Operators", "Comparisons", "Conditional Statements",
            "Loops", "Functions/Methods", "Classes", "Objects",
            "Binary", "Java Basics", "C/C++ Basics"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("FGCU codeLearner");

        ArrayAdapter<String> conceptList  = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                this.concepts
        );

        ListView myList = (ListView) findViewById(R.id.listView);
        myList.setAdapter(conceptList);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> conceptList, View myList, int position, long id){
                Object item = conceptList.getItemAtPosition(position);
                String concept = item.toString();
                concept = concept.replaceAll("/","");
                concept = concept.replaceAll(" ", "");
                concept = concept.replaceAll("\\+","");
                if(concept == "CC++Basics"){
                    concept = "CCBasics"; //doesn't like it as a class name
                }
                System.out.println(getPackageName() + "." + concept);
                Class<?> c = null;
                if(concept != null) {
                    try {
                        c = Class.forName("edu.fgcu.codelearner." + concept);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(MainActivity.this, c);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
