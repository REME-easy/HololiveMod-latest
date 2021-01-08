package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.MinatoAqua;
import hololivemod.patches.AbstractCardEnum;

public class CallMinatoAqua extends AbstractSummonCard {
    private static final String ID = "Hololive_CallMinatoAqua";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallMinatoAqua.png";
    private static final int COST = 2;

    public CallMinatoAqua(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 4;
        this.cardHP = 12;
        if(CardHelper.isBalance){
            this.cardATK = 2;
            this.cardHP = 6;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(31), CardHelper.CombinationsDescription.get(31)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(25), CardHelper.CombinationsDescription.get(25)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(16), CardHelper.CombinationsDescription.get(16)));

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new MinatoAqua(this.upgraded),this.cardATK,this.cardHP));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for(AbstractOrb o:AbstractDungeon.player.orbs){
            if(o instanceof EmptyOrbSlot){
                ++count;
            }
        }
        if(count == 0){
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return  false;
        }else {
            return super.canUse(p, m);
        }
    }

    public AbstractCard makeCopy(){
        return new CallMinatoAqua();
    }

    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            this.initializeDescription();
        }
    }
}
