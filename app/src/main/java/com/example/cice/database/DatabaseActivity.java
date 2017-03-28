package com.example.cice.database;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseActivity extends AppCompatActivity {

    TextView idView;
    EditText productBox;
    EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        quantityBox = (EditText) findViewById(R.id.productQ);
    }

    public void addProduct(View v){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        int quantity = Integer.parseInt(quantityBox.getText().toString());

        Product product = new Product(productBox.getText().toString(), quantity);
        dbHandler.addProduct(product);
        productBox.setText("");
        quantityBox.setText("");
    }

    public void deleteProduct(View v){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteProduct(productBox.getText().toString());
        if (result){
            idView.setText("Entrada eliminada");
            productBox.setText("");
            quantityBox.setText("");

        } else {
            idView.setText("No se ha encontrado nada");
        }
    }

    public  void findProduct(View v){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Product product = dbHandler.findProduct(productBox.getText().toString());
        if(product != null){
            idView.setText(String.valueOf(product.getmId()));
            quantityBox.setText(String.valueOf(product.getmQuantity()));

        } else {
            idView.setText("No se ha encontrado nada");
        }
    }
}
