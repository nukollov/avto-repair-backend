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
@Table(name = "type_service")

/**
 * Класс, описывающий сущность Вида услуги
 * */
public class TypeServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "photo")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] photo;

    @OneToMany(mappedBy = "typeService")
    List<ServiceEntity> services;
}
