// TheNovaOctathlon.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include <iostream>
#include "Util.h"
#include "Game.h"
#include<vector>
using namespace std;
enum weather { run, ruin, windy }a,b;
int main(int argc, char *argv[])
{
	cout << "------------------------------------------------------------------------------------------------------------------------" << endl;
	cout << endl;
	cout << "Welcome to the octahedron!"<<endl<<endl<<"Checking the command-line arguments you entered..." << endl;
	int n, m;
	n = argv[1][0] - '0';
	m = argv[2][0] - '0';

	n = (n > 0 && n < 9) ? n : 0;
	m = (m > 0 && m < 9) ? m : 0;
	cout << endl;
	if (n == 0 || m == 0) {
		cout << "Sorry, wrong parameter!" << endl;
		cout << "You must enter two integers between one and eight" << endl;
		return 0;
	}
	cout << "Well, your parameters are correct and you are going to enjoy a great game." << endl;



	cout << endl << "Now, at your command, " << n << " teams have been generated." << endl;
	cout << endl << "And, " << m << " match will be generated later." << endl<<endl;

	std::vector<Team*> team = Util::getRandomTeam(n);
	Game game;
	game.beginGame(team, n, m);

	return 0;
}
