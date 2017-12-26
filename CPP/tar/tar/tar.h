#pragma once

#include <string>
#include <vector>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <sys/types.h>
#include <sys/stat.h>
#include <sstream>
#include <fstream>

using namespace std;

// tar ファイル定義
#define BLOCK_SIZE 512

#define	NAME_SIZE 		100
#define	MODE_SIZE 		8
#define	UID_SIZE 		8
#define	GID_SIZE 		8
#define	SIZE_SIZE 		12
#define	MTIME_SIZE 		12
#define	CHKSUM_SIZE 	8
#define	TYPEFLAG_SIZE 	1
#define	LINKNAME_SIZE 	100
#define	MAGIC_SIZE 		6
#define	VERSION_SIZE 	2
#define	UNAME_SIZE 		32
#define	GNAME_SIZE 		32
#define	DEVMAJOR_SIZE 	8
#define	DEVMINOR_SIZE 	8
#define	PREFIX_SIZE 	155

#define NAME_OFFSET		0
#define	MODE_OFFSET 	(NAME_OFFSET + NAME_SIZE)
#define	UID_OFFSET 		(MODE_OFFSET + MODE_SIZE)
#define	GID_OFFSET 		(UID_OFFSET + UID_SIZE)
#define	SIZE_OFFSET 	(GID_OFFSET + GID_SIZE)
#define	MTIME_OFFSET 	(SIZE_OFFSET + SIZE_SIZE)
#define	CHKSUM_OFFSET 	(MTIME_OFFSET + MTIME_SIZE)
#define	TYPEFLAG_OFFSET (CHKSUM_OFFSET + CHKSUM_SIZE)
#define	LINKNAME_OFFSET (TYPEFLAG_OFFSET + TYPEFLAG_SIZE)
#define	MAGIC_OFFSET 	(LINKNAME_OFFSET + LINKNAME_SIZE)
#define	VERSION_OFFSET 	(MAGIC_OFFSET + MAGIC_SIZE)
#define	UNAME_OFFSET 	(VERSION_OFFSET + VERSION_SIZE)
#define	GNAME_OFFSET 	(UNAME_OFFSET + UNAME_SIZE)
#define	DEVMAJOR_OFFSET (GNAME_OFFSET + GNAME_SIZE)
#define	DEVMINOR_OFFSET (DEVMAJOR_OFFSET + DEVMAJOR_SIZE)
#define	PREFIX_OFFSET 	(DEVMINOR_OFFSET + DEVMINOR_SIZE)


namespace tar{


	class tarHeader{
	public :
		// tarファイル内のパスを保存
		string name;
		//9 ビットのファイルパーミッションと 3 ビットの setUID、setGID、TSVTX モード
		char mode[MODE_SIZE];
		// ユーザID
		long long	uid;
		// グループＩＤ
		long long	gid;
		// ファイルサイズのバイト数
		// LINKTYPE か SYMTYPEの場合は0
		// ヘッダーに続くブロック数は (size+511)/512
		long long size;
		long long mtime;
		char chksum[CHKSUM_SIZE];
		char typeflag;
		string linkname;
		string magic;
		char version[VERSION_SIZE];
		string uname;
		string gname;
		string devmajor;
		string devminor;
		string prefix;

		char headerData[BLOCK_SIZE];
		struct _stat stat;

