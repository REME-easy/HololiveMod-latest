package hololivemod.cards.skillCard;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.patches.AbstractCardEnum;
import hololivemod.powers.DurableLivePower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL;

public class DurableLive extends CustomCard {
    private static final String ID = "Hololive_DurableLive";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/skillcards/DurableLive.png";
    private static final int COST = 1;
    public DurableLive() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                SKILL, AbstractCardEnum.Hololive_BLUE,
                CardRarity.RARE, CardTarget.NONE);
            this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p,p,new DurableLivePower(p,1),1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DurableLive();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}