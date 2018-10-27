package edu.cs356.edgeoftheempirecharactercreator.model;

import java.util.ArrayList;
import java.util.List;

import edu.cs356.model.Character;
import edu.cs356.model.skills.Skill;
import edu.cs356.model.skills.SkillList;

public class XPModel {

    public enum AttrType {
        BRAWN,
        AGILITY,
        INTELLECT,
        CUNNING,
        WILL,
        PRESENCE;

    }

    private Integer xp;

    private Integer mBrawnValue;
    private Integer mAgilityValue;
    private Integer mIntValue;
    private Integer mCunValue;
    private Integer mWillValue;
    private Integer mPresValue;

    private SkillList skillList;

    private List<xpAction> actions;

    private static XPModel sInstance;
    private XPModel() {
        actions = new ArrayList<>();
    }
    public static XPModel getInstance() {
        if (sInstance == null) {
            sInstance = new XPModel();
        }
        return sInstance;
    }

    public void initValues(Character character) {
        mBrawnValue = character.getSpecies().getBrawn();
        mAgilityValue = character.getSpecies().getAgility();
        mIntValue = character.getSpecies().getIntelligence();
        mCunValue = character.getSpecies().getCunning();
        mWillValue = character.getSpecies().getWillpower();
        mPresValue = character.getSpecies().getPresence();

        skillList = character.getSkillList();

        xp = character.getSpecies().getStartingXP();
    }

    public Integer getXp() {
        return xp;
    }

    public Integer getBrawnValue() {
        return mBrawnValue;
    }

    public Integer getAgilityValue() {
        return mAgilityValue;
    }

    public Integer getIntValue() {
        return mIntValue;
    }

    public Integer getCunValue() {
        return mCunValue;
    }

    public Integer getWillValue() {
        return mWillValue;
    }

    public Integer getPresValue() {
        return mPresValue;
    }

    public Result increaseAttr(AttrType ATTR){

        int xpCost;
        xpAction action;

        switch(ATTR){
            case BRAWN:
                xpCost = (mBrawnValue + 1)*5;
                break;
            case AGILITY:
                xpCost = (mAgilityValue + 1)*5;
                break;
            case INTELLECT:
                xpCost = (mIntValue + 1)*5;
                break;
            case CUNNING:
                xpCost = (mCunValue + 1)*5;
                break;
            case WILL:
                xpCost = (mWillValue + 1)*5;
                break;
            case PRESENCE:
                xpCost = (mPresValue + 1)*5;
                break;
            default:
                return new Result(false, "Invalid Attribute Value");
        }

        action = new xpAction(true, ATTR, xpCost);

        return executeAction(action);

    }

    public Result increaseSkill(Skill.SkillType SKILL){

        Skill skill = skillList.getSkillByType(SKILL);

        if (skill.getRank() == 5){
            return new Result(false, "Cannot increase a skill past rank 5");
        }

        int xpCost;

        if(skill.isCareerSkill()){
            xpCost = (skill.getRank()+1)*5;
        }
        else xpCost = (skill.getRank()+2)*5;

        xpAction action = new xpAction(false, skill, xpCost);

        return executeAction(action);

    }

    private Result executeAction(xpAction action){
        int xpCost = action.cost;

        if(xpCost > xp){
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid: Need ");
            sb.append(xpCost);
            sb.append(" experience");
            return new Result(false, sb.toString());
        }

        if(action.isAttr){
            AttrType ATTR = (AttrType) action.increased;

            switch(ATTR){
                case BRAWN:
                    mBrawnValue += 1;
                    break;
                case AGILITY:
                    mAgilityValue += 1;
                    break;
                case INTELLECT:
                    mIntValue += 1;
                    break;
                case CUNNING:
                    mCunValue += 1;
                    break;
                case WILL:
                    mWillValue += 1;
                    break;
                case PRESENCE:
                    mPresValue += 1;
                    break;
                default:
                    return new Result(false, "Invalid Attribute Value");
            }
        }

        else {
            Skill skill = (Skill) action.increased;

            skill.incrementRank();
        }

        xp -= xpCost;
        actions.add(action);

        return new Result(true, null);
    }

    public Result undoAction() {
        if (actions.size() == 0) {
            return new Result(false, "Nothing to undo");
        }

        Result result = new Result(false, null);

        xpAction action = actions.remove(actions.size()-1);

        int xpCost = action.cost;

        if (action.isAttr) {

            AttrType ATTR = (AttrType) action.increased;
            result.setResult(ATTR);

            switch(ATTR){
                case BRAWN:
                    mBrawnValue -= 1;
                    break;
                case AGILITY:
                    mAgilityValue -= 1;
                    break;
                case INTELLECT:
                    mIntValue -= 1;
                    break;
                case CUNNING:
                    mCunValue -= 1;
                    break;
                case WILL:
                    mWillValue -= 1;
                    break;
                case PRESENCE:
                    mPresValue -= 1;
                    break;
                default:
                    result.setResult("Invalid Attribute Value");
                    return result;
            }
        }
        else {
            Skill skill = (Skill) action.increased;
            skill.decrementRank();
            result.setResult(skill);
        }

        xp += xpCost;
        result.setSuccess(true);

        return result;
    }

}

class xpAction {

    public boolean isAttr;
    public Object increased;
    public int cost;

    public xpAction(boolean isAttr, Object increased, int cost) {
        this.isAttr = isAttr;
        this.increased = increased;
        this.cost = cost;
    }
}