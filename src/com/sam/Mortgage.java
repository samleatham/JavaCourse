package com.sam;

import java.text.NumberFormat;

public class Mortgage {
    private int principal;
    private float annualInterest;
    private byte years;

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public Mortgage(int principal,float annualInterest,byte years){
        setPrincipal(principal);
        setAnnualInterest(annualInterest);
        setYears(years);
    }

    private void setPrincipal(int principal) {
        if (principal <= 0){
            throw new IllegalArgumentException("Please provide a principal greater than 0");
        }
        this.principal = principal;
    }

    private void setAnnualInterest(float annualInterest) {
        if (annualInterest <= 0){
            throw new IllegalArgumentException("Please provide an interest greater than 0");
        }
        this.annualInterest = annualInterest;
    }

    private void setYears(byte years) {
        if (years <= 0){
            throw new IllegalArgumentException("Please provide a number of years greater than 0");
        }
        this.years = years;
    }

    public void printMortgage() {
        double mortgage = calculateMortgage();
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    private double calculateBalance(short numberOfPaymentsMade) {
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        float numberOfPayments = years * MONTHS_IN_YEAR;

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    private double calculateMortgage() {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        float numberOfPayments = years * MONTHS_IN_YEAR;

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

}
