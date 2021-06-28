package com.example.readingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tires_Model {
    private Integer frontLeft;
    private Integer frontRight;
    private Integer rearLeft;
    private Integer rearRight;
}
