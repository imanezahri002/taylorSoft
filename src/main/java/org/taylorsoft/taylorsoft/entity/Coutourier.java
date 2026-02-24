package org.taylorsoft.taylorsoft.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coutouriers")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Coutourier extends User {


}
