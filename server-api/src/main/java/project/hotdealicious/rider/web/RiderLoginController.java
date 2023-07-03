package project.hotdealicious.rider.web;

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
import project.hotdealicious.rider.domain.Rider;
import project.hotdealicious.rider.dto.RiderLoginDto;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class RiderLoginController {

	private final MemberLoginService memberLoginService;

	@PostMapping("/login")
	public ApiResult<Rider> login(HttpSession session, @Valid @RequestBody RiderLoginDto riderLoginDto) {
		Rider loginRider = (Rider)memberLoginService.login(riderLoginDto.getId().toString(),
			riderLoginDto.getPassword(), UserType.RIDER);

		SessionUtil.setLoginKey(session, SessionUtil.LOGIN_RIDER_KEY, loginRider.getId());

		return ApiResult.onSuccess(loginRider);
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginKey(session, SessionUtil.LOGIN_RIDER_KEY);
	}
}
