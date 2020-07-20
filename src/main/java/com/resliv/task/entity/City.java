package com.resliv.task.entity;


import javax.persistence.*;
import java.util.Objects;


/**
 * Entity with its getter, setters, equals, hashCode and toString methods
 * Describes information about cities
 */
@Entity
@Table
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    @Column(unique = true, nullable = false)
    private String name;
    private String info;

    public City() {
    }

    public City(Long id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public City(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return getId() == city.getId() &&
                getName().equals(city.getName()) &&
                getInfo().equals(city.getInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getInfo());
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
