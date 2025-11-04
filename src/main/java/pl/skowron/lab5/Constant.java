package pl.skowron.lab5;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Constant extends Node {

    private double value;

    Constant(double value){
        this.sign = value<0?-1:1;
        this.value = value; // value<0?-value:value; //
    }

    @Override
    double evaluate() {
        return sign*value;
    }
 
    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.#####", new DecimalFormatSymbols(Locale.US));
        String s = format.format(value);
        return sign<0 ? "-(" + s + ")" : s;
    }
}