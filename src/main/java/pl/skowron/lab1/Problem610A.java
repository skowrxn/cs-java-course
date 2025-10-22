package pl.skowron.laby;

import java.util.Scanner;

public class Problem610A {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int result = 0;
        int i = 1;
        int b = (n-2*i)/2;
        while (i < b) {
            result += 1;
            i++;
            b = (n-(2*i))/2;
        }
        System.out.println(result);
    }

}
