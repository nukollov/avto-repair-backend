package serviceTests;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ukolov.Application;
import ukolov.dao.AvtoRepairRepository;
import ukolov.dao.ServiceRepository;
import ukolov.dao.TypeServiceRepository;
import ukolov.entity.AvtoRepairEntity;
import ukolov.entity.ServiceEntity;
import ukolov.entity.TypeServiceEntity;
import ukolov.form.ServiceForm;
import ukolov.service.AvtoRepairService;
import ukolov.service.ServiceService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class})
public class ServiceServiceTest {

    private static int SERVICE_COUNT = 3;
    private static String PRICE_FOR_TEST = "1";
    private static String NAME_TYPE_SERVICE_FOR_TEST = "5";
    private static String NAME_AVTOREPAIR_FOR_TEST = "10";
    private static Long ID_FOR_FIND_SERVICE = 10L;
    private static List<ServiceEntity> serviceEntityList;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AvtoRepairRepository avtoRepairRepository;

    @Autowired
    private TypeServiceRepository typeServiceRepository;

    public void createForeignEntity(){
        serviceEntityList = new ArrayList<>();
        AvtoRepairEntity avtoRepair = AvtoRepairEntity.builder()
                .name(NAME_AVTOREPAIR_FOR_TEST)
                .adress("adress: " + 5)
                .phone("phone: " + 5)
                .build();
        TypeServiceEntity typeService = TypeServiceEntity.builder()
                .name(NAME_TYPE_SERVICE_FOR_TEST)
                .build();
        avtoRepairRepository.save(avtoRepair);
        typeServiceRepository.save(typeService);
    }

    @BeforeEach
    public void createServiceForTest() {
        createForeignEntity();
        for (int i = 0; i < SERVICE_COUNT; i++) {
            ServiceEntity service = ServiceEntity.builder()
                    .id(i)
                    .time(Integer.toString(-i))
                    .price(Integer.toString(i))
                    .avtoRepair(avtoRepairRepository.findByName(NAME_AVTOREPAIR_FOR_TEST))
                    .typeService(typeServiceRepository.findByName(NAME_TYPE_SERVICE_FOR_TEST)).build();
            serviceRepository.save(service);
            serviceEntityList.add(service);
        }
    }

    @AfterEach
    @Transactional
    public void clearTable() {
        serviceRepository.deleteAll();
        avtoRepairRepository.deleteAll();
        typeServiceRepository.deleteAll();
    }

    @Test
    public void findAllTest() {
        List<ServiceEntity> resultFindAllMethod = serviceService.findAll();
        assertEquals(SERVICE_COUNT, resultFindAllMethod.size());
    }

    @Test public void findByIDTest(){
        Long id = serviceRepository.findByPrice(PRICE_FOR_TEST).getId();
        ServiceEntity foundService = serviceService.findById(id);
        assertNotNull(foundService);
    }

    @Test public void deleteByIDTest(){
        Long id = serviceRepository.findByPrice(PRICE_FOR_TEST).getId();
        serviceService.deleteById(id);
        assertFalse(serviceRepository.findById(id).isPresent());
    }

    @Test public void saveTest(){
        String price = "50";
        ServiceForm serviceForm = ServiceForm.builder()
                .time("15")
                .price(price)
                .typeServiceId(typeServiceRepository.findByName(NAME_TYPE_SERVICE_FOR_TEST).getId())
                .autoRepairId(avtoRepairRepository.findByName(NAME_AVTOREPAIR_FOR_TEST).getId())
                .build();
        serviceService.save(serviceForm);
        Long id = serviceRepository.findByPrice(price).getId();
        assertTrue(serviceRepository.findById(id).isPresent());
    }

    @Test public void updateTest(){
        Long id = serviceRepository.findByPrice(PRICE_FOR_TEST).getId();
        ServiceForm serviceForm = ServiceForm.builder()
                .id(id)
                .time("150")
                .price("100")
                .typeServiceId(typeServiceRepository.findByName(NAME_TYPE_SERVICE_FOR_TEST).getId())
                .autoRepairId(avtoRepairRepository.findByName(NAME_AVTOREPAIR_FOR_TEST).getId())
                .build();
        serviceService.update(serviceForm);
        ServiceEntity service = serviceRepository.findById(id).orElse(null);
        assertEquals(serviceForm.getPrice(), service.getPrice());
        assertEquals(serviceForm.getTime(), service.getTime());
    }
}
