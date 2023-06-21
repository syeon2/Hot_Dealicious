package project.hotdealicious.rider.web;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.util.SessionUtil;
import project.hotdealicious.rider.domain.Rider;
import project.hotdealicious.rider.service.RiderLoginService;

@RestController
@RequestMapping("/api/v1/rider")
@RequiredArgsConstructor
public class RiderLoginController {

	private final RiderLoginService riderLoginService;

	@GetMapping("/login")
	public Rider login(HttpSession session, Long id, String password) {
		Rider loginRider = riderLoginService.login(id, password);

		if (loginRider == null) {
			throw new NoSuchElementException("아이디 또는 비밀번호가 밎지 않습니다.");
		}

		SessionUtil.setLoginRiderId(session, id);

		return loginRider;
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {
		SessionUtil.removeLoginRiderID(session);
	}
}
