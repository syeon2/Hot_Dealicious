package project.hotdealicious.order.dao;

import java.util.Optional;

import project.hotdealicious.order.domain.Orders;
import project.hotdealicious.order.dto.SaveOrdersDto;
import project.hotdealicious.order.dto.UpdateOrdersDto;

public interface IOrdersDao {

	Long save(SaveOrdersDto saveOrdersDto);

	Optional<Orders> findById(Long id);

	void update(Long id, UpdateOrdersDto updateOrdersDto);

	void delete(Long id);
}
