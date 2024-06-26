package org.example.models;

public class Manager extends Employee {
    private double bonusSalary;
    private final double threshold;
    double revenue;
    public Manager(double baseSalary, double bonusSalary, double threshold){
        super(baseSalary);
        this.bonusSalary = bonusSalary;
        this.threshold = threshold;
    }

    public void setRevenue(double revenue){
        this.revenue = revenue;
    }


    @Override
    public double calculateSalary(){
        if(revenue>threshold){
            return getBaseSalary()+((revenue*bonusSalary)/100);
        }else{
            return getBaseSalary();
        }
    }


}
