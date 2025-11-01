package com.example.newsportal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subscribers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;

    @Enumerated(EnumType.STRING)
    private Channel channel;
}
