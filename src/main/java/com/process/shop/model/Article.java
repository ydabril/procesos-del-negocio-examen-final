package com.process.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @NotBlank(message = "El codigo no puede estar en blanco")
    @Pattern(regexp = "\\d+", message = "El codigo solo puede contener valores numericos")
    private String code;

    @NotBlank(message = "Please provide the description")
    private String description;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser un valor numérico positivo")
    private Double price;

    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser un valor entero no negativo")
    private Integer stock;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;
}
