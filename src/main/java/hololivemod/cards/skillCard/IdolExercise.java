package hololivemod.cards.skillCard;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hololivemod.minions.AbstractMinion;
import hololivemod.patches.AbstractCardEnum;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class IdolExercise extends CustomCard {
    private static final String ID = "Hololive_IdolExercise";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/skillcards/IdolExercise.png";
    private static final int COST = 1;
    public IdolExercise() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, AbstractCardEnum.Hololive_BLUE,
                CardRarity.RARE, CardTarget.NONE);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractOrb orb:AbstractDungeon.player.orbs) {
            if(orb instanceof AbstractMinion){
                if(!this.upgraded){
                    ((AbstractMinion) orb).ChangeStat(1, 1, false);
                }else {
                    ((AbstractMinion) orb).ChangeStat(2, 2, false);

                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new IdolExercise();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}