package hololivemod.minions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;

public class MomosuzuNene extends AbstractMinion {
    private static final String ID = "Hololive_MomosuzuNene";
    private static final OrbStrings cardStrings = CardCrawlGame.languagePack.getOrbString(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String[] DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/Orbs/MomosuzuNene.png";
    private static final int basePassiveAmount = 3;
    private static final int baseEvokeAmount = 3;
    private static final int originalHP = 3;
    private static final int originalATK = 3;
    private int timesUpgraded;
    public MomosuzuNene(int timesUpgraded){
        super(ID,NAME,basePassiveAmount,baseEvokeAmount,originalHP,IMG_PATH,DESCRIPTION[0],DESCRIPTION[1]);
        this.timesUpgraded = timesUpgraded;
        if(this.timesUpgraded > 0)
            this.upgraded = true;
        this.ATK = originalATK;
    }


    public AbstractMinion makeCopy(){
        return new MomosuzuNene(timesUpgraded);
    }

    @Override
    public void onEvoke(){
        super.onEvoke();
    }

    @Override
    public void triggerEvokeAnimation(){
        CardCrawlGame.sound.play("ORB_DARK_EVOKE", 0.1F);
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(this.cX, this.cY));
    }


    @Override
    public void onEndOfTurn(){
        this.AttackEffect();
    }

    @Override
    public void AttackEffect(){
        Attack(AbstractGameAction.AttackEffect.FIRE);
        Trigger();
        super.AttackEffect();
    }

    public void renderText(SpriteBatch sb){
        super.renderText(sb);
        if(this.upgraded)
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(timesUpgraded), this.cX - 25.0F * Settings.scale , this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 70.0F * Settings.scale,new Color(1.0F,1.0F,1.0F,1.0F), this.fontScale * 1.2F);
    }



    public void playChannelSFX(){
        CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
    }
}
