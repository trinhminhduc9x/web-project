package com.c0722g1repobe.dto.form;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DataFormDto {
    @NotBlank
    @Size(max = 200)
    @Size(min = 5)
    private String contentDataForm;
    @NotBlank
    private String urlDataForm;

    public DataFormDto() {
    }
    public String getContentDataForm() {
        return contentDataForm;
    }

    public void setContentDataForm(String contentDataForm) {
        this.contentDataForm = contentDataForm;
    }

    public String getUrlDataForm() {
        return urlDataForm;
    }

    public void setUrlDataForm(String urlDataForm) {
        this.urlDataForm = urlDataForm;
    }

}
