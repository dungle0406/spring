package spring.test.mentor.service;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import spring.test.cohort.service.CohortRepository;
import spring.test.mentor.error.InvalidRatingBadRequest;
import spring.test.mentor.error.LackOfInformation;
import spring.test.mentor.error.MentorNotFound;
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

    @Autowired
    public MentorService(MentorRepository mentorRepository, CohortRepository cohortRepository, MentorMapper mentorMapper) {
        this.mentorRepository = mentorRepository;
        this.cohortRepository = cohortRepository;
        this.mentorMapper = mentorMapper;
    }

    public MentorPostResponse createNewMentor(MentorDtoRequest request) {
        log.debug("Request: {}", request);

        if (request.getRating() < 1f || request.getRating() > 5f) {
            throw new InvalidRatingBadRequest();
        }

//        if(!cohortRepository.existsById(request.getCohortId())) {
//            throw new CohortNotFoundException();
//        }
//        if (!mentorRepository.findAll(Example.of(mentorMapper.mapMentorFromDtoRequest(request))).isEmpty()) {
//            throw new UsedIdBadRequest();
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
        Mentor mentor = mentorRepository.findById(id).orElseThrow(MentorNotFound::new);
        log.debug("Mentor Request: {}", mentor);

        return mentorMapper.mapDtoResponseFromMentor(mentor);
    }

    public MentorDtoPutResponse modifyMentors(Long id, MentorDtoRequest request) {
        log.debug("Request: {}", request);

        Mentor mentor = mentorRepository.findById(id).orElseThrow(MentorNotFound::new);
        log.debug("Mentor Request: {}", mentor);

        mentorMapper.updateMentorFromMentorDtoRequest(mentor, request);
        log.debug("Mentor Response: {}", mentor);
        mentorRepository.save(mentor);

        MentorDtoPutResponse response = mentorMapper.mapDtoPutResponseFromMentor(mentorRepository.findById(id).orElseThrow(MentorNotFound::new));
        log.debug("Response: {}", response);

        return response;
    }

    public void deleteMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(MentorNotFound::new);
        log.debug("Mentor Request: {}", mentor);

        mentorRepository.deleteById(id);
    }
}
