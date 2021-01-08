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
import hololivemod.minions.Rosalyn;

import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;

public class CallRosalyn extends AbstractSummonCard {
    private static final String ID = "Hololive_CallRosalyn";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallRosalyn.png";
    private static final int COST = 2;

    public CallRosalyn(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 8;
        this.cardHP = 8;
        if(CardHelper.isBalance){
            this.cardATK = 5;
            this.cardHP = 5;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(39), CardHelper.CombinationsDescription.get(39)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new Rosalyn(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallRosalyn();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.cardATK = this.cardATK + 2;
            this.cardHP = this.cardHP + 2;
        }
    }
}
