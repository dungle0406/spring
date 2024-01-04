package spring.test.mentor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.test.mentor.dto.MentorDtoPostResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.service.MentorService;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {
    private final MentorService mentorService;

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MentorDtoPostResponse createNewMentor(@RequestBody MentorDtoRequest request) {
        return mentorService.createNewMentor(request);
    }
}
