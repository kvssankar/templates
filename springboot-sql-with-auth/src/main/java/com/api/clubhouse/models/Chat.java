package com.api.clubhouse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private String senderId;
    private String message;
    private String sender;
    private String url;
    private LocalDateTime timestamp= LocalDateTime.now();
}
