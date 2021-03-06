package hololivemod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class TopUpMoney extends CustomRelic {
    public static final String ID = "Hololive_TopUpMoney";
    private boolean RclickStart = false;
    private boolean Rclick = false;

    public TopUpMoney() {
        super(ID, ImageMaster.loadImage("img/relics/TopUpMoney.png"),
                RelicTier.SPECIAL, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onEnterRoom(AbstractRoom room){
        if(AbstractDungeon.floorNum >= 50)
            if(!CardLibrary.getCard("Hololive_ChallengeTopUpMoney").isSeen)
                UnlockTracker.markCardAsSeen("Hololive_ChallengeTopUpMoney");
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        return numberOfCards - 99;
    }

    private void onRightClick(){
        if(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT){
            if(AbstractDungeon.player.gold >= 10){
                this.flash();
                AbstractDungeon.player.gold -= 10;
                this.addToBot(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat()));
            } else {
                AbstractDungeon.actionManager.addToBottom(new TalkAction(AbstractDungeon.player, DESCRIPTIONS[1], 0.10F, 2.5F));
            }
        }
    }

    public void update() {
        super.update();
        if (this.RclickStart && InputHelper.justReleasedClickRight) {
            if (this.hb.hovered) {
                this.Rclick = true;
            }
            this.RclickStart = false;
        }
        if (this.isObtained && this.hb != null && this.hb.hovered && InputHelper.justClickedRight) {
            this.RclickStart = true;
        }
        if (this.Rclick) {
            this.Rclick = false;
            this.onRightClick();
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new TopUpMoney();
    }
}
