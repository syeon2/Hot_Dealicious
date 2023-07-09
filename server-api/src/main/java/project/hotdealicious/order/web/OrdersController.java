package project.hotdealicious.order.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.order.dto.SaveOrdersDto;
import project.hotdealicious.order.dto.UpdateOrdersDto;
import project.hotdealicious.order.service.OrdersService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

	private final OrdersService ordersService;

	@PostMapping
	public ApiResult<Long> createOrder(@RequestBody SaveOrdersDto saveOrdersDto) {
		Long order = ordersService.createOrder(saveOrdersDto);

		return ApiResult.onSuccess(order);
	}

	@PostMapping("/{orderId}")
	public void changeOrdersStatus(@PathVariable Long orderId, @RequestBody UpdateOrdersDto updateOrdersDto) {
		ordersService.changeOrderStatus(orderId, updateOrdersDto);
	}
}
