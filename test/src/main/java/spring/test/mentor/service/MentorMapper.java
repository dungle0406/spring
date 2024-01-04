package spring.test.mentor.service;

import org.mapstruct.Mapper;
import spring.test.mentor.dto.MentorDtoPostResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.entity.Mentor;

@Mapper(componentModel = "spring")
public interface MentorMapper {
    Mentor mapMentorFromRequest(MentorDtoRequest request);
    MentorDtoPostResponse mapPostResponseFromMentor(Mentor mentor);
}
