#include "Weather.h"

Weather::Weather(int weather)
:weather(weather){}


double Weather::weatherInfluence(int characterstic, int nowWeather) {
	//0抑郁 1焦虑 2坚定 3乐天 4受虐狂 5神经质 6勤劳 7冷血 8迟钝 9敏感
	switch (nowWeather) {
	case 0: {//冷血降低百分之二十
		if (characterstic ==7 ) return 0.8;
		else return 1;
	}
	case 1: {//沮丧降低0.2
		if (characterstic == 0) return 0.8;
		else return 1;
	}
	case 2: {//迟钝和受虐狂不受影响，其余人降低百分之二十
		if (characterstic == 8 || characterstic == 4) return 1;
		else return 0.8;
	}
	case 3: {//敏感的人状态降低0.2
		if (characterstic == 9) return 0.8;
		else return 1;
	}
	case 4: {//焦虑和神经质降低百分之二十
		if (characterstic == 1|| characterstic == 5) return 0.8;
		else return 1;
	}
	case 5: {//坚定和勤劳的人降低百分之二十
		if (characterstic == 2 || characterstic == 6) return 0.8;
		else return 1;
	}
	case 6: {//乐天的人降低百分之二十
		if (characterstic == 3) return 0.8;
		else return 1;
	}
	}
	return false;
}

string Weather::getDescription() {
	if (!(weather >= 0 && weather < 7)) return (string)"wrong weather...please check system.";
	switch (weather) {
	case 0: {//冷血降低百分之二十
		return (string)"It's very cold now...As the name suggests, cold-blooded people receive a 20 percent reduction in status";
	}
	case 1: {//沮丧降低0.2
		return (string)"It's sunny now,Good weather for the race! Depressed players are down 20 percent...";
	}
	case 2: {//迟钝和受虐狂不受影响，其余人降低百分之二十
		return (string)"It's rainy now! Our players are going to put up with the bad weather...Those who were slow and determined were unaffected, while the rest of us experienced a 20 percent drop in status!";
	}
	case 3: {//敏感的人状态降低0.2
		return (string)"It's windy now!But not bad. Sensitive people had a 20 percent drop in status";
	}
	case 4: {//焦虑和神经质降低百分之二十
		return (string)"It's stormy now!Our contestants can't see each other's faces anymore, but it doesn't matter! Anxiety and _neuroticism were reduced by 20 percent";
	}
	case 5: {//坚定和勤劳的人降低百分之二十
		return (string)"It's very hot right now...I hope you're okay.Determined and industrious people are reduced by 20 percent";
	}
	case 6: {//乐天的人降低百分之二十
		return (string)"It's snowing now!Not even the most active person can stop it!Happy people saw a 20 percent drop in status";
	}
	}
	return (string)"Sorry,something wrong.";
}