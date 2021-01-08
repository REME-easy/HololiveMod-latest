package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.HimemoriLuna;
import hololivemod.patches.AbstractCardEnum;

public class CallHimemoriLuna extends AbstractSummonCard {
    private static final String ID = "Hololive_CallHimemoriLuna";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallHimemoriLuna.png";
    private static final int COST = 1;
    public CallHimemoriLuna(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 7;
        this.cardHP = 7;
        if(CardHelper.isBalance){
            this.cardATK = 3;
            this.cardHP = 3;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(37), CardHelper.CombinationsDescription.get(37)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(33), CardHelper.CombinationsDescription.get(33)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new HimemoriLuna(this.upgraded),this.cardATK,this.cardHP));
        if(CardHelper.UsedSummonCardsThisBattle.size() > 1) {
                AbstractSummonCard c = CardHelper.UsedSummonCardsThisBattle.get(CardHelper.UsedSummonCardsThisBattle.size() - 2);
                    if (upgraded) {
                        c.upgrade();
                    }
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
        }
    }

    public AbstractCard makeCopy(){
        return new CallHimemoriLuna();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
