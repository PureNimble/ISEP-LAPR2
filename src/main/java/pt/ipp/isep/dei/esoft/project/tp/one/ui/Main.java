package pt.ipp.isep.dei.esoft.project.tp.one.ui;

import pt.ipp.isep.dei.esoft.project.domain.Calculator;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(3, 4));
    }
}
