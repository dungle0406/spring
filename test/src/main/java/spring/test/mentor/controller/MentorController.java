package spring.test.mentor.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.test.mentor.aop.LogAspect;
import spring.test.mentor.aop.LogMessage;
import spring.test.mentor.dto.MentorDtoPutResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.dto.MentorDtoResponse;
import spring.test.mentor.dto.MentorPostResponse;
import spring.test.mentor.error.MentorNotFoundException;
import spring.test.mentor.service.MentorService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mentors")
@Slf4j
public class MentorController {
    private final MentorService mentorService;

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MentorPostResponse createNewMentors(@RequestBody MentorDtoRequest mentorDtoRequest) throws BadRequestException {
        return mentorService.createNewMentor(mentorDtoRequest);
    }
    @GetMapping("/actiontlog")
    public List<LogMessage> getActionLogs() {
        List<LogMessage> messages = LogAspect.getLogMessages();
        log.debug(messages.toString());
        return messages;
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
    public MentorDtoPutResponse modifyMentors(@PathVariable Long id, @RequestBody MentorDtoRequest mentorDtoRequest) throws MentorNotFoundException {
        return mentorService.modifyMentors(id, mentorDtoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteMentorById(@PathVariable Long id) {
        mentorService.deleteMentorById(id);
    }
}
