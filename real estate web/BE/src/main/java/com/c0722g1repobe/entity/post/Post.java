package com.c0722g1repobe.entity.post;


import com.c0722g1repobe.entity.customer.Customer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE post SET flag_deleted = true WHERE id_post=?")
@Where(clause = "flag_deleted=false")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;
    private String namePost;
    private Double area;
    private String note;
    private Double price;
    @JsonManagedReference
    @OneToMany(mappedBy = "post")
    private Set<Image> imageSet;
    private boolean flagDeleted = false;
    private boolean approval;
    private LocalDate dateCreation = LocalDate.now();
    @ManyToOne
    private Direction direction;
    @ManyToOne
    private StatusPost statusPost;
    @OneToOne
    private Address address;
    @ManyToOne
    private DemandType demandType;
    @ManyToOne
    private LandType landType;
    @ManyToOne
    @JsonBackReference
    private Customer customer;
}