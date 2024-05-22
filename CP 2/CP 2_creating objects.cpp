#include <iostream>
#include <string.h>
#pragma warning(disable : 4996)
using namespace std;
class Alphabet
{
private:
 char* letters;
 char* symbols;
 int a;
 int s;
public:
 Alphabet()
 {
 letters = new char[30];
 strcpy(letters, "abcdml");
 symbols = new char[30];
 strcpy(symbols, "/,.']");
 con();
 }
 Alphabet(char* ld, char* sd)
 {
 letters = new char[30];
 strcpy(letters, ld);
 symbols = new char[30];
 strcpy(symbols, sd);
 con();
 }
 Alphabet(const Alphabet& another)
 {
 letters = new char[30];
 strcpy(letters, another.letters);
 symbols = new char[30];
 strcpy(symbols, another.symbols);
 con();
 }
 ~Alphabet()
 {
 cout << "delated additional\n";
 }
 void con()
 {
 a = strlen(letters);
 s = strlen(symbols);
 }
 char* get_let() { return letters; }
 char* get_sym() { return symbols; }
 void set_let(char* ld) { strcpy(letters, ld); con(); }
 void set_sym(char* sd) { strcpy(symbols, sd); con(); }
 void print()
 {
 cout << "Alphabet with " << a << " letters: " << letters << " and with " << s << "
symbols: " << symbols << "\n";
 }
 void prints()
 {
 cout << "With " << letters << " and " << symbols << "\n";
 }
};
class Phrase
{
private:
 char* phrase;
 Alphabet A;
public:
 Phrase() : A()
 {
 phrase = new char[30];
 strcpy(phrase,"alabama.");
 }
 Phrase(char* pd, char* ld, char* sd) : A(ld, sd)
 {
 phrase = new char[30];
 strcpy(phrase,pd);
 }
 Phrase(const Phrase& other) : A(other.A)
 {
 phrase = new char[30];
 strcpy(phrase, other.phrase);
 }
 ~Phrase()
 {
 cout << "Cleared memory\n";
 }
 char* get_phrase() { return phrase; }
 char* get_letd() { return A.get_let(); }
 char* get_symd() { return A.get_sym(); }
 void set_phrase(char* pd) { strcpy(phrase, pd); }
 void set_let(char* ld) { A.set_let(ld); }
 void set_sym(char* sd) { A.set_sym(sd); }
 void print()
 {
 cout << "Phrase: " << phrase << " using alphabet " << "\n";
 A.print();
 }
 void prints()
 {
 cout << "Phrase: " << phrase << "\n";
 A.prints();
 }
};
int main()
{
 char ld[30];
 char sd[30];
 char pd[30];
 cout << "Enter letters and symbols for phrase\n";
 cin.getline(ld, 30);
 cin.getline(sd, 30);
 cout << "Enter phrase from the letters and symbols\n";
 cin.getline(pd, 30);
 Phrase P1(pd, ld, sd);
 Phrase P2;
 Phrase P3(P1);
 cout << "\n";
 cout << "Manually entered Phrase\n";
 P1.print();
 P1.prints();
 cout << "\n";
 cout << "Obychnaya phrase\n";
 P2.print();
 P2.prints();
 cout << "\n";
 cout << "Copy of manual\n";
 P3.print();
 P3.prints();
 cout << "\n";
}
