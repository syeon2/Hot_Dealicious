package project.hotdealicious.rider.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.rider.domain.Rider;
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

	@GetMapping("/{id}")
	public Rider findRider(@PathVariable Long id) {
		return riderProfileService.findRider(id);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable Long id, @Valid @RequestBody UpdateRiderDto updateRiderDto) {
		riderProfileService.update(id, updateRiderDto);
	}

	@PostMapping("/commute/{id}")
	void checkCommute(@PathVariable Long id, @RequestBody UpdateWorkStatusDto updateWorkStatusDto) {
		riderProfileService.updateWorkStatus(id, updateWorkStatusDto.getWorkStatus());
	}

	@DeleteMapping("/{id}")
	void withdraw(@PathVariable Long id) {
		riderProfileService.withdraw(id);
	}
}
