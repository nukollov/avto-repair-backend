package ukolov.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceForm {

    private Long id;

    private String time;

    private String price;

    private Long typeServiceId;

    private Long autoRepairId;
}