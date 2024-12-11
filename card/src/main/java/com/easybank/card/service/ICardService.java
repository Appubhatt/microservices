package com.easybank.card.service;

import com.easybank.card.dto.CardsDto;
import com.easybank.card.entity.Cards;

public interface ICardService {

    void createCard(String mobileNumber);

    CardsDto fetchCards(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
