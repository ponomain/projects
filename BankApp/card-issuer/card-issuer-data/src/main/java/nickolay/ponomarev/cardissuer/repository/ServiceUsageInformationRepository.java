package nickolay.ponomarev.cardissuer.repository;

import nickolay.ponomarev.cardissuer.model.ServiceUsageInformation;

import java.util.UUID;

/**
 * 16.03.2022
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface ServiceUsageInformationRepository extends DeletableRepository<ServiceUsageInformation>{

    ServiceUsageInformation findServiceUsageInformationByBankClient(UUID id);

}
