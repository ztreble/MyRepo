#include"Person.h"
Person::Person() {
	this->Gender(true);
	this->Identity("");
	this->Name("");
}
Person::Person(string name, bool gender, string identity):name(name), gender(gender), identity(identity)
{

}

Person::~Person()
{
}


void Person::printInfo(int Num, ...) {
	va_list ap;
	va_start(ap, Num);
	for (int i = 1; i < Num/2; i++)
	{
		cout << va_arg(ap, string) << " : ";
		cout<< va_arg(ap, string)<<endl;
	}
}
string Person::getCharacterstic(int n) {
	//0抑郁 1焦虑 2坚定 3乐天 4受虐狂 5神经质 6勤劳 7冷血 8迟钝 9敏感
	switch (n) {
	case 0: {
		return (string)"Depression";
	}
	case 1: {
		return (string)"Depression";
	}
	case 2: {
		return (string)"anxiety";
	}
	case 3: {
		return (string)"firmness";
	}
	case 4: {
		return (string)"happiness";
	}
	case 5: {
		return (string)"masochism";
	}
	case 6: {
		return (string)"_neuroticism";
	}
	case 7: {
		return (string)"industriousness";
	}
	case 8: {
		return (string)"coldness";
	}
	case 9: {
		return (string)"slowness";
	}
	case 10: {
		return (string)"sensitivity";
	}
			 return (string)"something wrong";
	}
	return (string)"Sorry,something wrong.";
}