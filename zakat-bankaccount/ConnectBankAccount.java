package org.example;

import java.util.Date;
import java.util.Random;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

class Card{
    private int CardId;
    private String CardPassword;
    public Card(int id, String pass){
        this.CardId = id;
        this.CardPassword = pass;
    }
    public int getId(){
        return this.CardId;
    }
    public String getpass(){
        return this.CardPassword;
    }
    public boolean IsFound(Card card){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("Cards.json");
        if (!file.exists()) {
            return false;
        }
        String reader = null;
        Card[] cardsArray = gson.fromJson(reader, Card[].class);
        List<Card> cards = new ArrayList<>(List.of(cardsArray));
        for(Card current : cards) {
            if (current!=null && this.CardId == current.getId() && this.CardPassword.equals(current.getpass())){
                return true;
            }
        }
        return false;
    }
}

class OTP{
    int otpcode;
    Date expaityDate;
    public void SendOTP() {
        Random random = new Random();
        this.otpcode = random.nextInt(100000);
    }
    public boolean VerifyOTP(int code){
        return code==this.otpcode;
    }
}

class ConnectBankAccount{
    Card card;
    int code;
    OTP otp;
    public boolean CheckCard(Card card){
        return card.IsFound(card);
    }
    public void Connect(int code, Card card){
        if(otp.VerifyOTP(code)){
            System.out.println("Card is connected");
        }
        else{
            System.out.println("OTP is not connected");
        }
    }
}