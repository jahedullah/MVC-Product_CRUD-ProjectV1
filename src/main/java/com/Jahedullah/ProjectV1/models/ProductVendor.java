package com.Jahedullah.ProjectV1.models;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Entity
@Table(name = "ProductVendor")
public class ProductVendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vendorId")
    private int vendorID;
    @Column(name = "vendorName")
    private String vendorName;
}
