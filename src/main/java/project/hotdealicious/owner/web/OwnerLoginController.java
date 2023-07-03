package project.hotdealicious.owner.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.SessionUtil;
import project.hotdealicious.common.util.basewrapper.ApiResult;
import project.hotdealicious.login.service.MemberLoginService;
import project.hotdealicious.login.service.UserType;
import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.OwnerLoginDto;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerLoginController {

	private final MemberLoginService memberLoginService;

	@PostMapping("/login")
	public ApiResult<Owner> login(HttpSession session, @Valid @RequestBody OwnerLoginDto ownerLoginDto) {
		Owner loginOwner = (Owner)memberLoginService.login(ownerLoginDto.getId().toString(),
			ownerLoginDto.getPassword(),
			UserType.OWNER);

		SessionUtil.setLoginKey(session, SessionUtil.LOGIN_OWNER_KEY, loginOwner.getId());

		return ApiResult.onSuccess(loginOwner);
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginKey(session, SessionUtil.LOGIN_OWNER_KEY);
	}
}
