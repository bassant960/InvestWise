package org.example;

import java.util.Calendar;
import java.util.Random;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

class Card{
    private int CVV;
    private String CardNumber;
    private int PIN;
    public Card(int cvv, String num, int pin){
        this.CVV = cvv;
        this.CardNumber = num;
        this.PIN = pin;
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
        if(now.after(calendar)){
            System.out.println("Time expired\n");
            return false;
        }
        if(code!=this.otpcode){
            System.out.println("Wrong code\n");
        }
        return (code==this.otpcode);
    }
}

