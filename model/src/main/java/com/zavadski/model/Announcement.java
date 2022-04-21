package com.zavadski.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zavadski.model.enumeration.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "announcement")
public class Announcement {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column
    private String body;

    @Column
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User user;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+3")
    private LocalDate dateOfCreating;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+3")
    private LocalDate dateOfClosing;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
