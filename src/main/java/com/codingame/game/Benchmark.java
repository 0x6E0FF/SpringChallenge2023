package com.codingame.game;
import java.io.*;

import com.codingame.gameengine.runner.MultiplayerGameRunner;
import com.codingame.gameengine.runner.simulate.GameResult;
import com.google.common.io.Files;

public class Benchmark {

    static final long[] seeds = {
        //1 base 
        2408834383668133000L, // small, low resources
        1175991425261531600L, // small, low resources
        -4368848596876891000L,// small, high resources
        3182039710020890000L, // big, low resources
        6393922099284703000L, // big, mid resources
        3522931312682409000L, // big, high resources
        1115133125914303700L, // big, low resources, holes

        // 2 bases 
        4962573035324602000L, //small, low resources
        1527454844828544300L, //small, mid resources
        -4107719636944320000L,//big, low resources
        851444510225116700L   //big, med resources
    };
    public static void main(String[] args){

        File dir = new File("../benchmark");
        int totalNbGames = 0;
        int totalWin = 0;
        MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();

        for (String f : dir.list()) {
            if (f.contains(".cpp")) continue;
            
            System.out.println("\u001B[0m " + f + " : ");
            for (long seed : seeds){    
                gameRunner = new MultiplayerGameRunner();
                gameRunner.setSeed(seed);
                gameRunner.addAgent("../bots/current", "current");
                gameRunner.addAgent("../benchmark/" + f, "x");
                gameRunner.setLeagueLevel(4);
                GameResult res = gameRunner.simulate();
                int myScore = res.scores.get(0);
                int oppScore = res.scores.get(1);

                String title = seed + "";
                String summary = String.format("%1$-30s : %2$3d / %3$3d", title, myScore, oppScore);

                totalNbGames++;
                if (myScore >= oppScore){
                    System.out.println("  \u001B[32m" + summary + " - W");
                    totalWin++;
                }
                else {
                    System.out.println("  \u001B[31m" + summary + " - L");
                }
            }
        }
        float winrate = (float)totalWin * 100 / (float)totalNbGames;
        System.out.println("\u001B[0mWin rate = " + winrate + "%");
    }
}
