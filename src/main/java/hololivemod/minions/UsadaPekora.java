package hololivemod.minions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;

public class UsadaPekora extends AbstractMinion {
    private static final String ID = "Hololive_UsadaPekora";
    private static final OrbStrings cardStrings = CardCrawlGame.languagePack.getOrbString(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String[] DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/Orbs/UsadaPekora.png";
    private static final int basePassiveAmount = 0;
    private static final int baseEvokeAmount = 3;
    private static final int originalHP = 14;
    private static final int originalATK = 1;
    public UsadaPekora(boolean upgraded){
        super(ID,NAME,basePassiveAmount,baseEvokeAmount,originalHP,IMG_PATH,DESCRIPTION[0],DESCRIPTION[1]);
        this.upgraded = upgraded;
        this.ATK = originalATK;
    }

    public AbstractMinion makeCopy(){
        return new UsadaPekora(this.upgraded);
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

    public void onCardUseEffect(){
        if (upgraded) {
            for (AbstractMonster abstractMonster : AbstractDungeon.getMonsters().monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, AbstractDungeon.player, new PoisonPower(abstractMonster, AbstractDungeon.player, 1), 1));
                Trigger();
            }
        } else {
            AbstractMonster monster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, AbstractDungeon.player, new PoisonPower(monster, AbstractDungeon.player, 1), 1));
            Trigger();
        }
    }

    public void onCardUse(AbstractCard c){
            onCardUseEffect();
    }

    @Override
    public void onEndOfTurn(){
        this.AttackEffect();
    }

    @Override
    public void AttackEffect(){
        Attack(AbstractGameAction.AttackEffect.POISON);
        Trigger();
        super.AttackEffect();
    }


    public void playChannelSFX(){
        CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
    }
}
