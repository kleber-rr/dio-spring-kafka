package dio.expertostech.tutorialrestkafka.controller;

import dio.expertostech.tutorialrestkafka.data.PedidoData;
import dio.expertostech.tutorialrestkafka.service.RegistraEventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido")
@RequiredArgsConstructor
public class PedidosController {

    private final RegistraEventoService service;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody PedidoData pedido){
        service.adcionarEvento("salvarPedido", pedido);
        return ResponseEntity.ok("Sucesso");
    }
}
