package project.hotdealicious.rider.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.rider.dto.SaveRiderDto;
import project.hotdealicious.rider.dto.UpdateRiderDto;
import project.hotdealicious.rider.service.RiderProfileService;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class RiderProfileController {

	private final RiderProfileService riderProfileService;

	@PostMapping
	public ApiResult<Long> join(@Valid @RequestBody SaveRiderDto saveRiderDto) {
		Long riderId = riderProfileService.join(saveRiderDto);
		return ApiResult.onSuccess(riderId);
	}

	@PostMapping("/{id}")
	public ApiResult<Long> updateRiderInfo(@PathVariable Long id, @Valid @RequestBody UpdateRiderDto updateRiderDto) {
		Long riderId = riderProfileService.update(id, updateRiderDto);
		return ApiResult.onSuccess(riderId);
	}

	@DeleteMapping("/{id}")
	public ApiResult<Long> withdrawRider(@PathVariable Long id) {
		Long riderId = riderProfileService.withdraw(id);
		return ApiResult.onSuccess(riderId);
	}
}
