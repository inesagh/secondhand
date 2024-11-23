package com.example.secondhandclothes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "GARMENTS")
public class GarmentDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String description;
    private String size;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private UserDAO publisher;

    public GarmentDAO() {
    }

    public GarmentDAO(Long id, String type, String description, String size, Double price, UserDAO publisher) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.size = size;
        this.price = price;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UserDAO getPublisher() {
        return publisher;
    }

    public void setPublisher(UserDAO userDAO) {
        this.publisher = userDAO;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof GarmentDAO that))
            return false;

        if (!getId().equals(that.getId()))
            return false;
        if (getType() != null ? !getType().equals(that.getType()) : that.getType() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getSize() != null ? !getSize().equals(that.getSize()) : that.getSize() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null)
            return false;
        return getPublisher().equals(that.getPublisher());
    }

    @Override public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + getPublisher().hashCode();
        return result;
    }

    @Override public String toString() {
        return "GarmentDAO{" + "id=" + id + ", type='" + type + '\'' + ", description='" + description + '\''
                + ", size=" + size + ", price=" + price + ", userDAO=" + publisher + '}';
    }
}
