package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.actions.CallPigeonManAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.ShishiroBotan;
import hololivemod.patches.AbstractCardEnum;

public class CallShishiroBotan extends AbstractSummonCard {
    private static final String ID = "Hololive_CallShishiroBotan";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallShishiroBotan.png";
    private static final int COST = 3;

    public CallShishiroBotan(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 6;
        this.cardHP = 12;
        this.baseMagicNumber = this.magicNumber = 1;
        if(CardHelper.isBalance){
            this.cardATK = 4;
            this.cardHP = 8;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(41), CardHelper.CombinationsDescription.get(41)));

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new CallPigeonManAction(new ShishiroBotan(upgraded),cardATK,cardHP,magicNumber));
    }

    public AbstractCard makeCopy(){
        return new CallShishiroBotan();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
