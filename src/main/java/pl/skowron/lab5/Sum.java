package pl.skowron.lab5;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {
 
    List<Node> args = new ArrayList<>();
 
    Sum(){}
 
    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
 
 
    Sum add(Node n){
        args.add(n);
        return this;
    }
 
    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }
 
    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result =0;
        for (Node node : args){
            result += node.evaluate();
        }
        return sign*result;
    }
 
    int getArgumentsCount(){return args.size();}
 
    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");
 
        for (Node n : args){
            b.append(n.evaluate()).append(" +");
        }

        String result = b.substring(0, b.length()-1);
        return sign < 0 ? result + ")" : result;
    }
 
 
}