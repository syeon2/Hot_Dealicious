package project.hotdealicious.rider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.hotdealicious.rider.domain.WorkStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiderGeoDto {

	private WorkStatus workStatus;
	private Long channelId;
	private WorkStatus status;
	private GeoLocation data;
}
