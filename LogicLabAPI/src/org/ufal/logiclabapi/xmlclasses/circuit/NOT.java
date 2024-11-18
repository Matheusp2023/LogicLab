//
// Este arquivo foi gerado pela Eclipse Implementation of JAXB, v4.0.5 
// Consulte https://eclipse-ee4j.github.io/jaxb-ri 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
//


package org.ufal.logiclabapi.xmlclasses.circuit;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
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
 *         <element name="Position" type="{}GridPosition"/>
 *       </sequence>
 *       <attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="inputPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="outputPin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "position"
})
@XmlRootElement(name = "NOT")
public class NOT {

    @XmlElement(name = "Position", required = true)
    protected GridPosition position;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "inputPin", required = true)
    protected String inputPin;
    @XmlAttribute(name = "outputPin", required = true)
    protected String outputPin;

    /**
     * Obtém o valor da propriedade position.
     * 
     * @return
     *     possible object is
     *     {@link GridPosition }
     *     
     */
    public GridPosition getPosition() {
        return position;
    }

    /**
     * Define o valor da propriedade position.
     * 
     * @param value
     *     allowed object is
     *     {@link GridPosition }
     *     
     */
    public void setPosition(GridPosition value) {
        this.position = value;
    }

    /**
     * Obtém o valor da propriedade id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtém o valor da propriedade inputPin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputPin() {
        return inputPin;
    }

    /**
     * Define o valor da propriedade inputPin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputPin(String value) {
        this.inputPin = value;
    }

    /**
     * Obtém o valor da propriedade outputPin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputPin() {
        return outputPin;
    }

    /**
     * Define o valor da propriedade outputPin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputPin(String value) {
        this.outputPin = value;
    }

}
