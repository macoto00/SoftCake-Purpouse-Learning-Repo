package com.example.demoapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadedFile {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;
    private boolean isScanned;
}
