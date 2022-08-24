package dio.expertostech.tutorialmicroservicokafka.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoData {
    private String codigo;
    private String nomeProduto;
    private BigDecimal valor;
    private String uuid;
}
