package ukolov.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "service")

/**
 * Класс описывающий сущность Услуги
 * */
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "price", nullable = false)
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_service", nullable = false)
    TypeServiceEntity typeService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avto_repair", nullable = false)
    AvtoRepairEntity avtoRepair;
}

