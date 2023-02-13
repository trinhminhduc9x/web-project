package com.c0722g1repobe.controller.form;

import com.c0722g1repobe.dto.form.DataFormDto;
import com.c0722g1repobe.entity.form.DataForm;
import com.c0722g1repobe.service.form.IDataFormService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/form")
public class DataFormRestController {
    @Autowired
    private IDataFormService iDataFormService;
    /**
     * Create by: KhanhLB
     * Date created: 31/01/2023
     * Function: show list or search  dataForm
     * @param contentDataForm
     * @param page
     * @return HttpStatus.OK if connect to database return json list dataForm or HttpStatus.BAD_REQUEST if list dataForm is empty
     */
    @GetMapping("")
    public ResponseEntity<Page<DataForm>>searchByContent(@RequestParam(defaultValue = "",required = false)String contentDataForm,@RequestParam(defaultValue = "0",required = false) int page){
        Pageable pageable = Pageable.ofSize(5);
        Page<DataForm>dataFormPage=iDataFormService.searchByContent(contentDataForm,pageable.withPage(page));
        dataFormPage.hasNext();
        if(dataFormPage.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dataFormPage,HttpStatus.OK);
    }
    /**
     * Create by: KhanhLB
     * Date created: 31/01/2023
     * Function: save dataForm
     * @param dataFormDto
     * @param bindingResult
     * @return HttpStatus.CREATED when the data is saved to the database, HttpStatus.NOT_MODIFIED when an error occurs
     */
    @PostMapping("/save")
    public ResponseEntity<?> createDataForm(@Valid @RequestBody DataFormDto dataFormDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
        }
        DataForm dataForm = new DataForm();
        BeanUtils.copyProperties(dataFormDto, dataForm);
        iDataFormService.saveDataForm(dataFormDto.getContentDataForm(), dataFormDto.getUrlDataForm());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * create by : DungND
     * Data create: 31/01/2023
     * funcion: findById
     * @param 'id'
     * @return HttpStatus.NOT_FOUND if result is not found or HttpStatus.OK is find
     */
    @GetMapping( "/{id}")
    public ResponseEntity<DataForm> findByID(@PathVariable("id") long id) {
        DataForm dataForm = iDataFormService.findByIdDataForm(id);
        if (dataForm==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(dataForm, HttpStatus.OK);
    }


    /**
     * create by : DungND
     * Data create: 31/01/2023
     * funcion: delete
     * @param 'id'
     * @return HttpStatus.NOT_FOUND if result is not found or HttpStatus.OK is find
     */
    @DeleteMapping( "/delete/{id}")
    public ResponseEntity<DataForm> delete(@PathVariable("id") long id) {
        DataForm dataForm = iDataFormService.findByIdDataForm(id);
        if (dataForm==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iDataFormService.deleteByIdDataForm(id);
        return new ResponseEntity(dataForm, HttpStatus.OK);
    }
    /**
     * create by : DungND
     * Data create: 31/01/2023
     * funcion: update
     * @param 'id'
     * @return HttpStatus.NOT_FOUND if result is not found or HttpStatus.OK is find
     */

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<DataForm> update(@PathVariable("id") long id, @Valid @RequestBody DataFormDto dataFormDto, BindingResult bindingResult){
        DataForm dataForm = iDataFormService.findByIdDataForm(id);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.NOT_MODIFIED);
        }

        if (dataForm==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            BeanUtils.copyProperties(dataFormDto,dataForm);
            iDataFormService.updateDataForm(dataForm);
            return new ResponseEntity<>(dataForm, HttpStatus.OK);

        }

    }
}
