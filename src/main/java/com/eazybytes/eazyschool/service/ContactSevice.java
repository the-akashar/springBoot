package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public  class ContactSevice {



    @Autowired
    private ContactRepository contactRepository;

    public ContactSevice() {
        System.out.println("Contact service bean initialized");
    }

    public static Logger log = LoggerFactory.getLogger(ContactSevice.class);
    public boolean saveMessageDetails(Contact contact){
        boolean issaved = false;

        contact.setStatus(EazySchoolConstants.OPEN);
        Contact saveContact = contactRepository.save(contact);
        if (null != saveContact && saveContact.getContactId()>0){
            issaved = true;
        }
        return issaved;

    }


    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId ){
        boolean isUpdated = false ;
        Optional<Contact> contact = contactRepository.findById(contactId);
        contact.ifPresent(contact1 -> {
            contact1.setStatus(EazySchoolConstants.CLOSE);
        });
        Contact updateContact = contactRepository.save(contact.get());
        if (null != updateContact && updateContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }


}
