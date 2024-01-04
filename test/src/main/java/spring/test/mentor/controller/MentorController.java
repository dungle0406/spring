package spring.test.mentor.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.test.advice.ActionLog;
import spring.test.advice.ActionLogService;
import spring.test.mentor.error.LackOfInformation;
import spring.test.mentor.error.MentorNotFound;
import spring.test.mentor.dto.MentorDtoPutResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.dto.MentorDtoResponse;
import spring.test.mentor.dto.MentorPostResponse;
import spring.test.mentor.service.MentorService;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
@Slf4j
public class MentorController {
    private final MentorService mentorService;
    private final ActionLogService logService;

    @Autowired
    public MentorController(MentorService mentorService, ActionLogService logService) {
        this.mentorService = mentorService;
        this.logService = logService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MentorPostResponse createNewMentors(@RequestBody MentorDtoRequest mentorDtoRequest) {
        return mentorService.createNewMentor(mentorDtoRequest);
    }

    @GetMapping()
    public List<MentorDtoResponse> findMentors(@ModelAttribute MentorDtoRequest mentorDtoRequest) {
        return mentorService.findMentors(mentorDtoRequest);
    }

    @GetMapping("/{id}")
    public MentorDtoResponse findMentorById(@PathVariable Long id) {
        return mentorService.findMentorById(id);
    }

    @PutMapping("/{id}")
    public MentorDtoPutResponse modifyMentors(@PathVariable Long id, @RequestBody MentorDtoRequest mentorDtoRequest) throws MentorNotFound {
        return mentorService.modifyMentors(id, mentorDtoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMentorById(@PathVariable Long id) {
        mentorService.deleteMentorById(id);
    }

    @GetMapping("/log")
    public List<ActionLog> getLogs() {
        return logService.getLogServices();
    }
}