package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.TsunomakiWatame;

import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;

public class CallTsunomakiWatame extends AbstractSummonCard {
    private static final String ID = "Hololive_CallTsunomakiWatame";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallTsunomakiWatame.png";
    private static final int COST = 1;

    public CallTsunomakiWatame(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 5;
        this.cardHP = 5;
        if(CardHelper.isBalance){
            this.cardATK = 2;
            this.cardHP = 3;
        }

        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(36), CardHelper.CombinationsDescription.get(36)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(33), CardHelper.CombinationsDescription.get(33)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(21), CardHelper.CombinationsDescription.get(21)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new PlatedArmorPower(AbstractDungeon.player,3),3));
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new TsunomakiWatame(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallTsunomakiWatame();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
