package project.hotdealicious.order.dao.mybatis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.order.dao.IOrdersDao;
import project.hotdealicious.order.domain.Orders;
import project.hotdealicious.order.dto.SaveOrdersDto;
import project.hotdealicious.order.dto.UpdateOrdersDto;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderDao implements IOrdersDao {

	private final OrdersMapper ordersMapper;

	@Override
	public Long save(SaveOrdersDto saveOrdersDto) {
		ordersMapper.save(saveOrdersDto);

		return saveOrdersDto.getId();
	}

	@Override
	public Optional<Orders> findById(Long id) {
		return ordersMapper.findById(id);
	}

	@Override
	public void update(Long id, UpdateOrdersDto updateOrdersDto) {
		ordersMapper.update(id, updateOrdersDto);
	}

	@Override
	public void delete(Long id) {
		ordersMapper.delete(id);
	}
}
