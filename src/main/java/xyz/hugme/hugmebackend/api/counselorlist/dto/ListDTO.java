package xyz.hugme.hugmebackend.api.counselorlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ListDTO {
    private  Long id;
    private String name;
    private int age;
    private String gender;
    private String field;
    private String review;
    private int score;
}
