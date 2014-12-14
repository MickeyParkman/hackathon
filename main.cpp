#include <iostream>

int main()
{
    Serial* SP = new Serial("\\\\.\\COM3");    // adjust as needed

	if (SP->IsConnected())
		printf("We're connected");
	else
		std::cout << "You are a fucking retard and this port does not exist. IE: FUCK YOU GORDAN" << std::endl;
    return 0;

	

}
