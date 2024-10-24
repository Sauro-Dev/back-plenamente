package com.plenamente.sgt.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "materials")
@Getter
@Setter
@NoArgsConstructor
public class Material {
    @Id
    @Column(length = 4)
    private String idMaterial;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    private String description;

    private Integer stock;

    private Boolean isComplete;

    @NotNull
    private boolean isSupport;

    @Enumerated(EnumType.STRING)
    private MaterialStatus status;

    @Column(name = "high_date", updatable = false)
    private LocalDateTime highDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room")
    private Room room;

    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MaterialArea> materialAreas;

    @PrePersist
    public void prePersist() {
        this.highDate = LocalDateTime.now();
    }
}