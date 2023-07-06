package project.hotdealicious.rider.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.rider.dto.SaveRiderDto;
import project.hotdealicious.rider.dto.UpdateRiderDto;
import project.hotdealicious.rider.dto.UpdateWorkStatusDto;
import project.hotdealicious.rider.service.RiderProfileService;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class RiderProfileController {

	private final RiderProfileService riderProfileService;

	@PostMapping
	public Long join(@Valid @RequestBody SaveRiderDto saveRiderDto) {
		return riderProfileService.join(saveRiderDto);
	}

	@PostMapping("/{id}")
	public Long updateRiderInfo(@PathVariable Long id, @Valid @RequestBody UpdateRiderDto updateRiderDto) {
		return riderProfileService.update(id, updateRiderDto);
	}

	@PostMapping("/commute/{id}")
	public Long checkCommute(@PathVariable Long id, @RequestBody UpdateWorkStatusDto updateWorkStatusDto) {
		return riderProfileService.updateWorkStatus(id, updateWorkStatusDto.getWorkStatus());
	}

	@DeleteMapping("/{id}")
	public Long withdrawRider(@PathVariable Long id) {
		return riderProfileService.withdraw(id);
	}
}
