package project.hotdealicious.owner.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OwnerLoginDto {

	private final Long id;
	private final String password;
}
