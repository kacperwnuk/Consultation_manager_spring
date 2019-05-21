package com.example.pikkonsultacje.Dto;

import com.example.pikkonsultacje.Enum.Status;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ConsultationSearchForm {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateStart;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateEnd;

    private String studentUsername;

    private String tutorUsername;

    private Status status;

    @Override
    public String toString() {
        return "ConsultationSearchForm{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", student=" + studentUsername +
                ", tutor=" + tutorUsername +
                ", consultationType=" + status +
                '}';
    }

}
