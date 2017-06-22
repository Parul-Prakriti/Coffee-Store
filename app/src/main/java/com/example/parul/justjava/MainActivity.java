package com.example.parul.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    int quantity = 1;
    int PriceOfCoffee =10;
    int totPrice;

    public void submitOrder(View view) {
        EditText nameText = (EditText) findViewById(R.id.editText) ;
        String name = nameText.getText().toString();
        CheckBox Whippingcb = (CheckBox)findViewById(R.id.checkBox);
        boolean hasWhippedChecked = Whippingcb.isChecked();
        CheckBox Chococb = (CheckBox)findViewById(R.id.checkBox2);
        boolean hasChocoChecked = Chococb.isChecked();

        if (hasChocoChecked==true)
           PriceOfCoffee = PriceOfCoffee+2;
        if (hasWhippedChecked==true)
            PriceOfCoffee = PriceOfCoffee+1;

        totPrice = quantity * PriceOfCoffee;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order Summary for "+name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary(name,quantity,totPrice,hasWhippedChecked,hasChocoChecked));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

     public void funcI(View view){
         if(quantity==20){
             Toast.makeText(this,"You cannot order more than 20 coffees",Toast.LENGTH_SHORT).show();
             return;
         }
         quantity = quantity + 1;
         display(quantity);
     }

     public void funcD(View view){
         if(quantity==0){
             Toast.makeText(this,"You cannot order less than 0 coffees",Toast.LENGTH_SHORT).show();
             return;
         }

         quantity=quantity-1;
         display(quantity);

     }

     private String orderSummary(String name, int quantity,int price, boolean state1, boolean state2){
         String orderString="Hey, "+name + "\nYou've ordered "+ quantity +" cups of coffee"+"\nWhipped cream ordered: "+state1+ "\nChocolate Topping: "+state2+"\nTotal price "+price+ " dollars";
                 return orderString;
     }

}