package com.Abhishek.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Abhishek.Models.BasicInfo;
import com.Abhishek.Models.Profile;
import com.Abhishek.Services.ProfileService;

@RestController
@RequestMapping("/api/v1/profile")
@CrossOrigin(origins = "http://localhost:3000/")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/save")
    public Profile addProfile(@RequestBody Profile profile){
        return profileService.saveProfile(profile);
    }

    @GetMapping("/{email}")
    public Profile getProfile(@PathVariable String email){
        return profileService.showProfile(email);
    }

    @GetMapping("/basicinfo/{email}")
    public ResponseEntity<BasicInfo> getBasicInfo(@PathVariable String email){
        return ResponseEntity.ok(profileService.findBasicInfo(email));
    }

}
