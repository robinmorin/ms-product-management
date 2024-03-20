package org.test.capitole.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable {

    private static final long serialVersionUID = -69651175129566982L;

    @Id
    @Column(name = "CURRENCY_ISO", nullable = false)
    private String currencyIso;

    @Column(name = "CURRENCY_NAME", nullable = false)
    private String currencyName;

}
