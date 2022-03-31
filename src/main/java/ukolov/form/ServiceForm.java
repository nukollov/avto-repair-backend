package ukolov.form;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

/**
 * Форма для создания новой Услуги или редактирования уже существующей
 * */
public class ServiceForm {

    private Long id;

    private String time;

    private String price;

    private Long typeServiceId;

    private Long autoRepairId;
}