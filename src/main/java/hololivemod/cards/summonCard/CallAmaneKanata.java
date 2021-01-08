package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.AmaneKanata;
import hololivemod.powers.SingPower;

import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;

public class CallAmaneKanata extends AbstractSummonCard {
    private static final String ID = "Hololive_CallAmaneKanata";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallAmaneKanata.png";
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    public CallAmaneKanata(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 6;
        this.cardHP = 6;
        if(CardHelper.isBalance){
            this.cardATK = 3;
            this.cardHP = 3;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(38), CardHelper.CombinationsDescription.get(38)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(32), CardHelper.CombinationsDescription.get(32)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AmaneKanata a = new AmaneKanata(this.upgraded);
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(a,this.cardATK,this.cardHP));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new SingPower(AbstractDungeon.player,1),1));

    }

    public AbstractCard makeCopy(){
        return new CallAmaneKanata();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}
