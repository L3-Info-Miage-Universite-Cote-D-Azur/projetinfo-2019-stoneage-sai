/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stoneage;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ossama
 */
public class PartieTest {
    
    public PartieTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of jouer method, of class Partie.
     */
    @Test
    public void testJouer() {
        System.out.println("jouer");
        Partie instance = new Partie();
        instance.jouer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unTour method, of class Partie.
     */
    @Test
    public void testUnTour() {
        System.out.println("unTour");
        Partie instance = new Partie();
        instance.unTour();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of phaseAction method, of class Partie.
     */
    @Test
    public void testPhaseAction() {
        System.out.println("phaseAction");
        ArrayList<Zone> listeZonesJouées = null;
        Partie instance = new Partie();
        instance.phaseAction(listeZonesJouées);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of phasePlacement method, of class Partie.
     */
    @Test
    public void testPhasePlacement() {
        System.out.println("phasePlacement");
        ArrayList<Zone> listeZonesDispo = null;
        ArrayList<Zone> listeZonesJouées = null;
        Partie instance = new Partie();
        instance.phasePlacement(listeZonesDispo, listeZonesJouées);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Partie.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Partie.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
