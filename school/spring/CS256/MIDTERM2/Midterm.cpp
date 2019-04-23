: //Name: Shayna Stewart
//CS256 Midterm
//Due: 4/18/19

#include <vector>
#include <iostream>

using namespace std;

class Human {
private: string name;
		 int age;
		 char sex;
		 Human();
		 
protected: Human(string n, int a, char s) {
	name = n;
	age = a;
	sex = s;
}
		   //protected destructr
public: 
	friend class Parent;
	friend class Child;

		void setName(string n) {
			name = n;
		}
		string getName() {
			return name;
		}
		void setAge(int a) {
			age = a;
		}
		int getAge() {
			return age;
		}
		void setSex(char ch) {
			sex = ch;
		}
		char getSex() {
			return sex;
		}

		
		
};

class Child : public Human {
private: string momName;
		 string dadName;
		 int allowance;
		 Child();
public: Child(string n, int a, char s, string mN, string dN) {
	allowance = 0;
	Human::name = n;
	age = a;
	sex = a;
	momName = mN;
	dadName = dN;
}
		int getAllowance() {
			return allowance;
		}
		friend class Parent;
		void printParent() {
			cout << &momName << endl;
			cout << &dadName;
		}

};

class Parent : public Human {

private: vector<string> children;
		 Parent();
public: Parent(string n, int a, char s) {
	name = n;
	age = a;
	sex = s;
}
		void setChild(Child ch){
	//	children.push_back(ch.getName);
}

		void printChild() {
			for (auto i = children.begin(); i != children.end(); ++i) {
			//cout << "" << *i << " ";
		}

		}

		void setChildAllowance(int allow, Child ch) {
			ch.allowance = allow;
		
		}
		
};

void printInfo(Human hu) {
	cout << "Name: " << &hu.getName() << endl;
	cout << "Age: " << hu.getAge()<< endl;
	cout << "Sex: " << hu.getSex() << endl;
}

int main()
{

	Parent homer("Homer",36,'M');
	Parent marge("Marge",34,'F');
	Child maggie("Maggie",3,'F',"Marge","Homer");
	Child lisa("Lisa",12,'F',"Marge","Homer");
	Child bart("Bart",10,'M',"Marge","Homer");


	homer.setChild(bart);
	homer.setChild(maggie);
	homer.setChild(lisa);

	marge.setChild(lisa);
	marge.setChild(maggie);
	marge.setChild(bart);

	cout << bart.getAllowance();
	homer.setChildAllowance(5,bart);
	cout << bart.getAllowance();

	
	bart.printParent();


	printInfo(marge);
	printInfo(lisa);

}
