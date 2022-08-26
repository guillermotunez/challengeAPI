package com.challenge.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name = Swagger3Constants.HOLA_MUNDO)
public class HolaMundoController {


	@Operation(tags = Swagger3Constants.HOLA_MUNDO, method = "GET",
            description = "Saludar al usuario en funcion del 'nombre' provisto as query param",
			operationId = "holaMundo")
    @GetMapping(path = "/holaMundo", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<String> holaMundo(@Parameter(name = "nombre", description = "Nombre a saludar", required = false)
    						@RequestParam(value = "nombre", required = false) String nombre){
        if (nombre != null && nombre.equals("Exception")) {
        	throw new RuntimeException("Quiero probar mi GlobalExceptionHandler.");
        }
        Optional<String> oName = Optional.ofNullable(nombre);
        return ResponseEntity.ok(String.format( "{\"mensaje\":\"Hola %s\"}", oName.orElse("Usuario")));
    }
}