package com.benRem.brPoMgmt.controller.login;

import com.benRem.brPoMgmt.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facebook")
//FaceBook app secret bd1ac4c094d537a664c049e5af93d9b1
//appId facebook 642087329301902
public class FaceBookController {

    @Autowired
    CustomerDao customerDao;

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    public FaceBookController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }
        String [] fields = { "id","name","birthday","email","location","hometown","gender","first_name","last_name"};
        User user = facebook.fetchObject("me", User.class, fields);
        String name=user.getName();
        String birthday=user.getBirthday();
        String email=user.getEmail();
        String gender=user.getGender();
        String firstname=user.getFirstName();
        String lastname=user.getLastName();
        model.addAttribute("name",name );
        model.addAttribute("birthday",birthday );
        model.addAttribute("email",email );
        model.addAttribute("gender",gender);
        model.addAttribute("firstname",firstname);
        model.addAttribute("lastname",lastname);
        model.addAttribute("facebookProfile", facebook.fetchObject("me", User.class, fields));
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);

        return "hello";
    }

}
