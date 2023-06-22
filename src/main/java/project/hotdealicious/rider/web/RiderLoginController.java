package project.hotdealicious.rider.web;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.SessionUtil;
import project.hotdealicious.rider.domain.Rider;
import project.hotdealicious.rider.dto.RiderLoginDto;
import project.hotdealicious.rider.service.RiderLoginService;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class RiderLoginController {

	private final RiderLoginService riderLoginService;

	@PostMapping("/login")
	public Rider login(HttpSession session, @Valid @RequestBody RiderLoginDto riderLoginDto) {
		Optional<Rider> loginRider = riderLoginService.login(riderLoginDto.getId(), riderLoginDto.getPassword());

		if (loginRider.isEmpty()) {
			throw new NoSuchElementException("아이디 또는 비밀번호가 밎지 않습니다.");
		}

		Rider rider = loginRider.get();
		SessionUtil.setLoginKey(session, SessionUtil.LOGIN_RIDER_KEY, rider.getId());

		return loginRider.get();
	}

	@PostMapping("/logout")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginKey(session, SessionUtil.LOGIN_RIDER_KEY);
	}
}
