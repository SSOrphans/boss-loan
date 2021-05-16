package org.ssor.boss.loan.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Derrian Harris
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loan_type", schema = "boss")
public class LoanTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

}
