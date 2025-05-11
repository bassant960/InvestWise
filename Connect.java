package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Calendar;
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

class BankAccount{

    OTP otp;
    public BankAccount(){
        this.otp = new OTP();
    }
    public void ConnectAccount(PortfolioManager manager, Card card, int code){
        if(!otp.VerifyOTP(code)){
            return;
        }
        manager.connectcard(card);
        manager.saveCardsToFile();
        System.out.println("Card connected to Bank");

    }
}

class OTP{
    int otpcode;
    Calendar calendar;
    int expairyDate;
    public OTP() {
        Random random = new Random();
        this.otpcode = random.nextInt(100000);
        this.expairyDate = 30;
        calendar = Calendar.getInstance();
        System.out.println("OTP code is " + this.otpcode);
    }
    public boolean VerifyOTP(int code){
        calendar.add(Calendar.SECOND, expairyDate);
        Calendar now = Calendar.getInstance();
        if(!now.equals(calendar)){
            System.out.println("Time expired\n");
            return false;
        }
        if(code!=this.otpcode){
            System.out.println("Wrong code\n");
        }
        return (code==this.otpcode);
    }
}

