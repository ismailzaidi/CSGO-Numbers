package com.csgo.iz.modal;

import java.util.ArrayList;
import java.util.Hashtable;

import com.csgo.iz.R;
import com.csgo.iz.modal.bean.GeneralStats;
import com.csgo.iz.modal.bean.LastMatchStats;
import com.csgo.iz.modal.bean.OtherStats;
import com.csgo.iz.modal.bean.Profile;
import com.csgo.iz.modal.bean.Summary;
import com.csgo.iz.modal.bean.Weapon;
import com.csgo.iz.modal.http.ModelData;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

public class SummaryModel extends ModelAbstract {
    private ArrayList<Summary> listOfSummary;
    private Hashtable<String, Integer> tableStats;
    private Context context;
    private Profile profile;

    public SummaryModel(String userID, Context context) {
        super(userID, context);
        this.context = context;
    }

    public SummaryModel(Context context, Hashtable<String, Integer> tableStats, Profile profile) {
        super(context);
        this.context = context;
        this.tableStats = tableStats;
        this.profile = profile;
    }

    public void generateSummary(ModelData data) {
//		listOfSummary = new ArrayList<Summary>();
//		listOfSummary.add(data.());
    }

    public ArrayList<Summary> getListOfSummary() {
        if (tableStats != null) {
            listOfSummary = new ArrayList<Summary>();
            listOfSummary.add(new Summary(profile, getSummaryGeneral(), getSummaryLastMatch(), getSummaryOtherStats()));
            return listOfSummary;
        }
        return null;
    }

    private GeneralStats getSummaryGeneral() {
        if (tableStats != null) {
            int totalKills = getHashValue("total_kills");
            int total_deaths = getHashValue("total_deaths");
            int total_shots_hit = getHashValue("total_shots_hit");
            int total_shots_fired = getHashValue("total_shots_fired");
            int total_time_played = getHashValue("total_time_played");
            int total_kills_headshot = getHashValue("total_kills_headshot");
            int total_mvps = getHashValue("total_mvps");
            int total_matches_won = getHashValue("total_matches_won");
            int total_matches_played = getHashValue("total_matches_played");

            double kda = (double) totalKills / total_deaths;
            double accuracy = (double) 100 * total_shots_hit / total_shots_fired;
            double matchWinRate = (double) 100 * total_matches_won / total_matches_played;
            double time = (double) total_time_played * 1000 / (3600 * 1000);
            String timeResult = ((int) time) + "h";
            return new GeneralStats(totalKills, kda, accuracy, total_mvps, timeResult, total_kills_headshot, matchWinRate);
        }
        return null;
    }

    private LastMatchStats getSummaryLastMatch() {
        if (tableStats != null) {
            String[] weapon_id = context.getResources().getStringArray(R.array.weapon_id);
            String[] weapon_names = context.getResources().getStringArray(R.array.weapon_names);
            TypedArray weapon_image_arr = context.getResources().obtainTypedArray(R.array.weapon_images);

            int matchResult = getHashValue("last_match_wins"); // 4
            int rounds_won = getHashValue("last_match_t_wins"); //
            int rounds_Lost = getHashValue("last_match_ct_wins");
            int last_match_kills = getHashValue("last_match_kills");
            int last_match_deaths = getHashValue("last_match_deaths");
            int last_match_damage = getHashValue("last_match_damage");
            int last_match_money_spent = getHashValue("last_match_money_spent");
            int last_match_dominations = getHashValue("last_match_dominations");
            int last_match_mvps = getHashValue("last_match_mvps");
            int last_match_revenges = getHashValue("last_match_revenges");
            int weaponID = getHashValue("last_match_favweapon_id");
            int weaponShots = getHashValue("last_match_favweapon_shots");
            int weaponHits = getHashValue("last_match_favweapon_hits");
            int weaponKills = getHashValue("last_match_favweapon_kills");
            String roundsWinRatio = "";
            double threhold = (double) 100 * matchResult / (rounds_won + rounds_Lost);
            Log.v("TEST Summary", "Threshold: " + threhold);
            int total_rounds = rounds_won + rounds_Lost;
            if (threhold < 50) {// Lose
                roundsWinRatio = "" + matchResult + " - " + total_rounds + ",LOSE";
            } else if (threhold == 50) {// Draw
                roundsWinRatio = "" + matchResult + " - " + total_rounds + ",DRAW";
            } else {// Win
                roundsWinRatio = "" + matchResult + " - " + total_rounds + ",WIN";
            }
            double kda = (double) last_match_kills / last_match_deaths;

            Weapon weapon = null;
            int position = getWeaponIDPosition(weapon_id, weaponID);
            int weaponImage = weapon_image_arr.getResourceId(position, -1);
            String weaponName = weapon_names[position].split(",")[0];
            String weaponType = weapon_names[position].split(",")[1];
            int weaponAccuracy = (weaponHits == 0 && weaponShots == 0) ? 0 : 100 * weaponHits / weaponShots;
            int missedShots = weaponShots - weaponHits;
            weapon = new Weapon(weaponImage, weaponType, weaponName, weaponKills, weaponHits, weaponAccuracy, missedShots,
                    weaponShots);
            return new LastMatchStats(roundsWinRatio, String.valueOf(matchResult), weapon, kda, last_match_kills,
                    last_match_deaths, last_match_damage, last_match_money_spent, last_match_mvps, last_match_dominations,
                    last_match_revenges);
        }
        return null;
    }

