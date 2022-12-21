package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] v1 = {1,2,3};
        int[] v2 = {0,0,0};
        Vector3D vec = new Vector3D(v1[0],v1[1],v1[2]);

        vec.printInfo();

        System.out.println("Len " + vec.Len());
        System.out.println("Scal " + vec.Scal(v2));
        System.out.println("Vec " + Arrays.toString(vec.Vect(v2)));

        int[][] vecarr = Vector3D.Ran(4);
        for (int[] v:vecarr){
            System.out.println("x = " + v[0] + ", y = " + v[1]+ ", z = " + v[2]);
        }
        System.out.println("Angle " + vec.Angle(v2));
        System.out.println("Sum " + Arrays.toString(vec.Sum(v2)));
        System.out.println("Multiply " + Arrays.toString(vec.Multiply(v2)));

        System.out.println(vec.Enter("qwerty"));
        System.out.println(vec.Enter("qwerty"));
        System.out.println(vec.Enter("qwerty1"));
        System.out.println(vec.Enter("qwerty1"));
        System.out.println(vec.Enter("qwerty1"));
    }


}