package ukolov.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "avto_repair")

/**
 * Класс, описывающий сущность Автомастерская
 * */
public class AvtoRepairEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "adress", nullable = false)
    private String adress;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "avtoRepair")
    List<ServiceEntity> services;
}
