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
import hololivemod.minions.AiraniIofifteen;
import hololivemod.patches.AbstractCardEnum;

public class CallAiraniIofifteen extends AbstractSummonCard {
    private static final String ID = "Hololive_CallAiraniIofifteen";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallAiraniIofifteen.png";
    private static final int COST = 2;
    public CallAiraniIofifteen(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 2;
        this.cardHP = 8;
        if(CardHelper.isBalance){
            this.cardHP = 5;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(40), CardHelper.CombinationsDescription.get(40)));
    }

    @Override
    public void initializeDescription() {
        super.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new AiraniIofifteen(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallAiraniIofifteen();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardHP = this.cardHP + 4;
        }
    }
}
