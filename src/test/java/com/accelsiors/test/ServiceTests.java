package com.accelsiors.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.accelsiors.test.model.Activity;
import com.accelsiors.test.model.Task;

public class ServiceTests {
	
	TestService testService;
	SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );

	@Before
	public void setUp() throws Exception {
		testService = new TestService();
	}

	@Test
	public void testActivities() {
		Activity[] activities = testService.findAllActivities().toArray( new Activity[0] );
		assertEquals( 3, activities.length );
		
		Activity driving = activities[0];
		assertEquals( new Integer(1), driving.getId() );
		assertEquals( "Driving", driving.getName() );
		
		Activity fishing = activities[1];
		assertEquals( new Integer(2), fishing.getId() );
		assertEquals( "Fishing", fishing.getName() );

		Activity shopping = activities[2];
		assertEquals( new Integer(3), shopping.getId() );
		assertEquals( "Shopping", shopping.getName() );
	}

	@Test
	public void testTasks() {
		Activity[] activities = testService.findAllActivities().toArray( new Activity[0] );
		Task[] tasks = testService.findAllTasks().toArray( new Task[0] );
		assertEquals( 3, tasks.length );
		
		Task driving = tasks[0];
		assertEquals( new Integer(1), driving.getId() );
		assertEquals( "2019-05-10", sdf.format( driving.getDate() ) );
		assertEquals( 1.5, driving.getDuration(), 0 );
		assertEquals( activities[0], driving.getActivity() );
		assertEquals( "There was a traffic jam", driving.getComment() );
		
		Task fishing = tasks[1];
		assertEquals( new Integer(2), fishing.getId() );
		assertEquals( "2019-05-10", sdf.format( fishing.getDate() ) );
		assertEquals( 5.5, fishing.getDuration(), 0 );
		assertEquals( activities[1], fishing.getActivity() );
		assertEquals( "It was exciting", fishing.getComment() );

		Task shopping = tasks[2];
		assertEquals( new Integer(3), shopping.getId() );
		assertEquals( "2019-05-12", sdf.format( shopping.getDate() ) );
		assertEquals( 3, shopping.getDuration(), 0 );
		assertEquals( activities[2], shopping.getActivity() );
		assertEquals( "It was boring", shopping.getComment() );
	}

	@Test
	public void testSave() throws Exception {
		
		
		Activity[] activities = testService.findAllActivities().toArray( new Activity[0] );
		Task[] tasks = testService.findAllTasks().toArray( new Task[0] );

		Task persist = tasks[0];

		Date newDate = new Date( persist.getDate().getTime()-86400*1000 );
		Task t = new Task( persist.getId(), newDate, 0.5, activities[1], "" );

		testService.saveTask( t );
		
		assertEquals( 3, testService.findAllTasks().size() );
		assertEquals( "", persist.getComment() );
		assertEquals( activities[1], persist.getActivity() );
		assertEquals( 0.5, persist.getDuration(), 0 );
		assertEquals( newDate, persist.getDate() );
		
		t.setActivity( null );
		boolean thrown = false;
		try {
			testService.saveTask( t );
		} catch( Exception ex ) {
//			ex.printStackTrace();
			assertEquals( "Invalid activity for task.", ex.getMessage() );
			thrown = true;
		}
		assertTrue( thrown );
		
		Activity a = new Activity( 100, "Working" );
		t.setActivity( null );
		thrown = false;
		try {
			testService.saveTask( t );
		} catch( Exception ex ) {
//			ex.printStackTrace();
			assertEquals( "Invalid activity for task.", ex.getMessage() );
			thrown = true;
		}
		assertTrue( thrown );
		t.setActivity( activities[1] );

		thrown = false;
		try {
			testService.saveTask( null );
		} catch( Exception ex ) {
//			ex.printStackTrace();
			assertEquals( "Task reference is null", ex.getMessage() );
			thrown = true;
		}
		assertTrue( thrown );

		thrown = false;
		t.setId( 100 );
		try {
			testService.saveTask( t );
		} catch( Exception ex ) {
//			ex.printStackTrace();
			assertEquals( "Invalid task", ex.getMessage() );
			thrown = true;
		}
		assertTrue( thrown );
		
		t.setId( null );
		testService.saveTask( t );
		tasks = testService.findAllTasks().toArray( new Task[0] );
		assertEquals( 4, tasks.length );
		
		assertEquals( 4, tasks[3].getId().intValue() );
		
		
//		

	}


}
