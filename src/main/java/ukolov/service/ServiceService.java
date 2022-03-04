package ukolov.service;

import ukolov.dao.AvtoRepairRepository;
import ukolov.dao.ServiceRepository;
import ukolov.dao.TypeServiceRepository;
import ukolov.form.ServiceForm;
import ukolov.form.UpdateServiceForm;
import ukolov.entity.AvtoRepairEntity;
import ukolov.entity.ServiceEntity;
import ukolov.entity.TypeServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    AvtoRepairRepository avtoRepairRepository;

    @Autowired
    TypeServiceRepository typeServiceRepository;

    public List<ServiceEntity> findAll() {
        return serviceRepository.findAll();
    }

    public ServiceEntity findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    @Transactional
    public void save(ServiceForm serviceForm) {
        Optional<AvtoRepairEntity> avtoRepairEntity = avtoRepairRepository.findById(serviceForm.getAutoRepairId());
        Optional<TypeServiceEntity> typeServiceEntity = typeServiceRepository.findById(serviceForm.getTypeServiceId());
        if(avtoRepairEntity.isPresent() && typeServiceEntity.isPresent()) {
            ServiceEntity service = ServiceEntity.builder()
                    .price(serviceForm.getPrice())
                    .time(serviceForm.getTime())
                    .avtoRepair(avtoRepairEntity.get())
                    .typeService(typeServiceEntity.get()).build();
            serviceRepository.save(service);
        }
    }

    @Transactional
    public void update(ServiceForm serviceForm) {
        Optional<ServiceEntity> serviceEntityOptional =  serviceRepository.findById(serviceForm.getId());
        if(serviceEntityOptional.isPresent()){
            ServiceEntity serviceEntity = serviceEntityOptional.get();
            serviceEntity.setTime(serviceForm.getTime());
            serviceEntity.setPrice(serviceForm.getPrice());
            serviceRepository.save(serviceEntity);
        }
    }
}