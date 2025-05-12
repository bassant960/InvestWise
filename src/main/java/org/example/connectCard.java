package org.example;

import java.util.Calendar;
import java.util.Random;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

/**
 * Represents a Card with CVV, CardNumber, and PIN details.
 */
class Card {
    private int CVV;
    private String CardNumber;
    private int PIN;

    /**
     * Constructs a new Card with the given details.
     *
     * @param cvv The CVV of the card.
     * @param num The card number.
     * @param pin The card PIN.
     */
    public Card(int cvv, String num, int pin) {
        this.CVV = cvv;
        this.CardNumber = num;
        this.PIN = pin;
    }
}

/**
 * Represents a BankAccount that manages card connections and OTP verification.
 */
class BankAccount {
    OTP otp;

    /**
     * Constructs a new BankAccount with an OTP instance.
     */
    public BankAccount() {
        this.otp = new OTP();
    }

    /**
     * Connects a card to a PortfolioManager if the provided OTP is valid.
     *
     * @param manager The PortfolioManager to connect the card to.
     * @param card    The card to be connected.
     * @param code    The OTP code provided for verification.
     */
    public void ConnectAccount(PortfolioManager manager, Card card, int code) {
        if (!otp.VerifyOTP(code)) {
            return;
        }
        manager.connectcard(card);
        manager.saveCardsToFile();
        System.out.println("Card connected to Bank");
    }
}

/**
 * Represents an OTP system for generating and verifying one-time passwords.
 */
class OTP {
    int otpcode;
    Calendar calendar;
    int expairyDate;

    /**
     * Constructs a new OTP with*
