package com.khans.codes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter @Setter
@ToString
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    private Date createdAt;
    private Date updatedAt;
    ///ManyToOne with Backlog

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "backlog_id", updatable = false,nullable = false)
    @JsonIgnore
    Backlog backlog;

    @Column(updatable = false)
    private String projectIdentifier;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.updatedAt = new Date();
    }
}

