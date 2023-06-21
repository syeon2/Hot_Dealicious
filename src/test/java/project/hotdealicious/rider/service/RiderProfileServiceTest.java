package project.hotdealicious.rider.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import project.hotdealicious.rider.dao.IRiderDAO;
import project.hotdealicious.rider.domain.Rider;
import project.hotdealicious.rider.dto.SaveRiderDto;
import project.hotdealicious.rider.dto.UpdateRiderDto;

@SpringBootTest
@Sql(scripts = "classpath:schema_mysql.sql")
class RiderProfileServiceTest {

	@Autowired
	private RiderProfileService riderProfileService;

	@Autowired
	private IRiderDAO riderDAO;

	@Test
	void join() {
		// given
		String name = "suyeon";
		SaveRiderDto saveRiderDto = new SaveRiderDto(name, "01011112222");
		saveRiderDto.setPassword("testing");

		// when
		riderProfileService.join(saveRiderDto);

		// then
		Rider findRider = riderDAO.findById(1L);
		assertThat(findRider.getName()).isEqualTo(name);
	}

	@Test
	void update() {
		// given
		join();
		UpdateRiderDto updateRiderDto = new UpdateRiderDto("01033334444");
		updateRiderDto.setPassword("testing");

		// when
		riderProfileService.update(1L, updateRiderDto);

		// then
		Rider findRider = riderDAO.findById(1L);
		assertThat(findRider.getPhone()).isEqualTo("01033334444");
	}
}
