package com.c0722g1repobe.service.post.impl;

import com.c0722g1repobe.entity.post.District;
import com.c0722g1repobe.entity.post.Wards;
import com.c0722g1repobe.repository.post.IDistrictRepository;
import com.c0722g1repobe.service.post.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService implements IDistrictService {
    /**
     * DI IDistrictRepository to use IDistrictRepository's methods;
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Autowired
    private IDistrictRepository districtRepository;

    /**
     * Call method findNameDistrictAndIdDistrictQuery() of IDistrictRepository
     * @return : List<District> from IDistrictRepository
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Override
    public List<District> findNameDistrictAndIdDistrictQuery() {
        return districtRepository.findNameDistrictAndIdDistrictQuery();
    }
    /**
     * Call method findNameByIdNativeQuery() of IDistrictRepository
     * @return : nameDistrict from IDistrictRepository
     * @param : idDistrict
     * Author: DatTQ ; Date create: 03/02/2023
     */
    @Override
    public String findNameByIdNativeQuery(Long idDistrict) {
        return districtRepository.findNameByIdNativeQuery(idDistrict);
    }
    /**
     * Create by: NgocLV
     * Date Create: 03/02/2023
     * Description: find list District .
     *
     * @return  list District or null if not found
     */
    @Override
    public List<District> findListDistrict(Long idCity) {
        return districtRepository.findListDistrict(idCity);
    }
}
