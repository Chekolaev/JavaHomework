package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;



public class Vector3D extends Vector {
    private int x,y,z;
    private int count = 0;
    private String correctpass = "qwerty";
    Vector3D(int x, int y, int z){
        super(x,y);
        this.z = z;
    }

    public String Enter(String pass) {
        count++;
        try {
            if (count > 2)
                throw new MyException("Access denied");
            if (pass.equals(correctpass)){
                count = 0;
                return "Success";
            }
        } catch (MyException e) {
            System.out.printf("My exception: %s\n", e.getMessage());
        }
        return "Access denied";
    }

    public double Len(){
        double Len;
        return Len = Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
    }
    private double Leng(int[] arr){
        double result;
        return result = Math.sqrt(arr[0]*arr[0]+arr[1]*arr[1]+arr[2]*arr[2]);
    }

    public double Scal(int[] second){
        double result;
        return result = this.x * second[0] + this.y*second[1] + this.z*second[2];
    }

    public int[] Vect(int[] second){
        int[] result = new int[3];
        result[0] = this.y*second[2] - this.z*second[1];
        result[1] = this.z*second[0] - this.x*second[2];
        result[2] = this.x*second[1] - this.y*second[0];
        return result;
    }

    public double Angle(int[] second){
        double result;
        //Vector3D v2 = new Vector3D(second);
        try {
            result = this.Scal(second)/(this.Len()*Leng(second));
            if (Double.isNaN((result)))
                throw new ArithmeticException();
        }catch (ArithmeticException e){System.out.println ("You Shouldn't divide a number by zero");};
        return 0;
    }

    public int[] Sum(int[] second){
        int[] result = new int[3];
        result[0] = this.x+second[0];
        result[1] = this.y+second[1];
        result[2] = this.z+second[2];
        return result;
    }
    public int[] Multiply(int[] second){
        int[] result = new int[3];
        result[0] = this.x*second[0];
        result[1] = this.y*second[1];
        result[2] = this.z*second[2];
        return result;
    }

    public static int[][] Ran(int N){
        final Random random = new Random();
        int[][] fin = new int[N][3];
        for(int j = 0; j < N; j++) {
            int[] result = new int[N];
            for (int i = 0; i < 3; i++) {
                result[i] = random.nextInt(10);
                fin[j][i] = result[i];
            }
        }
        return fin;
    }


    public void printInfo() {
        System.out.println("X: " + this.x);
        System.out.println("Y: " + this.y);
        System.out.println("Z: " + this.z);
        System.out.println("--------------------------");
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setZ(int z) {
        this.z = z;
    }

    static class MyException extends Exception {
        public MyException(String msg) {
            super(msg);
        }
    }

}

