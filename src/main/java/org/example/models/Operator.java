package org.example.models;

public class Operator extends Employee {

    public Operator(double baseSalary){
        super(baseSalary);
    }

    @Override
    public double calculateSalary(){
        return getBaseSalary();
    }

}
