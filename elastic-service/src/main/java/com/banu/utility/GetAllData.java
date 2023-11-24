package com.banu.utility;

import com.banu.manager.UserManager;
import com.banu.repository.entity.UserProfile;
import com.banu.service.UserProfileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // service, controller vs en ust basligi. Spring tarafindan cozumlenip yonetilir.
@RequiredArgsConstructor
public class GetAllData {

    private final UserProfileService userProfileService;
    private final UserManager userManager;


    //@PostConstruct  //required args ile dependency, constructor injection yapıyor. Beanin baslatılması için gereken islemleri yapıyor. metoda girmeden önce nesnelerin olusturulmasi ve sonrasında metodu cagırıyor
    // Bir kere calıistirdiktan sonra kapatıyoruz cumku her seferinde tum verileri tekrar tekrar getirecek
    public void initDate(){
        List<UserProfile> userProfileList = userManager.findAll().getBody();
        userProfileService.saveAll(userProfileList);
    }
}
