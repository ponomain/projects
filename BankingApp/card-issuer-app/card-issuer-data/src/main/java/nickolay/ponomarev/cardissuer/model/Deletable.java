package nickolay.ponomarev.cardissuer.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * 21.02.2022
 * Общая сущность для всех, обозначающая поле "удалено"
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class Deletable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}
