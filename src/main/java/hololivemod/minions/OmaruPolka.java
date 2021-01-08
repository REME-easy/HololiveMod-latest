package hololivemod.minions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class OmaruPolka extends AbstractMinion {
    private static final String ID = "Hololive_OmaruPolka";
    private static final OrbStrings cardStrings = CardCrawlGame.languagePack.getOrbString(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String[] DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/Orbs/OmaruPolka.png";
    private static final int basePassiveAmount = 3;
    private static final int baseEvokeAmount = 3;
    private static final int originalHP = 9;
    private static final int originalATK = 3;
    private ArrayList<AbstractOrb> orbs = new ArrayList<>();
    private float time = 0.0F;
    public OmaruPolka(boolean upgraded){
        super(ID,NAME,basePassiveAmount,baseEvokeAmount,originalHP,IMG_PATH,DESCRIPTION[0],DESCRIPTION[1]);
        this.upgraded = upgraded;
        this.ATK = originalATK;
    }

    public AbstractMinion makeCopy(){
        return new OmaruPolka(this.upgraded);
    }

    @Override
    public void onEvoke(){
        super.onEvoke();
        if(!orbs.isEmpty()){
            for(AbstractOrb o:orbs){
                o.onEvoke();
            }
            orbs.clear();
        }

    }

    @Override
    public void triggerEvokeAnimation(){
        CardCrawlGame.sound.play("ORB_DARK_EVOKE", 0.1F);
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(this.cX, this.cY));
    }


    @Override
    public void onEndOfTurn(){
        this.AttackEffect();
        if(!orbs.isEmpty())
        for(AbstractOrb o:orbs){
            o.onEndOfTurn();
        }

    }

    @Override
    public void onStartOfTurn() {
        super.onStartOfTurn();
        if(!orbs.isEmpty())
        for(AbstractOrb o:orbs){
            o.onStartOfTurn();
        }

    }

    @Override
    public void AttackEffect(){
        Attack(AbstractGameAction.AttackEffect.LIGHTNING);
        Trigger();
        super.AttackEffect();
        int i, j = upgraded ? 2 :1;
        for(i = 0 ; i < j ; i++){
            if(orbs.size() >= (upgraded ? 5 : 4)){
                AbstractOrb o1 = orbs.get(0);
                o1.onEvoke();
                orbs.remove(o1);
            }
            AbstractOrb o = AbstractOrb.getRandomOrb(true);
            o.cX = cX;
            o.cY = cY;
            this.orbs.add(o);
            o.playChannelSFX();
            Iterator var6 = AbstractDungeon.player.powers.iterator();

            while(var6.hasNext()) {
                AbstractPower p = (AbstractPower)var6.next();
                p.onChannel(o);
            }

            AbstractDungeon.actionManager.orbsChanneledThisCombat.add(o);
            AbstractDungeon.actionManager.orbsChanneledThisTurn.add(o);

            refreshOrbs();
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        if(!orbs.isEmpty()){
            for(AbstractOrb o:orbs){
                o.render(sb);
            }
        }
        super.render(sb);

    }

    @Override
    public void update() {
        super.update();
        if(!orbs.isEmpty()){
            for(AbstractOrb o:orbs){
                o.updateAnimation();
            }
        }
        time += Gdx.graphics.getDeltaTime();
        if(time >= 6.2832F)
            time = 0.0F;
        refreshOrbs();
    }

    public void refreshOrbs(){
        int i;
        float timef = (float)Math.cos(time) + time;
        float pif = 2 * (float)Math.PI / orbs.size();
        if(!orbs.isEmpty())
        for(i = 0 ; i < orbs.size() ; i++){
            AbstractOrb o = orbs.get(i);
            o.tX = cX + 50.0F * Settings.scale *(float)Math.cos(timef + pif * i);
            o.tY = cY + 100.0F * Settings.scale + 50.0F * Settings.scale * (float)Math.sin(timef + pif * i);
        }
    }

    public void playChannelSFX(){
        CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
    }

}
