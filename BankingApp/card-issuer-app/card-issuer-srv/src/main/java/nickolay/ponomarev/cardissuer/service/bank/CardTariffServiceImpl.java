package nickolay.ponomarev.cardissuer.service.bank;

import lombok.RequiredArgsConstructor;
import nickolay.ponomarev.cardissuer.dto.CardTariffDto;
import nickolay.ponomarev.cardissuer.mapper.CardTariffMapper;
import nickolay.ponomarev.cardissuer.model.CardTariff;
import nickolay.ponomarev.cardissuer.model.Deletable;
import nickolay.ponomarev.cardissuer.repository.CardTariffRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 27.02.2022
 * Card tariff service
 *
 * @author Nikolay Ponomarev
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class CardTariffServiceImpl implements CardTariffService {

    private final CardTariffRepository cardTariffRepository;
    private final CardTariffMapper cardTariffMapper;

    @Override
    public CardTariffDto getCardTariffById(@NotNull UUID id) throws IllegalArgumentException{
        CardTariff existingTariff = cardTariffRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Tariff with id -" + id + "didn't found"));
        return cardTariffMapper.toDto(existingTariff);
    }

    @Override
    public List<UUID> getAllCardTariffs(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by("id"));
        Page<CardTariff> result = cardTariffRepository.findAll(request);
        List<CardTariff> tariffs = result.getContent();
        return tariffs.stream().map(Deletable::getId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CardTariffDto createCardTariff(@NotNull CardTariffDto cardTariffDto) {
        CardTariff newCardTariff = new CardTariff();
        cardTariffMapper.toSource(cardTariffDto, newCardTariff);
        newCardTariff = cardTariffRepository.save(newCardTariff);
        return cardTariffMapper.toDto(newCardTariff);
    }

    @Override
    @Transactional
    public void deleteCardTariff(@NotNull UUID id) {
        cardTariffRepository.deleteById(id);
    }
}
