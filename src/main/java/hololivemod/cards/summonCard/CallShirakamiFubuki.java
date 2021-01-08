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
import hololivemod.minions.ShirakamiFubuki;

import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;

public class CallShirakamiFubuki extends AbstractSummonCard {
    private static final String ID = "Hololive_CallShirakamiFubuki";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallShiragamiFubuki.png";
    private static final int COST = 1;

    public CallShirakamiFubuki(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 4;
        this.cardHP = 6;
        if(CardHelper.isBalance){
            this.cardATK = 2;
            this.cardHP = 3;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(30), CardHelper.CombinationsDescription.get(30)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(19), CardHelper.CombinationsDescription.get(19)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(17), CardHelper.CombinationsDescription.get(17)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(23), CardHelper.CombinationsDescription.get(23)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(22), CardHelper.CombinationsDescription.get(22)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new ShirakamiFubuki(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallShirakamiFubuki();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
