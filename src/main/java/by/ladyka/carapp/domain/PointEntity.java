package by.ladyka.carapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "car_point")
@Data
public class PointEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_point_id")
	private Long id;

	private double latitude;
	private double longitude;

	@Column(name = "car_vin")
	private String vin;

	private double speed;

	@Column(name = "create_date")
	private ZonedDateTime createDate;


}
