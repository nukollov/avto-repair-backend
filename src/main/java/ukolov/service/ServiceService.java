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
        ServiceEntity service = serviceRepository.findById(id).get();
        return service;
    }

    public void deleteById(Long id){
        serviceRepository.deleteById(id);
    }

    @Transactional
    public void save(ServiceForm serviceForm){
        AvtoRepairEntity avtoRepairEntity = avtoRepairRepository.findById(serviceForm.getAutoRepairId()).get();
        TypeServiceEntity typeServiceEntity = typeServiceRepository.findById(serviceForm.getTypeServiceId()).get();
        ServiceEntity service = new ServiceEntity();
        service.setPrice(serviceForm.getPrice());
        service.setTime(serviceForm.getTime());
        service.setAvtoRepair(avtoRepairEntity);
        service.setTypeService(typeServiceEntity);
        serviceRepository.save(service);
    }

    @Transactional
    public void update(Long id, UpdateServiceForm updateServiceForm){
        ServiceEntity serviceEntity = serviceRepository.findById(id).get();
        serviceEntity.setTime(updateServiceForm.getTime());
        serviceEntity.setPrice(updateServiceForm.getPrice());
        serviceRepository.save(serviceEntity);
    }
}