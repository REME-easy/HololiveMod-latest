package hololivemod.cards.optionCard;

import basemod.abstracts.CustomCard;
import hololivemod.patches.AbstractCardEnum;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public abstract class AbstractCombinatiionCard extends CustomCard {
    public AbstractCombinatiionCard(String ID,String NAME,String IMG_PATH,String DESCRIPTION){
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION,
                SKILL, AbstractCardEnum.Achievements,
                CardRarity.SPECIAL, CardTarget.ENEMY);
    }

    public void CombinationEffect(){}
}
