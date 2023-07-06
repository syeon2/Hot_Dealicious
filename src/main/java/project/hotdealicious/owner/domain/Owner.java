package project.hotdealicious.owner.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Owner {

	private final Long id;
	private final String password;
	private final String name;
	private final String phone;
	private final Timestamp createdAt;
	private final String salt;
}
