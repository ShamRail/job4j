package jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class StoreXML {

    private static final Logger LOG = LogManager.getLogger(StoreXML.class.getName());

    private File destination;

    public StoreXML(File destination) {
        this.destination = destination;
    }

    public void save(List<Entry> list) {
        EntryStore entryStore = new EntryStore(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EntryStore.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entryStore, destination);
            LOG.debug(String.format("List has save to %s", destination.getPath()));
        } catch (Exception e) {
            LOG.error(String.format("Saving has fallen with %s", e.getClass().getSimpleName()));
        }
    }

}
