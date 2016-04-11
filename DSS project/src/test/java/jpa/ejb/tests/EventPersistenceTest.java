//package jpa.ejb.tests;
//
//import static org.junit.Assert.*;
//
//
//import java.io.File;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.Date;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.arquillian.junit.InSequence;
//import org.jboss.shrinkwrap.api.ArchivePaths;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import entities.Event;
//import entities.EventCause;
//import entities.EventCauseId;
//import entities.FailureClass;
//import entities.MobileType;
//import entities.Network;
//import entities.NetworkId;
//import jpa.dao.EventCauseDAO;
//import jpa.dao.EventDAO;
//import jpa.dao.FailureClassDAO;
//import jpa.dao.IEventCauseDAO;
//import jpa.dao.IEventDAO;
//import jpa.dao.IFailureClassDAO;
//import jpa.dao.IMobileTypeDAO;
//import jpa.dao.INetworkDAO;
//import jpa.dao.MobileTypeDAO;
//import jpa.dao.NetworkDAO;
//
//
//
//@RunWith(Arquillian.class)
//public class EventPersistenceTest {
//
//	@Inject
//	IEventDAO eventDAO;
//	
//	@Inject
//	IFailureClassDAO failureClassDAO;
//	
//	@Inject
//	IEventCauseDAO eventCauseDAO;
//	
//	@Inject
//	IMobileTypeDAO mobileTypeDAO;
//	
//	@Inject
//	INetworkDAO networkDAO;
//	
//	@Deployment
//	public static JavaArchive createDeployment() {
//		return ShrinkWrap.create(JavaArchive.class, "test.jar")
//		.addClasses(Event.class,IEventDAO.class,EventDAO.class,
//					IFailureClassDAO.class,FailureClass.class,FailureClassDAO.class,
//				    IEventCauseDAO.class,EventCauseDAO.class,EventCause.class,EventCauseId.class,
//				    IMobileTypeDAO.class,MobileTypeDAO.class,MobileType.class,
//				    INetworkDAO.class,NetworkDAO.class,Network.class,NetworkId.class)
//		.addAsManifestResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"))
//		.addAsManifestResource(new File("src/test/resources/test-persistence.xml"),"persistence.xml");
//		}
//
//	@Test @InSequence(1)
//	public void loadBaseData(){
//		Event event = new Event(new Date(),4098,1,21060800,344,930,4,1000,0	,"11B",	"344930000000011",
//								"4809532081614990000","8226896360947470000","	1150444940909480000");
//		eventDAO.addData(event);
//
//		MobileType mobileType = new MobileType(100100,"G410","Mitsubishi","GSM 1800, GSM 900	G410"	,	
//				"(null)",	"(null)",	"(null)");
//		mobileTypeDAO.addMobileType(mobileType);
//		
//		EventCause eventCause = new EventCause(0,4097,"RRC CONN SETUP-SUCCESS");
//		eventCauseDAO.addEventCause(eventCause);
//		
//		FailureClass failureClass = new FailureClass (0,"EMERGENCY");
//		failureClassDAO.addFailureClass(failureClass);
//		
//		Network network = new Network(238,1,"Denmark","TDC-DK");
//		networkDAO.addNetwork(network);
//	}
//	
//	
//	@Test @InSequence(2)
//	public void testThatallEventshavebeenPopulated() throws ParseException, IOException {
//	
//		List<Event> result = (List<Event>) eventDAO.getAllEvents();
//	
//		
//		assertEquals(result.size(),1);
//
//	}
//	
//	@Test @InSequence (3)
//	public void failureClassTablePopluated(){
//		
//		List<FailureClass> result = (List<FailureClass>) failureClassDAO.getAllFailureClass();
//		
//		assertEquals(result.get(0).getFailureCode(),0);
//		assertEquals(result.get(0).getDescription(),"EMERGENCY");
//	}
//	
//	@Test @InSequence(4)
//	public void eventCausePopulated(){
//		
//		
//		List<EventCause> result = (List<EventCause>) eventCauseDAO.getAllEventCauses();
//		assertEquals(result.get(0).getCauseCode(),0);
//		assertEquals(result.get(0).getEventId(),4097);
//		assertEquals(result.get(0).getDescription(),"RRC CONN SETUP-SUCCESS");
//	}
//	
//	@Test @InSequence(5)
//	public void mobileTypePopulated(){
//		List<MobileType>result = (List<MobileType>) mobileTypeDAO.getAllMobileTypes();
//		assertEquals(result.get(0).getTac(),100100);
//		assertEquals(result.get(0).getMarketingName(),"G410");
//		assertEquals(result.get(0).getManufacturer(),"Mitsubishi");
//		assertEquals(result.get(0).getAccessCompatibility(),"GSM 1800, GSM 900	G410");
//		assertEquals(result.get(0).getUeType(),"(null)");
//		assertEquals(result.get(0).getOs(),"(null)");
//		assertEquals(result.get(0).getInputType(),"(null)");
//	}
//	
//	@Test @InSequence(6)
//	public void networksPopulated(){
//		List<Network>result = (List<Network>) networkDAO.getAllNetworks();
//		assertEquals(result.get(0).getMcc(),238);
//		assertEquals(result.get(0).getMnc(),1);
//		assertEquals(result.get(0).getCountry(),"Denmark");
//		assertEquals(result.get(0).getOperator(),"TDC-DK");
//	}
//	
//}
