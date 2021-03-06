package hololivemod.abilities;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import hololivemod.minions.AbstractMinion;

public class SpyAbility extends AbstractAbility {
    private static final String ID = "Hololive_Spy";
    private AbstractMinion owner = null;
    private static final String[] description = CardCrawlGame.languagePack.getPowerStrings(ID).DESCRIPTIONS;
    public SpyAbility(int MagicNum, boolean upgraded,AbstractMinion owner){
        super(ID,MagicNum,description,upgraded);
        this.owner =owner;
    }

    public void Effect(){
        owner.ChangeStat(MagicNum, MagicNum, false);
    }

    public String getDescription(){
        return description[0] + " #b" + this.MagicNum + " " + description[1] + " #b" + this.MagicNum + " " + description[2];
    }
}
