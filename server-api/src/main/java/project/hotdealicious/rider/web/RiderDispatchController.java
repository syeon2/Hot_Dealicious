package project.hotdealicious.rider.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.rider.dto.GeoLocation;
import project.hotdealicious.rider.service.RiderDispatcherService;

@RestController
@RequestMapping("/api/v1/rider/dispatch")
@RequiredArgsConstructor
public class RiderDispatchController {

	private final RiderDispatcherService riderDispatcherService;

	@PostMapping("/{id}")
	public void dispatchOrder(@PathVariable Long id, @RequestBody GeoLocation geoLocation) {
		riderDispatcherService.dispatchOrder(id, geoLocation);
	}
}
