package com.eazybytes.eazyschool.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO , generator="native")
    @GenericGenerator(name = "native" , strategy = "native")
    @Column(name = "contact_id")
    private int contactId;

    private String status;

    @NotBlank(message = "Name must not be a blank")
    @Size(min = 3 , message = "Name must be atleast 3 character long")
    private String name;

    @NotBlank(message = "Mobile Number must not be a null")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNum;

    @NotBlank(message = "Email must not ne a blank")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Subject cannot be a blank")
    @Size(min = 5 , message = "should have min 5 character")
    private String subject ;

    @NotBlank(message = "message cannot be blank")
    @Size(min = 5 , message = "message should be at least 5 character")
    private String message;


}
