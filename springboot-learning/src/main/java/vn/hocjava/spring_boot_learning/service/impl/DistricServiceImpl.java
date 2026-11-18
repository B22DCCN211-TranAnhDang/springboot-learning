package vn.hocjava.spring_boot_learning.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.hocjava.spring_boot_learning.dto.DistricDTO;
import vn.hocjava.spring_boot_learning.repository.DistricRepository;
import vn.hocjava.spring_boot_learning.repository.entity.DistricEntity;
import vn.hocjava.spring_boot_learning.service.DistricService;

@Service
public class DistricServiceImpl implements DistricService {

    @Autowired
    private DistricRepository districRepository;

    @Override
    public List<DistricDTO> findAll(String name) {
        List<DistricEntity> districEntities = districRepository.findAll(name);
        List<DistricDTO> result = new ArrayList<>();

        for (DistricEntity entity : districEntities) {
            DistricDTO dto = new DistricDTO();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            result.add(dto);
        }

        return result;
    }
}
