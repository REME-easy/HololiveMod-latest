package hololivemod.cards.summonCard;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import hololivemod.actions.SpawnMateAction;
import hololivemod.helper.CardHelper;
import hololivemod.minions.AbstractMinion;
import hololivemod.minions.AkaiHaato;

import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;

public class CallAkaiHaato extends AbstractSummonCard {
    private static final String ID = "Hololive_CallAkaiHaato";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "img/card/summoncards/CallAkaiHaato.png";
    private static final int COST = 1;
    private static final int MAGIC_NUM = 3;
    private static final int UPGRADE_MAGIC_NUM = 1;
    public CallAkaiHaato(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.POWER, Hololive_BLUE,
                CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardATK = 5;
        this.cardHP = 6;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
        if(CardHelper.isBalance){
            this.cardATK = 3;
            this.cardHP = 3;
            this.baseMagicNumber = this.magicNumber = 1;
        }
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(35), CardHelper.CombinationsDescription.get(35)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(36), CardHelper.CombinationsDescription.get(36)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(30), CardHelper.CombinationsDescription.get(30)));
        tips.add(new TooltipInfo(CardHelper.CombinationsName.get(21), CardHelper.CombinationsDescription.get(21)));


    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SpawnMateAction(new AkaiHaato(this.upgraded),this.cardATK,this.cardHP));
            for(AbstractOrb orb:AbstractDungeon.player.orbs){
                if(orb instanceof AbstractMinion){
                    ((AbstractMinion) orb).ChangeHP(magicNumber,false);
                }
            }
    }

    public AbstractCard makeCopy(){
        return new CallAkaiHaato();
    }


    public void upgrade(){
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUM);
        }
    }
}
