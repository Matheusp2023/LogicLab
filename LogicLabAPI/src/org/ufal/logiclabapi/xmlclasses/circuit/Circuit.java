//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package org.ufal.logiclabapi.xmlclasses.circuit;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.</p>
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Gates">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <choice maxOccurs="unbounded">
 *                     <element ref="{}NOT"/>
 *                     <element ref="{}OR"/>
 *                     <element ref="{}AND"/>
 *                   </choice>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="Connections">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="Connection" maxOccurs="unbounded">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <attribute name="fromGate" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           <attribute name="fromPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           <attribute name="toGate" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           <attribute name="toPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "gates",
    "connections"
})
@XmlRootElement(name = "Circuit")
public class Circuit {

    @XmlElement(name = "Gates", required = true)
    protected Circuit.Gates gates;
    @XmlElement(name = "Connections", required = true)
    protected Circuit.Connections connections;

    /**
     * Obtém o valor da propriedade gates.
     * 
     * @return
     *     possible object is
     *     {@link Circuit.Gates }
     *     
     */
    public Circuit.Gates getGates() {
        return gates;
    }

    /**
     * Define o valor da propriedade gates.
     * 
     * @param value
     *     allowed object is
     *     {@link Circuit.Gates }
     *     
     */
    public void setGates(Circuit.Gates value) {
        this.gates = value;
    }

    /**
     * Obtém o valor da propriedade connections.
     * 
     * @return
     *     possible object is
     *     {@link Circuit.Connections }
     *     
     */
    public Circuit.Connections getConnections() {
        return connections;
    }

    /**
     * Define o valor da propriedade connections.
     * 
     * @param value
     *     allowed object is
     *     {@link Circuit.Connections }
     *     
     */
    public void setConnections(Circuit.Connections value) {
        this.connections = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.</p>
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="Connection" maxOccurs="unbounded">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <attribute name="fromGate" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 <attribute name="fromPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 <attribute name="toGate" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 <attribute name="toPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "connection"
    })
    public static class Connections {

        @XmlElement(name = "Connection", required = true)
        protected List<Circuit.Connections.Connection> connection;

        /**
         * Gets the value of the connection property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the connection property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getConnection().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Circuit.Connections.Connection }
         * </p>
         * 
         * 
         * @return
         *     The value of the connection property.
         */
        public List<Circuit.Connections.Connection> getConnection() {
            if (connection == null) {
                connection = new ArrayList<>();
            }
            return this.connection;
        }


        /**
         * <p>Classe Java de anonymous complex type.</p>
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <attribute name="fromGate" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       <attribute name="fromPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       <attribute name="toGate" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       <attribute name="toPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Connection {

            @XmlAttribute(name = "fromGate", required = true)
            protected String fromGate;
            @XmlAttribute(name = "fromPin", required = true)
            protected String fromPin;
            @XmlAttribute(name = "toGate", required = true)
            protected String toGate;
            @XmlAttribute(name = "toPin", required = true)
            protected String toPin;

            /**
             * Obtém o valor da propriedade fromGate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFromGate() {
                return fromGate;
            }

            /**
             * Define o valor da propriedade fromGate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFromGate(String value) {
                this.fromGate = value;
            }

            /**
             * Obtém o valor da propriedade fromPin.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFromPin() {
                return fromPin;
            }

            /**
             * Define o valor da propriedade fromPin.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFromPin(String value) {
                this.fromPin = value;
            }

            /**
             * Obtém o valor da propriedade toGate.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getToGate() {
                return toGate;
            }

            /**
             * Define o valor da propriedade toGate.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setToGate(String value) {
                this.toGate = value;
            }

            /**
             * Obtém o valor da propriedade toPin.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getToPin() {
                return toPin;
            }

            /**
             * Define o valor da propriedade toPin.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setToPin(String value) {
                this.toPin = value;
            }

        }

    }


    /**
     * <p>Classe Java de anonymous complex type.</p>
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <choice maxOccurs="unbounded">
     *           <element ref="{}NOT"/>
     *           <element ref="{}OR"/>
     *           <element ref="{}AND"/>
     *         </choice>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "notOrOROrAND"
    })
    public static class Gates {

        @XmlElements({
            @XmlElement(name = "NOT", type = NOT.class),
            @XmlElement(name = "OR", type = OR.class),
            @XmlElement(name = "AND", type = AND.class)
        })
        protected List<Object> notOrOROrAND;

        /**
         * Gets the value of the notOrOROrAND property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the notOrOROrAND property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getNOTOrOROrAND().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AND }
         * {@link NOT }
         * {@link OR }
         * </p>
         * 
         * 
         * @return
         *     The value of the notOrOROrAND property.
         */
        public List<Object> getNOTOrOROrAND() {
            if (notOrOROrAND == null) {
                notOrOROrAND = new ArrayList<>();
            }
            return this.notOrOROrAND;
        }

    }

}
