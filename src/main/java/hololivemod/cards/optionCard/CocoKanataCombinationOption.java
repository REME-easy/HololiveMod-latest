package hololivemod.cards.optionCard;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import hololivemod.relics.TheStar;

public class CocoKanataCombinationOption extends AbstractCombinatiionCard {
    private static final String ID = "Hololive_CocoKanataCombinationOption";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/optioncards/CocoKanata.png";

    public CocoKanataCombinationOption(){
        super(ID, NAME, IMG_PATH, DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void CombinationEffect() {
        for(AbstractRelic relic: AbstractDungeon.player.relics){
            if(relic instanceof TheStar){
                this.addToBot(new DrawCardAction(5));
                TheStar.CombinationIndex[23] = true;
                relic.description = relic.getUpdatedDescription();
                relic.tips.clear();
                relic.tips.add(new PowerTip(relic.name, relic.description));
                break;
            }
        }
    }

    public void upgrade() {
    }

    public AbstractCard makeCopy() {
        return new CocoKanataCombinationOption();
    }
}