package project.hotdealicious.customer.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Customer {

	private final Long id;
	private final String email;
	private final String password;
	private final String name;
	private final String phone;
	private final String address;
	private final Timestamp createdAt;
	private final String salt;
}
