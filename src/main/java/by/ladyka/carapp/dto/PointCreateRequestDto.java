package by.ladyka.carapp.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PointCreateRequestDto {
    private double latitude;
    private double longitude;
    private String vin;
    private double speed;
    private ZonedDateTime createDate;
}
