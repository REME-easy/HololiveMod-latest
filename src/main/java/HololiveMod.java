import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RestartForChangesEffect;
import hololivemod.Char.HololiveCharacter;
import hololivemod.cards.attackCard.*;
import hololivemod.cards.cursecard.RevenueRecovery;
import hololivemod.cards.optionCard.*;
import hololivemod.cards.skillCard.*;
import hololivemod.cards.summonCard.*;
import hololivemod.helper.CardHelper;
import hololivemod.helper.OrbHelper;
import hololivemod.minions.AbstractMinion;
import hololivemod.minions.AiraniIofifteen;
import hololivemod.minions.OmaruPolka;
import hololivemod.patches.CharacterSelectPatch;
import hololivemod.relics.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

import static com.megacrit.cardcrawl.core.Settings.language;
import static hololivemod.patches.AbstractCardEnum.Achievements;
import static hololivemod.patches.AbstractCardEnum.Hololive_BLUE;
import static hololivemod.patches.MyPlayerClassEnum.Hololive_HololiveCharacter;


@SpireInitializer
public class HololiveMod implements  EditCharactersSubscriber,EditStringsSubscriber,EditCardsSubscriber,EditRelicsSubscriber,EditKeywordsSubscriber,OnCardUseSubscriber,OnPlayerDamagedSubscriber,OnStartBattleSubscriber,PostBattleSubscriber,PostInitializeSubscriber,PostDungeonInitializeSubscriber{
    private static final Logger logger = LogManager.getLogger(HololiveMod.class);
    private static  final String MY_CHARACTER_BUTTON = "img/char/hololiveCharacterButton.png";
    private static  final String MY_CHARACTER_PORTRAIT = "img/char/hololiveCharacterPortrait.png";
    private static final  String BG_ATTACK_512 = "img/512/bg_attack_default_gray.png";
    private static final  String BG_POWER_512 = "img/512/bg_power_default_gray.png";
    private static final  String BG_SKILL_512 = "img/512/bg_skill_default_gray.png";
    private static final  String small_orb = "img/512/card_default_gray_orb.png";
    private static final  String BG_ATTACK_1024 = "img/1024/bg_attack_default_gray.png";
    private static final  String BG_POWER_1024 = "img/1024/bg_power_default_gray.png";
    private static final  String BG_skill_1024 = "img/1024/bg_skill_default_gray.png";
    private static final  String big_orb = "img/1024/card_default_gray_orb.png";
    private static final  String energy_orb = "img/512/card_small_orb.png";
    private static final Color light_blue = getColor(100.0f,225.0f,240.0f);
    public static HashMap<String, String> combinations = new HashMap<>();

    public static boolean isBalance = false;

    private int dmg;
    private boolean CanAWSL = true;

    public HololiveMod() {
            BaseMod.subscribe(this);
            BaseMod.addColor(Hololive_BLUE,light_blue,light_blue,light_blue,light_blue,light_blue,light_blue,light_blue,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,small_orb,BG_ATTACK_1024,BG_skill_1024,BG_POWER_1024,big_orb,energy_orb);
            BaseMod.addColor(Achievements,light_blue,light_blue,light_blue,light_blue,light_blue,light_blue,light_blue,BG_ATTACK_512,BG_SKILL_512,BG_POWER_512,small_orb,BG_ATTACK_1024,BG_skill_1024,BG_POWER_1024,big_orb,energy_orb);
    }

    public static void initialize() {
        new HololiveMod();
        try{
            Properties defaults = new Properties();
            defaults.setProperty("BalanceMode", "false");
            SpireConfig config = new SpireConfig("HololiveMod", "Common", defaults);

            isBalance = config.getBool("BalanceMode");

            CardHelper.getBalanceMode(isBalance);
        }catch (IOException var2){
            var2.printStackTrace();
        }
    }


