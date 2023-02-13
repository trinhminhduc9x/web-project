package com.c0722g1repobe.service.form.impl;

import com.c0722g1repobe.entity.form.DataForm;
import com.c0722g1repobe.repository.form.IDataFormRepository;
import com.c0722g1repobe.service.form.IDataFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DataFormService implements IDataFormService {
    @Autowired
    private IDataFormRepository iDataFormRepository;
    /**
     * Create by: KhanhLB
     * Date created: 31/01/2023
     * Function: show list dataForm
     *
     * @param contentDataForm,pageable
     * @return json list dataForm
     */
    @Override
    public Page<DataForm> searchByContent(String contentDataForm, Pageable pageable) {
        return iDataFormRepository.searchByContent(contentDataForm,pageable);
    }
    /**
     * Create by: KhanhLB
     * Date created: 31/01/2023
     * Function: save dataForm
     *
     * @param contentDataForm,urlDataForm
     * @return json list dataForm
     */
    @Override
    public void saveDataForm(String contentDataForm, String urlDataForm) {
        iDataFormRepository.saveDataForm(contentDataForm,urlDataForm);
    }
    @Override
    public void deleteByIdDataForm(long id) {
        iDataFormRepository.deleteByIdDataForm(id);
    }


    @Override
    public DataForm findByIdDataForm(long id) {
        return iDataFormRepository.findByIdDataForm(id);
    }

    @Override
    public void updateDataForm(DataForm dataForm) {
        iDataFormRepository.updateDataForm( dataForm.getContentDataForm(), dataForm.getUrlDataForm(), dataForm.getIdDataForm());
    }
}
