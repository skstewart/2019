//Name: Shayna Stewart
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
		 
protected: Human(string name, int age, char sex);
		   //protected destructr
public: void setName(string n);
		string getName();
		void setAge(int a);
		int getAge();
		void setSex(char ch);
		char getSex();
};

class Child : public Human {
private: string momName;
		 string dadName;
		 int allowance;
		 Child();
public: Child(string name, int age, char sex, string momName, string dadName);
		int getAllowance();
		void printParent();

};

class Parent : public Human {

private: vector<string> children;
		 Parent();
public: Parent(string name, int age, char sex);
		void printChild();
		void setChild(Child ch);
		void setChildAllowance(int allow, Child ch);


		void setChild(Child ch){
		children.push_back(ch);
}

		void printChild() {


		}

		void setChildAllowance(int allow, Child ch) {
			ch.allowance = allow;
		
		}
		
};

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


}
