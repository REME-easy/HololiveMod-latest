package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.HoshimachiSuisei;
import hololivemod.patches.AbstractCardEnum;

public class CallHoshimachiSuisei extends AbstractSummonCard {
    private static final String ID = "Hololive_CallHoshimachiSuisei";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallHoshimachiSuisei.png";
    private static final int COST = 3;
    public CallHoshimachiSuisei(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 6;
        this.cardHP = 12;
        if(CardHelper.isBalance){
            this.cardATK = 3;
            this.cardHP = 10;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(29), CardHelper.CombinationsDescription.get(29)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(34), CardHelper.CombinationsDescription.get(34)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new HoshimachiSuisei(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallHoshimachiSuisei();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
