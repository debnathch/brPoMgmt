package com.benRem.brPoMgmt.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by debnathchatterjee on 19/06/17.
 */

@Entity
@Table(name="company")
@Data
public class Company {

    @Id
    @Column(name="company_id")
    int company_id;

    @Column(name="company_name")
    String company_name;

    @Column(name="parent_company_id")
    int parent_company_id;

    @Column(name="company_address")
    String company_address;

    @Column(name="company_desc")
    String company_desc;


}
