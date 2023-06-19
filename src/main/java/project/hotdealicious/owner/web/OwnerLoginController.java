package project.hotdealicious.owner.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.SessionUtil;
import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.OwnerLoginDto;
import project.hotdealicious.owner.service.OwnerLoginService;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerLoginController {

	private final OwnerLoginService ownerLoginService;

	@GetMapping("/login")
	public Owner login(HttpSession session, @Valid @RequestBody OwnerLoginDto ownerLoginDto) {
		Owner loginOwner = ownerLoginService.login(ownerLoginDto.getId(), ownerLoginDto.getPassword());

		SessionUtil.setLoginOwnerId(session, ownerLoginDto.getId());

		return loginOwner;
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginOwnerId(session);
	}
}
