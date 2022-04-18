package mk.timas.model.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String name;

    private Double price;

    private Integer quantity;

    private String code;

    private Long barcode;

    private String size;

    private String material;

    private String weight;

    private String description;

    private Long category;

    private Long manufacturer;

    private String image;

    public ProductDto() {
    }

    public ProductDto(String name, Double price, Integer quantity,String code,Long barcode,String size, String material,String weight, String description, Long category, Long manufacturer,String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.manufacturer = manufacturer;
        this.image = image;
        this.code = code;
        this.barcode = barcode;
        this.material = material;
        this.size = size;
        this.weight = weight;
        this.description = description;
    }
}
