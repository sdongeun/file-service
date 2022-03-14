package kr.co.e8ight.management.dto;

public class ProductDto {
    private int id;
    private String name;
    private String description;

    public ProductDto() {
    }

    public ProductDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "ItemDto [id=" + this.id + ", name=" + this.name + ", description=" + this.description + "]";
    }
}
