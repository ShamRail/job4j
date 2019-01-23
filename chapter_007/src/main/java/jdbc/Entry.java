package jdbc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Entry {

    private int value;

    public Entry() {
    }

    public Entry(int value) {
        this.value = value;
    }

    @XmlAttribute(name = "field")
    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj.getClass().equals(this.getClass()) && this.value == ((Entry)obj).getValue());
    }
}
