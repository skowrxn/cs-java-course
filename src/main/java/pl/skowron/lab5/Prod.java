package pl.skowron.lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();
 
    private Prod(){}

    private Prod(Node n1){
        args.add(n1);
    }

    private Prod(double c){
        args.add(new Constant(c));
    }
 
    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }

    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c) {
        args.add(new Constant(c));
        return this;
    }

    @Override
    double evaluate() {
        double d = sign*args.stream().map(Node::evaluate).reduce((a, b) -> a*b).orElse(1.0);
        return d;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        
        return b.toString();
    }
 
 
}