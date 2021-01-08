package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hololivemod.actions.CallMoonahoshinovaAction;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.AkiRosenthal;
import hololivemod.minions.MoonaHoshinova;
import hololivemod.patches.AbstractCardEnum;

public class CallMoonaHoshinova extends AbstractSummonCard {
    private static final String ID = "Hololive_CallMoonaHoshinova";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallMoonaHoshinova.png";
    private static final int COST = 2;
    public CallMoonaHoshinova(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 8;
        this.cardHP = 8;
        if(CardHelper.isBalance){
            this.cardATK = 4;
            this.cardHP = 4;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(40), CardHelper.CombinationsDescription.get(40)));


    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new MoonaHoshinova(this.upgraded),this.cardATK,this.cardHP));
        this.addToBot(new CallMoonahoshinovaAction(upgraded));

    }

    public void triggerOnGlowCheck() {
        int akipluscount = 0;
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof AkiRosenthal && ((AkiRosenthal) orb).upgraded)
                ++akipluscount;
        }
        if (akipluscount > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    public AbstractCard makeCopy(){
        return new CallMoonaHoshinova();
    }


    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
