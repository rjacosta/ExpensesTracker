package com.example.romel.expensestracker;

/**
 * Created by romel on 11/11/2017.
 */

public class Totals {

    private double netExpenses;
    private double dad;
    private double prevMonth;
    private double work;
    private double food;
    private double uber;
    private double misc;

    public Totals(){}


    public double getNetExpenses() {
        return netExpenses;
    }

    public void setNetExpenses(double d, boolean reset) {
        if (reset) this.netExpenses = netExpenses;
        else this.netExpenses += d;
    }

    public double getDad() {
        return dad;
    }

    public void setDad(double d, boolean reset) {
        if (reset) this.dad = d;
        else this.dad += d;
    }

    public double getPrevMonth() {
        return prevMonth;
    }

    public void setPrevMonth(double d, boolean reset) {
        if (reset) this.prevMonth = d;
        else this.prevMonth += d;
    }

    public double getWork() {
        return work;
    }

    public void setWork(double d, boolean reset) {
        if (reset) this.work = d;
        else this.work += d;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double d, boolean reset) {
        if (reset) this.food = d;
        else this.food += d;
    }

    public double getUber() {
        return uber;
    }

    public void setUber(double d, boolean reset) {
        if (reset) this.uber = uber;
        else this.misc += d;
    }

    public double getMisc() {
        return misc;
    }

    public void setMisc(double d, boolean reset) {
        if (reset) this.misc = misc;
        else this.misc += d;
    }

}
