package com.c0722g1repobe.repository.form;

import com.c0722g1repobe.entity.form.DataForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IDataFormRepository extends JpaRepository<DataForm,Long> {
    /** Method use: searchByContent()
     * Get List data of required attributes from the database
     * Parameter: contentDataForm,pageable
     * Author: KhanhLB
     * */
    @Query(value="select * from data_form where data_form.flag_delete=false and data_form.content_data_form like %:contentDataForm% order by data_form.id_data_form desc",
    countQuery ="select * from data_form where data_form.flag_delete=false and data_form.content_data_form like %:contentDataForm% order by data_form.id_data_form desc",
    nativeQuery = true)
    Page<DataForm>searchByContent(@Param("contentDataForm")String contentDataForm, Pageable pageable);
    /**
     * Create by: KhanhLB
     * Created date: 31/01/2023
     * Function:  saveDataForm
     * @param contentDataForm
     * @param urlDataForm
     */
    @Modifying
    @Query(value = "insert into data_form (content_data_form,url_data_form,flag_delete) " +
            "value (:contentDataForm, :urlDataForm , false )", nativeQuery = true)
    @Transactional
    void saveDataForm(@Param("contentDataForm") String contentDataForm, @Param("urlDataForm") String urlDataForm);
    /**
     * create by : DungND
     * Data create: 31/01/2023
     * funcion: deleteByIdDataForm()
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update data_form  set flag_delete = true where id_data_form = :id",countQuery = "update data_form  set flag_delete = true where id_data_form = :id",nativeQuery = true)
    void deleteByIdDataForm(@Param("id") long id);


    /**
     * create by : DungND
     * Data create: 31/01/2023
     * funcion: findByIdDataForm()
     * @param 'id'
     */
    @Query(value = "SELECT * FROM data_form where id_data_form= :id and flag_delete = false ",countQuery = "SELECT * FROM data_form where id_data_form= :id and flag_delete = false",nativeQuery = true)
    DataForm findByIdDataForm(@Param("id") long id);


    /**
     * create by : DungND
     * Data create: 31/01/2023
     * funcion: updateDataForm()
     * @param 'id'
     * @param 'contentDataForm'
     * @param 'urlDetailForm'
     */
    @Transactional
    @Modifying
    @Query(value = "update data_form  set content_data_form = :contentDataForm, url_data_form = :urlDataForm where id_data_form= :id",countQuery = "update data_form  set content_data_form = :contentDataForm, url_data_form = :urlDataForm where id_data_form= :id",nativeQuery = true)
    void updateDataForm(@Param("contentDataForm")String contentDataForm, @Param("urlDataForm")String urlDataForm, @Param("id") long id);
}