		void createHeader(string fileName){
			
			
			int result = _stat(fileName.c_str(), &stat);

			if  (result == -1){
				cout << "ファイル読み込みエラー[" << fileName.c_str() << "]" << endl;
				return ;
			}


			//ファイル名
			this->name = fileName;
			// パーミッション とりあえず0で埋める。
			memset(this->mode,0x00 , sizeof(this->mode));
			// Uid:とりあえず0で埋める
			//memset(&header.uid,0x00 , sizeof(header.uid));
			//stat.
			this->uid = stat.st_uid;
			// gid:とりあえず0で埋める
			//memset(&header.gid,0x00 , sizeof(header.gid));
			this->gid = stat.st_gid;
			// Size
			/*
			std::ostringstream ss;
			ss << std::setw(12) << std::setfill('0') << result ;
			header.size = string(ss.str());
			*/
			this->size = stat.st_size;
			// 更新日付
			this->mtime = stat.st_mtime;
			
			//チェックさむは空にしておこ
			memset(this->chksum,0x00,sizeof(this->chksum));

			// 0:ファイル、5:ディレクトリ
			if ((stat.st_mode & _S_IFDIR) != 0 ){
				//ディレクトリ
				this->typeflag = 0x05;
			} else {
				//ディレクトリ以外はファイル
				this->typeflag = 0x00;
			}

			this->linkname.clear();
			this->magic.clear();
			memset(this->version,0x00 , sizeof(this->version));
			this->uname.clear();
			this->gname.clear();
			this->devmajor.clear();
			this->devminor.clear();
			this->prefix.clear();
		}
		void createData(){
			memset(headerData,0x00,sizeof(headerData));
			sprintf(&headerData[NAME_OFFSET],"%-100s",this->name.c_str());
			memcpy(&headerData[MODE_OFFSET],this->mode,MODE_SIZE);
			sprintf(&headerData[UID_OFFSET],"%8d",this->uid);
			sprintf(&headerData[UID_OFFSET],"%8d",this->gid);
			sprintf(&headerData[SIZE_OFFSET],"%012d" , this->size );
			sprintf(&headerData[MTIME_OFFSET],"%012d" , this->mtime );
			memcpy(&headerData[CHKSUM_OFFSET],this->chksum,sizeof(this->chksum));
			memcpy(&headerData[TYPEFLAG_OFFSET],&this->typeflag,sizeof(this->typeflag));
			sprintf(&headerData[LINKNAME_OFFSET] , "%-100s",this->linkname);
			sprintf(&headerData[MAGIC_OFFSET],"%-6s",this->magic);
			sprintf(&headerData[VERSION_OFFSET],"%-2s",this->version);
			sprintf(&headerData[UNAME_OFFSET],"%-32s",this->uname);
			sprintf(&headerData[GNAME_OFFSET],"%-32s",this->gname);
			sprintf(&headerData[DEVMAJOR_OFFSET],"%-8s",this->devmajor);
			sprintf(&headerData[DEVMINOR_OFFSET],"%-8s",this->devminor);
			sprintf(&headerData[PREFIX_OFFSET],"%-155s",this->prefix);
		}

	};
	class tarFileData{

	public:
		tarFileData(){
			data = NULL;
		}
		~tarFileData(){
			if (data != NULL){
				free(this->data);
			}

		}
		char* data ;
		struct _stat stat;
		int block;
		long dataSize;
		void compression(string fileName){

			//作成するブロック
			block = (stat.st_size + 511) / BLOCK_SIZE;
			dataSize = block * BLOCK_SIZE;
			data = new char[dataSize];
			memset(data,0x00 , dataSize);

			// ファイルオープン
			ifstream ifs;
			//ofs.open("out.dat" , std::fstream::out | std::fstream::binary | std::fstream::write);
			ifs.open(fileName.c_str() , ios::in| ios::binary);
			//ofs.write(stat.st_.headerData,BLOCK_SIZE);
			ifs.read(data , dataSize);
			ifs.close();


		}
	};

	class tarFile{

	public :
		string fileNmae;
		tarHeader header;
		tarFileData data;
		tarFile(string fileName){
			this->fileNmae = fileName;

		}
		//void compression(char* data){
		void compression(){
			// ファイル情報の取得をしてheaderを作成する。
			//tarHeader header;
			header.createHeader(this->fileNmae);
			header.createData();
			cout << header.headerData << endl;
			
			data.stat = header.stat;
			data.compression(this->fileNmae);

#if 0
			// ===== Debug
			ofstream ofs ;
			//ofs.open("out.dat" , std::fstream::out | std::fstream::binary | std::fstream::write);
			ofs.open("out_head.dat" , ios::out| ios::binary|ios::trunc);
			ofs.write(header.headerData,BLOCK_SIZE);
			ofs.close();
			ofs.open("out_data.dat", ios::out|ios::binary|ios::trunc);
			ofs.write(data.data,data.dataSize);
			ofs.close();
			// ===== Debug
#endif
		}
	};


