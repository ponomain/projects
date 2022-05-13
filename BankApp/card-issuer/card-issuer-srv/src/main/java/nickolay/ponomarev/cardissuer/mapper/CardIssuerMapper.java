package nickolay.ponomarev.cardissuer.mapper;

import org.mapstruct.MappingTarget;

/**
 * 02.03.2022
 * Card issuer mapper
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface CardIssuerMapper<S, D> {

    D toDto(S source);

    S toSource(D dto, @MappingTarget S source);
}
