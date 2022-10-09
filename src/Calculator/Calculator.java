package Calculator;

import FileManager.Config;

import java.util.Scanner;

public class Calculator {

    public Calculator(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Import");
        String importproduct = scan.nextLine();
        System.out.println("Export");
        String exportproduct = scan.nextLine();
        System.out.println("Export Level");
        double exportlevel = Double.valueOf(scan.nextLine());
        Config config = new Config("Product.txt");
        double result = getTrade(config.get(exportproduct).getTrade(),config.get(importproduct).getTrade(),exportlevel,config.get(importproduct).getLevel());
        System.out.println(result);
    }

    public double getTrade(double E,double I,double e, double i){
        /*
        E: Export Product trade
        I: Import Product trade
        e: Export Factor
        i: Export Factor
         */

        double trade = (E/I)*(e/i);
        return trade;
    }

}
