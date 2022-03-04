package ukolov.service;

import ukolov.dao.TypeServiceRepository;
import ukolov.entity.TypeServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceService {

    @Autowired
    TypeServiceRepository typeServiceRepository;

    public List<TypeServiceEntity> findAll(){return typeServiceRepository.findAll();}
}