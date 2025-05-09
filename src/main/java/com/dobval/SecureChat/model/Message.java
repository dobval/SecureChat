package com.dobval.SecureChat.model;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id 
    @GeneratedValue
    private Long id;

    private String sender;
    private String content;

    @CreatedDate
    private Instant timestamp;
}

