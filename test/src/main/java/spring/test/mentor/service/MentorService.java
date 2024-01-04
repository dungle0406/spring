package spring.test.mentor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.test.mentor.dto.MentorDtoPostResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.entity.Mentor;
import spring.test.mentor.error.InvalidIdBadRequest;

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
        log.debug("Request: {}", request.toString());

        if(request.getRating() < 1f || request.getRating() > 5f) {
            throw new InvalidIdBadRequest();
        }

        Mentor mentor = mentorMapper.mapMentorFromRequest(request);
        log.debug("Mentor based on request: {}", mentor.toString());
        mentorRepository.save(mentor);

        MentorDtoPostResponse postResponse = mentorMapper.mapPostResponseFromMentor(mentor);
        log.debug("Post Response: {}", postResponse.toString());

        return postResponse;
    }

//    public List<MentorDtoResponse> findMentors() {
//
//    }
}
