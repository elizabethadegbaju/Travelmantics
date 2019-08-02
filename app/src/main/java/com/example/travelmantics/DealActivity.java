package com.example.travelmantics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.travelmantics.FirebaseUtil.mDatabaseReference;
import static com.example.travelmantics.FirebaseUtil.openFbReference;

public class DealActivity extends AppCompatActivity {
    EditText txtTitle;
    EditText txtPrice;
    EditText txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        openFbReference("traveldeals");

        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this,"Deal saved!", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clean() {
        txtPrice.setText("");
        txtDescription.setText("");
        txtTitle.setText("");
        txtTitle.requestFocus();
    }

    private void saveDeal() {
        String title = txtTitle.getText().toString();
        String description = txtDescription.getText().toString();
        String price = txtPrice.getText().toString();
        TravelDeal deal = new TravelDeal(title,description,price,"");
        mDatabaseReference.push().setValue(deal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
}
