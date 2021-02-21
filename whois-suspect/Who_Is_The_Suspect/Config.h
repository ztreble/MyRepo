#pragma once
/*
* @ClassName : Config
* @Auther : treblez
* @E-Mail : treblez@126.com 
* @Date : 2020/11/03
* @Description : This class specifies the parameters of the game, and you can modify them here.Room from left to right from top to bottom shall be specified as 1 -- TOTALROOM
*				 The door needs to be pointed out and identified between the two rooms by 'WALL'
*				 You don't need to write every door twice, but be sure to write all the doors
*				 WALL ends with 0
*/

#define MAP "Loundry","Kitchen","Wordrobe","Lounge","Study","Storage","Bedroom","Gallery","Basement","Entry","Dinning","Bathroom"
#define MAPLENGTH 4
#define MAPHEIGHT 4
#define TOTALROOM 12
#define MAXWARNING 0xfffff
#define WALL (string)"1 2 2 3 5 6 2 6 6 10 7 8 8 12 4 8 9 10 10 11 11 12 0"
#define PEOPLENUM 6
#define DETECTIVEDESCRIPTION (string)"You're not the famous detective who makes all the cases go away...You're just an ordinary forensicist...Of course, it depends on your choice"