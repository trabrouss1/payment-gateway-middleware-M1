package ci.trabrouss.paymentservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/payments")
@Tag(name = "Gestion des paiements", description = "API de gestion des paiements")
public interface PaymentApi {

    @Operation(summary = "Créer une nouvelle année académique", description = "Création d'une nouvelle année académique.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
//                    schema = @Schema(implementation = AnneeRequestDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "",
                                    description = "Exemple de création d'une nouvelle anneée",
                                    value = """
                                            {
                                              "":""
                                            }"""
                            )
                    }
            )
    )
    @PostMapping
    ResponseEntity<String> processPayment(@RequestBody Map<String, Object> paymentDetails);
}
