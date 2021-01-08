package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.random.Random;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.AbstractMinion;
import hololivemod.minions.YuzukiChoco;
import hololivemod.patches.AbstractCardEnum;

import java.util.ArrayList;

public class CallYuzukiChoco extends AbstractSummonCard {
    private static final String ID = "Hololive_CallYuzukiChoco";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallYuzukiChoco.png";
    private static final int COST = 1;

    public CallYuzukiChoco(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 6;
        this.cardHP = 6;
        if(CardHelper.isBalance){
            this.cardATK = 3;
            this.cardHP = 3;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(21), CardHelper.CombinationsDescription.get(21)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(31), CardHelper.CombinationsDescription.get(31)));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded){
            ArrayList<AbstractMinion> abstractMinionArrayList = new ArrayList<>();
            Random random = new Random();
            for (AbstractOrb orb:AbstractDungeon.player.orbs) {
                if(orb instanceof AbstractMinion)
                    abstractMinionArrayList.add((AbstractMinion) orb);
            }
            if(abstractMinionArrayList.size() != 0) {
                AbstractMinion abstractMinion = abstractMinionArrayList.get(random.random(0, abstractMinionArrayList.size() - 1));
                abstractMinion.ChangeStat(2, 2, false);
            }
        }
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new YuzukiChoco(this.upgraded),this.cardATK,this.cardHP));
    }

    public AbstractCard makeCopy(){
        return new CallYuzukiChoco();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
