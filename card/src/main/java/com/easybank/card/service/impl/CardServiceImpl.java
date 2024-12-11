package com.easybank.card.service.impl;

import com.easybank.card.constants.CardConstants;
import com.easybank.card.dto.CardsDto;
import com.easybank.card.entity.Cards;
import com.easybank.card.exception.CardAlreadyExistsException;
import com.easybank.card.exception.ResourceNotFoundException;
import com.easybank.card.mapper.CardsMapper;
import com.easybank.card.respository.CardRepository;
import com.easybank.card.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private CardRepository cardRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optional = cardRepository.findByMobileNumber(mobileNumber);
        if (optional.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));

    }

    @Override
    public CardsDto fetchCards(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
                new ResourceNotFoundException("Card","mobileNumber",mobileNumber));

        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Cards","cardNumber", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto,cards);
        cardRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Cards","mobileNumber",mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCard(String mobileNumber){
        Cards newCard = new Cards();

        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setCardNumber(String.valueOf(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);

        return newCard;
    }
}
