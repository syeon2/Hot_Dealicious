package project.hotdealicious.owner.web;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;
import project.hotdealicious.owner.service.OwnerProfileService;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerProfileController {

	private final OwnerProfileService ownerProfileService;

	@PostMapping
	public ApiResult<Long> join(@Valid @RequestBody SaveOwnerDto saveOwnerDto) {
		Long ownerId = ownerProfileService.join(saveOwnerDto);
		return ApiResult.onSuccess(ownerId);
	}

	@PostMapping("/{id}")
	public ApiResult<Long> updateOwnerInfo(@PathVariable Long id, @Valid @RequestBody UpdateOwnerDto updateOwnerDto) {
		Long ownerId = ownerProfileService.update(id, updateOwnerDto);
		return ApiResult.onSuccess(ownerId);
	}

	@DeleteMapping("/{id}")
	public ApiResult<Long> withdrawOwner(@PathVariable Long id) {
		Long ownerId = ownerProfileService.withdraw(id);
		return ApiResult.onSuccess(ownerId);
	}
}
