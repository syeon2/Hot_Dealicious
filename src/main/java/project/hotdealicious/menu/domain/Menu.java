package project.hotdealicious.menu.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Menu {

	private final Long id;
	private final String name;
	private final String introduction;
	private final Integer stock;
	private final BigDecimal price;
	private final Timestamp createdAt;
	private final Timestamp updatedAt;
	private final Long shopId;
}
