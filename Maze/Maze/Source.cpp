#include <iostream>
using namespace std;
#include <vector>

int main(){
	char my_array[8][16];
	for (int i = 0; i < 8; i++){
		for (int j = 0; j < 16; j++){
			my_array[i][j] = ' ';
		}
	}
	for (int i = 1; i < 15; i++){
		my_array[0][i] = '_';
		my_array[7][i] = '-';
	}
	for (int i = 1; i < 7; i++){
		my_array[i][0] = '|';
		my_array[i][15] = '|';
	}
	for (int i = 0; i < 8; i++){
		for (int j = 0; j < 16; j++){
			cout << my_array[i][j];
		}
		cout << endl;
	}

}