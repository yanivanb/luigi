package be.vdab.luigi.restclients;

import be.vdab.luigi.exceptions.KoersClientException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Qualifier("ECB")
class ECBKoersClient implements KoersClient {
    private final URL url; // uit package java.net
    private final XMLInputFactory factory = XMLInputFactory.newInstance();
    ECBKoersClient(@Value("${ecbKoersURL}") URL url) {
        this.url = url;
    }
    @Override
    public BigDecimal getDollarKoers() {
        try (var stream = url.openStream()) {
            for (var reader = factory.createXMLStreamReader(stream);
            reader.hasNext(); ) {
                reader.next();
                if (reader.isStartElement()) {
                    if ("USD".equals(reader.getAttributeValue(0))) {
                        return new BigDecimal(reader.getAttributeValue(1));
                    }
                }
            }
            throw new KoersClientException("XML van ECB bevat geen USD.");
        } catch (IOException | NumberFormatException | XMLStreamException ex) {
            throw new KoersClientException("Kan koers niet lezen via ECB.", ex);
        }
    }
}
