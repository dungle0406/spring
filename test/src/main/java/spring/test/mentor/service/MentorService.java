package spring.test.mentor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import spring.test.mentor.dto.MentorDtoPostResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.dto.MentorDtoResponse;
import spring.test.mentor.entity.Mentor;
import spring.test.mentor.error.InvalidIdBadRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MentorService {
    private final MentorRepository mentorRepository;
    private final MentorMapper mentorMapper;

    @Autowired
    public MentorService(MentorRepository mentorRepository, MentorMapper mentorMapper) {
        this.mentorRepository = mentorRepository;
        this.mentorMapper = mentorMapper;
    }

    public MentorDtoPostResponse createNewMentor(MentorDtoRequest request) {
        log.debug("Request: {}", request);

        if (request.getRating() < 1f || request.getRating() > 5f) {
            throw new InvalidIdBadRequest();
        }

        Mentor mentor = mentorMapper.mapMentorFromRequest(request);
        log.debug("Mentor Response: {}", mentor.toString());
        mentorRepository.save(mentor);

        MentorDtoPostResponse postResponse = mentorMapper.mapPostResponseFromMentor(mentor);
        log.debug("Post Response: {}", postResponse.toString());

        return postResponse;
    }

    public List<MentorDtoResponse> findMentors(MentorDtoRequest request) {
        log.debug("Request: {}", request);

        log.debug(mentorRepository.findAll().toString());
        log.debug(mentorMapper.mapMentorFromRequest(request).toString());

        List<Mentor> mentors = mentorRepository.findAll(Example.of(mentorMapper.mapMentorFromRequest(request))).stream().toList();
        log.debug("Mentor Response: {}", mentors);

        List<MentorDtoResponse> dtoResponses = mentors.stream()
                .map(mentorMapper::mapResponseFromMentor)
                .toList();
        log.debug(dtoResponses.toString());

        return dtoResponses;
    }
}
