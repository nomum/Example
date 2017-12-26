// tar.cpp : コンソール アプリケーションのエントリ ポイントを定義します。
//

#include "stdafx.h"
#include <iostream>


#include "tar.h"

using namespace std;

int _tmain(int argc, _TCHAR* argv[])
{

	tar::tarCompression tc ;
	tc.addFile("data\\data1.txt");
	tc.addFile("data\\data2.txt");
	char* data=NULL;
	long size;
	//tc.compression2(&data,&size);
	tc.compression2(&data,&size);

	free(data);

	/*
	ofstream ofs ;
	ofs.open("out.tar",ios::out|ios::binary|ios::trunc);
	ofs.write(data,size);
	ofs.close();
	*/

	//delete &data;


	return 0;
}

