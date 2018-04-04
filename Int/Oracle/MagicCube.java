#include <iostream>
using namespace std;

class MagicCube
{
private:
    enum{LEN = 3,SPACE = 6};
    enum color{red,yellow,black,blue,green,purple};
    enum color Spacexy[SPACE][LEN][LEN];
public:
    MagicCube();
    ~MagicCube(){};
    void LeftRotate(int x,int y);
    ////void RightRotate(int x,int y);
    void UpRotate(int x,int y);
    //void DownRotate(int x,int y);
    void PrintCube();
};
void MagicCube::UpRotate(int x,int y)
{
    color tmp[3];

    for(int i = 0;i<3;i++)
        tmp[i] = Spacexy[0][i][y];

    for(int i = 0;i<3;i++)
        Spacexy[0][i][y] = Spacexy[5][i][y];
    for(int i = 0;i<3;i++)
        Spacexy[5][i][y] = Spacexy[2][i][2-y];
    for(int i = 0;i<3;i++)
        Spacexy[2][i][2-y] = Spacexy[4][i][y];
    for(int i = 0;i<3;i++)
        Spacexy[4][i][y] = tmp[i];
}
void MagicCube::PrintCube()
{
    for(int s = 0;s<6;s++)
    {
        switch(s)
        {
        case 0:cout << " 正面:" << endl; break;
        case 1:cout << " 右面:" << endl; break;
        case 2:cout << " 后面:" << endl; break;
        case 3:cout << " 左面:" << endl; break;
        case 4:cout << " 上面:" << endl; break;
        case 5:cout << " 下面:" << endl; break;
        default:break ;
        }
        for(int i = 0;i<3;i++)
        {
            for(int j = 0;j<3;j++)
            {
                cout << Spacexy[s][i][j] << " ";
            }
            cout << endl;
        }
        cout << endl;
    }

    cout << "---------------------------------------" << endl;
}
MagicCube::MagicCube()
{
    for(int i = 0;i<6;i++)//每一面一个颜色
        for(int j = 0;j<3;j++)
            for(int k = 0;k<3;k++)
            {
                Spacexy[i][j][k] = (color)i;
            }
}
void MagicCube::LeftRotate(int x,int y)
{
    color tmp[3];

    for(int i = 0;i<3;i++)
        tmp[i] = Spacexy[0][x][i];

    for(int i = 0;i<3;i++)
        Spacexy[0][x][i] = Spacexy[1][x][i];
    for(int i = 0;i<3;i++)
        Spacexy[1][x][i] = Spacexy[2][x][i];
    for(int i = 0;i<3;i++)
        Spacexy[2][x][i] = Spacexy[3][x][i];
    for(int i = 0;i<3;i++)
        Spacexy[3][x][i] = tmp[i];

}
int main(void )
{
    MagicCube a;
    a.PrintCube();
    a.UpRotate(0,0);
    a.PrintCube();
    return 0;
}
