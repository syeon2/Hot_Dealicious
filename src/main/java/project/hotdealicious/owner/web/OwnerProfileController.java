package project.hotdealicious.owner.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;
import project.hotdealicious.owner.service.OwnerProfileService;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerProfileController {

	private final OwnerProfileService ownerProfileService;

	@GetMapping
	public Long join(@Valid @RequestBody SaveOwnerDto saveOwnerDto) {
		return ownerProfileService.join(saveOwnerDto);
	}

	@PostMapping("/{id}")
	public void update(@PathVariable Long id, @Valid @RequestBody UpdateOwnerDto updateOwnerDto) {
		ownerProfileService.update(id, updateOwnerDto);
	}

	@DeleteMapping("/{id}")
	public void withdraw(@PathVariable Long id) {
		ownerProfileService.withdraw(id);
	}
}
