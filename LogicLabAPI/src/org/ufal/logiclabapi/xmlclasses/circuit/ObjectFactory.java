//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package org.ufal.logiclabapi.xmlclasses.circuit;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.ufal.logiclabapi.xmlclasses.circuit package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.ufal.logiclabapi.xmlclasses.circuit
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Circuit }
     * 
     * @return
     *     the new instance of {@link Circuit }
     */
    public Circuit createCircuit() {
        return new Circuit();
    }

    /**
     * Create an instance of {@link Circuit.Connections }
     * 
     * @return
     *     the new instance of {@link Circuit.Connections }
     */
    public Circuit.Connections createCircuitConnections() {
        return new Circuit.Connections();
    }

    /**
     * Create an instance of {@link Circuit.Gates }
     * 
     * @return
     *     the new instance of {@link Circuit.Gates }
     */
    public Circuit.Gates createCircuitGates() {
        return new Circuit.Gates();
    }

    /**
     * Create an instance of {@link NOT }
     * 
     * @return
     *     the new instance of {@link NOT }
     */
    public NOT createNOT() {
        return new NOT();
    }

    /**
     * Create an instance of {@link GridPosition }
     * 
     * @return
     *     the new instance of {@link GridPosition }
     */
    public GridPosition createGridPosition() {
        return new GridPosition();
    }

    /**
     * Create an instance of {@link OR }
     * 
     * @return
     *     the new instance of {@link OR }
     */
    public OR createOR() {
        return new OR();
    }

    /**
     * Create an instance of {@link AND }
     * 
     * @return
     *     the new instance of {@link AND }
     */
    public AND createAND() {
        return new AND();
    }

    /**
     * Create an instance of {@link Circuit.Connections.Connection }
     * 
     * @return
     *     the new instance of {@link Circuit.Connections.Connection }
     */
    public Circuit.Connections.Connection createCircuitConnectionsConnection() {
        return new Circuit.Connections.Connection();
    }

}
