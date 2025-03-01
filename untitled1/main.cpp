#include <iostream>
#include <string>
#include <ctime>
#include <span>
using namespace std;
string aWord = "xxxxx";
char output[5] = {'-','-','-','-','-'};
bool hasWon = false;
int count = 0;
void setAnswer() {
    string wordBank[] = {"adieu","forge","mouth","facet","organ","flute"};
    srand(time(0));
    int ran = rand() % size(wordBank);
    aWord = wordBank[ran];
}



void printCArr(char arr[]) {
    for (int h =0; h < aWord.length(); h++) {
        cout << " " << arr[h];
    }
}

void printArr(int arr[]) {
    for (int h =0; h < aWord.length(); h++) {
        cout << " " << arr[h];
    }
}
bool isInt(string gue) {
    bool isIt = false;
    for (int k = 0; k < gue.length(); k++) {
        if (isdigit(gue[k])) {
            isIt = true;
        }
    }
    return isIt;
}

void convertAndPrint(char given[], string orgWord) {
    for (int j = 0; j < aWord.length(); j++) {
        if (given[j] == '?') {
            given[j] = orgWord[j];
            cout << given[j];
        } else if (given[j] == toupper(aWord[j])) {
            given[j] = tolower(given[j]);
            //green highlight
            std::cout << "\033[42;37m" << given[j] << "\033[0m";
        } else if (given[j] != toupper(given[j])) {
            //make an isYellow method
            std::cout << "\033[43;37m" << given[j] << "\033[0m";
        }
    }
    if (given == aWord) {
        hasWon = true;
    }
    cout << endl;
}
bool isYellow(int j, string wor) {

        for (int k = 0; k < aWord.length(); k++) {
            if (wor[j] == aWord[k]) {
                return true;
            }
        }
        return false;

}
void index(string word) {
    //if letter in exact position and correct
    for (int g = 0; g < word.length(); g++) {
            if (word[g] == aWord[g]) {
                //mark the letter that is found in the correct position for green with uppercase
                output[g] = toupper(word[g]);
            } else if (isYellow(g, word))  {
                //mark the letter that is found for yellow
                output[g] = word[g];
            } else {
            output[g] = '?';
            }
    }

    cout << endl;
    // convertAndPrint(output, "aixxu");
}
string guess() {
    string gWord;
    cout << "Please enter your guess below" << endl;

    cin >> gWord;
    if (gWord == "@") {
        return gWord;
    }

    if (gWord.length() > aWord.length())
    {
        cout << "Please enter a guess with less than the length of " << aWord.length() << endl;
        gWord = "2";
        return gWord;
    } else if (isInt(gWord)) {
        cout << "Please enter a guess that is not a number" << endl;
        gWord = "2";
        return gWord;
    }
    else if (gWord.length() != aWord.length()) {
        cout << "Please enter a guess that is ";
        cout << "exactly the length of the answer word, which is " << aWord.length() << endl;
        gWord = "2";
        return gWord;
    }

    if (gWord != "2") {
        return gWord;
    }

}






    // TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
    // click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
    int main() {
        // TIP Press <shortcut actionId="RenameElement"/> when your caret is at the
        // <b>lang</b> variable name to see how CLion can help you rename it.
        setAnswer();
        //makes a random answer from the wordbank
        //cout << aWord << endl;

        string gu;
        cout << "Welcome to Wordle! Press @ if want to quit" << endl;
        while (true) {
            if (gu == "@") {
                return false;
            }
            if (count == 6) {
                cout << "You ran out of guesses. " << endl;
                cout << "The correct answer is " << aWord << ". Try again next time!" << endl;
                return false;
            }
            gu = guess();
            if (gu == "2") {
                gu = guess();
            }
            if (gu != "2" && gu != "@") {
                index(gu);
                //o and l are oddly being yellowed
                //printCArr(output);
                cout << "Your guess, and its evaluation is " << endl;
                convertAndPrint(output,gu);
            }
            if (hasWon) {
                cout << "Your guess is the correct answer word. You win!" << endl;
                gu = "@";
            }
            count++;
        }
        return 0;
        }


        //yellow highlight = "\033[43;37m" << text << "\033[0m"
        //green highlight = "\033[42;37m" << text << "\033[0m"


        //size_t is a big data type so worst case it could go on for a long time of looking for the text
        //index("holli");

        //highlightGreen();



// TIP See CLion help at <a
// href="https://www.jetbrains.com/help/clion/">jetbrains.com/help/clion/</a>.
//  Also, you can try interactive lessons for CLion by selecting
//  'Help | Learn IDE Features' from the main menu.