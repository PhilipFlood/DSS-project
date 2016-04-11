//package ejb.tests;
//
//import static org.junit.Assert.*;
//
//import javax.ejb.EJB;
//import javax.inject.Inject;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ArchivePaths;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import entities.Event;
//import jpa.dao.EventDAO;
//import jpa.dao.IEventDAO;
//import services.EventService;
//import services.EventServiceEJB;
//
//
//@RunWith(Arquillian.class)
//public class EventPersistenceTest {
//
//	@Inject
//	EventService event;
//	
//	@Deployment
//	public static JavaArchive createDeployment() {
//		return ShrinkWrap.create(JavaArchive.class, "test.jar")
//		.addClasses(EventService.class, Event.class, EventServiceEJB.class,IEventDAO.class,EventDAO.class)
//		.addAsManifestResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"))
//		.addAsManifestResource("META-INF/persistence.xml","persistence.xml");
//		}
//	
//	@Test
//	public void testThatallEventshavebeenPopulated() {
//		//assert(true);
//		assertEquals(event.getAllEvents().size(),1597);
//	}
//
//	
//}
