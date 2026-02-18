package com.retrosocial.app.loader;

import com.retrosocial.app.entity.Post;
import com.retrosocial.app.entity.User;
import com.retrosocial.app.repo.PostRepo;
import com.retrosocial.app.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostDataLoader implements CommandLineRunner {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public PostDataLoader(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) {


        User user1 = userRepo.save(new User("retroRocker"));
        User user2 = userRepo.save(new User("webmaster90"));
        User user3 = userRepo.save(new User("millenniumFan"));


        if (postRepo.count() == 0) {
            postRepo.save(new Post("Just got my new 28.8k modem installed! The sound of dial-up is the sound of pure potential. Now I can actually download a JPEG in under five minutes. Welcome to the information superhighway! Anyone else on AOL?", user1));
            postRepo.save(new Post("Spent the afternoon building my first Geocities page. It's got a starry night background, a visitor counter, flaming .gifs, and a link to my 'Under Construction' page. It's a masterpiece of the early web. Remember to sign my guestbook!", user1));
            postRepo.save(new Post("The world is waiting. Y2K is coming, and nobody knows if the computers will handle the date change. There's a weird, thrilling anxiety in the air. Some people are stockpiling canned beans. I'm just backing up my Napster MP3s onto Zip disks. Whatever happens, we'll face it together, online.", user1));
            postRepo.save(new Post("Sitting by the radio with my finger on the record button, waiting for my favorite song to come on so I can make the perfect mixtape. It's an art form. The DJ always talks over the first three seconds. CURSE YOU! This tape is for someone special. Track 1 is critical.", user1));
            postRepo.save(new Post("The CD-ROM is a miracle. My new Encarta encyclopedia has videos and sound clips. It feels like holding the future in my hand. I spent an hour just clicking through the interactive timeline. Sorry, dusty old paper encyclopedia set, you're obsolete.", user1));
            postRepo.save(new Post("The series finale of *Seinfeld* is TONIGHT. It's a cultural event. The whole watercooler tomorrow will be nothing but hot takes. Are they going to jail? Are they moving to LA? I've got my TV recorded set on my VCR just in case the phone rings. Master of my domain, people.", user2));
            postRepo.save(new Post("Waited in line at the record store at midnight for the new Pearl Jam album, *Vitalogy*. The booklet is weird and made of vellum. The music is raw and angry and perfect. This tape is going to live in my Walkman for the next six months. No skips.", user3));
            postRepo.save(new Post("Just saw *The Matrix* for the third time. My mind is still blown. The bullet time, the philosophy, the leather coats... It's the coolest thing ever put to film. I keep trying to bend spoons with my mind. There is no phone. #glitchinthematrix" , user2));
        }
    }
}
