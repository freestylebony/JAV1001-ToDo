package com.hafeesfalana.hafees_todo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     //declare the variables
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
     Button button;
    EditText userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate the variables
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        //set onClick listener for the button
        button.setOnClickListener(this);
           // instantiate the arraylist items
          items = new ArrayList<>();
          itemsAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1,items);
          listView.setAdapter(itemsAdapter);
          //call this method to work on list view elements
          setUpListView();

    }
   // method setUpListView to delete the list view on long click/press ( to do items)
    private void setUpListView() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "item removed", Toast.LENGTH_LONG).show();

                items.remove(i);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    @Override
    public void onClick(View view) {
        // call the method addToDoItem
          addTodoItem(view);
    }
     // method addToDoItem
    private void addTodoItem(View view) {
        userInput = findViewById(R.id.toDoItem);
        String userText = userInput.getText().toString();
        //we need to verify that user does not submit empty string

         if(!(userText.equals(""))){

             // add user input to the list view
             itemsAdapter.add(userText);

             //set the edit text back to empty
             userInput.setText("");

         }else{
              //Show error message to user
             Toast.makeText(getApplicationContext(), "Please enter your todo Item", Toast.LENGTH_LONG).show();
         }
    }
}