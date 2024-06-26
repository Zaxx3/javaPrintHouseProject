package org.example.models;

public abstract class Employee{
    protected double baseSalary;
    public Employee(double baseSalary){
        this.baseSalary = baseSalary;
    }
    public double getBaseSalary(){
        return this.baseSalary;
    }

    public abstract double calculateSalary();


}
