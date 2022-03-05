import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DistributorSerializer extends StdSerializer<Distributor> {

    public DistributorSerializer() {
        this(null);
    }

    public DistributorSerializer(final Class<Distributor> t) {
        super(t);
    }

    /**
     * scrie in fisierele de output datele rezultate
     */
    @Override
    public void serialize(final Distributor distributor, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", distributor.getId());
        jsonGenerator.writeNumberField("budget", distributor.getBudget());
        jsonGenerator.writeBooleanField("isBankrupt", distributor.isBankrupt());
        jsonGenerator.writeArrayFieldStart("contracts");
        for (Contract c: distributor.getContracts()) {
            jsonGenerator.writeObject(c);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