    public Summary getSummary() {
        if (tableStats != null) {
            return new Summary(getSummaryGeneral(), getSummaryLastMatch(), getSummaryOtherStats());
        }
        return null;
    }

    private int getWeaponIDPosition(String[] weapon_id, int json_weapon_id) {
        for (int i = 0; i < weapon_id.length; i++) {
            if (Integer.parseInt(weapon_id[i]) == json_weapon_id) {
                return i;
            }
        }
        return 0;
    }

    private int getHashValue(String key) {
        Object item = tableStats.get(key);
        if (item != null) {
            return (int) item;
        } else {
            return 0;
        }
    }

    private OtherStats getSummaryOtherStats() {
        if (tableStats != null) {
            int totalKills = getHashValue("total_kills");
            int total_deaths = getHashValue("total_deaths");
            int total_kills_headshot = getHashValue("total_kills_headshot");
            int total_money_earned = getHashValue("total_money_earned");
            int total_damage_done = getHashValue("total_damage_done");
            int total_contribution_score = getHashValue("total_contribution_score");
            int total_planted_bombs = getHashValue("total_planted_bombs");
            int total_defused_bombs = getHashValue("total_defused_bombs");
            int total_rescued_hostages = getHashValue("total_rescued_hostages");
            int total_matches_played = getHashValue("total_matches_played");
            int total_matches_won = getHashValue("total_matches_won");
            int total_rounds_played = getHashValue("total_rounds_played");
            int total_rounds_won = getHashValue("total_wins");
            int total_mvps = getHashValue("total_mvps");
            int total_weapons_donated = getHashValue("total_weapons_donated");
            int total_kills_enemy_weapon = getHashValue("total_kills_enemy_weapon");
            int total_kills_enemy_blinded = getHashValue("total_kills_enemy_blinded");
            int total_kills_knife_fight = getHashValue("total_kills_knife_fight");
            int total_kills_against_zoomed_sniper = getHashValue("total_kills_against_zoomed_sniper");
            int total_shots_fired = getHashValue("total_shots_fired");
            int total_shots_hit = getHashValue("total_shots_hit");
            int total_dominations = getHashValue("total_dominations");
            int total_domination_overkills = getHashValue("total_domination_overkills");
            int total_revenges = getHashValue("total_revenges");
            int total_wins_pistolround = getHashValue("total_wins_pistolround");
            int total_kills_taser = getHashValue("total_kills_taser");
            int total_broken_windows = getHashValue("total_broken_windows");
            return new OtherStats(totalKills, total_deaths, total_kills_headshot, total_money_earned, total_damage_done,
                    total_contribution_score, total_planted_bombs, total_defused_bombs, total_rescued_hostages,
                    total_matches_played, total_matches_won, total_rounds_played, total_rounds_won, total_mvps,
                    total_weapons_donated, total_kills_enemy_weapon, total_kills_enemy_blinded, total_kills_knife_fight,
                    total_kills_against_zoomed_sniper, total_shots_fired, total_shots_hit, total_dominations,
                    total_domination_overkills, total_revenges, total_wins_pistolround, total_kills_taser,
                    total_broken_windows);
        }
        return null;
    }
//	public ArrayList<Summary> getSummaries() {
//		return compareData.getUserSummaries();
//	}
}
