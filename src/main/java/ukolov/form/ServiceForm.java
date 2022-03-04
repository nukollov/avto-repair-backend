package ukolov.form;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceForm {

    private Long id;

    private String time;

    private String price;

    private Long typeServiceId;

    private Long autoRepairId;
}