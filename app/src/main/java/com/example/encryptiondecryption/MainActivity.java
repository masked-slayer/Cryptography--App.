package com.example.encryptiondecryption;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {

    private String alias = ""; // this string is like an "alias"

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1, button2;
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);


        EditText text1,text2,text4,text5,text3,text6;

        text1 = findViewById(R.id.edit1);
        text2 = findViewById(R.id.edit2);
        text3 = findViewById(R.id.edit3);
        text4 = findViewById(R.id.edit4);
        text5 = findViewById(R.id.edit5);
        text6 = findViewById(R.id.edit6);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    alias = text2.getText().toString();
                    String encryptedText = EncryptDecrypt.encrypt(String.valueOf(text1.getText()),alias);
                    text3.setText(encryptedText);
                    text1.setText(" ");
                    text2.setText(" ");
                } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
                    e.printStackTrace();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                   if(alias.equals(text5.getText().toString()))
                   {
                       String decryptedText = EncryptDecrypt.decrypt(text4.getText().toString(),alias);
                       text6.setText(decryptedText);
                       text4.setText(" ");
                       text5.setText(" ");
                   }else
                   {
                       text5.setText("Wrong key enterred , try again");
                   }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}