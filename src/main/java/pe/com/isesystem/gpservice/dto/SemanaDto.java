package pe.com.isesystem.gpservice.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SemanaDto implements Serializable {
    Long id;
    Long fechaInicio;
    Long fechaFin;
    String tipoSemana;
    Boolean estado;
}