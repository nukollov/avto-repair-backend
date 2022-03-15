package serviceTests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ukolov.Application;
import ukolov.dao.AvtoRepairRepository;
import ukolov.dao.TypeServiceRepository;
import ukolov.entity.AvtoRepairEntity;
import ukolov.entity.TypeServiceEntity;
import ukolov.service.AvtoRepairService;
import ukolov.service.TypeServiceService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class TypeServiceServiceTest {

    private static int TYPE_SERVICES_COUNT = 3;
    private static List<TypeServiceEntity> typeServiceEntityList;

    @Autowired
    private TypeServiceService typeServiceService;

    @Autowired
    private TypeServiceRepository typeServiceRepository;

    @BeforeEach
    @Transactional
    public void createTypeServicesForTest() {
        typeServiceEntityList = new ArrayList<>();
        for (int i = 0; i < TYPE_SERVICES_COUNT; i++) {
            TypeServiceEntity typeService = TypeServiceEntity.builder()
                    .id(-i)
                    .name(Integer.toString(i))
                    .build();
            typeServiceRepository.save(typeService);
            typeServiceEntityList.add(typeService);
        }
    }

    @AfterEach
    @Transactional
    public void clearTable() {
        typeServiceRepository.deleteAll();
    }

    @Test
    public void findAllTest() {
        List<TypeServiceEntity> resultFindAllMethod = typeServiceService.findAll();
        assertEquals(TYPE_SERVICES_COUNT, resultFindAllMethod.size());
    }
}
