package project.hotdealicious.owner.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import project.hotdealicious.owner.dao.IOwnerDAO;
import project.hotdealicious.owner.domain.Owner;
import project.hotdealicious.owner.dto.SaveOwnerDto;
import project.hotdealicious.owner.dto.UpdateOwnerDto;

@SpringBootTest
@Sql(scripts = {"classpath:schema_mysql.sql"})
class OwnerProfileServiceTest {

	@Autowired
	private OwnerProfileService ownerProfileService;

	@Autowired
	private IOwnerDAO ownerDAO;

	@Test
	void join() {
		// given
		String name = "suyeon";
		SaveOwnerDto saveOwnerDto = new SaveOwnerDto(name, "01011112222");
		saveOwnerDto.setPassword("testing");

		// when
		ownerProfileService.join(saveOwnerDto);

		// then
		Owner findOwner = ownerDAO.findById(1L);
		assertThat(findOwner.getName()).isEqualTo(name);
	}

	@Test
	void update() {
		// given
		join();
		UpdateOwnerDto updateOwnerDto = new UpdateOwnerDto("01022223333");
		updateOwnerDto.setPassword("testing");

		// when
		ownerProfileService.update(1L, updateOwnerDto);

		// then
		Owner findOwner = ownerDAO.findById(1L);
		assertThat(findOwner.getPhone()).isEqualTo("01022223333");
	}
}
