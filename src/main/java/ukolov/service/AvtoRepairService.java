package ukolov.service;

import ukolov.dao.AvtoRepairRepository;
import ukolov.entity.AvtoRepairEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvtoRepairService {

    @Autowired
    AvtoRepairRepository avtoRepairRepository;

    public List<AvtoRepairEntity> findAll(){return avtoRepairRepository.findAll();}
}
