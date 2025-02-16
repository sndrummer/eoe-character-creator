package edu.cs356.model.skills;

import java.io.Serializable;

import edu.cs356.model.species.Species;

public class Skill implements Serializable {

    /* MAX DICE ROLLS !!!!!!!!!
    6 Ability -- green
    5 Proficiency -- yellow
    5 Difficulty -- purple
    5 Challenge -- red
     */

    public Skill(String name, Species.Characteristic governingAtt, SkillListWrapper wrapper) {
        this.name = name;
        this.governingAtt = governingAtt;
        isCareerSkill = false;
        listWrapper = wrapper;
    }

    private String description;
    private boolean isCareerSkill;
    private transient SkillListWrapper listWrapper;
    private String name;

    public static final int MAX_RANK = 5;
    private Species.Characteristic governingAtt;
    private int ability; //green dice
    private int rank = 0; //yellow dice
    private int bonus = 0;
    private int setback = 0;



    public enum SkillType {
        ASTROGATION,
        ATHLETICS,
        CHARM,
        COERCION,

        COMPUTERS,
        COOL,
        COORDINATION,
        DECEPTION,

        DISCIPLINE,
        LEADERSHIP,
        MECHANICS,
        MEDICINE,

        NEGOTIATION,
        PERCEPTION,
        PILOTING_SPACE,
        PILOTING_PLANETARY,
        RESILIENCE,

        SKULLDUGGERY,
        STEALTH,
        STREETWISE,
        VIGILANCE,

        BRAWL,
        GUNNERY,
        MELEE,
        RANGED_HEAVY,
        RANGED_LIGHT,

        //Knowledge Skills
        CORE_WORLDS,
        EDUCATION,
        LORE,
        OUTER_RIM,
        UNDERWORLD,
        WARFARE,
        XENOLOGY;
    }


    public boolean isCareerSkill() {
        return isCareerSkill;
    }

    public void setCareerSkill(boolean careerSkill) {
        isCareerSkill = careerSkill;
    }

    public String getGoverningAttString() {
        String res = "";
        switch (governingAtt) {
            case BR:
                res = "(BR)";
                break;
            case INT:
                res = "(INT)";
                break;
            case AG:
                res = "(AG)";
                break;
            case CUN:
                res = "(CUN)";
                break;
            case WILL:
                res = "(WILL)";
                break;
            case PR:
                res = "(PR)";
                break;
        }
        return res;
    }

    public int getAbility() {
        return listWrapper.getAttr(governingAtt);
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public int getRank() {
        return rank;
    }

    public void incrementRank() {
        if (rank < MAX_RANK) rank++;
    }

    public void decrementRank() {
        if (rank > 0) rank--;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getSetback() {
        return setback;
    }

    public void setSetback(int setback) {
        this.setback = setback;
    }

    public Species.Characteristic getGoverningAtt() {
        return governingAtt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", governingAtt=" + governingAtt +
                ", ability=" + ability +
                ", rank=" + rank +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}




