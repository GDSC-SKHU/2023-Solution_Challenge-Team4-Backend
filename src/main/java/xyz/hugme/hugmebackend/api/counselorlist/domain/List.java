package xyz.hugme.hugmebackend.api.counselorlist.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.*;
import lombok.*;
import xyz.hugme.hugmebackend.api.counselorlist.dto.ListDTO;

@Getter
@Entity
@Table(name="list")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private  Long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String gender;

    @Column
    private  String field;

    @Column
    private String review;

    @Column
    private int score;


    public ListDTO toDTO(){
        return ListDTO.builder()
                .id(id)
                .name(name)
                .age(age)
                .gender(gender)
                .field(field)
                .review(review)
                .score(score)
                .build();
    }
}
