package pl.skowron.lab5;

import java.lang.foreign.ValueLayout;

public class Variable extends Node {

    private final String name;
    private Double value;

    Variable(String name){
        this.name = name;
        this.value = 0.0;
    }

    void setValue(double d){
        this.value = d;
        // value = d<0?-value:value;
        // sign = d<0?-1:1;
    }

    @Override
    double evaluate() {
         return sign*value;
    }

    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }
 
}