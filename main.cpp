#include <iostream>

using namespace std;

int main()
{
    Serial* SP = new Serial("\\\\.\\COM3");    // adjust as needed

	if (SP->IsConnected())
		printf("We're connected");
    return 0;

	

}
