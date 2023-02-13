package com.c0722g1repobe.service.form;

import com.c0722g1repobe.entity.form.DataForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IDataFormService {
    /**
     * Create by: KhanhLB
     * Date created: 31/01/2023
     * Function: show list dataForm
     *
     * @param contentDataForm,pageable
     * @return json list dataForm
     */
    Page<DataForm> searchByContent(@Param("contentDataForm")String contentDataForm, Pageable pageable);
    /**
     * Create by: KhanhLB
     * Date created: 31/01/2023
     * Function: save dataForm
     *
     * @param contentDataForm,urlDataForm
     * @return json list dataForm
     */
    void saveDataForm(@Param("contentDataForm") String contentDataForm, @Param("urlDataForm") String urlDataForm);
    void deleteByIdDataForm(@Param("id") long id);
    DataForm findByIdDataForm(@Param("id") long id);
    void updateDataForm(DataForm dataForm);
}
