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
import hololivemod.helper.OrbHelper;
import hololivemod.minions.AZKi;
import hololivemod.minions.AbstractMinion;
import hololivemod.patches.AbstractCardEnum;

public class CallAZKi extends AbstractSummonCard {
    private static final String ID = "Hololive_CallAZKi";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallAZKi.png";
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public CallAZKi(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 7;
        this.cardHP = 7;
        if(CardHelper.isBalance){
            this.cardATK = 3;
            this.cardHP = 4;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(34), CardHelper.CombinationsDescription.get(34)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int i;
        for(i = 0 ; i < p.orbs.size() - 1 ; ++i){
            if(p.orbs.get(i) instanceof AbstractMinion){
                AbstractSummonCard c = OrbHelper.GetCardInstance(i);
                if( c != null) {
                    if (((AbstractMinion) p.orbs.get(i)).upgraded) c.upgrade();
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new AZKi(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallAZKi();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
        }
    }
}
