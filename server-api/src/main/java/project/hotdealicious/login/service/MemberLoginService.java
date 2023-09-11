package project.hotdealicious.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.hotdealicious.common.error.exception.LoginException;
import project.hotdealicious.common.util.Sha256Util;
import project.hotdealicious.customer.dao.ICustomerDAO;
import project.hotdealicious.customer.domain.Customer;
import project.hotdealicious.owner.dao.IOwnerDAO;
import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.rider.dao.IRiderDAO;
import project.hotdealicious.rider.domain.Rider;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

	private final ICustomerDAO customerDAO;
	private final IOwnerDAO ownerDAO;
	private final IRiderDAO riderDAO;

	public Object login(String id, String password, UserType userType) {
		Optional<?> memberOptional = findMemberOptional(id, userType);

		if (memberOptional.isEmpty()) {
			throw new LoginException("아이디가 존재하지 않습니다.");
		}

		Optional<?> compareMemberPasswordOptional = compareMemberPassword(password, userType, memberOptional);

		if (compareMemberPasswordOptional.isEmpty()) {
			throw new LoginException("비밀번호가 일치하지 않습니다.");
		}

		return compareMemberPasswordOptional.get();
	}

	private Optional<?> findMemberOptional(String id, UserType userType) {
		Optional<?> findMemberOptional;

		if (userType == UserType.CUSTOMER) {
			findMemberOptional = customerDAO.findByLoginId(id);
		} else if (userType == UserType.OWNER) {
			findMemberOptional = ownerDAO.findById(Long.parseLong(id));
		} else if (userType == UserType.RIDER) {
			findMemberOptional = riderDAO.findById(Long.parseLong(id));
		} else {
			throw new IllegalArgumentException("지원하지 않는 유저타입입니다.");
		}
		return findMemberOptional;
	}

	private Optional<?> compareMemberPassword(String password, UserType userType, Optional<?> memberOptional) {
		String salt = "";
		if (userType == UserType.CUSTOMER) {
			salt = ((Customer)memberOptional.get()).getSalt();
		} else if (userType == UserType.OWNER) {
			salt = ((Owner)memberOptional.get()).getSalt();
		} else {
			salt = ((Rider)memberOptional.get()).getSalt();
		}

		String encryptPassword = Sha256Util.getEncrypt(password, salt);
		return memberOptional.filter(member -> {
			String memberPassword = "";
			if (userType == UserType.CUSTOMER) {
				memberPassword = ((Customer)member).getPassword();
			} else if (userType == UserType.OWNER) {
				memberPassword = ((Owner)member).getPassword();
			} else if (userType == UserType.RIDER) {
				memberPassword = ((Rider)member).getPassword();
			}

			return memberPassword.equals(encryptPassword);
		});
	}
}
