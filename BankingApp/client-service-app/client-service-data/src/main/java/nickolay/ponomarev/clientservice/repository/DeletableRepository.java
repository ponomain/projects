package nickolay.ponomarev.clientservice.repository;

import lombok.NonNull;
import nickolay.ponomarev.clientservice.model.Deletable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * 21.04.2022
 * client-service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
public interface DeletableRepository<T extends Deletable> extends JpaRepository<T, UUID> {

    @Override
    default Page<T> findAll(@NonNull Pageable pageable) {
        return findAllByDeletedIsFalse(pageable);
    }

    Page<T> findAllByDeletedIsFalse(@NonNull Pageable pageable);

    @Override
    @Transactional
    default void deleteById(@NonNull UUID id) {
        T t = findById(id).orElseThrow();
        t.setDeleted(true);
    }
}
