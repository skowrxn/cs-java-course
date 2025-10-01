package pl.skowrxn.laby;

import java.util.Scanner;

public class Fibo {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        if (n < 1 || n > 45) return;

        int[] tab = new int[n];
        int a = 0;
        int b = 1;

        for(int i = 0; i < n; i++){
            tab[i] = a;
            b = a + b;
            a = b - a;
        }

        for (int i = 0; i < n; i++){
            System.out.println(tab[i]);
        }
    }

}