	class tarCompression {

	private:
		char* tarData;
	public : //公開変数




	public :
		
		tarCompression(){
			files.clear();
			tarData=NULL;
		}
		~tarCompression(){
			files.clear();
			if(tarData != NULL){
				free(tarData);
			}
		}

		void addFile(string file){
			files.push_back(tarFile(file));
		}

		void compression(char** data , long *size){

			stringstream ss ;

			int block = 0;
			//for (std::vector<tarFile>::const_iterator itr = this->files.cbegin(); itr != files.end() ; itr++){
			for (std::vector<tarFile>::iterator itr = this->files.begin(); itr != files.end() ; itr++){
			//std::vector<tarFile>::const_iterator itr = this->files.cbegin();
			//while(itr != this->files.end()){
				tarFile file = *itr;
				file.compression();
				ss.write(file.header.headerData,BLOCK_SIZE);
				ss.write(file.data.data,file.data.dataSize);

				//*itr = file;
				block ++; //header分足す
				block += file.data.block; //データ分足す
			}
			//終端(バイナリーゼロを入れる
			char binaryZero[BLOCK_SIZE *2] ;
			memset(binaryZero,0x00,sizeof(binaryZero));
			ss.write(binaryZero,sizeof(binaryZero));


			// 全部入りのエリアを確保する。
			*size = (block * BLOCK_SIZE) + (2*BLOCK_SIZE);
			//tarData = new char[*size];
			*data = new char[*size];
			memset(*data,0x00 , *size);
			//cout << "作成データサイズ:" << *size << endl;

			cout << "サイズ：" << *size << endl;
			ss.read(*data,sizeof(*size));


			//終端(バイナリーゼロを入れる。
			// →最初に0埋めしているからＯＫか
			

		}
		
		void compression2(char** data , long *size){
			struct TEMP_DATA{
				long size;
				char* data;
			} tempData;
			vector<TEMP_DATA> tempVector;
			int tempSize = 0;

			int block = 0;
			//for (std::vector<tarFile>::const_iterator itr = this->files.cbegin(); itr != files.end() ; itr++){
			for (std::vector<tarFile>::iterator itr = this->files.begin(); itr != files.end() ; itr++){
			//std::vector<tarFile>::const_iterator itr = this->files.cbegin();
			//while(itr != this->files.end()){
				tarFile file = *itr;
				file.compression();
				*itr = file;
				block ++; //header分足す
				block += file.data.block; //データ分足す
				TEMP_DATA t;
				t.data = new char[BLOCK_SIZE * (1+ file.data.block )];
				memset(t.data,0x00,(BLOCK_SIZE * (1+ file.data.block )));
				memcpy(t.data,file.header.headerData,BLOCK_SIZE);
				memcpy(&t.data[BLOCK_SIZE],file.data.data,file.data.dataSize);
				t.size = (BLOCK_SIZE * (1+ file.data.block ));
				tempVector.push_back(t);
			}
			// 全部入りのエリアを確保する。
			*size = (block * BLOCK_SIZE) + (2*BLOCK_SIZE);
			//tarData = new char[*size];
			*data = new char[*size];
			memset(*data,0x00 , *size);
			cout << "作成データサイズ:" << *size << endl;
			//各データを埋め込んでいく
			/*
			long offset = 0;
			for (std::vector<tarFile>::const_iterator itr = this->files.cbegin(); itr != files.end() ; itr++){
				tarFile file = *itr;
				//headerコピー
				memcpy(&data[offset],file.header.headerData,BLOCK_SIZE);
				offset += BLOCK_SIZE;
				memcpy(&data[offset],file.data.data,file.data.dataSize);
				offset += file.data.dataSize;
				

			}*/
			long offset = 0;
			for (std::vector<TEMP_DATA>::iterator itr = tempVector.begin(); itr != tempVector.end() ; itr++){
				TEMP_DATA t = *itr;
				memcpy(&*data[offset],t.data,t.size);
				offset += t.size;
			}

			//終端(バイナリーゼロを入れる。
			// →最初に0埋めしているからＯＫか
			

		}

	private :
		vector<tarFile> files;


	};

}