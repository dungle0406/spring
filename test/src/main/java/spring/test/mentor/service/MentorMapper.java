package spring.test.mentor.service;

import org.mapstruct.*;
import spring.test.mentor.dto.MentorDtoPutResponse;
import spring.test.mentor.dto.MentorDtoRequest;
import spring.test.mentor.dto.MentorDtoResponse;
import spring.test.mentor.dto.MentorPostResponse;
import spring.test.mentor.entity.Mentor;

@Mapper(componentModel = "spring")
public interface MentorMapper {
    Mentor mapMentorFromDtoRequest(MentorDtoRequest request);

    MentorDtoResponse mapDtoResponseFromMentor(Mentor mentor);

    MentorDtoPutResponse mapDtoPutResponseFromMentor(Mentor mentor);

    MentorPostResponse mapMentorPostResponseFromMentor(Mentor mentor);

    @Mapping(target = "rating", ignore = true)
    void updateMentorFromMentorDtoRequest(@MappingTarget Mentor mentor, MentorDtoRequest request);
}
