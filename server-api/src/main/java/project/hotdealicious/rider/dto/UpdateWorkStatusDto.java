package project.hotdealicious.rider.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.hotdealicious.rider.domain.WorkStatus;

@Getter
@NoArgsConstructor
public class UpdateWorkStatusDto {

	private WorkStatus workStatus;
}
