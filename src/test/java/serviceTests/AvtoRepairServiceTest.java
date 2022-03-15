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
import ukolov.entity.AvtoRepairEntity;
import ukolov.service.AvtoRepairService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class AvtoRepairServiceTest {

    private static int AVTO_REPAIRS_COUNT = 3;
    private static List<AvtoRepairEntity> avtoRepairEntityList;

    @Autowired
    private AvtoRepairService avtoRepairService;

    @Autowired
    private AvtoRepairRepository avtoRepairRepository;

    @BeforeEach
    @Transactional
    public void createAvtoRepairsForTest() {
        avtoRepairEntityList = new ArrayList<>();
        for (int i = 0; i < AVTO_REPAIRS_COUNT; i++) {
            AvtoRepairEntity avtoRepair = AvtoRepairEntity.builder()
                    .id(-i)
                    .name(Integer.toString(i))
                    .adress("adress: " + i)
                    .phone("phone: " + i)
                    .build();
            avtoRepairRepository.save(avtoRepair);
            avtoRepairEntityList.add(avtoRepair);
        }
    }

    @AfterEach
    @Transactional
    public void clearTable(){
        avtoRepairRepository.deleteAll();
    }

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(avtoRepairRepository);
    }

    @Test
    public void findAllTest() {
        List <AvtoRepairEntity> resultFindAllMethod = avtoRepairService.findAll();
        assertEquals(AVTO_REPAIRS_COUNT, resultFindAllMethod.size());
    }

}
