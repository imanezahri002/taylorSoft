package org.taylorsoft.taylorsoft.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type_echantillon")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeEchantillon {
        @Id
        private Long id;
        @Column(nullable = false, length = 100)
        private String nom;
}
