package com.stealth.yash.FaceRecognition.model;

import com.stealth.yash.FaceRecognition.enums.Days;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;

/**
 * @author STEALTH
 * @created 02/11/2020 - 9:09 p.m.
 * @project demo
 */
@Data
@Embeddable
public class Timeslot {

    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(value = EnumType.STRING)
    private Days day;

}
