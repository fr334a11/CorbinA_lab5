package testing;

import java.util.*;
/**
 * @Anthony Corbin
 * Neumont University
 * Class: Intro to Computer Sciences
 * Quarter: 1
 */
public class Calculator {
    private double currentAnswer = 0;//hold current value
    public Calculator()       { currentAnswer = 0; }//sets default value
    public double getAnswer() { return currentAnswer; }//returns current value
    
    private static String pretty(double a, char operator, double b, double c) {
        return pretty(a,operator,b,c,99999999);
    }
    private static String pretty(double a, char operator, double b, double c, int max) {
        String str1 = ""+a;
        String str2 = ""+b;
        String str3 = ""+c;
        String ret  = "";
        int maxLength = str1.length();
        if (str2.length()>maxLength) {
            maxLength = str2.length();
        }
        if (str3.length()>maxLength) {
            maxLength = str3.length();
        }
        if (maxLength>max) { maxLength = max; }
        ret+=str1;
        for (int i=str1.length();i<maxLength;i++) { ret += " "; }
        ret+=" "+operator+" ";
        ret+=str2;
        for (int i=str2.length();i<maxLength;i++) { ret += " "; }
        ret+=" = ";
        ret+=str3;
        for (int i=str3.length();i<maxLength;i++) { ret += " "; }
        return ret;
    }
    //rounds a number
    private static double round(double num, int round) {
        int rounder = 1;
        for (int i=0;i<round;i++) { rounder *= 10; }
        return (double)((int)(num*rounder))/rounder;
    }
    //creates a random double
    private double rand(double min, double max) {
        Random rand = new Random();
        return ( rand.nextDouble()+rand.nextInt((int)(max*max)) )%(max-min)+min;
    }
    //creates a random int
    private int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max-min)+min;
    }
    
    
    public double add     (double a, double b) { return currentAnswer = a+b; }
    public double multiply(double a, double b) { return currentAnswer = a-b; }
    public double subtract(double a, double b) { return currentAnswer = a*b; }
    public double divide  (double a, double b) { return currentAnswer = a/b; }
    
    public void supriseAnswer(double a, double b) {
        currentAnswer = a*this.rand(1,100)+b/this.rand(1,100);
    }
    public void supriseAnswer() {
        currentAnswer = this.rand(1,100)*this.rand(1,100)+this.rand(1,100)/this.rand(1,100);
    }
    
    /**
     * creats a calc and plays with it
     */
    public static void main(String[] args) {
        int toRound = 3;
        Calculator myCalc = new Calculator();
        
        double a = Calculator.round(myCalc.rand(.01,100),toRound);
        double b = Calculator.round(myCalc.rand(.01,100),toRound);
        System.out.println(pretty(a,'+',b,myCalc.add(a,b),toRound+3));
        
        a = Calculator.round(myCalc.rand(.01,100),toRound);
        b = Calculator.round(myCalc.rand(.01,100),toRound);
        System.out.println(pretty(a,'+',b,myCalc.add(a,b),toRound+3));
        
        a = Calculator.round(myCalc.rand(.01,100),toRound);
        b = Calculator.round(myCalc.rand(.01,100),toRound);
        System.out.println(pretty(a,'+',b,myCalc.add(a,b),toRound+3));
        
        a = Calculator.round(myCalc.rand(.01,100),toRound);
        b = Calculator.round(myCalc.rand(.01,100),toRound);
        myCalc.supriseAnswer(a,b);
        System.out.println( "suprise: "+Calculator.round(myCalc.getAnswer(),toRound) );
    }
}