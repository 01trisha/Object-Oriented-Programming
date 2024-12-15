#pragma once
#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

class View
{
public:
	View(int argc, char** argv);
	~View() = default;

	bool start();
	bool help() const;
	string getOutputFile();
	string getInputFile();
	vector<string> getMixFiles();
	string getConfig();

private:
	bool _help;
	string _outputFile;
	string _inputFile;
	vector<string> _mixFiles;
	string _config;
};
