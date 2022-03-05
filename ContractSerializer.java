import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ContractSerializer extends StdSerializer<Contract> {

    public ContractSerializer() {
        this(null);
    }

    public ContractSerializer(final Class<Contract> t) {
        super(t);
    }

    /**
     * scrie in fisierele de output datele rezultate
     */
    @Override
    public void serialize(final Contract contract, final JsonGenerator jsonGenerator,
                          final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("consumerId", contract.getConsumerId());
        jsonGenerator.writeNumberField("price", contract.getPrice());
        jsonGenerator.writeNumberField("remainedContractMonths",
                contract.getRemainedContractMonths());
        jsonGenerator.writeEndObject();
    }
}
