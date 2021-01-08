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
import hololivemod.minions.MomosuzuNene;

import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;

public class CallMomosuzuNene extends AbstractSummonCard {
    private static final String ID = "Hololive_CallMomosuzuNene";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallMomosuzuNene.png";
    private static final int COST = 1;
    public CallMomosuzuNene(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 3;
        this.cardHP = 3;
        if(CardHelper.isBalance){
            this.cardATK = 2;
            this.cardHP = 2;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(41), CardHelper.CombinationsDescription.get(41)));

    }

    public CallMomosuzuNene(int timesUpgrade){
        this();
        this.timesUpgraded = timesUpgrade;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new MomosuzuNene(this.timesUpgraded),this.cardATK,this.cardHP));
        this.upgrade();
        for(AbstractCard c:AbstractDungeon.player.masterDeck.group){
            if(c instanceof AbstractSummonCard && c.uuid.equals(this.uuid)){
                c.upgrade();
            }
        }
    }

    public AbstractCard makeCopy(){
        return new CallMomosuzuNene(timesUpgraded);
    }

    public boolean canUpgrade() {
        return true;
    }

    public void upgrade(){
        this.cardATK = this.cardATK + 1;
        this.cardHP = this.cardHP + 1;
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        this.initializeDescription();
    }
}
