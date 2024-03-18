package org.test.capitole.infrastructure.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BRAND")
public class Brand implements Serializable {

    private static final long serialVersionUID = -8866512402807353987L;

    @Id
    @Column(name = "BRAND_ID", nullable = false)
    private Integer brandId;

    @Column(name = "BRAND_NAME", nullable = false)
    private String brandName;

}
