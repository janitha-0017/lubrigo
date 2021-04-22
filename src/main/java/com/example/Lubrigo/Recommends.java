package com.example.Lubrigo;


import javax.persistence.*;

@Entity
@Table(name ="oil111")
public class Recommends {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="brand")
    private String brand;
    @Column(name="grade_SAE")
    private String grade;
    @Column(name="model")
    private String model;
    @Column(name="year")
    private String year;
    @Column(name="oilType")
    private String oilType;
    @Column(name="recommendation")
    private String recommendation;

    public Recommends() {
    }

    public Recommends(String brand, String grade, String model, String year, String oilType, String recommendation) {
        super();
        this.brand = brand;
        this.grade = grade;
        this.model = model;
        this.year = year;
        this.oilType = oilType;
        this.recommendation = recommendation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOilType() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType = oilType;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
