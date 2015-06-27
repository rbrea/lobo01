package com.icetea.manager.pagodiario.service;

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
public class ProjectServiceTest {

//	@Inject
//	private ProjectService target;
//	@Inject
//	private ProjectDao projectDao;
//	@Inject
//	private ProjectXmlProcessor projectXmlProcessor;
	
	@Test @Ignore
	public void insert_ok(){
//		ProjectElement e = new ProjectElement();
//		e.setDbName("proj1");
//		this.target.insert(e);
//		List<Project> r = this.projectDao.findByCriteria(Restrictions.eq("name", "proj1"));
//		assertThat(r.size()).isEqualTo(1);
	}
//	
//	@Test
//	public void insert_file_ok(){
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("xml/fidias_dd_to_export.xml");
//		ProjectElement e = this.projectXmlProcessor.process(is);
//		this.target.insert(e);
//		List<Project> r = this.projectDao.findByCriteria(Restrictions.eq("name", "Proyecto1"));
//		assertThat(r.size()).isEqualTo(1);
//	}
//
//	@Test
//	public void searchByidType_ok(){
//		Project p = new Project();
//		p.setName("p1");
//		ProjectTable t1 = new ProjectTable();
//		t1.setName("t1");
//		p.addTable(t1);
//		ProjectTable t2 = new ProjectTable();
//		t2.setName("t2");
//		p.addTable(t2);
//		ColumnTable c1 = new ColumnTable();
//		c1.setName("c1");
//		t1.addColumn(c1);
//		
//		Project p2 = new Project();
//		p.setName("p2");
//		ProjectTable t3 = new ProjectTable();
//		t3.setName("t3");
//		p2.addTable(t3);
//		ProjectTable t4 = new ProjectTable();
//		t4.setName("t4");
//		p2.addTable(t4);
//		ColumnTable c2 = new ColumnTable();
//		c2.setName("c2");
//		t3.addColumn(c2);
//		
//		this.projectDao.saveOrUpdate(p);
//		this.projectDao.saveOrUpdate(p2);
//		
//		List<ProjectDto> pp = this.target.searchByidType("proj", p.getId());
//		assertThat(pp.size()).isEqualTo(1);
//		
//		pp = this.target.searchByidType("table", t1.getId());
//		assertThat(pp.size()).isEqualTo(1);
//	}
}
