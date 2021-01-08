package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import hololivemod.actions.SpawnMateAction;
import hololivemod.cards.skillCard.Yeah;
import hololivemod.helper.CardHelper;
import hololivemod.minions.NatsuiroMatsuri;
import hololivemod.patches.AbstractCardEnum;

public class CallNatsuiroMatsuri extends AbstractSummonCard {
    private static final String ID = "Hololive_CallNatsuiroMatsuri";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallNatsuiroMatsuri.png";
    private static final int COST = 2;

    public CallNatsuiroMatsuri(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
            this.cardsToPreview = new Yeah();
            this.cardsToPreview.upgrade();
        this.cardATK = 8;
        this.cardHP = 8;
        if(CardHelper.isBalance){
            this.cardATK = 4;
            this.cardHP = 6;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(30), CardHelper.CombinationsDescription.get(30)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(28), CardHelper.CombinationsDescription.get(28)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(37), CardHelper.CombinationsDescription.get(37)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(17), CardHelper.CombinationsDescription.get(17)));

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new NatsuiroMatsuri(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallNatsuiroMatsuri();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
