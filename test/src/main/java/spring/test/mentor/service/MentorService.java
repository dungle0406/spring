package spring.test.mentor.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import spring.test.cohort.error.CohortNotFoundException;
import spring.test.cohort.service.CohortRepository;
import spring.test.mentor.aop.MentorExceptionController;
import spring.test.mentor.aop.MentorNotFoundException;
import spring.test.mentor.aop.UsedIdBadRequest;
import spring.test.mentor.dto.MentorDtoPutResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.dto.MentorDtoResponse;
import spring.test.mentor.dto.MentorPostResponse;
import spring.test.mentor.entity.Mentor;

import java.util.List;

@Service
@Slf4j
public class MentorService {
    private final MentorRepository mentorRepository;
    private final CohortRepository cohortRepository;
    private final MentorMapper mentorMapper;
    private final MentorExceptionController mentorExceptionController;

    @Autowired
    public MentorService(MentorRepository mentorRepository, CohortRepository cohortRepository, MentorMapper mentorMapper, MentorExceptionController mentorExceptionController) {
        this.mentorRepository = mentorRepository;
        this.cohortRepository = cohortRepository;
        this.mentorMapper = mentorMapper;
        this.mentorExceptionController = mentorExceptionController;
    }

    public MentorPostResponse createNewMentor(MentorDtoRequest request) {
        log.debug("Request: {}", request);

        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new UsedIdBadRequest();
        }

//        if(!cohortRepository.existsById(request.getCohortId())) {
//            throw new CohortNotFoundException();
//        }

        Mentor mentor = mentorMapper.mapMentorFromDtoRequest(request);
        log.debug("Mentor Request: {}", mentor);
        mentorRepository.save(mentor);

        MentorPostResponse mentorPostResponse = mentorMapper.mapMentorPostResponseFromMentor(mentor);
        log.debug("Response: {}", mentorPostResponse);

        return mentorPostResponse;
    }

    public List<MentorDtoResponse> findMentors(MentorDtoRequest request) {
        log.debug("Request: {}", request);

        List<Mentor> mentors = mentorRepository
                .findAll(Example.of(mentorMapper.mapMentorFromDtoRequest(request)));
        log.debug("Mentor Response: {}", mentors);

        List<MentorDtoResponse> responses = mentors
                .stream()
                .map(mentorMapper::mapDtoResponseFromMentor)
                .toList();
        log.debug("Response: {}", responses);

        return responses;
    }

    public MentorDtoResponse findMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(MentorNotFoundException::new);
        log.debug("Mentor Request: {}", mentor);

        return mentorMapper.mapDtoResponseFromMentor(mentor);
    }

    public MentorDtoPutResponse modifyMentors(Long id, MentorDtoRequest request) {
        log.debug("Request: {}", request);

        Mentor mentor = mentorRepository.findById(id).orElseThrow(MentorNotFoundException::new);
        log.debug("Mentor Request: {}", mentor);

        mentorMapper.updateMentorFromMentorDtoRequest(mentor, request);
        log.debug("Mentor Response: {}", mentor);
        mentorRepository.save(mentor);

        MentorDtoPutResponse response = mentorMapper.mapDtoPutResponseFromMentor(mentorRepository.findById(id).orElseThrow(MentorNotFoundException::new));
        log.debug("Response: {}", response);

        return response;
    }

    public void deleteMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(MentorNotFoundException::new);
        log.debug("Mentor Request: {}", mentor);

        mentorRepository.deleteById(id);
    }
}
