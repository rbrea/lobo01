package com.icetea.manager.pagodiario.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ProjectDaoTest {

//	@Inject
//	private ProjectDao target;
	
	@Test @Ignore
	public void saveOrUpdate_ok(){
//		Project project = new Project();
//		project.setName("proj1");
//		this.target.saveOrUpdate(project);
//		
//		List<Project> result = this.target.findByCriteria(Restrictions.eq("name", "proj1"));
//		assertThat(result.size()).isEqualTo(1);
	}
	
}
