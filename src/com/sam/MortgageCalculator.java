package com.sam;

public class MortgageCalculator {
    private int principal;
    private float annualInterest;
    private byte years;

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public MortgageCalculator(int principal, float annualInterest, byte years){
        setPrincipal(principal);
        setAnnualInterest(annualInterest);
        setYears(years);
    }

    private double calculateBalance(short numberOfPaymentsMade) {
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayments = getNumberOfPayments();

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    public double calculateMortgage() {
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayments = getNumberOfPayments();

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public double[] getPaymentPlan(){
        int numberOfPayments = getNumberOfPayments();
        double[] plan = new double[numberOfPayments];

        for (short month = 1; month <= numberOfPayments; month++) {
            double balance = calculateBalance(month);
            plan[month-1] = balance;
        }

        return plan;
    }

    private int getNumberOfPayments() {
        return years * MONTHS_IN_YEAR;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
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

    public byte getYears() {
        return years;
    }
}
