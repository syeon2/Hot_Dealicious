package project.hotdealicious.order.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Orders {

	private final Long id;
	private final OrderStatus orderStatus;
	private final String address;
	private final Timestamp createdAt;
	private final Timestamp updatedAt;

	private final Long customerId;
	private final Long storeId;
	private final Long riderId;
}
