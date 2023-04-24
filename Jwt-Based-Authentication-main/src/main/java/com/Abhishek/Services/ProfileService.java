package com.Abhishek.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Abhishek.Models.BasicInfo;
import com.Abhishek.Models.Profile;
import com.Abhishek.Models.User;
import com.Abhishek.Repos.ProfileRepo;
import com.Abhishek.Repos.UserRepo;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepo repo;

    @Autowired
    private UserRepo repo2;

    public Profile saveProfile(Profile profile){
        try {
            return repo.save(profile);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e +" Occured in Profile.saveProfile()");
            return null;
        }
        
    }

    public Profile showProfile(String email){

        try {
            return repo.findByEmail(email);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e+" Occured in Profile.showProfile");
            return null;
        }
        
    }

    public BasicInfo findBasicInfo(String email){
        try {
            Optional<User> user=repo2.findByEmail(email);
            return new BasicInfo(user.get().getFname(), user.get().getLname(), email);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e +" occured at ProfileService.findUser");
            return null;
        }
    }

   
     

}