    @Override
    public void receiveEditCharacters() {
        logger.info("开始加载vtuber");
        logger.info("add " + Hololive_HololiveCharacter.toString());
        BaseMod.addCharacter(new HololiveCharacter(CardCrawlGame.playerName),
                MY_CHARACTER_BUTTON,
                MY_CHARACTER_PORTRAIT,
                Hololive_HololiveCharacter);
        logger.info("b（￣▽￣）d　");
    }
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "zh";
        } else if(language == Settings.GameLanguage.JPN){
            lang = "jp";
        } else {
            lang = "eng";
        }
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/REMERelics_" + lang + ".json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/REMECards_" + lang + ".json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/REMEPowers_" + lang + ".json");
        //BaseMod.loadCustomStringsFile(EventStrings.class, "localization/REMEEvent_" + lang + ".json");
        BaseMod.loadCustomStringsFile(MonsterStrings.class,"localization/REMEMonsters_"+ lang +".json");
        BaseMod.loadCustomStringsFile(OrbStrings.class,"localization/REMEOrbs_" + lang + ".json");
        BaseMod.loadCustomStringsFile(UIStrings.class,"localization/REMEUI_" + lang + ".json");
    }

    public void receiveEditCards() {
        BaseMod.addCard(new OvertimeWork());
        BaseMod.addCard(new Rebound());
        BaseMod.addCard(new Sequa());
        BaseMod.addCard(new IdolExercise());
        BaseMod.addCard(new HuntDinosaur());
        BaseMod.addCard(new MaximGun());
        BaseMod.addCard(new LuckPotion());
        BaseMod.addCard(new Breathe());
        BaseMod.addCard(new Yeah());
        BaseMod.addCard(new Recruit());
        BaseMod.addCard(new RecruitS());
        BaseMod.addCard(new ILoveYou());
        BaseMod.addCard(new Teleport());
        BaseMod.addCard(new CocoNews());
        BaseMod.addCard(new RevenueBasedLive());
        BaseMod.addCard(new SoulCatcher());
        BaseMod.addCard(new Nanodesu());
        BaseMod.addCard(new DiamondStrike());
        BaseMod.addCard(new SummonFox());
        BaseMod.addCard(new ManjiGathering());
        BaseMod.addCard(new Rebirth());
        BaseMod.addCard(new Watashimo());
        BaseMod.addCard(new Drill());
        BaseMod.addCard(new StarCraft());
        BaseMod.addCard(new BringToTrial());
        BaseMod.addCard(new Psychosis());
        BaseMod.addCard(new HighAtkLowDef());
        BaseMod.addCard(new Yoholoon());
        BaseMod.addCard(new WolfAndLamb());
        BaseMod.addCard(new OpenUp());
        BaseMod.addCard(new FlyingPonyTail());
        BaseMod.addCard(new CandyCastle());
        BaseMod.addCard(new StrengthChampion());
        BaseMod.addCard(new DevilCharm());
        BaseMod.addCard(new ScarfAndHalo());
        BaseMod.addCard(new Spy());
        BaseMod.addCard(new ASMR());
        BaseMod.addCard(new Thief());
        BaseMod.addCard(new AWSL());
        BaseMod.addCard(new DDD());
        BaseMod.addCard(new DurableLive());
        BaseMod.addCard(new BeCareful());
        BaseMod.addCard(new Trident());
        BaseMod.addCard(new FlareMind());
        BaseMod.addCard(new HololiveBlizzard());
        BaseMod.addCard(new CallFriendA());
        BaseMod.addCard(new CallMinatoAqua());
        BaseMod.addCard(new CallTokinoSora());
        BaseMod.addCard(new CallYuzukiChoco());
        BaseMod.addCard(new CallInugamiKorone());
        BaseMod.addCard(new CallHoushouMarine());
        BaseMod.addCard(new CallUsadaPekora());
        BaseMod.addCard(new CallNatsuiroMatsuri());
        BaseMod.addCard(new CallKiryuuCoco());
        BaseMod.addCard(new CallMurasakiShion());
        BaseMod.addCard(new CallShiroganeNoel());
        BaseMod.addCard(new CallAkaiHaato());
        BaseMod.addCard(new CallShirakamiFubuki());
        BaseMod.addCard(new CallRoboco());
        BaseMod.addCard(new CallAkiRosenthal());
        BaseMod.addCard(new CallNakiriAyame());
        BaseMod.addCard(new CallOzoraSubaru());
        BaseMod.addCard(new CallSakuraMiko());
        BaseMod.addCard(new CallShiranuiFlare());
        BaseMod.addCard(new CallShiroganeNoel());
        BaseMod.addCard(new CallUruhaRushia());
        BaseMod.addCard(new CallYozoraMel());
        BaseMod.addCard(new CallHimemoriLuna());
        BaseMod.addCard(new CallTsunomakiWatame());
        BaseMod.addCard(new CallTokoyamiTowa());
        BaseMod.addCard(new CallAmaneKanata());
        BaseMod.addCard(new CallAZKi());
        BaseMod.addCard(new CallSpadeEcho());
        BaseMod.addCard(new CallCivia());
        BaseMod.addCard(new CallYogiri());
        BaseMod.addCard(new CallOkamiMio());
        BaseMod.addCard(new CallHoshimachiSuisei());
        BaseMod.addCard(new CallNekomataOkayu());
        BaseMod.addCard(new CallRosalyn());
        BaseMod.addCard(new CallArtia());
        BaseMod.addCard(new CallDoris());
        BaseMod.addCard(new CallAyundaRisu());
        BaseMod.addCard(new CallMoonaHoshinova());
        BaseMod.addCard(new CallAiraniIofifteen());
        BaseMod.addCard(new CallYukihanaLamy());
        BaseMod.addCard(new CallShishiroBotan());
        BaseMod.addCard(new CallMomosuzuNene());
        BaseMod.addCard(new CallManoAloe());
        BaseMod.addCard(new CallOmaruPolka());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Strike());

        BaseMod.addCard(new BlondHairCombinationOption());
        BaseMod.addCard(new CatDogCombinationOption());
        BaseMod.addCard(new FamsCombinationOption());
        BaseMod.addCard(new GamersCombinationOption());
        BaseMod.addCard(new HappyFamilyCombinationOption());
        BaseMod.addCard(new ManjiCombinationOption());
        BaseMod.addCard(new NatsuiroFubukiCombinationOption());
        BaseMod.addCard(new NewCatDogCombinationOption());
        BaseMod.addCard(new PekoMikoCombinationOption());
        BaseMod.addCard(new AquaMarineCombinationOption());
        BaseMod.addCard(new SoraACombinationOption());
        BaseMod.addCard(new NoelFlareCombinationOption());
        BaseMod.addCard(new MatsuriShionCombinationOption());
        BaseMod.addCard(new ZeroCombinationOption());
        BaseMod.addCard(new OneCombinationOption());
        BaseMod.addCard(new TwoCombinationOption());
        BaseMod.addCard(new ThreeCombinationOption());
        BaseMod.addCard(new FourCombinationOption());
        BaseMod.addCard(new IdolCombinationOption());
        BaseMod.addCard(new AllCombinationOption());
        BaseMod.addCard(new WatameHaatoCombinationOption());
        BaseMod.addCard(new MatsuriLunaCombinationOption());
        BaseMod.addCard(new CocoKanataCombinationOption());
        BaseMod.addCard(new CNTwoCombinationOption());
        BaseMod.addCard(new IDCombinationOption());
        BaseMod.addCard(new FiveCombinationOption());
        BaseMod.addCard(new ChallengeYagooBigFamily());
        BaseMod.addCard(new ChallengeOneManArmy());
        BaseMod.addCard(new ChallengeTopUpMoney());
        BaseMod.addCard(new ChallengeYo());
        BaseMod.addCard(new ChallengeChaos());
        BaseMod.addCard(new ChallengeKindergarten());
        BaseMod.addCard(new ChallengeTheOnlyOne());
        BaseMod.addCard(new ChallengePeko());


        BaseMod.addCard(new RevenueRecovery());
    }

    public void receiveEditRelics(){
        BaseMod.addRelicToCustomPool(new TheStar(),Hololive_BLUE);
        BaseMod.addRelic(new OneManArmy(), RelicType.SHARED);
        BaseMod.addRelic(new YagooBigFamily(), RelicType.SHARED);
        BaseMod.addRelic(new TopUpMoney(), RelicType.SHARED);
        BaseMod.addRelic(new Yo(), RelicType.SHARED);
        BaseMod.addRelic(new Chaos(), RelicType.SHARED);
        BaseMod.addRelic(new Kindergarten(), RelicType.SHARED);
        BaseMod.addRelic(new TheOnlyOne(), RelicType.SHARED);
        BaseMod.addRelic(new Peko(), RelicType.SHARED);


        BaseMod.addRelic(new TopUpMoneyS(), RelicType.SHARED);
        BaseMod.addRelic(new ChaosS(), RelicType.SHARED);

        BaseMod.addRelic(new LightStick(),RelicType.SHARED);
        BaseMod.addRelic(new Finger(),RelicType.SHARED);
        BaseMod.addRelicToCustomPool(new FriedShrimpLion(),Hololive_BLUE);

    }

    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "zh";
        } else if(language == Settings.GameLanguage.JPN){
            lang = "jp";
        } else {
            lang = "eng";
        }

        logger.info("====开始编辑关键词====");
        CardHelper.CombinationsName.add("null");
        CardHelper.CombinationsDescription.add("null");
        String json = Gdx.files.internal("localization/REMEKeywords_" + lang + ".json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = (Keyword[])((Keyword[])gson.fromJson(json, Keyword[].class));
        if (keywords != null) {
            Keyword[] var6 = keywords;
            int var7 = keywords.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Keyword keyword = var6[var8];
                BaseMod.addKeyword(keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
                CardHelper.CombinationsName.add(keyword.NAMES[0]);
                CardHelper.CombinationsDescription.add(keyword.DESCRIPTION);
            }
        }

    }



    public int receiveOnPlayerDamaged(int amount, DamageInfo info) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.orbs.size() > 0 ) {
            boolean overBlock = false;
            int j;
            dmg = amount;
            if(AbstractDungeon.player.currentBlock > 0){
                if(dmg - AbstractDungeon.player.currentBlock <= 0) {
                    AbstractDungeon.player.loseBlock(dmg);
                    return 0;
                } else {
                    dmg -= AbstractDungeon.player.currentBlock;
                    if(AbstractDungeon.player.hasPower("Plated Armor"))
                        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player, "Plated Armor", 1));
                    overBlock = true;
                }
            }
            logger.info("先攻击嘲讽队友");
            boolean hasMate = false;
            for(AbstractOrb o:AbstractDungeon.player.orbs){
                if(o instanceof AbstractMinion){
                    hasMate = true;
                    break;
                }
            }
            if(hasMate)
                for(j = 0 ; j < AbstractDungeon.player.maxOrbs ; j++){
                    if(!(AbstractDungeon.player.orbs.get(j) instanceof AbstractMinion)){
                        AbstractDungeon.player.orbs.get(j).onEvoke();
                        AbstractOrb orbSlot = new EmptyOrbSlot();
                        int i;
                        for (i = j + 1; i < AbstractDungeon.player.orbs.size(); ++i) {
                            Collections.swap(AbstractDungeon.player.orbs, i, i - 1);
                        }
                        AbstractDungeon.player.orbs.set(AbstractDungeon.player.orbs.size() - 1, orbSlot);
                        for (i = j; i < AbstractDungeon.player.orbs.size(); ++i) {
                            AbstractDungeon.player.orbs.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
                        }
                    }
                }
            onAttackAssitTaunt();
            onAttackAssit();
            for(AbstractOrb o:AbstractDungeon.player.orbs){
                if(o instanceof OmaruPolka){
                    ((OmaruPolka) o).refreshOrbs();
                }
            }
            try{
                return dmg;
            }finally {
                if(overBlock)
                    AbstractDungeon.player.loseBlock(AbstractDungeon.player.currentBlock);
            }

        }
        return amount;
    }
    private void onAttackAssit(){
        logger.info("onAttackAssit");
        int j = 0;
            AbstractOrb orb = AbstractDungeon.player.orbs.get(j);
            logger.info("onAttackAssit：选取" + orb.name);
            if(orb instanceof AbstractMinion){
                AbstractMinion student = (AbstractMinion) orb;
                logger.info("onAttackAssit：现在受到伤害的是" + student.name);
                if (student.HP > this.dmg){
                    student.ChangeHP(-this.dmg,true);
                    student.onDamaged(this.dmg,true);
                    this.dmg = 0;
                    logger.info("onAttackAssit:队友扛下了伤害");
                } else {
                    student.onDamaged(student.HP,true);
                    this.dmg -= student.HP;
                    student.HP = 0;
                    student.isdead = true;
                    if(TheStar.CombinationIndex[24])
                        student.AttackEffect();
                    if(student instanceof AiraniIofifteen){
                        dmg = 0;
                    }
                    student.onEvoke();
                    logger.info("onAttackAssit:队友没扛下伤害，死了");
                    AbstractOrb orbSlot = new EmptyOrbSlot();
                    int i;
                    for (i = 1; i < AbstractDungeon.player.orbs.size(); ++i) {
                        Collections.swap(AbstractDungeon.player.orbs, i, i - 1);
                    }
                    AbstractDungeon.player.orbs.set(AbstractDungeon.player.orbs.size() - 1, orbSlot);
                    for (i = 0; i < AbstractDungeon.player.orbs.size(); ++i) {
                        AbstractDungeon.player.orbs.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
                    }
                    onAttackAssit();
                }
            }

    }
    private void onAttackAssitTaunt() {
        logger.info("onAttackAssitTaunt");
        int j;
        for(j = 0;j<AbstractDungeon.player.orbs.size();++j) {
            AbstractOrb orb = AbstractDungeon.player.orbs.get(j);
            logger.info("onAttackAssitTaunt：选取" + orb.name);
            if (orb instanceof AbstractMinion &&  ((AbstractMinion) orb).Taunt) {
                logger.info("onAttackAssitTaunt：现在受到伤害的是" + orb.name);
                if (((AbstractMinion) orb).HP > this.dmg) {
                    ((AbstractMinion) orb).ChangeHP(-this.dmg,true);
                    ((AbstractMinion) orb).onDamaged(this.dmg,true);
                    this.dmg = 0;
                    logger.info("onAttackAssitTaunt:队友扛下了伤害");
                    return;
                } else {
                    ((AbstractMinion) orb).onDamaged(((AbstractMinion) orb).HP,true);
                    this.dmg -= ((AbstractMinion) orb).HP;
                    ((AbstractMinion) orb).HP = 0;
                    ((AbstractMinion) orb).isdead = true;
                    if(TheStar.CombinationIndex[24])
                        ((AbstractMinion) orb).AttackEffect();
                    if(orb instanceof AiraniIofifteen){
                        dmg = 0;
                    }
                    orb.onEvoke();
                    logger.info("onAttackAssitTaunt:队友没扛下伤害，死了");
                    AbstractOrb orbSlot = new EmptyOrbSlot();
                    int i;
                    for (i = j + 1; i < AbstractDungeon.player.orbs.size(); ++i) {
                        Collections.swap(AbstractDungeon.player.orbs, i, i - 1);
                    }
                    AbstractDungeon.player.orbs.set(AbstractDungeon.player.orbs.size() - 1, orbSlot);
                    for (i = j; i < AbstractDungeon.player.orbs.size(); ++i) {
                        AbstractDungeon.player.orbs.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
                    }
                    onAttackAssitTaunt();
                }
                break;
            }
        }
    }

    public void receiveCardUsed(AbstractCard c) {
            if (AbstractDungeon.player instanceof HololiveCharacter) {
                if (c instanceof AbstractSummonCard) {
                    CardHelper.UsedSummonCardsThisBattle.add((AbstractSummonCard) c.makeCopy());
                    }
                }
            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof AbstractMinion) {
                    ((AbstractMinion) orb).onCardUse(c);
                }
            }
        }

    public void receiveOnBattleStart(AbstractRoom room){
        OrbHelper.CallMinionsThisBattle = 0;
        OrbHelper.DeadMinionsThisBattle.clear();
        CardHelper.UsedSummonCardsThisBattle.clear();
        if(CanAWSL)
            for(AbstractCard card:AbstractDungeon.player.masterDeck.group) {
                if(card instanceof AWSL ) {
                    AbstractDungeon.player.increaseMaxHp(1,true);
                }
            }
        this.CanAWSL = false;
    }

    public void receivePostBattle(AbstractRoom var1){
        this.CanAWSL = true;
    }

    public void receivePostInitialize() {
        try {
            this.CreatePanel();
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    public void receivePostDungeonInitialize(){
        if(AbstractDungeon.player instanceof HololiveCharacter){
            if(CharacterSelectPatch.DeckIndex == 11){
                obtain(AbstractDungeon.player,new Peko(),false);
            }
            if(CharacterSelectPatch.DeckIndex == 10){
                obtain(AbstractDungeon.player,new TheOnlyOne(),false);
            }
            if(CharacterSelectPatch.DeckIndex == 9){
                obtain(AbstractDungeon.player,new Kindergarten(),false);
            }
            if(CharacterSelectPatch.DeckIndex == 8){
                obtain(AbstractDungeon.player,new Chaos(),false);
            }
            if(CharacterSelectPatch.DeckIndex == 7){
                obtain(AbstractDungeon.player,new Yo(),false);
            }
            if(CharacterSelectPatch.DeckIndex == 6){
                obtain(AbstractDungeon.player,new TopUpMoney(),false);
            }
            if(CharacterSelectPatch.DeckIndex == 5){
                obtain(AbstractDungeon.player,new OneManArmy(),false);
            }
            if(CharacterSelectPatch.DeckIndex == 4){
                obtain(AbstractDungeon.player,new YagooBigFamily(),false);
            }
        }
        AbstractDungeon.uncommonRelicPool.remove("Mummified Hand");

    }

    private void CreatePanel() throws IOException {
        Texture badgeTexture = new Texture(Gdx.files.internal("img/Hololive.png"));
        SpireConfig spireConfig = new SpireConfig("HololiveMod", "Common");
        ModPanel settingsPanel = new ModPanel();
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("BALANCE_MODE");
        String[] TEXT = uiStrings.TEXT;
        ModLabeledToggleButton BalanceMode = new ModLabeledToggleButton(TEXT[0], 500.0F, 500.0F, Settings.BLUE_TEXT_COLOR, FontHelper.charDescFont, isBalance, settingsPanel, (label) -> {}
        ,(button) -> {
            spireConfig.setBool("BalanceMode", isBalance = button.enabled);
            CardCrawlGame.mainMenuScreen.optionPanel.effects.clear();
            CardCrawlGame.mainMenuScreen.optionPanel.effects.add(new RestartForChangesEffect());

            try {
                spireConfig.save();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        });
        settingsPanel.addUIElement(BalanceMode);
        BaseMod.registerModBadge(badgeTexture, "HololiveMod", "REME", TEXT[1], settingsPanel);
    }

    public static boolean obtain(AbstractPlayer p, AbstractRelic r, boolean canDuplicate) {
        if (r == null) {
            return false;
        } else if (p.hasRelic(r.relicId) && !canDuplicate) {
            return false;
        } else {
            int slot = p.relics.size();
            r.makeCopy().instantObtain(p, slot, true);
            return true;
        }
    }

    private static Color getColor(float r, float g, float b) {
        return new Color(r / 255.0F, g / 255.0F, b / 255.0F, 1.0F);
    }

}