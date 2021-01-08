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
import hololivemod.minions.ShiroganeNoel;

import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;

public class CallShiroganeNoel extends AbstractSummonCard {
    private static final String ID = "Hololive_CallShiroganeNoel";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallShiroganeNoel.png";
    private static final int COST = 3;

    public CallShiroganeNoel(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 12;
        this.cardHP = 12;
        if(CardHelper.isBalance){
            this.cardATK = 8;
            this.cardHP = 8;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(32), CardHelper.CombinationsDescription.get(32)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(27), CardHelper.CombinationsDescription.get(27)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new ShiroganeNoel(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallShiroganeNoel();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.cardATK += 2;
            this.cardHP += 2;
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
