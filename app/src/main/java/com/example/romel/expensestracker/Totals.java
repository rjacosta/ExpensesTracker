package com.example.romel.expensestracker;

/**
 * Created by romel on 11/11/2017.
 */

class Totals {

    private double netExpenses;
    private double dad;
    private double prevMonth;
    private double work;
    private double food;
    private double transportation;
    private double misc;

    Totals(){}


    double getNetExpenses() {
        return netExpenses;
    }

    private void updateNetExpenses() {
        netExpenses = dad + prevMonth + work - food - transportation + misc;
    }

    double getDad() {
        return dad;
    }

    private void setDad(double d, boolean reset) {
        if (reset) this.dad = d;
        else this.dad += d;
        updateNetExpenses();
    }

    double getPrevMonth() {
        return prevMonth;
    }

    private void setPrevMonth(double d, boolean reset) {
        if (reset) this.prevMonth = d;
        else this.prevMonth += d;
        updateNetExpenses();
    }

    double getWork() {
        return work;
    }

    private void setWork(double d, boolean reset) {
        if (reset) this.work = d;
        else this.work += d;
        updateNetExpenses();
    }

    double getFood() {
        return food;
    }

    private void setFood(double d, boolean reset) {
        if (reset) this.food = d;
        else this.food += d;
        updateNetExpenses();
    }

    double getTransportation() {
        return transportation;
    }

    private void setTransportation(double d, boolean reset) {
        if (reset) this.transportation = d;
        else this.misc += d;
        updateNetExpenses();
    }

    double getMisc() {
        return misc;
    }

    private void setMisc(double d, boolean reset) {
        if (reset) this.misc = d;
        else this.misc += d;
        updateNetExpenses();
    }

    void setExpense(String type, double d, boolean reset) {
        switch (type) {
            case "Dad":
                this.setDad(d, reset);
                break;
            case "Previous Month":
                this.setPrevMonth(d, reset);
                break;
            case "Work":
                this.setWork(d, reset);
                break;
            case "Food":
                this.setFood(d, reset);
                break;
            case "Transportation":
                this.setTransportation(d, reset);
                break;
            case "Misc":
                this.setMisc(d, reset);
                break;
        }
    }

}
