package project.hotdealicious.order.dao.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.hotdealicious.order.domain.Orders;
import project.hotdealicious.order.dto.SaveOrdersDto;
import project.hotdealicious.order.dto.UpdateOrdersDto;

@Mapper
public interface OrdersMapper {

	Long save(SaveOrdersDto saveOrderDto);

	Optional<Orders> findById(Long id);

	void update(@Param("id") Long id, @Param("updateParam") UpdateOrdersDto updateOrdersDto);

	void delete(Long id);
}
