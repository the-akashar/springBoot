package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactSevice;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class ContactController {

    private  ContactSevice contactSevice;


    @Autowired
    public ContactController(ContactSevice contactSevice) {
        this.contactSevice = contactSevice;
    }



    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg" , method = POST)
//
//
//    public ModelAndView saveMessage(@RequestParam String name , @RequestParam String mobileNum ,
//                                    @RequestParam String email , @RequestParam String subject ,
//                                    @RequestParam String message){
//
//        log.info("Name" + name);
//        log.info("Mobile Number" + mobileNum);
//        log.info("Email Address" + email);
//        log.info("Subject" + subject);
//        log.info("Message"+ message);
//        return new ModelAndView("redirect:/contact");

    @RequestMapping(value = "/saveMsg" , method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact , Errors error){

        if (error.hasErrors()){
            log.error("Contact form validation failed due to : "+error.toString());
            return "contact.html";
        }
        contactSevice.saveMessageDetails(contact);
        return "redirect:/contact";
    }


    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model){
        List<Contact> contactMsgs = contactSevice.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg" , method = GET)
    public String closeMsg(@RequestParam int id ){
        contactSevice.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }



    }



