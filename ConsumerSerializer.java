import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ConsumerSerializer extends StdSerializer<Consumer> {

    public ConsumerSerializer() {
        this(null);
    }

    public ConsumerSerializer(final Class<Consumer> t) {
        super(t);
    }

    /**
     * scrie in fisierul de output datele rezultate
     */
    @Override
    public void serialize(final Consumer consumer, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", consumer.getId());
        jsonGenerator.writeBooleanField("isBankrupt", consumer.isBankrupt());
        jsonGenerator.writeNumberField("budget", consumer.getBudget());
        jsonGenerator.writeEndObject();
    }
}
