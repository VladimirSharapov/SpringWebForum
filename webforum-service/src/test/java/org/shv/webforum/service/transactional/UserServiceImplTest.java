/**
 * This project is a simple web forum. I created it just to
 * demonstrate my programming skills to potential employers.
 *
 * Here is short description: ( for more detailed description please reade README.md or
 * go to https://github.com/VladimirSharapov/SpringWebForum )
 *
 * Front-end: jsp, bootstrap, jquery
 * Back-end: Spring, Hibernate
 * DB: MySQL and H2(for testing) were used while developing, but the project is database independent.
 *     Though it must be a relational DB.
 * Tools: git,maven,jenkins,nexus,liquibase.
 *
 * My LinkedIn profile: https://ru.linkedin.com/in/vladimir-sharapov-6075207
 */
package org.shv.webforum.service.transactional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {


    @Mock
    List mockedList;
//
//    @Before
//    public void setUp() {
//        mockedList = mock(List.class);
//    }

    @Test
    public void testGetCurrentUser() throws Exception {

    }

    @Test
    public void testEditUser() throws Exception {

    }

    @Test
    public void testUpdatePostCount() throws Exception {

      //  when(mockedList.indexOf("one")).thenReturn(10,11);
        when(mockedList.indexOf(any(String.class))).thenReturn(10,11);
        when(mockedList.indexOf(anyString())).thenReturn(10, 11);
        when(mockedList.indexOf(eq("one"))).thenReturn(10, 11);
      //  doThrow(new RuntimeException()).when(mockedList).clear();

        // when(mockedList.indexOf("one")).thenThrow(new RuntimeException());

        mockedList.add("one");
        mockedList.add("two");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();

        int i = mockedList.indexOf("one");
        System.out.println(i);
        i = mockedList.indexOf("aone");
        System.out.println(i);

        verify(mockedList, times(2)).indexOf(anyString());
        verify(mockedList, never()).add("never happened");

        verify(mockedList, atLeast(2)).indexOf(anyString());
        verify(mockedList, atMost(1)).indexOf(anyString());
    }


    @Test
    public void testInOrder() {
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        //following will make sure that add is first called with "was added first, then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder1 = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrder1.verify(firstMock).add("was called first");
        inOrder1.verify(secondMock).add("was called second");
    }

    @Test
    public void testZeroInteractions() {
        // mockedList.clear();    // if uncomment will fail
        verifyZeroInteractions(mockedList);

        mockedList.clear();
        mockedList.add("one");
        verify(mockedList).clear();
        verify(mockedList).add("one");
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void testConsecutive() {
        when(mockedList.add("some arg"))
                .thenThrow(new RuntimeException())
                .thenReturn(true);

        //First call: throws runtime exception:
        try{
            mockedList.add("some arg");
        } catch(RuntimeException ex) {}


        //Second call: prints true
        System.out.println(mockedList.add("some arg"));

        //Any consecutive call: prints true as well (last stubbing wins).
        System.out.println(mockedList.add("some arg"));



    }

}