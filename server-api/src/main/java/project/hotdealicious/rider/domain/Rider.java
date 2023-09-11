package project.hotdealicious.rider.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Rider {

	private final Long id;
	private final String password;
	private final String name;
	private final String phone;
	private final WorkStatus workStatus;
	private final Timestamp createdAt;
	private final Timestamp updatedAt;
	private final String salt;
}
