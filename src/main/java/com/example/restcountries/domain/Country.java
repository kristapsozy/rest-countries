package com.example.restcountries.domain;

public class Country {
    private String name;
    private String capital;
    private String currencies;
    private int population;
    private double area;

    public Country(String name, String capital, String currencies, int population, double area) {
        this.name = name;
        this.capital = capital;
        this.currencies = currencies;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrencies() {
        return currencies;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", currencies='" + currencies + '\'' +
                ", population=" + population +
                ", area=" + area +
                '}';
    }
}
