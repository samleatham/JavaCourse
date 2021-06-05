package com.sam;

import java.text.NumberFormat;

public class MortgageReport {

    public MortgageCalculator mortgageCalculator;

    public MortgageReport(MortgageCalculator mortgage) {
        this.mortgageCalculator = mortgage;
    }

    public void printMortgage() {
        double mortgage = mortgageCalculator.calculateMortgage();
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
        var plan = mortgageCalculator.getPaymentPlan();
        for (double balance : plan) {
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }
}
