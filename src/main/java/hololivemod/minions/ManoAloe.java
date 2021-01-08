package hololivemod.minions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;

public class ManoAloe extends AbstractMinion {
    private static final String ID = "Hololive_ManoAloe";
    private static final OrbStrings cardStrings = CardCrawlGame.languagePack.getOrbString(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String[] DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/Orbs/ManoAloe.png";
    private static final int basePassiveAmount = 3;
    private static final int baseEvokeAmount = 3;
    private static final int originalHP = 5;
    private static final int originalATK = 4;
    public ManoAloe(boolean upgraded){
        super(ID,NAME,basePassiveAmount,baseEvokeAmount,originalHP,IMG_PATH,DESCRIPTION[0],DESCRIPTION[1]);
        this.upgraded = upgraded;
        this.ATK = originalATK;
    }

    public AbstractMinion makeCopy(){
        return new ManoAloe(this.upgraded);
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
    public void ChangeATK(int DeltaATK, boolean isDamage) {
        super.ChangeATK(DeltaATK, isDamage);
        Effect(DeltaATK, 0, isDamage);
    }

    @Override
    public void ChangeHP(int DeltaHP, boolean isDamage) {
        super.ChangeHP(DeltaHP, isDamage);
        Effect(0, DeltaHP, isDamage);
    }

    @Override
    public void ChangeStat(int ATK, int HP, boolean isDamage) {
        super.ChangeStat(ATK, HP, isDamage);
        Effect(ATK, HP, isDamage);
    }

    private void Effect(int ATK, int HP, boolean isDamage){
        if(!isDamage && (ATK > 0 || HP > 0)){
            boolean can = false;
            for(AbstractOrb o:AbstractDungeon.player.orbs){
                if(o instanceof AbstractMinion && !(o instanceof ManoAloe)){
                    can = true;
                    break;
                }
            }
            if(can){
                while(true){
                    AbstractOrb o = AbstractDungeon.player.orbs.get(MathUtils.random(AbstractDungeon.player.orbs.size() - 1));
                    if(o instanceof AbstractMinion && !(o instanceof ManoAloe)){
                        ((AbstractMinion) o).ChangeStat(ATK, HP, false);
                        break;
                    }
                }
                if(upgraded){
                    while(true){
                        AbstractOrb o = AbstractDungeon.player.orbs.get(MathUtils.random(AbstractDungeon.player.orbs.size() - 1));
                        if(o instanceof AbstractMinion && !(o instanceof ManoAloe)){
                            ((AbstractMinion) o).ChangeStat(ATK, HP, false);
                            break;
                        }
                    }
                }
            }


        }
    }

    @Override
    public void AttackEffect(){
        Attack(AbstractGameAction.AttackEffect.LIGHTNING);
        Trigger();
        super.AttackEffect();
    }


    public void playChannelSFX(){
        CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
    }
}
