package hololivemod.cards.summonCard;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import hololivemod.helper.CardHelper;
import hololivemod.relics.TheStar;

import java.util.ArrayList;
import java.util.List;

import static hololivemod.patches.CardTagEnum.SUMMON_CARD;

public abstract class AbstractSummonCard extends CustomCard {
    public int cardATK = 0;
    public int cardHP = 0;
    public int Index = 0;
    public int MagicNum = 0;
    public int MagicNum2 = 0;
    public int Effectnum = 0;
    public int EffectTimes = 1;
    protected ArrayList<TooltipInfo> tips = new ArrayList<>();
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Hololive_CantCallMinions");
    public AbstractSummonCard(String ID,String NAME,String IMG_PATH,int COST,String DESCRIPTION,CardType CARDTYPE,CardColor CARDCOLOR,CardRarity CARDRARITY,CardTarget CARDTARGET){
        super(ID,NAME,IMG_PATH,COST,DESCRIPTION,CARDTYPE,CARDCOLOR,CARDRARITY,CARDTARGET);
        this.tags.add(SUMMON_CARD);
    }

    public void upgrade(){

    }

    public AbstractCard makeCopy(){
        return null;
    }

    public void use(AbstractPlayer p, AbstractMonster m){

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for(AbstractOrb o:AbstractDungeon.player.orbs){
            if(o instanceof EmptyOrbSlot){
                ++count;
            }
        }
        if(count == 0 && !TheStar.CombinationIndex[21]){
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return  false;
        }else {
            return super.canUse(p, m);
        }
    }

    public void onCallMinion(){}

    public void onChoseThisOption(){
        if(AbstractDungeon.player.maxOrbs > 0)
            CardHelper.OnChooseThisCard(this,Index,EffectTimes,Effectnum,MagicNum,MagicNum2);
        else if(Effectnum == 0)
            this.addToBot(new MakeTempCardInHandAction(this.makeCopy()));

    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return tips;
    }
}
