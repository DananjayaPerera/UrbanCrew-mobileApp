package com.example.urban_crew_extended;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentCard extends AppCompatActivity {

    String cardName,cardNumber,cardExpirationDate,cardCCV;
    TextView desc;
    Button btnPay;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_card);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final CardForm cardForm = findViewById(R.id.alto_CardForm);

        desc = findViewById(R.id.payment_amount);
        desc.setText("Rs 6000.00 LKR");

        btnPay = findViewById(R.id.btn_pay);
        btnPay.setText(String.format("Payer %s", desc.getText()));

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {

                cardName = card.getName().toString();
                cardNumber = card.getNumber().toString();
                cardExpirationDate = card.getExpYear().toString();
                cardCCV = card.getCVC().toString();

                SendInput();
            }
        });
    }

    public void SendInput(){

        DatabaseReference AltoCard = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Payment Options").child("Card Payment").child("Card Details");
        CardProfile cardProfile = new CardProfile(cardName,cardNumber,cardExpirationDate,cardCCV);
        AltoCard.setValue(cardProfile);
    }
}
